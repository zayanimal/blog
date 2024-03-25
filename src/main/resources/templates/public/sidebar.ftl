<#macro aside topics>
    <div id="sidebar-background" class="sidebar__background sidebar__background_hidden"></div>
    <aside id="sidebar" class="sidebar sidebar_hidden">
        <h1>Темы</h1>
        <nav>
            <ul>
                <#list topics as topic>
                    <li>${topic.name}</li>
                </#list>
            </ul>
        </nav>
    </aside>
</#macro>
