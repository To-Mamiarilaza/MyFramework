<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Insertion d'un legume</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./View/asset/bootstrap.css">
    </head>
    <body>
        <div class="container mt-5">
            <h4>Insertion d'un nouveau legume</h4>
            <hr>
            <form action="insert-legume.do" class="form" method="POST">
                <div class="mt-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom">
                </div>
                <div class="mt-3">
                    <label for="nom" class="form-label">Prix</label>
                    <input type="number" class="form-control" id="nom" name="prix">
                </div>
                <p style="color: rgb(245, 105, 105);font-size: 14px;" class="my-3">Une erreur s'est produite !</p>
                <input type="submit" class="btn btn-success mt-2" value="Valider">
            </form>
        </div>
    </body>
</html>
