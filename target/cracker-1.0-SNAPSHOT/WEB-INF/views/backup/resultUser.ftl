<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message "title.result"/></title>
    <#setting classic_compatible=true>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
</head>
<body>
<div class="wrapResult">
<h1>Users list</h1>
<table border="1" width="60%" class="userTable" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>Gender</th>
        <th>BirthDate</th>
        <th>registrDate</th>
    </tr>
<#list listUser as user>
    <tr>
        <td width="20px">${user.id}</td>
        <td width="100px">${user.login}</td>
        <td width=100px">${user.name}</td>
        <td width="100px">${user.surname}</td>
        <td width="80px">${user.role}</td>
        <td width="60px">${user.gender}</td>
        <td width="70px">${user.birthDate}</td>
        <td width="70px">${user.registrDate}</td>
           <td width="60px">
               <form class="changePageForm" method="post" action="/message/send_mes">
                   <input type="hidden" name="command" value="user_message">
                   <input type="hidden" name="recipient" value="${user.login}">
                   <input type="submit"  value="message" class="uploadButtonTable"/>
               </form>
           </td>
        </tr>
</#list>
</table>

<br/>
<h4 class="h3login"><a class="registration" href='/back'>
        <@spring.message "label.backmain" /></a>
</h4>

</div>
</body>
</html>