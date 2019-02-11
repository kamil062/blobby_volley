CREATE OR REPLACE PROCEDURE addPlayer(pName IN VARCHAR2, pPass IN VARCHAR2, ret OUT VARCHAR2) IS 
  usernames NUMBER;
BEGIN
  SELECT count(*) INTO usernames FROM player WHERE name = pName;
  if usernames = 0 THEN 
    INSERT INTO player(playerId, name, pass, dateRegistered) 
    VALUES (playerIdSeq.nextval, pName, pPass, sysdate);
    ret := 'Rejestracja sie powiodla. Mozesz sie zalogowac';
  ELSE
    ret := 'Taki uzytkownik juz istnieje, podaj inna nazwe';
  END IF;
END;

/

INSERT INTO player(playerId, name, pass, dateRegistered, x, y)
VALUES (playerIdSeq.nextval, 'server', 'server', sysdate, 850, 400);

/

CREATE OR REPLACE PROCEDURE addPoints(pGameId NUMBER, pPlayerId NUMBER) AS BEGIN
	INSERT INTO points (pointsId, gameId, playerId, dateScored) 
	VALUES(pointsIdSeq.nextval, pGameId, pPlayerId, sysdate);
END;

/

CREATE OR REPLACE PROCEDURE loginPlayer(pName IN VARCHAR2, pPass IN VARCHAR2, ret1 OUT VARCHAR2, ret2 OUT NUMBER) IS
  user NUMBER;
  logged NUMBER;
BEGIN
  SELECT count(*) INTO user FROM player WHERE name = pName AND pass = pPass;
  IF user <> 0 THEN
    SELECT count(*) INTO logged FROM player WHERE name = pName AND pass = pPass AND isonline = 1;
    IF logged = 0 THEN
      UPDATE player SET isonline = 1 WHERE name = pName AND pass = pPass;
      ret1 := 'Zalogowano uzytkownika';
      ret2 := 1;
    ELSE
      ret1 := 'Ten uzytkownik jest juz zalogowany';
      ret2 := 0;
    END IF;
  ELSE
    ret1 := 'Podano zle dane albo uzytkownik nie istnieje';
    ret2 := 0;
  END IF;
END;

/

CREATE OR REPLACE PROCEDURE logoutPlayer(pName IN VARCHAR2, ret OUT NUMBER) IS
  user NUMBER;
BEGIN
  SELECT count(*) INTO user FROM player WHERE name = pName;
  IF user <> 0 THEN
    UPDATE player SET isonline = 0 WHERE name = pName;
    ret := 1;
  ELSE
    ret := 0;
  END IF;
END;

/

CREATE OR REPLACE PROCEDURE isInGame(pName IN VARCHAR2, ret OUT VARCHAR2) IS
  ingame NUMBER;
BEGIN
  SELECT count(*) INTO ingame FROM game g, player p WHERE (p.playerId = g.player1 OR p.playerId = g.player2) AND p.name = pName;
  IF ingame > 0 THEN ret := '1'; ELSE ret := '0'; END IF;
END;

/

CREATE OR REPLACE PROCEDURE createGame(pName IN VARCHAR2) IS
  pid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  
  INSERT INTO game (gameId, player1, player2, dateCreated) 
	VALUES (gameIdSeq.nextval, pid, 0, sysdate);
END;

/

CREATE OR REPLACE PROCEDURE isFreeGame(ret OUT NUMBER) IS
BEGIN
  SELECT g.gameId INTO ret FROM game g WHERE ((g.player1 = 0 AND g.player2 <> 0) OR (g.player2 = 0 AND g.player1 <> 0)) AND ROWNUM <= 1;
  
  EXCEPTION
    WHEN NO_DATA_FOUND THEN ret := 0;
END;

/

CREATE OR REPLACE PROCEDURE joinToGame(pName IN VARCHAR2, game IN NUMBER, ret OUT NUMBER) IS
  game2 NUMBER;
  pid NUMBER;
BEGIN
  SELECT count(*) INTO game2 FROM game g, player p 
  WHERE (p.playerId = g.player1 OR p.playerId = g.player2) 
    AND g.gameId = game 
    AND (
          (g.player1 = 0 AND g.player2 <> 0) 
          OR (g.player2 = 0 AND g.player1 <> 0)
        );
        
  IF game2 > 0 THEN
    SELECT playerId INTO pid FROM player WHERE player.name = pName;
    UPDATE game g SET g.player2 = pid, g.started = 1 WHERE g.gameId = game;
    ret := 1;
  ELSE
    ret := 0;
  END IF;
  
  EXCEPTION
    WHEN NO_DATA_FOUND THEN ret := 0;
END;

/

CREATE OR REPLACE PROCEDURE exitFromGame(pName IN VARCHAR2) IS
  pid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  
  UPDATE game SET player1 = 0, started = 0 WHERE player1 = pid;
  UPDATE game SET player2 = 0, started = 0 WHERE player2 = pid;
END;

/

CREATE OR REPLACE PROCEDURE deleteEmptyGames IS
BEGIN
  DELETE FROM game WHERE player1 = 0 AND player2 = 0;
END;

/

CREATE OR REPLACE PROCEDURE updatePos(pName IN VARCHAR2, px IN NUMBER, py IN NUMBER) IS
BEGIN
  UPDATE player SET x = px, y = py WHERE name = pName;
END;

/

CREATE OR REPLACE PROCEDURE getOpponentPos(pName IN VARCHAR2, px OUT NUMBER, py OUT NUMBER) IS
  pid NUMBER;
  player1 NUMBER;
  player2 NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.player1, g.player2 INTO player1, player2 FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
  
  IF player1 = pid THEN
    SELECT x, y INTO px, py FROM player WHERE playerId = player2;
  ELSE
    SELECT x, y INTO px, py FROM player WHERE playerId = player1;
  END IF;
END;

/

CREATE OR REPLACE PROCEDURE getPoints(pName IN VARCHAR2, p1 OUT NUMBER, p2 OUT NUMBER) IS
  pid NUMBER;
  player1 NUMBER;
  player2 NUMBER;
  gameid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.player1, g.player2, g.gameid INTO player1, player2, gameid FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
  
  SELECT nvl(count(*), 0) INTO p1 FROM points p WHERE p.gameid = gameid AND p.playerId = player1; 
  SELECT nvl(count(*), 0) INTO p2 FROM points p WHERE p.gameid = gameid AND p.playerId = player2;  
  
  EXCEPTION
    WHEN NO_DATA_FOUND THEN BEGIN p1 := 0; p2 := 0; END;
END;

/

CREATE OR REPLACE PROCEDURE scorePlayer(pName IN VARCHAR2) IS
  pid NUMBER;
  player1 NUMBER;
  player2 NUMBER;
  gameid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.player1, g.player2, g.gameid INTO player1, player2, gameid FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
  
  IF player1 = pid THEN
    addPoints(gameid, player1);
  ELSE
    addPoints(gameid, player2);
  END IF;
END;

/

CREATE OR REPLACE PROCEDURE scoreOpponent(pName IN VARCHAR2) IS
  pid NUMBER;
  player1 NUMBER;
  player2 NUMBER;
  gameid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.player1, g.player2, g.gameid INTO player1, player2, gameid FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
  
  IF player1 = pid THEN
    addPoints(gameid, player2);
  ELSE
    addPoints(gameid, player1);
  END IF;
END;

/

CREATE OR REPLACE PROCEDURE getBallPos(pName IN VARCHAR2, p1 OUT NUMBER, p2 OUT NUMBER) IS
  pid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.x, g.y INTO p1, p2 FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
END;


/

CREATE OR REPLACE PROCEDURE updateBallPos(pName IN VARCHAR2, p1 IN NUMBER, p2 IN NUMBER) IS
  pid NUMBER;
  gameid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.gameId INTO gameid FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
  
  UPDATE game g SET g.x = p1, g.y = p2 WHERE g.gameId = gameid;
END;

/

CREATE OR REPLACE PROCEDURE isGameRunning(pName IN VARCHAR2, ret OUT NUMBER) IS
  pid NUMBER;
  gameid NUMBER;
BEGIN
  SELECT playerId INTO pid FROM player WHERE player.name = pName;
  SELECT g.started INTO ret FROM game g, player p WHERE (g.player1 = pid OR g.player2 = pid) AND p.name = pName;
END;