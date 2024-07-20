<#macro section post>
    <section class="section">
        <div class="section__topic">${post.topic}</div>
        <div class="section__username">${post.username}</div>
        <#nested>
        <hr class="section__delimeter">
        <div class="section__time">
            <time>${post.date}</time>
        </div>
    </section>
</#macro>
