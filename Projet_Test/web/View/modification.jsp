<%@page import="etu1964.model.Legume"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Modification d'un legume</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./View/asset/bootstrap.css">
    </head>
    <body>
        <div class="container mt-5">
            <% Legume legume = (Legume) request.getAttribute("legume"); %>
            <h4>Modifier un nouveau legume</h4>
            <hr>
            <form action="modifier.do" class="form" method="POST">
                <div class="mt-3">
                    <label for="id" class="form-label">ID du legume</label>
                    <input type="number" class="form-control" id="id" name="idLegume" value="<%= legume.getIdLegume() %>" readonly>
                </div>
                <div class="mt-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" value="<%= legume.getNom()%>">
                </div>
                <div class="mt-3">
                    <label for="nom" class="form-label">Prix</label>
                    <input type="number" class="form-control" id="nom" name="prix" value="<%= legume.getPrix() %>">
                </div>
                <input type="submit" class="btn btn-success mt-3" value="Valider">
            </form>
        </div>
    </body>
</html>
