<#import 'layout.ftl' as l>
<#import 'section.ftl' as s>
<#import 'block.ftl' as b>

<@l.layout>
    <@s.section>
        <#list post.blocks as block>
            <#switch block.type>
                <#case 'header'>
                    <@b.header block.data.text />
                    <#break>
                <#case 'paragraph'>
                    <@b.paragraph block.data.text />
                    <#break>
                <#case 'image'>
                    <@b.image block.data.file.url block.data.caption />
                    <#break>
                <#case 'delimiter'>
                    <@b.delimiter />
                    <#break>
            </#switch>
        </#list>
        <hr class="section__delimeter">
        <div class="section__time">
            <time>${post.time}</time>
        </div>
    </@s.section>
</@l.layout>
