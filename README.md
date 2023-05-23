# SAE_VAE
## Les membres du groupe :
- Yannis BARACHE
- Gaël SIMON
- Tom DUBOIS
- Jordan LAVENANT
- Quentin PIEDANNA
- Marin TRÉMINE

```
cd existing_repo
git remote add origin https://gitlab.com/Ya.nnis/sae_vae.git
git branch -M main
git push -uf origin main
```

## Pour compiler :
```
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java

javac -d bin src/*.java
```

## Pour executer :
```
java -cp .:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls ExempleConnec
```

## Générer la doc
```
javadoc -d doc -charset utf8 -private -noqualifier all --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java
```