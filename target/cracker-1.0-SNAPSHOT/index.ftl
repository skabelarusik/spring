<!DOCTYPE html>
    <head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title>LOGIN</title>
    </head>

<!-- top menu -->
<div class="headName">
    <h2 class="headH2">CrackerTracker
        <small class="headH2small"> - your best helper!</small></h2>
</div>

<div class="wrapperLogin">


    <div class="blockLogin">
        <!-- Login form -->
        <h3 class="h3login">привет!</h3><br/><br/>
        <form class="loginForm" method="post" action="/login">
            <p>
            <label for="loginField" class="formText">LOGIN</label>
            <input type="text" name="login" maxlength="45" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
                   data-required="true" value=""  class="formInput" placeholder="INPUT LOGIN"/>
            </p>
                <br/><br/>
            <p>
            <label for="passwordField" class="formText">PASSWORD  </label>
            <input autofocus id="passwordField" class="formInput" type="password" placeholder="password"
                   data-required="true" name="password" value="" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}"/>
            </p><br/><br/>
            <input type="submit" value="press" class="login-button"/>
            <div class="formInput">
            </div>
            <br/>
            <br/>
        </form>



    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <a href="registration">
    <h4 class="h3login">Register</h4></a>
    <
</div>
<!-- block buttom -->

</div>

<div class="languageLogin">
</div>
</body>
</html>

