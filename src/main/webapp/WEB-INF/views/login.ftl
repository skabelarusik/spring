<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.login"/></title>
<#include "template_main.ftl">
</head>

<body>
<#include "header.ftl">
<div class="wrapperLogin">
                 <div class="wrapperLoginInner">
                       <div class="blockLogin">
                       <!-- Login form -->

<br/>
            <!-- Login form -->
<h3 class="h3login"><@spring.message "label.enterlogin"/></h3><br/><br/>
            <form class="loginForm" method="post" action="/login">
                <p>
<label for="loginField" class="formText"><@spring.message "label.login"/></label>
<input type="text" name="username" maxlength="45" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
                           data-required="true" value=""  class="formInput" placeholder="<@spring.message "input.login"/>"/>
                    <br/><small class="smallField"><@spring.message "input.logininfo"/> </small>
</p>
<br/><br/>
                <p>
<label for="passwordField" class="formText"><@spring.message "label.password"/></label>
<input autofocus id="passwordField" class="formInput" type="password" placeholder="<@spring.message "input.password"/>"
                           data-required="true" name="password" value="" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}"/>
                    <br/><small class="smallField"><@spring.message "input.passinfo"/></small>
</p><br/><br/>
                <input type="submit" value="<@spring.message "button.login"/>" class="login-button"/>
                <br/>
                ${messageAuth}
<div class="formInput">
                </div>
<br/>
                <br/>
            </form>
</div>
</div>
<!-- registration block -->
<div class="blockReg">
                             <a class="aTagCrackerLogin" href="/user/registration">
                             <h5 class="h3login"><@spring.message "label.withoutacc"/></h5></a>
</div>


<div class="languageLogin">
                            <div class="uui-input-group horizontal">

                                 <form class="language" method="post" action="/lang">
                                    <input type="hidden" name="currentPage" value="login">
                                    <button class="uui-button small" name="langv" value="BY">BY</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="login">
                                                                <button class="uui-button small" name="langv" value="RU">RU</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="login">
                                                                <button class="uui-button small" name="langv" value="EN">EN</button>
</form>
</div>
</div>
</div>
</div>
<#include "footer.ftl">
</body>
</html>
