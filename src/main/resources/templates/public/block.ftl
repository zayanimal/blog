<#macro header title>
    <div class="section__header">
        <h2>${title}</h2>
    </div>
</#macro>

<#macro paragraph text>
    <div class="section__description">${text}</div>
</#macro>

<#macro image src alt>
    <img class="section__img" src=${src} alt=${alt}/>
</#macro>

<#macro delimiter>
    <hr class="section__delimeter">
</#macro>
