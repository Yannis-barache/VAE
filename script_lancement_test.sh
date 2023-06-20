#!/bin/bash
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin ./src/*.java
java -cp bin:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ExecutableTest