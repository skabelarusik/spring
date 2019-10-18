<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.about"/></title>
<#include "template_main.ftl">
</head>

<body>
<#include "header.ftl">
<div class="wrapperLogin">
                 <div class="wrapperLoginInner">
                       <div class="blockLogin">
                            <h3 class="h3login"><@spring.message "text.aboutservice"/></h3><br/><br/>
                        </div>
<!-- registration block -->
<div class="blockReg">
                             <a class="aTagCrackerLogin" href="/user/registration">
                             <h5 class="h3login"><@spring.message "label.withoutacc"/></h5></a>
                            <h6 class="h3login"><@spring.message "label.backlogin"/>
                            <a class="registration" href='/'>
                                        <@spring.message "label.back"/></a>
                            </h6>
</div>


<div class="languageLogin">
                            <div class="uui-input-group horizontal">

                                 <form class="language" method="post" action="/lang">
                                    <input type="hidden" name="currentPage" value="about">
                                    <button class="uui-button small" name="langv" value="BY">BY</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="about">
                                                                <button class="uui-button small" name="langv" value="RU">RU</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="about">
                                                                <button class="uui-button small" name="langv" value="EN">EN</button>
</form>
</div>
</div>
</div>
</div>
<#include "footer.ftl">
</body>
</html>
