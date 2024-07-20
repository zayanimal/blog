<#import 'header.ftl' as h>
<#import 'sidebar.ftl' as s>
<#import 'user-menu.ftl' as u>

<#macro layout topics isAuth>
    <!DOCTYPE html>
    <html lang="ru">
        <head>
            <title>Rabbit</title>
            <meta charset="UTF-8">
            <link rel="icon" type="image/x-icon" href="/asset/favicon.ico">
            <link type="text/css" rel="stylesheet" href="/index.css">
            <script src="http://localhost:35729/livereload.js"></script>
        </head>

        <body>
            <@h.header isAuth />
            <@s.aside topics />

            <main class="main">
                <#nested>
            </main>

            <@u.menu isAuth />
        </body>
        <script src="/index.js"></script>
    </html>
</#macro>