<#import 'section.ftl' as s>

<!DOCTYPE html>
<html lang="ru">
<#include 'head.ftl'>

<body>
    <#include 'header.ftl'>

    <main class="main">
        <#list posts as post>
            <@s.section post.title post.image post.text post.link post.date />
        </#list>
    </main>
    </body>
</html>
