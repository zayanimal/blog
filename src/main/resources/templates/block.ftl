<#macro switch block>
    <#switch block.type>
        <#case 'header'>
            <div class="section__header">
                <h2>${block.data.text}</h2>
            </div>
            <#break>
        <#case 'paragraph'>
            <div class="section__description">${block.data.text}</div>
            <#break>
        <#case 'image'>
            <img class="section__img" src=${block.data.file.url} alt=${block.data.caption}/>
            <#break>
        <#case 'delimiter'>
            <hr class="section__delimeter">
            <#break>
    </#switch>
</#macro>