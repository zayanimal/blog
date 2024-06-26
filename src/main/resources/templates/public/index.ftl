<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout topics isAuth>
    <#list posts as post>
        <#if post.isPublic>
            <@s.section post>
                <#list post.blocks as block>
                    <@b.switch block />
                </#list>
                <div class="section__read-more">
                    <a href="/post/${post.linkText}">Читать дальше</a>
                </div>
            </@s.section>
        </#if>
    </#list>
</@l.layout>
