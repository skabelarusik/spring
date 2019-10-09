<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message "title.review"/></title>
    <#setting classic_compatible=true>
            <style type="text/css">
                <#include "/css/style.css">
            </style>
</head>
    <body>
    <header class="headLogin">
    </header>
    <div class="headName">
        <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
            <small class="headH2small">&nbsp; - your best helper!</small></h2>
    </div>
    <div class="wrapperReview">
            <div class="blockMessage">
                 <form class="loginForm" method="post" action="/review/send">
                 <h4><@spring.message "label.sendmessage" /></h4>
                 <p>
                     <label for="textField" class="formText3"><@spring.message "label.text"/></label>
                     <textarea autofocus id="textField" class="formInput3" type="text"
                         placeholder="<@spring.message "input.text"/>"
                         data-required="true" name="text" value="" maxlength="500"></textarea>
                  </p>
                     <br/>
                     <input type="submit" value="<@spring.message "button.send" />" class="login-button"/>
                     ${messageReview}
                 </form>
            <br/>
            <h4 class="h3login"><a class="registration" href='/back'>
                    <@spring.message "label.backmain" /></a>
            </h4>
            </div>
    </div>
    </body>
</html>
