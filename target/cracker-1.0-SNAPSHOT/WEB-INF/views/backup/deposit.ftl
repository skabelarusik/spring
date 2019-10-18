<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<head>
    <style type="text/css">
        <#include "css/style.css">
    </style>
    <title><@spring.message "title.deposit"/></title>
    <#setting classic_compatible=true>
</head>
<body>
<header class="headLogin">
</header>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
        <small class="headH2small">&nbsp; - your best helper!</small></h2>
</div>

<div class="wrapperLogin">
    <div class="blockLogin">
        <!-- Login form -->
        <h3 class="h3login"><@spring.message "label.choose_dep"/> </h3><br/><br/>
        <form class="loginForm" method="post" action="/user/deposit">
            <p>
                <!-- sum -->
                <label for="loginField" class="formText"><@spring.message "label.sum"/> * </label>
                <input autofocus id="loginField" class="formInput" type="number" name="sum"
                       value="" placeholder="<@spring.message "placeholder.sum"/>"
                required="true">
            </p></br><br/>
            <!-- gender -->
            <p class="formText">
                <@spring.message "label.type_dep" /> *<br/><br/>
                <label for="type1" class="gender"> VISA/MasterCard</label>
                <input autofocus id="type1" type="radio" name="type"  value="visa" checked>
                <label for="type2" class="gender">WebMoney</label>
                <input autofocus id="type2" type="radio" name="type" value="webmoney"><br/><br/>
                <label for="type3" class="gender">Kiwi</label>
                <input autofocus id="type3" type="radio" name="type" value="kiwi">
                <label for="type4" class="gender">Skrill</label>
                <input autofocus id="type4" type="radio" name="type" value="skrill">
                <br/>
                <input type="submit" value="Pay" class="login-button"/>
            </p></br><br/>

            <div class="formInput">
                ${depositMessage}
            </div>
            <br/><br/>
        </form>
    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <h4 class="h3login"><@spring.message "label.backmain" />
        <a class="registration" href='/back'>
            <@spring.message "label.back" /></a>
    </h4>
</div>

<div class="languageLogin">
    <form class="language" name="lanEn" method="get" action="/lang/en">
        <input type="hidden" name="currentPage" value="deposit">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="get" action="/lang/ru">
    <input type="hidden" name="currentPage" value="deposit">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="get" action="/lang/by">
    <input type="hidden" name="currentPage" value="deposit">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
</div>
</body>
</html>
