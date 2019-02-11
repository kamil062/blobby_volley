dir /s /B *.java > sources.txt
javac -cp jsfml.jar -d bin @sources.txt
rm sources.txt