<#import "commons.ftlh" as c>

<@c.page>
    <div class="chatbox">
        <div class="to">
            ${currentUser.name!}
        </div>
        <div class="messages">
            <#list messages as message>
                <div class="message">
                    <#if user.id==message.from.id>
                        <div class="my">
                            ${message.text}
                            <div class="date">
                                ${message.creationTime}
                            </div>
                        </div>
                    <#else>
                        <div class="his">
                            ${message.text}
                            <div class="date">
                                ${message.creationTime}
                            </div>
                        </div>
                    </#if>
                </div>
            </#list>
        </div>
        <div class="send">
            <form method="post">
                <div class="sendbox">
                    <textarea name="text" placeholder="Сообщение"></textarea>
                    <@c.error "messageForm.text"/>
                </div>
                <input type="submit">
            </form>
        </div>
    </div>
</@c.page>
