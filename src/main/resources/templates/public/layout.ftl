<#macro layout>
    <!DOCTYPE html>
    <html lang="ru">
        <head>
            <title>Это мой блог</title>
            <meta charset="UTF-8">
            <link rel="icon" type="image/x-icon" href="/icons/favicon.ico">
            <link type="text/css" rel="stylesheet" href="/css/public/index.css">
        </head>
        <body>
            <header class="header">
                <h1>In my life</h1>
            </header>

            <main class="main">
                <#nested>
            </main>
        </body>
    </html>
</#macro>