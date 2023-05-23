jarPathTarget="./myFramework.jar"
jarPathSource="./Framework/build/web/WEB-INF/classes/ etu1964"
testLibPath="./Projet_Test/build/web/WEB-INF/lib/"
pathTempProject="./projet/"
projetTestSource="./Projet_Test/src/"
dossierSource="./Projet_Test/build/web/"
pathDeployement="/home/to/Téléchargements/apache-tomcat-8.5.81/webapps/"

# Création du .jar du framework
jar -cf $jarPathTarget -C $jarPathSource
mv $jarPathTarget $testLibPath

# Création du dossier comme interface
mkdir -p $pathTempProject"WEB-INF/classes" $pathTempProject"WEB-INF/lib/" $pathTempProject"META-INF" $pathTempProject"View"

# Migration des données 
cp -r $dossierSource"View" $pathTempProject
cp -r $dossierSource"META-INF" $pathTempProject
cp $dossierSource"WEB-INF/lib/myFramework.jar" $pathTempProject"WEB-INF/lib/"
cp $dossierSource"WEB-INF/web.xml" $pathTempProject"WEB-INF/"

# Compilation des code du nouveau projet
find $projetTestSource -name "*.java" | xargs -I {} javac -cp ./projet/WEB-INF/lib/myFramework.jar -d ./projet/WEB-INF/classes/ {}

# Création du .war a déployer
cd $pathTempProject
jar -cf ./projet.war *

# Déployement du .war et suppression du dossier temporaire
mv ./projet.war $pathDeployement
rm -r ../projet
