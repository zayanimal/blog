<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout topics>
    <@s.section post>
        <#list post.blocks as block>
            <@b.switch block />
        </#list>
    </@s.section>
</@l.layout>
