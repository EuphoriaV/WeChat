<#import "/spring.ftl" as spring/>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>WeChat</title>
        <link rel="icon" type="image/png" href="<@spring.url '/img/logo.png'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <#--        <script src="<@spring.url '/js/jquery-3.6.1.min.js'/>"></script>-->
    </head>
    <body>
    <header>
        <a href="<@spring.url '/chats'/>"><img src="<@spring.url '/img/logo.png'/>" alt="WeChat"></a>
        <label>WeChat</label>
        <#if ! user??>
            <div class="buttons">
                <a href="<@spring.url '/login'/>">Войти</a>
                <a href="<@spring.url '/register'/>">Зарегистрироваться</a>
            </div>
        <#else>
            <div class="buttons">
                <a href="<@spring.url '/logout'/>">Выйти</a>
                <a href="<@spring.url '/chats'/>">${user.name}</a>
            </div>
        </#if>
    </header>
    <main>
        <#nested/>
    </main>
    </body>
    </html>
</#macro>

<#macro error field>
    <#if errors?? && errors[field]??>
        <div class="error">
            ${errors[field]}
        </div>
    </#if>
</#macro>