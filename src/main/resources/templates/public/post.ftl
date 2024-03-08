<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout>
    <#if post.isPublic>
        <@s.section>
            <#list post.blocks as block>
                <@b.switch block />
            </#list>
            <hr class="section__delimeter">
            <div class="section__time">
                <time>${post.time}</time>
            </div>
        </@s.section>
    <#else>
        <span>Данный пост недоступен</span>
    </#if>
</@l.layout>
