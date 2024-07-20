<#macro hamburger>
    <div id="hamburger" class="hamburger">
        <svg id="hamburger-icon" width="60px" height="60px" viewBox="0 0 24 24" fill="none">
            <path d="M4 18L20 18" stroke="ivory" stroke-width="2" stroke-linecap="round" />
            <path d="M4 12L20 12" stroke="ivory" stroke-width="2" stroke-linecap="round" />
            <path d="M4 6L20 6" stroke="ivory" stroke-width="2" stroke-linecap="round" />
        </svg>

        <svg id="cross-icon" class="hamburger_hidden" width="60px" height="60px" viewBox="0 0 24 24" fill="none">
            <path d="M19 5L5 19M5.00001 5L19 19" stroke="ivory" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
    </div>
</#macro>

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
