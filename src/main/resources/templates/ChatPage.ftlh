<#import "commons.ftlh" as c>

<@c.page>
    <div class="chatbox">
        <div class="to">
            ${to.name}
        </div>
        <div class="messages">
            <div class="messagesbox">
                <#list messages as message>
                    <#if user.id==message.from.id>
                        <div class="message my">
                            <label hidden="hidden">my</label>
                            <div class="messagetext">
                                ${message.text}
                            </div>
                            <div class="date">
                                ${message.getPrettyCreatedAt()}
                            </div>
                        </div>
                    <#else>
                        <div class="message his">
                            <label hidden="hidden">his</label>
                            <div class="messagetext">
                                ${message.text}
                            </div>
                            <div class="date">
                                ${message.getPrettyCreatedAt()}
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>
        </div>
        <div class="send">
            <form method="post">
                <div class="sendbox">
                    <textarea name="text" placeholder="Сообщение"></textarea>
                    <@c.error "text"/>
                </div>
                <input type="submit">
            </form>
        </div>
    </div>
    <script>
        setInterval(function () {
            const login = "${to.login}"
            $.ajax({
                type: "GET",
                uri: "/chats/" + login,
                success: function (response) {
                    $(".messagesbox").text("");
                    $(response).find(".message").each(function (index, value) {
                        const date = $(value).find(".date").text();
                        const text = $(value).find(".messagetext").text();
                        const whose = $(value).find("label").text();
                        $(".messagesbox").append(
                            "<div class='message " + whose + "'>" +
                                "<label hidden='hidden'>" + whose + "</label>" +
                                "<div class='messagetext'>" +
                                    text +
                                "</div>" +
                                "<div class='date'>" +
                                    date +
                                "</div>" +
                            "</div>");
                    });
                }
            })
        }, 500)
    </script>
</@c.page>
