<#macro layout>
    <!DOCTYPE html>
    <html lang="ru">
        <head>
            <title>Мой профиль</title>
            <meta charset="UTF-8">
            <link rel="icon" type="image/x-icon" href="/asset/favicon.ico">
            <link type="text/css" rel="stylesheet" href="/admin/index.css">
        </head>
        <body>
            <header class="header">
                <h1>Профиль</h1>
            </header>

            <main class="main">
                <#nested>
            </main>
        </body>
        <script src="/admin/index.js"></script>
    </html>
</#macro>
