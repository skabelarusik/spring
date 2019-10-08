<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
    <head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title><@spring.message "title.service"/></title>
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
        <h3 class="h3login"><@spring.message "text.aboutservice"/></h3><br/><br/>
    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <c:url value="/jsp/register.jsp" var="reg" />
    <h4 class="h3login"><@spring.message "label.backlogin"/>
        <a class="registration" href='/'>
            <@spring.message "label.back"/></a>
    </h4>
</div>

<div class="languageLogin">
    <form class="language" method="get" action="/lang/en">
        <input type="hidden" name="currentPage" value="about">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" method="get" action="/lang/ru">
        <input type="hidden" name="currentPage" value="about">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" method="get" action="/lang/by">
        <input type="hidden" name="currentPage" value="about">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
</div>
</body>
</html>