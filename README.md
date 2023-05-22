# SAE_VAE

```
cd existing_repo
git remote add origin https://gitlab.com/Ya.nnis/sae_vae.git
git branch -M main
git push -uf origin main
```

## Pour compiler :

javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin ./src/*.java

javac -d bin src/*.java

## Pour executer :

java -cp bin:img --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls Executable


## Générer la doc

<!-- javadoc -d doc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java -->


javadoc -d doc -charset utf8 -private -noqualifier all --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls *.java