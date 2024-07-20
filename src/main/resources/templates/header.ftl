<#import 'sidebar.ftl' as s>
<#import 'user-menu.ftl' as u>

<#macro header isAuth>
    <header class="header">
        <div class="header__title">
            <@s.hamburger />
            <h1><a href="/">Rabbit</a></h1>
        </div>

        <div class="header__user-group">
            <#if isAuth == true>
                <a href="/post/create">
                    <svg width="60px" height="60px" viewBox="0 0 24 24" fill="none">
                        <path d="M4 12H20M12 4V20" stroke="ivory" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                </a>
            </#if>

            <@u.icon />
        </div>
    </header>
</#macro>