<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.register"/></title>
<#include "/WEB-INF/views/static/template.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header.ftl">
<div class="wrapperLogin">
                 <div class="wrapperRegInner">
                       <div class="blockDataReg">
                                 <h3 class="h3login"><@spring.message "label.registr"/> </h3><br/>
                                            <form class="loginForm" method="post" action="/user/registration">

                                                <!-- login -->
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="loginReg" name="login"
                                                           value="" placeholder="LOGIN" pattern="[(\w)-]{3,16}" required="true" />
                                                    <label for="loginReg" class="formText"><@spring.message "label.login"/> * </label>
                                                </div>
                                                </p><br/>

                                                <!-- password -->
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="passwordReg" type="password"
                                                           name="password" value="" placeholder="PASSWORD"
                                                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}"  />
                                                    <label for="passwordReg" class="formText"><@spring.message "label.password"/> * </label>
                                                </div>
                                                </p><br/>

                                                    <!-- name, surname -->
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="nameReg" type="text"
                                                           name="username" value="" placeholder="NAME"
                                                           pattern="[A-Za-zА-Яа-яЁё]{2,35}"  />
                                                    <label for="nameReg" class="formText"><@spring.message "label.name"/> </label>
                                                </div>
                                                </p><br/>
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="surnameReg" type="text"
                                                           name="usersurname" value="" placeholder="NAME"
                                                           pattern="[A-Za-zА-Яа-яЁё]{2,35}"  />
                                                    <label for="surnameReg" class="formText"><@spring.message "label.surname"/> </label>
                                                </div>
                                                </p><br/>

                                                <!-- email -->
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="email" class="uui-form-element"  id="emailReg" type="text"
                                                           name="email" value="" />
                                                    <label for="emailReg" class="formText"><@spring.message "label.email"/> *</label>
                                                </div>
                                                </p><br/>

                                                <!-- birthday -->
                                                <p>
                                                <div class="uui-input-group input-login small default">
                                                    <input type="date" class="uui-form-element"  id="dateReg" type="text"
                                                           name="birthday" min="1920-01-01" value="1970-01-01" />
                                                    <label for="dateReg" class="formText"><@spring.message "label.birthday"/> *</label>
                                                </div>
                                                </p><br/>

                                <!-- gender -->
                                                <h6>ПОЛ *</h6>
                                                <div class="uui-input-group vertical">
                                                    <p class="uui-radio">
                                                        <input type="radio" name="gender" value="Male" id="a1" checked />
                                                        <label for="a1"><@spring.message "label.sexmale"/></label>
                                                        <input type="radio" name="gender" value="Female" id="a2" />
                                                        <label for="a2"><@spring.message "label.sexfemale"/></label>
                                                    </p>
                                                </div>
                                                <br/>
                                                <button class="uui-button"><@spring.message "label.registr"/></button>
                                            </form>
                                ${wrongDataPass}
                        </div>
                 </div>
<!-- registration block -->
<div class="blockReg">
                                     <a href="/">
                                    <h4>
                                        <@spring.message "label.backmain"/>
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
        <button class="uui-button small" name="langv" value="US">EN</button>
    </form>
</div>
</div>
</div>
</div>
<#include "static/footer_main.ftl">
</body>
</html>



