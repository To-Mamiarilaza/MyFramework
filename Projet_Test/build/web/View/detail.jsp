<%@page import="etu1964.model.Legume"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Detail d'un legume</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./View/asset/bootstrap.css">
    </head>
    <body>
        <div class="container mt-5 p-5">
            <% Legume legume = (Legume) request.getAttribute("legume"); %>
            <h4>Detail d'un legume</h4>
            <hr>
            <p class="my-4">ID : <strong><%= legume.getIdLegume() %></strong></p>
            <p class="my-4">Nom : <strong><%= legume.getNom() %></strong></p>
            <p class="my-4">Prix : <strong><%= legume.getPrix() %> AR</strong></p>
        </div>
    </body>
</html>
