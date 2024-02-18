<#macro section post>
    <section class="section">
        <div class="section__header">
            <h2>${post.title}</h2>
        </div>
        <div class="section__body">
            <img class="section__img" src=${post.image} alt="Фотография"/>
            <div class="section__description">${post.text}</div>
            <#nested>
        </div>
        <hr class="section__delimeter">
        <div class="section__time">
            <time>${post.date}</time>
        </div>
    </section>
</#macro>
