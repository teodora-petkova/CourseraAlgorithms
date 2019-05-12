set checkJar="..\algs4\checkstyle-5.5\checkstyle-5.5-all.jar"
set checkXml="..\algs4\checkstyle-5.5\checkstyle.xml"
java -jar %checkJar% -c %checkXml% *.java
pause
