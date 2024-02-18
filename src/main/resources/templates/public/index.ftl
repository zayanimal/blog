<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>

<@l.layout>
    <#list posts as post>
        <@s.section post>
            <div class="section__link">
                <a href=${post.link + "/" + post.id}>Читать дальше</a>
            </div>
        </@s.section>
    </#list>
</@l.layout>
