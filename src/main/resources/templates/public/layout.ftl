<#macro layout>
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
                <svg width="60px" height="60px" viewBox="0 0 24 24" fill="none">
                    <path d="M4 18L20 18" stroke="ivory" stroke-width="2" stroke-linecap="round" />
                    <path d="M4 12L20 12" stroke="ivory" stroke-width="2" stroke-linecap="round" />
                    <path d="M4 6L20 6" stroke="ivory" stroke-width="2" stroke-linecap="round" />
                </svg>

                <h1>In my life</h1>
            </header>

            <main class="main">
                <#nested>
            </main>
        </body>
    </html>
</#macro>