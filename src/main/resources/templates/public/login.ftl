<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Это мой блог</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/x-icon" href="/asset/favicon.ico">
        <link type="text/css" rel="stylesheet" href="/public/index.css">
        <script src="http://localhost:35729/livereload.js"></script>
    </head>

    <body>
        <form class="login" name="login" action="login" method="POST">
            <label class="login__label" for="username">User:</label>
            <input class="login__input" type='text' name='username' id="username">

            <label class="login__label" for="password">Password:</label>
            <input class="login__input" type="password" name="password" id="password" />

            <input class="login__button" name="submit" type="submit" />
        </form>
    </body>
</html>
