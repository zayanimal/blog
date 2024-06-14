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
        <main class="login">
            <form class="login__form" name="login" action="login" method="POST">
                <label class="login__label" for="username">Пользователь:</label>
                <input type='text' name='username' id="username">

                <label class="login__label" for="password">Пароль:</label>
                <input type="password" name="password" id="password" />

                <label class="login__label" for="remember-me">Запомнить меня:</label>
                <input type="checkbox" name="remember-me" value="remember-me" id="remember-me" checked />

                <input class="login__button" name="submit" type="submit" value="Подтвердить" />
            </form>
        </main>
    </body>
</html>
