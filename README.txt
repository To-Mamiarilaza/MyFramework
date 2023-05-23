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
	- On peut aussi les affecter par URL (?id=1&...)
	- On peut les acceder par this.id par exemple

5. Passer des valeurs aux paramètres de la fonction :
	- On peut les affecter depuis l'URL (?id=1&...)
	- Il faut seulement que les clés des valeurs depuis l'URL et les paramètres
	du fonction ont le même nom.
	- Le paramètre d'un fonction non affécté a comme valeur NULL

