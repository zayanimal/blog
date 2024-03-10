<#macro section post>
    <section class="section">
        <#nested>
        <div class="section__topic">
            <#list post.topics as chip>
                <span class="section__chip">${chip}</span>
            </#list>
        </div>
        <hr class="section__delimeter">
        <div class="section__time">
            <time>${post.date}</time>
        </div>
    </section>
</#macro>
