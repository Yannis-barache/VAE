# SAE_VAE
## Les membres du groupe :
- Yannis BARACHE
- Gaël SIMON
- Tom DUBOIS
- Jordan LAVENANT
- Quentin PIEDANNA
- Marin TRÉMINE

## Pour clone le répertoire

```
git clone 
```

## Pour compiler :
```
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java
```

## Pour executer :
```
java -cp .:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ExecutableTest
```

## Générer la doc
```
javadoc -d doc -charset utf8 -private -noqualifier all --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java
```