<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <div class="text">
        Если у Вас есть аккаунт нажмите <a href="<@spring.url '/enter'/>">Войти</a>
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
                        <input type="text" name="login" autocomplete="off" value="${registerForm.login!}"/>
                    </div>
                    <@c.error "registerForm.login"/>
                </div>
                <div class="field">
                    <div class="name">
                        Имя
                    </div>
                    <div class="inp">
                        <input type="text" name="name" autocomplete="off" value="${registerForm.name!}"/>
                    </div>
                    <@c.error "registerForm.name"/>
                </div>
                <div class="field">
                    <div class="name">
                        Пароль
                    </div>
                    <div class="inp">
                        <input name="password" type="password" autocomplete="off" value="${registerForm.password!}"/>
                    </div>
                    <@c.error "registerForm.password"/>
                </div>
                <div class="field">
                    <div class="name">
                        Подтвердите пароль
                    </div>
                    <div class="inp">
                        <input name="passwordConfirm" type="password" autocomplete="off"
                               value="${registerForm.passwordConfirm!}"/>
                    </div>
                    <@c.error "registerForm.passwordConfirm"/>
                </div>
                <input type="submit" value="Войти">
            </div>
        </form>
    </div>
</@c.page>
