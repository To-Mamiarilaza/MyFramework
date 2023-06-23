<%-- 
    Document   : home
    Created on : 13 juin 2023, 11:20:59
    Author     : to
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="./checkLogin.do" method="GET">
            <input type="text" name="user" placeholder="Nom d'utilisateur">
            <input type="password" name="mdp" placeholder="Mot de passe">
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
