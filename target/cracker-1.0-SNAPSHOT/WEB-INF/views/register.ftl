<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.register"/></title>
<#include "template_main.ftl">
</head>

<body>
<#include "header.ftl">
<div class="wrapperLogin">
                 <div class="wrapperLoginInner">
                       <div class="blockLogin">
                                 <h3 class="h3login">РЕГИСТРАЦИЯ </h3><br/><br/>
                                            <form class="loginForm" method="post" action="/user/registration">
                                                <p>
                                <!-- login -->
                                <label for="loginField" class="formText">Введите логин * </label>
                                <input autofocus id="loginField" class="formInput" type="text" name="login"
                                                       value="" placeholder="LOGIN" pattern="[(\w)-]{3,16}" required="true"/>
                                                                       </p></br>
                                <p>
                                <!-- password -->
                                <label for="passwordField" class="formText">Введите пароль *</label>
                                <input autofocus id="passwordField" class="formInput" type="password" name="password"
                                                       value="" placeholder="PASSWORD"
                                                       pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}" />
                                                <br/>
                                                    <!-- name, surname -->
                                <p><label for="nameField" class="formText">Введите имя</label>
                                <input autofocus id="nameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                                                       name="username"  value="" placeholder="NAME"/></p></br>

                                <p><label for="surnameField" class="formText">ФАМИЛИЯ</label>
                                <input autofocus id="surnameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                                                       name="usersurname"  value="" placeholder="surname"/>"/>
                                                </p></br>
                                <p>
                                <!-- email -->
                                <label for="emailField" class="formText">ЕМЭЙЛ *</label>
                                <input autofocus id="emailField" class="formInput" value="" type="email" name="email"/>
                                                </p><br/>
                                                <!-- birthday -->
                                <p><label for="birthdayField" class="formText">Введите дату рождения *</label>
                                <input type="date" class="formInputDate" name="birthday" min="1920-01-01" value="1970-01-01" id="birthdayField"/>
                                                    <br/><br/>
                                                </p>
                                <!-- gender -->
                                <p class="formText">
                                                    ПОЛ *<br/>
                                                <label for="sex1" class="gender"> MALE</label>
                                <input autofocus id="sex1" type="radio" name="gender"  value="Male" checked>
                                                <label for="sex2" class="gender"> FEMALE</label>
                                <input autofocus id="sex2" type="radio" name="gender" value="Female"></p></br>
                                <br/><br/>
                                                <input type="submit" value="registr" class="login-button"/>
                                                <br/><br/>
                                                <br/><br/>
                                            </form>
                                ${wrongData}
                        </div>
                 </div>
<!-- registration block -->
<div class="blockReg">
                                     <a href="/">
                                    <h4>
                                         Назад в логин
                                    </h4></a>
</div>


<div class="languageLogin">
                            <div class="uui-input-group horizontal">

                                 <form class="language" method="post" action="/lang">
                                    <input type="hidden" name="currentPage" value="register">
                                    <button class="uui-button small" name="langv" value="BY">BY</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="register">
                                                                <button class="uui-button small" name="langv" value="RU">RU</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="register">
                                                                <button class="uui-button small" name="langv" value="EN">EN</button>
</form>
</div>
</div>
</div>
</div>
<#include "footer.ftl">
</body>
</html>



