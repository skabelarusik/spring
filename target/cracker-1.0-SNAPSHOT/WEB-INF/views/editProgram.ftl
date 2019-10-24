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
                                           <form class="loginForm" method="post" action="/program_name/update">
                                                       <input type="hidden" name="id" value="${id}">
                                                <!-- name program -->
<p>
<div class="uui-input-group input-login small default">
                                                    <input type="text" class="uui-form-element"  id="nameField" name="name" minlength="2" maxlength="40"
                                                           value="${name}"/>
                                                    <label for="nameField" class="formText"><@spring.message "label.progname"/>  </label>
</div>
</p><br/>
                                                <!-- duration -->
<p>
<div class="uui-input-group input-login small default">
                                                                            <input type="number" class="uui-form-element"  id="surnameField" name="duration"
                                                                                   value="${duration}"/>
                                                                            <label for="surnameField" class="formText"><@spring.message "label.durprog"/>  </label>
</div>
</p><br/>

                                                <!-- cost -->
<p>
<div class="uui-input-group input-login small default">
                                                    <input type="number" class="uui-form-element"  id="emailField" type="text"
                                                           name="cost" value="${cost}" />
                                                    <label for="emailField" class="formText"><@spring.message "label.costprog"/></label>
</div>
</p><br/>

                        <br/>
                                                     <button class="uui-button">UPDATE!</button>
</form>
${wrongData}
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


