
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />


<html>
<head>
    <title><fmt:message key="title.edit" bundle="${rb}"/> </title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<header class="headLogin"></header>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">CrackerTracker<small class="headH2small"> - your best helper!</small></h2>
</div>
<div class="wrapperLogin">
    <div class="blockRegist">
        <h3 class="h3login"><fmt:message key="label.editing" bundle="${rb}"/> </h3><br/><br/>
       <!-- update entity -->

        <form class="loginForm" method="post" action="/update">
            <input type="hidden" name="command" value="update_profile">
            <p><label for="nameField" class="formText"><fmt:message key="label.name" bundle="${rb}"/></label>
                <input autofocus id="nameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                       name="username" value="${username}" placeholder="<fmt:message key="placeholder.name"
                    bundle="${rb}"/>"/></p></br>
            <p><label for="surnameField" class="formText"><fmt:message key="label.surname" bundle="${rb}"/></label>
                <input autofocus id="surnameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                       name="usersurname" value="${usersurname}"
                       placeholder="<fmt:message key="placeholder.surname" bundle="${rb}"/>"/></p></br>
            <p><label for="emailField" class="formText"><fmt:message key="label.email" bundle="${rb}"/></label>
                <input autofocus id="emailField" class="formInput" type="email"
                       name="email" maxlength="45" value="${email}"
                       placeholder="<fmt:message key="placeholder.surname" bundle="${rb}"/>"/></p></br>
            <p><label for="birthdayField" class="formText">Введите дату рождения *</label>
                <input type="date" class="formInput" name="birthday" min="1920-01-01" value="${birthday}" id="birthdayField"/>
                <br/><br/>
            </p>
            ${errorLoginPassMessage}
            ${editmessage}
            ${nullPage}
            <input type="submit" value="update" class="login-button"/>
            <br/><br/>
        </form>

        <form class="loginForm" action="/update" method="post">
            <input type="hidden" name="command" value="change_pass">
            <p>
                <label for="oldpasswordField" class="formText"> <fmt:message key="label.passwordold" bundle="${rb}"/></label>
                <input type="password" name="oldpassword" class="formInput" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="oldpasswordField"><br/><br/></p>
            <p>
            <label for="newPasswordField" class="formText"> <fmt:message key="label.passwordnew" bundle="${rb}"/></label>
                <input type="password" name="newpassword" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="newPasswordField" class="formInput"><br/><br/></p>
            <p>
            <label for="newPasswordField2" class="formText"> <fmt:message key="label.trypassword" bundle="${rb}"/></label>
                <input type="password" name="newpasswordCheck" pattern="[A-Za-z0-9!@#$%^&*()_+={};:><.,/?`~±§-]{8,20}"
                       required="" id="newPasswordField2" class="formInput"><br/><br/></p>
                <input type="submit" class="login-button" value="update"><br/>
            </label>
        </form>

        <!-- back -->
    </div><br/>
        <a class="registration" href='<c:out value="${currentPage}"/>'><h4 class="h3login">
            <fmt:message key="label.back" bundle="${rb}"/></h4></a>
    <br/>
</div>
<!-- block buttom -->
<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/langedit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/langedit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/langedit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
    <a class="extra" href="#" ><fmt:message key="label.service" bundle="${rb}"/> </a>
    <a class="extra" href="#" ><fmt:message key="label.feedback" bundle="${rb}" /> </a>
    <input type="submit" class="lan3" value="<fmt:message key="label.vk" bundle="${rb}"/>" />
    <input type="submit" class="lan3" value="<fmt:message key="label.facebook" bundle="${rb}"/>">

</div>


</body>
</html>
