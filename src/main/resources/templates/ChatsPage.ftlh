<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <form method="post">
        <div class="search">
            <input name="search" placeholder="Поиск пользователя" autocomplete="off"/>
        </div>
    </form>
    <div class="chatspage">
        <#if chats?size == 0>
            <div class="title">
                У вас пока нет чатов
            </div>
        <#else>
            <div class="title">
                Чаты
            </div>
            <#list chats as chat>
                <a href="<@spring.url '/chat/${chat.user.login}'/>">
                    <div class="chat">
                        <div class="friendname">
                            ${chat.user.name}
                        </div>
                        <div class="lasttime">
                            ${chat.message.creationTime}
                        </div>
                        <div class="lastmessage">
                            ${chat.message.from.name}:
                            <#if chat.message.text?length gt 35>
                                ${chat.message.text?substring(0,35)}...
                            <#else>
                                ${chat.message.text}
                            </#if>
                        </div>
                    </div>
                </a>
            </#list>
        </#if>
    </div>
    <script type="text/javascript" src="https://livejs.com/live.js"></script>
</@c.page>
