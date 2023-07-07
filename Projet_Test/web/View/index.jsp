<%@page import="java.util.List"%>
<%@page import="etu1964.model.Legume"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Listes des legumes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./View/asset/bootstrap.css">
    </head>
    <body>
        <% List<Legume> legumes = (List<Legume>) request.getAttribute("legumes"); %>
        <div class="container mt-5">
            <h1>Listes des legumes</h1>
            <a href="new-legume.do" class="btn btn-success my-2">Ajouter nouveau legume</a>
                <a href="deconnect.do" class="mx-3">Se deconnecter</a>
            <table class="table mt-3">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Nom</td>
                        <td>Prix</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <% for (Legume legume : legumes) {%>
                    <tr>
                        <td><a href="detail.do?idLegume=<%= legume.getIdLegume()%>" class="link" style="text-decoration: none;"><%= legume.getIdLegume()%></a></td>
                        <td><%= legume.getNom()%></td>
                        <td><%= legume.getPrix()%></td>
                        <td><a href="modify-legume.do?idLegume=<%= legume.getIdLegume()%>" style="text-decoration: none;">Modifier</a></td>
                        <td><a href="delete-legume.do?idLegume=<%= legume.getIdLegume()%>" style="text-decoration: none;">Supprimer</a></td>
                    </tr>
                    <% }%>   

                </tbody>
            </table>
        </div>
    </body>
</html>
