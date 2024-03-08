<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout>
    <#list posts as post>
        <#if post.isPublic>
            <@s.section>
                <#list post.blocks as block>
                    <@b.switch block />
                </#list>
                <a href="/post/${post.id}">Читать дальше</a>
                <hr class="section__delimeter">
                <div class="section__time">
                    <time>${post.time}</time>
                </div>
            </@s.section>
        </#if>
    </#list>
</@l.layout>
