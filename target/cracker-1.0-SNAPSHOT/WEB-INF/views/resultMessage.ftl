<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
    <head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title><@spring.message "title.service"/></title>
        <#setting classic_compatible=true>
    </head>
    <body>
    <div class="wrapResult">
    <h1>Users list</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Text</th>
            <th>Topic</th>
            <th>Sender</th>
            <th>Recipient</th>
            <th>LocalDate</th>
        </tr>
    <#list messages as message>
        <tr>
            <td width="20px">${message.id}</td>
            <td width="100px">${message.text}</td>
            <td width=100px">${message.topik}</td>
            <td width="80px">${message.sender}</td>
            <td width="80px">${message.recipient}</td>
            <td width="60px">${message.localDate}</td>
         </tr>
    </#list>
    </table>

    <br/>
<h4 class="h3login"><a class="registration" href='/back'>
        <@spring.message "label.backmain" /></a>
</h4>

    </div>
    </body>