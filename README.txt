MY FRAMEWORK ( Un framework pour le développement d'application WEB )
Requis :
	- JDK 8 minimum

DEMARRAGE DU FRAMEWORK :
1. Copier le fichier framework.jar dans le lib de votre projet

2. Dans le web.xml, Ajouter un servlet avec un nom FrontServlet, servlet-class : etu1964.framework.servlet.FrontServlet, servlet-mapping /. C'est pour recevoir et traiter tous les process.

3. Dans la balise servlet, ajouter un balise init-param avec param-name : rootPackage et param-value : Le package dans lequel le framework doit analyser tout votre classe

UTILISATION DANS UN PROJET :
1. Tout d'abord vous devez importer dans un class où vous allez travailler : etu1964.framework.ModelView et etu1964.framework.annotations.Url.

2. Pour pouvroir executer un fonction , annoter le avec @Url(path="exemple") après on peut executer cette fonction via : ..../exemple

3. Si on veut afficher un view , il faut que le type de retour du fonction soit un ModelView, sinon void . Dans un fonction , pour passer des données vers un view il faut premièrement créer un objet de type modelView avec un argument "../../view.jsp" dans le constructeur, après appeler le fonction addItem(clé, valeur) sur cette objet pour passer la valeur dans la view, celui ci est accéssible par request.getAttribute(clé).

4. Mais si c'est depuis un view que vous devez passer des données vers un fonction, ces données vont s'affecter vers l'attribut du class contenant cette fonction si la clé et l'attribut on le même nom. Si il n'ont pas le même nom rien ne se passe. En resumé , le nom des champs du formulaire doit etre le même que le nom des attributs. C'est même pour le passage de données par URL (?id=1&...)

5. Si la fonction a appelé contient des paramètres, on peut les affecter depuis l'URL (?id=1&...), il faut seulement que les clés des valeurs depuis l'URL ont le même nom que les parametres du fonction pour que l'affectation soit valide, le paramètre d'un fonction non affécté a comme valeur NULL.
