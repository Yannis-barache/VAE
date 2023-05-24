#!/bin/bash
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin ./TestSQL/*.java
cd ./bin
java -cp .:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ExecutableTest