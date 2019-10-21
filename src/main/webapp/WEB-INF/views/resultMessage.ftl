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
        <h1>Message list</h1>
        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Text</th>
                <th>Topic</th>
                <th>Sender</th>
                <th>Recipient</th>
                <th>LocalDate</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list messages as message>
                <tr>
                    <td width="20px">${message.id}</td>
                    <td width="100px">${message.text}</td>
                    <td width=100px">${message.topik}</td>
                    <td width="80px">${message.sender}</td>
                    <td width="80px">${message.recipient}</td>
                    <td width="60px">${message.localDate}</td>
                    <td class="prod3" width="60px">
                        <form class="changePageForm" method="post" action="/message/delete">
                            <input type="hidden" name="id" value="${message.id}">
                            <input type="hidden" name="sender" value="${message.sender}">
                            <input type="submit"  value="delete" class="uploadButtonTable"/>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        ${wrongData}
        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>
