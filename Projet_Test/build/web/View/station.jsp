<%-- 
    Document   : station
    Created on : 13 juin 2023, 10:05:50
    Author     : to
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Station</h1>
        <% List<String> listes = (List<String>) request.getAttribute("sessions");%>

        <ul>
            <% for (String element : listes) { %>
                <li><%= element %></li>
            <% }%>
        </ul>

    </body>
</html>
