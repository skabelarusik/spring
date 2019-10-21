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
                            <h3 class="h3login"><@spring.message "label.editing"/> </h3><br/><br/>
                                           <form class="loginForm" method="post" action="/user/update">

                                                <!-- name -->
                        <p>
                            <div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="nameField" name="username"
                                                           value="${user.surname}" placeholder="<@spring.message "placeholder.name"/>" pattern="[A-Za-zА-Яа-яЁё]{2,35}"/>
                                                    <label for="nameField" class="formText"><@spring.message "label.name"/>  </label>
                            </div>
                        </p><br/>

                                                <!-- surname -->
                        <p>
                            <div class="uui-input-group input-login small default">
                                                                            <input type="text" class="uui-form-element"  id="surnameField" name="usersurname"
                                                                                   value="${user.name}" placeholder="<@spring.message "placeholder.surname"/>" pattern="[A-Za-zА-Яа-яЁё]{2,35}"/>
                                                                            <label for="surnameField" class="formText"><@spring.message "label.surname"/>  </label>
                            </div>
                        </p><br/>

                                                <!-- email -->
                        <p>
                            <div class="uui-input-group input-login small default">
                                                    <input type="email" class="uui-form-element"  id="emailField" type="text"
                                                           name="email" placeholder="<@spring.message "placeholder.surname" />" value="${user.email}" />
                                                    <label for="emailField" class="formText"><@spring.message "label.email"/> *</label>
                            </div>
                        </p><br/>

                                                <!-- birthday -->
                        <p>
                        <div class="uui-input-group input-login small default">
                                                                            <input type="date" class="uui-form-element"  id="birthdayField" type="text"
                                                                                   name="birthday" min="1920-01-01" value="${user.birthday}" />
                                                                            <label for="birthdayField" class="formText"><@spring.message "label.birthday"/> *</label>
                        </div>
                        </p><br/>
                        <br/>
                                                     <button class="uui-button"><@spring.message "label.update"/></button>
                        </form>
                        ${wrongData}
                        </div>

                <div class="blockDataReg">
                            <h3 class="h3login"><@spring.message "label.editingPass"/> </h3><br/><br/>
                                    <form class="loginForm" action="/user/update_pass" method="post">
                                                <!-- old pass -->
<p>
<div class="uui-input-group input-login small default">
                                                    <input type="password" class="uui-form-element"  id="oldpasswordField" name="oldpassword"
                                                           value="" placeholder="" pattern="[A-Za-z0-9!@#$%^&*()_+={};:>.,/?`~±§-]{8,20}" required="true" />
                                                    <label for="oldpasswordField" class="formText"><@spring.message "label.passwordold"/>  </label>
</div>
</p><br/>

                                                <!-- new pass -->
<p>
<div class="uui-input-group input-login small default">
                                                                            <input type="password" class="uui-form-element"  id="newPasswordField" name="newpassword"
                                                                                   value="" placeholder="" pattern="[A-Za-z0-9!@#$%^&*()_+={};:>.,/?`~±§-]{8,20}" required="true" />
                                                                            <label for="newPasswordField" class="formText"><@spring.message "label.passwordnew" />  </label>
</div>
</p><br/>

                                                <!-- new pass 2 -->
<p>
<div class="uui-input-group input-login small default">
                                                                            <input type="password" class="uui-form-element"  id="newPasswordField2" name="newpasswordCheck"
                                                                                   value="" placeholder="" pattern="[A-Za-z0-9!@#$%^&*()_+={};:>.,/?`~±§-]{8,20}" required="true" />
                                                                            <label for="newPasswordField2" class="formText"><@spring.message "label.trypassword" />  </label>
</div>
</p><br/>


                             <button class="uui-button"><@spring.message "label.update"/></button>
</form>
${wrongDataPass}
</div>
</div>
<!-- registration block -->
<div class="blockReg">
                                     <a href="/back">
                                    <h4>
<@spring.message "label.backmain"/>
</h4></a>
</div>
<br/><br/><br/><br/>

<div class="languageLogin">
<div class="uui-input-group horizontal">
    <form class="language" method="post" action="/lang">
        <input type="hidden" name="currentPage" value="editUser">
        <button class="uui-button small" name="langv" value="BY">BY</button>
</form>
<form class="language" method="post" action="/lang">
          <input type="hidden" name="currentPage" value="editUser">
           <button class="uui-button small" name="langv" value="RU">RU</button>
</form>
<form class="language" method="post" action="/lang">
        <input type="hidden" name="currentPage" value="editUser">
        <button class="uui-button small" name="langv" value="US">EN</button>
</form>
</div>
</div>
</div>
</div>
<#include "static/footer_main.ftl">
</body>
</html>


