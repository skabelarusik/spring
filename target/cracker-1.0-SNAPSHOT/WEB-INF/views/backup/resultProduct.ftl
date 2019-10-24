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
<h1>Product list</h1>
        <form class="changePageForm" method="post" action="/product/select">
             <input type="hidden" name="currentPage" value=${currPage}>
            <input type="submit" class="lan3" name="nextPage" value="${previousPage}">
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <input type="submit" class="lan3" name="nextPage" value="${nextPage}">
            <input type="radio" class="lan3" name="type" value="INCREASE_CALORIES"><small>INCR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_CALORIES"><small>DECR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_NAME"><small>INCR(NAME)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_NAME"><small>DECR(NAME)</small>
        </form>
<table border="1" width="60%" class="userTable" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Calories</th>
        <th>Proteins</th>
        <th>Fats</th>
        <th>Carbs</th>
    </tr>
<#list listProducts as product>
    <tr>
        <td width="20px">${product.id}</td>
        <td width="100px">${product.name}</td>
        <td width=100px">${product.calories}</td>
        <td width="100px">${product.proteins}</td>
        <td width="80px">${product.fats}</td>
        <td width="60px">${product.carbs}</td>
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