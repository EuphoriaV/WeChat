<#import "commons.ftlh" as c>

<@c.page>
    <div class="text">
        Если у Вас нет аккаунта нажмите <a href="/register">Зарегистрироваться</a>
    </div>
    <div class="enterform">
        <div class="title">
            Авторизация
        </div>
        <form method="post">
            <div class="box">
                <div class="field">
                    <div class="name">
                        Логин
                    </div>
                    <div class="inp">
                        <input type="text" name="login" autocomplete="off" value="${enterForm.login!}"/>
                    </div>
                    <@c.error "enterForm.login"/>
                </div>
                <div class="field">
                    <div class="name">
                        Пароль
                    </div>
                    <div class="inp">
                        <input name="password" type="password" autocomplete="off" value="${enterForm.password!}"/>
                    </div>
                    <@c.error "enterForm.password"/>
                </div>
                <input type="submit" value="Войти">
            </div>
        </form>
    </div>
</@c.page>
