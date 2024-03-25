<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout topics>
    <#if post.isPublic>
        <@s.section post>
            <#list post.blocks as block>
                <@b.switch block />
            </#list>
        </@s.section>
    <#else>
        <span>Данный пост недоступен</span>
    </#if>
</@l.layout>
