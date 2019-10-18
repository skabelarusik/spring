<#import "/spring.ftl" as spring/>
<head>
<style type="text/css">
        <#include "/WEB-INF/views/css/style.css">
</style>
<title><@spring.message "title.login"/></title>
<#setting classic_compatible=true>
</head>
<body>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">CrackerTracker
        <small class="headH2small"> - your best helper!</small></h2>


<div class="wrapperLogin">


        <div class="blockLogin">
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

<h1><a href="/test">TEST</a></h1>



</div>
</div>
<!-- registration block -->
<div class="blockReg">
        <a href="/user/registration">
            <h4 class="h3login"><@spring.message "label.withoutacc"/></h4></a>
</div>
<!-- block buttom -->
</div>

<div class="languageLogin">
    <form class="language" method="post" action="/lang">
        <input type="hidden" name="currentPage" value="index">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
<form class="language" method="post" action="/lang">
        <input type="hidden" name="currentPage" value="index">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
<form class="language" method="post" action="/lang">
        <input type="hidden" name="currentPage" value="index">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
<a class="extra" href="/about" ><@spring.message "label.service"/></a>
<a class="extra" href="/review/show" ><@spring.message "label.feedback"/></a>
<input type="hidden" name="command" value="language">
    <input type="submit" class="lan3" name="langv" value="<@spring.message "label.vk"/>" />
    <input type="hidden" name="command" value="language">
    <input type="button" class="lan3" name="langv" value="<@spring.message "label.facebook"/>">

</body>
</html>

