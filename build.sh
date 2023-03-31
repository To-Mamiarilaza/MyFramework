# Création du .jar du framework
jar -cf ./myFramework.jar -C ./Framework/build/web/WEB-INF/classes/ etu1964
mv ./myFramework.jar ./Projet\ de\ Test/build/web/WEB-INF/lib/

# Création du dossier comme interface
mkdir -p ./projet/WEB-INF/classes ./projet/WEB-INF/lib/ ./projet/META-INF ./projet/View

# Migration des données 
cp -r ./Projet\ de\ Test/build/web/View ./projet/
cp -r ./Projet\ de\ Test/build/web/META-INF ./projet/
cp ./Projet\ de\ Test/build/web/WEB-INF/lib/myFramework.jar ./projet/WEB-INF/lib/
cp ./Projet\ de\ Test/build/web/WEB-INF/web.xml ./projet/WEB-INF/

# Compilation des code du nouveau projet
find ./Projet\ de\ Test/src/ -name "*.java" | xargs -I {} javac -cp ./projet/WEB-INF/lib/myFramework.jar -d ./projet/WEB-INF/classes/ {}

# Création du .war a déployer
cd ./projet/
jar -cf ./projet.war *

# Déployement du .war et suppression du dossier temporaire
mv ./projet.war /home/to/Téléchargements/apache-tomcat-8.5.81/webapps/
rm -r ../projet
