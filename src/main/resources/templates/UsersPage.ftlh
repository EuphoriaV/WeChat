<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <div class="results">
        <#if users?size == 0>
            <div class="text">
                Отсутствуют результаты по поиску: ${search}
            </div>
        <#else>
            <div class="text">
                Результаты по поиску: ${search}
            </div>
            <#list users as user1>
                <a href="<@spring.url '/chats/${user1.login}'/>">
                    <div class="user1">
                        ${user1.name} (${user1.login})
                    </div>
                </a>
            </#list>
        </#if>
    </div>
</@c.page>
