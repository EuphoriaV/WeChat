<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <div class="search">
        <input name="search"/>
    </div>
    <div class="chatspage">
        <div class="title">
            Чаты
        </div>
        <div class="chats">
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
                            <#if chat.message.text?length gt 40>
                                ${chat.message.text?substring(0,40)}...
                            <#else>
                                ${chat.message.text}
                            </#if>
                        </div>
                    </div>
                </a>
            </#list>
        </div>
    </div>
</@c.page>
