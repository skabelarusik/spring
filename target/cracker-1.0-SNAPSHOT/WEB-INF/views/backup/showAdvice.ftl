<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <title>SHOW ADVICE</title>
</head>
<body>
<h1>Users list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Text</th>
    </tr>
<#list advices as advice>
    <tr>
        <td><a href="/advice/${advice.id}">${advice.id}</a></td>
        <td>${advice.text}</td>
    </tr>
</#list>
</table>

<a href="/addUser">Create user</a>
</body>
</html>