<%-- 
    Document   : ListeEmployer
    Created on : 4 avr. 2023, 10:01:54
    Author     : to
--%>

<%@page import="java.util.List"%>
<%@page import="etu1964.model.Emp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listes de tous les employers</h1>
        <p>Date debut : <%=(String) request.getAttribute("dateDebut")%></p>
        <% List<Emp> listes = (List) request.getAttribute("listes"); %>
        <ul>
            <% for (Emp elem : listes) {%>
            <li><%= elem.getNom()%></li>
                <% }%>
        </ul>
        <form action="insertNewEmp.do" method="POST" enctype="multipart/form-data">
            <input type="text" name="nom" value="Koto">
            <input type="number" name="age" value="18">
            <input type="date" name="naissance" value="2004-07-07">
            <input type="file" name="photo">
            <input type="submit" value="tester">
        </form>
    </body>
</html>
