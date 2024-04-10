<#import 'sidebar.ftl' as s>
<#import 'user-menu.ftl' as u>

<#macro header>
    <header class="header">
        <div class="header__title">
            <@s.hamburger />
            <h1>In my life</h1>
        </div>

        <@u.icon />
    </header>
</#macro>