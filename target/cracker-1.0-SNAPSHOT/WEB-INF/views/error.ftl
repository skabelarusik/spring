<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "label.error"/></title>
    <#include "/WEB-INF/views/static/template_main.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header.ftl">
<div class="wrapperLogin">
    <div class="wrapperLoginInner">
        <div class="blockLogin">
            <h3 class="h3login2"><@spring.message "label.error"/></h3><br/><br/>
            <h4 class="h3login2"><@spring.message "message.error"/></h4>
        </div>
        <!-- registration block -->
        <div class="blockReg">
            <a class="aTagCrackerLogin" href="/back">
                <h5 class="styleMessageTitle"><@spring.message "label.backmain"/></h5></a>
            <h6 class="styleMessageTitle"><@spring.message "label.backlogin"/>
                <a class="registration" href='/'>
                    <@spring.message "label.back"/></a>
            </h6>
        </div>


        <div class="languageLogin">
            <div class="uui-input-group horizontal">

                <form class="language" method="post" action="/lang">
                    <input type="hidden" name="currentPage" value="error">
                    <button class="uui-button small" name="langv" value="BY">BY</button>
                </form>
                <form class="language" method="post" action="/lang">
                    <input type="hidden" name="currentPage" value="error">
                    <button class="uui-button small" name="langv" value="RU">RU</button>
                </form>
                <form class="language" method="post" action="/lang">
                    <input type="hidden" name="currentPage" value="error">
                    <button class="uui-button small" name="langv" value="EN">EN</button>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "static/footer_main.ftl">
</body>
</html>
