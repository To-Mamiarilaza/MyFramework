<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./View/asset/bootstrap.css"/>
    <title>Se connecter</title>
</head>
<body>
    <div class="container mt-5">
        <h1>Veiller vous connecter !</h1>
        <form action="checkLogin.do" class="form" method="get" style="width:60%;">
            <div class="mt-2">
                <label for="username" class="form-label">Nom d'utilisateur</label>
                <input type="text" id="username" class="form-control" name="username">
            </div>
            <div class="mt-2">
                <label for="username" class="form-label">Mot de passe</label>
                <input type="text" id="username" class="form-control" name="password">
            </div>
            <% if (request.getAttribute("erreur") != null) { %>
                <p class="erreur mt-3" style="color: rgb(214, 111, 111); font-size: 12px;">Verifier votre username et password !</p>
            <% } %>   
            <input type="submit" class="btn btn-success mt-2" value="Se connecter">
        </form>
    </div>
</body>
</html>