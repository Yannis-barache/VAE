# SAE_VAE
## Les membres du groupe :
- Yannis BARACHE
- Gaël SIMON
- Tom DUBOIS
- Jordan LAVENANT
- Quentin PIEDANNA
- Marin TRÉMINE

## Pour clone le répertoire

```bash
git clone https://gitlab.com/Ya.nnis/sae_vae.git
```

## Pour compiler :
```bash
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java
```

## Pour executer :
```bash
java -cp .:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ExecutableTest
```

## Générer la doc
```bash
javadoc -d doc -charset utf8 -private -noqualifier all --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java
```
