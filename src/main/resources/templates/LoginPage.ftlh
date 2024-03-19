<#import "commons.ftlh" as c>

<@c.page>
    <div class="enterform">
        <div class="title">
            Вход
        </div>
        <form method="post" autocomplete="off">
            <div class="box">
                <div class="field">
                    <div class="name">
                        Логин
                    </div>
                    <div class="inp">
                        <input type="text" name="username"/>
                    </div>
                    <#if error??>
                        <div class="error">неверный логин или пароль</div>
                    </#if>
                </div>
                <div class="field">
                    <div class="name">
                        Пароль
                    </div>
                    <div class="inp">
                        <input name="password" type="password"/>
                    </div>
                    <#if error??>
                        <div class="error">неверный логин или пароль</div>
                    </#if>
                </div>
                <input type="submit" value="Войти">
            </div>
        </form>
    </div>
</@c.page>
