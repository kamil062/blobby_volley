DROP TABLE player CASCADE CONSTRAINTS
/
DROP TABLE game CASCADE CONSTRAINTS
/
DROP TABLE points CASCADE CONSTRAINTS
/
CREATE TABLE player ( 	-- Tabela 'player' zawiera zarejestrowanych użytkowników
	playerId		NUMBER(3) 		CONSTRAINT playerIdPK 		PRIMARY KEY,	-- ID użytkownika
	name			VARCHAR2(20) 	CONSTRAINT playerNameU 		UNIQUE,			-- Login (nazwa) użytkownika
	pass			VARCHAR2(40) 	CONSTRAINT playerPassNN		NOT NULL,		-- Hash hasła użytkownika
	dateRegistered	DATE,														-- Data rejestracji
	isOnline		NUMBER(1)		DEFAULT	0,									-- Czy użytkownik jest online
	x				NUMBER(4)		DEFAULT 1,									-- Pozycja x gracza
	y				NUMBER(4)		DEFAULT 1									-- Pozycja y gracza
)
/
CREATE TABLE game (		--Tabela 'game' zawiera pojedyncze rozgrywki pomiędzy graczami
	gameId		NUMBER(3)	CONSTRAINT gameIdPK			PRIMARY KEY,					-- ID gry
	player1		NUMBER(3)	CONSTRAINT gameplayer1FK	REFERENCES player(playerId),	-- Pierwszy gracz
	player2		NUMBER(3)	CONSTRAINT gameplayer2FK	REFERENCES player(playerId),	-- Drugi gracz
	x			NUMBER(4) 	DEFAULT 20,													-- Pozycja x piłki
	y			NUMBER(4)	DEFAULT 300,												-- Pozycja y piłki
	turn		NUMBER(1)	DEFAULT 1,													-- Który gracz aktualnie ma ruch
	started		NUMBER(1)  	DEFAULT 0,													-- Czy gra się rozpoczęła
	dateCreated	DATE,																	-- Data rozpoczęcia gry
	dateEnded	DATE																	-- Data zakończenia gry
)
/
CREATE TABLE points (	-- Tabela z punktami zdobywanymi przez graczy podczas gier
	pointsId	NUMBER(3)	CONSTRAINT pointsIdPK	PRIMARY KEY,								-- ID punktu
	gameId		NUMBER(3)	CONSTRAINT pointsFK1	REFERENCES game(gameId) ON DELETE CASCADE,	-- ID gry
	playerId	NUMBER(3)	CONSTRAINT pointsFK2	REFERENCES player(playerId),				-- ID gracza, który zdobył punkt
	dateScored	DATE																			-- Data zdobycia punktu
)
/



