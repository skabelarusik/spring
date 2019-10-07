<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<head>
    <style type="text/css">
        <#include "/css/style.css">
    </style>
    <meta charset="UTF-8">
    <#setting classic_compatible=true>
    <title><@spring.message "title.edit"/></title>
</head>

<body>
<header class="headLogin"></header>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">CrackerTracker<small class="headH2small"> - your best helper!</small></h2>
</div>
<div class="wrapperLogin">
    <div class="blockRegist">
        <h3 class="h3login"><@spring.message "label.editing"/> </h3><br/><br/>
       <!-- update entity -->

        <form class="loginForm" method="post" action="/user/update">
            <p><label for="nameField" class="formText"><@spring.message "label.name"/></label>
                <input autofocus id="nameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                       name="username" value="${user.name}" placeholder="<@spring.message "placeholder.name"/></p></br>
            <p><label for="surnameField" class="formText"><@spring.message "label.surname" /></label>
                <input autofocus id="surnameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                       name="usersurname" value="${user.surname}"
                       placeholder="<@spring.message "placeholder.surname"/></p></br>
            <p><label for="emailField" class="formText"><@spring.message "label.email"/></label>
                <input autofocus id="emailField" class="formInput" type="email"
                       name="email" maxlength="45" value="${user.email}"
                       placeholder="<@spring.message "placeholder.surname" /></p></br>
            <p><label for="birthdayField" class="formText">Введите дату рождения *</label>
                <input type="date" class="formInput" name="birthday" min="1920-01-01" value="${user.birthday}" id="birthdayField"/>
                <br/><br/>
            </p>
            ${wrongData}
            <input type="submit" value="update" class="login-button"/>
            <br/><br/>
        </form>

        <form class="loginForm" action="/user/update_pass" method="post">
            <p>
                <label for="oldpasswordField" class="formText"> <@spring.message "label.passwordold" /></label>
                <input type="password" name="oldpassword" class="formInput" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="oldpasswordField"><br/><br/></p>
            <p>
            <label for="newPasswordField" class="formText"> <@spring.message "label.passwordnew" /></label>
                <input type="password" name="newpassword" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="newPasswordField" class="formInput"><br/><br/></p>
            <p>
            <label for="newPasswordField2" class="formText"><@spring.message "label.trypassword" /></label>
                <input type="password" name="newpasswordCheck" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="newPasswordField2" class="formInput"><br/><br/></p>
                <input type="submit" class="login-button" value="update"><br/>
            </label>
        </form>
            ${wrongDataPass}
        <!-- back -->
    </div><br/>
        <a class="registration" href='/back'><h4 class="h3login">
           <@spring.message "label.back" /></h4></a>
    <br/>
</div>
<!-- block buttom -->
<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/lang/en">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang/ru">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang/by">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
    <a class="extra" href="#" ><@spring.message "label.service"/> </a>
    <a class="extra" href="#" ><@spring.message "label.feedback" /> </a>
    <input type="submit" class="lan3" value="<@spring.message "label.vk" />" />
    <input type="submit" class="lan3" value="<@spring.message "label.facebook" />">
</div>
</body>
</html>