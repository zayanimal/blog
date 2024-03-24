<#import 'hamburger.ftl' as h>
<#import 'sidebar.ftl' as s>

<#macro layout topics>
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
            <header class="header">
                <@h.hamburger />

                <h1>In my life</h1>
            </header>

            <@s.aside topics />

            <main class="main">
                <#nested>
            </main>
        </body>
        <script src="public/index.js"></script>
    </html>
</#macro>