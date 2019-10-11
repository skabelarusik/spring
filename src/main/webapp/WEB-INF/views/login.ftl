<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<head>
    <style type="text/css">
        <#include "css/style.css">
    </style>
    <title><@spring.message "title.login"/></title>
</head>
<body>
<h1>LOGIN</h1>
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form class="loginForm" method="post" action="/j_spring_security_check">
        <p>
            <label for="loginField" class="formText">LOGIN</label>
            <input type="text" name="j_username" maxlength="45" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
                   data-required="true" value=""  class="formInput" placeholder=""/>
            <br/><small class="smallField">INPUT LOGIN </small>
        </p>
        <br/><br/>
        <p>
            <label for="passwordField" class="formText">PASSWORD</label>
            <input autofocus id="passwordField" class="formInput" type="password" placeholder=""
                   data-required="true" name="j_password" value="" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}"/>
            <br/><small class="smallField">INPUT PASSWORD</small>
        </p><br/><br/>
        <input type="submit" value="INPUT" class="login-button"/>
        <br/>
    </form>

</body>
</html>
