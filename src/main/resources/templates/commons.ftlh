<#import "/spring.ftl" as spring/>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>WeChat</title>
        <link rel="icon" type="image/png" href="<@spring.url '/img/logo.png'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
    </head>
    <body>
    <header>
        <img src="img/logo.png" alt="WeChat">
        <label>WeChat</label>
        <#if ! user??>
            <div class="buttons">
                <a href="/enter">Войти</a>
                <a href="/register">Зарегистрироваться</a>
            </div>
        <#else>
            <div class="buttons">
                <a href="/logout">Выйти</a>
                <a href="/user/${user.login}">${user.name}</a>
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
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>