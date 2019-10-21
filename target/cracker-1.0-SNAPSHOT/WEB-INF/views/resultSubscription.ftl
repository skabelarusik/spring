<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.result"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperLogin">
    <div class="wrapperLoginInner">
        <h1>Subscriptions list</h1>

        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>ID</th>
                <th>SUBSCRIBER</th>
                <th>PROGRAM NAME</th>
                <th>START</th>
                <th>FINISH</th>
            </tr>
            </thead>
            <tbody>
            <#list listSubscr as subscription>
            <tr>
                <td>${subscription.id}</td>
                <td>${subscription.subscriber}</td>
                <td>${subscription.nameProgram}</td>
                <td>${subscription.start}</td>
                <td>${subscription.finish}</td>
            </tr>
            </#list>
            </tbody>
        </table>


        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>
