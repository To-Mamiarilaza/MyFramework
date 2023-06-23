<%-- 
    Document   : bienvenue
    Created on : 13 juin 2023, 11:34:39
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
        <h1>Bienvenue cher, <%= request.getAttribute("profile") %></h1>
        <a href="deleteEmp.do">Supprimer employer</a>
        <a href="getAllEmployer.do">Liste d'employer</a>
    </body>
</html>
