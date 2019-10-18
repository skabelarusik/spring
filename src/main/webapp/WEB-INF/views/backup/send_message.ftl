<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
    <head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title><@spring.message "label.sendmessage"/></title>
        <#setting classic_compatible=true>
    </head>
    <body>
    <header class="headLogin">
    </header>
    <div class="headName">
        <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
            <small class="headH2small">&nbsp; - your best helper!</small></h2>
    </div>
    <div class="wrapperReview">
        <h4 class="h3login"><a class="smallField"
                               href='/back'><@spring.message "label.backmain"/></a>
        </h4>
        <br/>
        <div class="review">
            <form class="messageForm" method="post" action="/message/send">
                <h4><@spring.message "label.sendmessage"/></h4>
            <p>
            <label for="loginField" class="formText3"><@spring.message "label.login"/> </label>
            <input type="text" name="recipient" maxlength="16" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
                   data-required="true" class="formInput4" value="${recipient}" placeholder="<@spring.message "input.login"/>">
            </p><br/>
            <p>
                <label for="topikField" class="formText3"><@spring.message "label.topik" /> </label>
                <input autofocus id="topikField" class="formInput4" type="text" placeholder="<@spring.message "input.topik" />"
                       data-required="true" name="topic" value="" maxlength="40">
            </p><br/>
            <p>
                <label for="textField" class="formText3"><@spring.message "label.text" /> </label>
                <textarea autofocus id="textField" class="formInput3" type="text"
                          placeholder="<@spring.message "input.text" />"
                          data-required="true" name="text" value="" maxlength="500"></textarea>
            </p>
            <br/>
            <input type="submit" value="<@spring.message "button.send" />" class="login-button"/>
                ${wrongData}
            </form>
        </div>
        </body>
        </html>
