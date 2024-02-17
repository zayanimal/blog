<#macro section title image text link date>
    <section class="section">
        <div class="section__header">
            <h2>${title}</h2>
        </div>
        <div class="section__body">
            <img class="section__img" src=${image} alt="Фотография" />
            <div class="section__description">${text}</div>
            <div class="section__link">
                <a href=${link}>Читать дальше</a>
            </div>
        </div>
        <hr class="section__delimeter">
        <div class="section__time">
            <time>${date}</time>
        </div>
    </section>
</#macro>
