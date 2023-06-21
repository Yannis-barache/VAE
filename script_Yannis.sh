#!/bin/bash
javac -d binYannis --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.swing ./src_Yannis/*.java
cd binYannis
java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp :../img ApplicationVAE