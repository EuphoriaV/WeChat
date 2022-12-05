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
        <div class="friends">
            <#list friends as friend>
                <div class="friend">
                    <a href="<@spring.url '/chat/${friend}'/>">${friend}</a>
                </div>
            </#list>
        </div>
    </div>
</@c.page>
