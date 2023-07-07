MY FRAMEWORK ( Un framework pour le développement d'application WEB )
Requis :
	- JDK 8 minimum

DEMARRAGE DU FRAMEWORK :
1. Copier le fichier framework.jar dans le lib de votre projet

2. Dans le web.xml :
	- ajouter un servlet avec un nom FrontServlet
	- son servlet-class : etu1964.framework.servlet.FrontServlet
	- servlet-mapping : *.do  ( C'est pour recevoir et traiter tous les requettes )
	- ajouter un parametre d'initialisation init-param avec param-name :
	rootPackage et param-value : le package de source pour analyser tout vos
	classes.
	
UTILISATION DANS UN PROJET :
1. Importer dans votre classe : *
	- etu1964.framework.ModelView 
	- etu1964.framework.annotations.Url

2. Routage : Annoter les fonctions avec @Url(path="example.do") 
	- On peut invoker cette fonction par l'url : ..../example
	- Il faut que les url d'action se termine par .do
	
3. Affichage d'un view : 
	- changer le type de retour du fonction en ModelView 
	- pour passer les données vers le view de destination :
		- créer un objet ModelView avec un argument "../../view.jsp" dans
		  son constructeur 
		- appeler le fonction addItem(clé, valeur) sur cette objet pour
		  passer la valeur dans la view
		- Celui si est accéssible par request.getAttribute(clé)
		
4. Passer les données depuis le View par methode POST :
	- Les données vont s'affecter vers l'attribut du class contenant la fonction 	
	à executer si la clé et l'attribut ont le même nom sinon rien ne se passe
	- Si le type de l'attribut est un tableau et l'input a un nom terminant par [] les valeurs s'affectent aussi
	- On peut aussi les affecter par URL (?id=1&...)
	- On peut les acceder par this.id par exemple

5. Passer des valeurs aux paramètres de la fonction :
	- On peut les affecter depuis l'URL (?id=1&...)
	- Il faut seulement que les clés des valeurs depuis l'URL et les paramètres
	du fonction ont le même nom.
	- Le paramètre d'un fonction non affécté a comme valeur NULL
	
6. Upload fichier :
	- Ajouter un attribut FileUpload dans le class de destination qui veut utiliser le fichier
	- FileUpload est un class composé des attributs name (nom du fichier) , path (destination) , bytes[] (contenue du fichier) 
	- Ensuite nous allons affecté le fichier dans cette attribut , dans ce cas il faut que le input file doit entre au meme nom 
	  que l'attribut FileUpload est ca ce fait automatiquement après.
	  
7. Authentification :
	Pour filtrer les utilisateurs qui ont accés à un fonction :
	- Ajouter en tant que init-param du web.xml le nom du session d'authentification par exemple : isConnected
	- Ajouter aussi en init-param le nom du session du profil d'utilisateur par exemple : profile pour stocker les profiles d'utilisateurs
	- Pour authentifié un utilisateur:
		- Dans la classe ModelView on doit ajouter le session addSession("isConnected", true) par exemple
		- Et pour préciser le profil de l'utilisateur : addSession("profile", "admin")
	- Pour ajouter de l'authentification a un fonction , il faut mettre une annotation @Auth sur le fonction ou @Auth("nom du profil autorisé")
	
8. Gestion des sessions :
	- Pour ajouter des sessions on doit appeler seulement addSession(cle, valeur)
	- Pour prendre les sessions:
		- Annoter la fonction appelée avec @Session
		- on doit ajouter un attribut HashMap<String, Object> session dans la classe qui va l'utiliser.
		- Ensuite getSession(cle) dans la fonction pour avoir le valeur
		
9. Éxposition API:
	Pour obtenir le résultat d'une fonction en JSON:
	1 ér méthode:
		- appeler la fonction isJSON(true) du modèle view et après on ajoute les données à partir du addItem() et c'est fini
		
	2 ème méthode:
		- Ajouter une annotation @JSON sur le fonction et le type de retour les objets en questions
		- Annoter la fonction avec une annotation @JSON
		- Le type de retour du fonction est l'objet à passer par exemple List<Emp>
	
