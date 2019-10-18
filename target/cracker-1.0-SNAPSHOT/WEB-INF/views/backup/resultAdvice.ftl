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
<h1>Advice list</h1>
<table border="1" width="560px" class="userTable" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th>Id</th>
        <th>Text</th>
    </tr>
<#list listAdvice as advice>
    <tr>
        <td width="20px">${advice.id}</td>
        <td width="100px">${advice.text}</td>
           <td class="prod3" width="60px">
           <form class="changePageForm" method="post" action="/advice/delete">
               <input type="hidden" name="id" value="${advice.id}">
               <input type="hidden" name="currentPage" value="resultAdvice">
               <input type="submit"  value="delete" class="uploadButtonTable"/>
           </form>
           </td>
    </tr>
</#list>
</table>
    <h3>${addmessagedelete}</h3>
<br/>
<h4 class="h3login"><a class="registration" href='/back'>
        <@spring.message "label.backmain" /></a>
</h4>
</div>
</body>
</html>
