dir /s /B *.java > sources.txt
javac -cp jsfml.jar;ojdbc6.jar -d bin @sources.txt
rm sources.txt