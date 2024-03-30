<#import "commons.ftlh" as c>

<@c.page>
    <div class="enterform">
        <div class="title">
            Регистрация
        </div>
        <form method="post" autocomplete="off">
            <div class="box">
                <div class="field">
                    <div class="name">
                        Логин
                    </div>
                    <div class="inp">
                        <input type="text" name="login" value="${(registerRequest.login)!}"/>
                    </div>
                    <@c.error "login"/>
                </div>
                <div class="field">
                    <div class="name">
                        Имя
                    </div>
                    <div class="inp">
                        <input type="text" name="name" value="${(registerRequest.name)!}"/>
                    </div>
                    <@c.error "name"/>
                </div>
                <div class="field">
                    <div class="name">
                        Пароль
                    </div>
                    <div class="inp">
                        <input name="password" type="password" value="${(registerRequest.password)!}"/>
                    </div>
                    <@c.error "password"/>
                </div>
                <div class="field">
                    <div class="name">
                        Подтвердите пароль
                    </div>
                    <div class="inp">
                        <input name="passwordConfirmation" type="password" value="${(registerRequest.passwordConfirmation)!}"/>
                    </div>
                    <@c.error "passwordConfirmation"/>
                </div>
                <input type="submit" value="Войти">
            </div>
        </form>
    </div>
</@c.page>
