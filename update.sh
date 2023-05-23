jarPathTarget="./myFramework.jar"
jarPathSource="./Framework/build/web/WEB-INF/classes/ etu1964"
testLibPath="./Projet_Test/build/web/WEB-INF/lib/"

# Création du .jar du framework
jar -cf $jarPathTarget -C $jarPathSource
mv $jarPathTarget $testLibPath

