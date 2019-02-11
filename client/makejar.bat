dir /s /B *.class > sources2.txt
jar cvfm client.jar manifest.txt @sources2.txt
rm sources2.txt