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
<h1>Review list</h1>
<table border="1" width="560px" class="userTable" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Date</th>
        <th>Text</th>
    </tr>
<#list reviewList as review>
    <tr>
        <td width="20px">${review.id}</td>
        <td width="100px">${review.name}</td>
        <td width=100px">${review.date}</td>
        <td width="100px">${review.text}</td>
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
