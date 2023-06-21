#!/bin/bash
javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.swing ./src/*.java
java --class-path bin:img:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.swing ./src/ApplicationVAE.java
