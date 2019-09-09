<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.07.2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />


<html>
<head>
    <title><fmt:message key="title.registr" bundle="${rb}"/></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">


</head>
<body>
<header class="headLogin"></header>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">CrackerTracker<small class="headH2small"> - your best helper!</small></h2>
</div>
<div class="wrapperLogin">
    <div class="blockRegist">
        <h3 class="h3login"><fmt:message key="label.registr" bundle="${rb}"/> </h3><br/><br/>
        <form class="loginForm" method="post" action="/register">
            <input type="hidden" name="command" value="registration">
            <p>
                <!-- login -->
            <label for="loginField" class="formText"><fmt:message key="label.login" bundle="${rb}"/> * </label>
            <input autofocus id="loginField" class="formInput" type="text" name="login"
                   value="" placeholder="<fmt:message key="placeholder.login" bundle="${rb}"/>"
                   pattern="[(\w)-]{3,16}" required="true">
            <br/><small class="smallField"><fmt:message key="input.logininfo" bundle="${rb}"/> </small>
            </p></br>
            <p>
                <!-- password -->
            <label for="passwordField" class="formText"><fmt:message key="label.password" bundle="${rb}"/> *</label>
            <input autofocus id="passwordField" class="formInput" type="password" name="password"
                   value="" placeholder="<fmt:message key="placeholder.password" bundle="${rb}"/>"
                   pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}">
                <br/><small class="smallField"><fmt:message key="input.passinfo" bundle="${rb}"/></small></p>
            <br/>
                <!-- name, surname -->
            <p><label for="nameField" class="formText"><fmt:message key="label.name" bundle="${rb}"/></label>
            <input autofocus id="nameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                   name="username"  value="" placeholder="<fmt:message key="placeholder.name" bundle="${rb}"/>"/></p></br>

            <p><label for="surnameField" class="formText"><fmt:message key="label.surname" bundle="${rb}"/></label>
            <input autofocus id="surnameField" class="formInput" type="text" pattern= "[A-Za-zА-Яа-яЁё]{2,35}"
                   name="usersurname"  value="" placeholder="<fmt:message key="placeholder.surname" bundle="${rb}"/>"/>
            </p></br>
            <p>
                <!-- email -->
                <label for="emailField" class="formText"><fmt:message key="label.email" bundle="${rb}"/> *</label>
                <input autofocus id="emailField" class="formInput" value="" type="email" name="email"/>
            </p><br/>
            <!-- birthday -->
            <p><label for="birthdayField" class="formText">Введите дату рождения *</label>
                <input type="date" class="formInputDate" name="birthday" min="1920-01-01" value="1970-01-01" id="birthdayField"/>
                <br/><br/>
            </p>
            <!-- gender -->
            <p class="formText">
                <fmt:message key="label.gender" bundle="${rb}"/> *<br/>
            <label for="sex1" class="gender"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
            <input autofocus id="sex1" type="radio" name="gender"  value="Male" checked>
            <label for="sex2" class="gender"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
            <input autofocus id="sex2" type="radio" name="gender" value="Female"></p></br>
            ${errorLoginPassMessage}
            <br/><br/>
            <input type="submit" value="registr" class="login-button"/>
            <br/><br/>
            <small><fmt:message key="label.required" bundle="${rb}"/></small>
            <br/><br/>
        </form>
    </div>
</div>
<!-- back to login -->
<div class="blockReg">
    <c:url value="/jsp/login.jsp" var="login" />
    <h4 class="h3login"><fmt:message key="label.regquest" bundle="${rb}"/>
        <a class="linkreg" href='<c:out value="${login}"/>'><fmt:message key="title.login" bundle="${rb}"/></a>
    </h4>
</div>
<!-- block footer -->
<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/register">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/register">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/register">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
    <a class="extra" href="/jsp/about_service.jsp" ><fmt:message key="label.service" bundle="${rb}"/> </a>
    <a class="extra" href="/controller?command=show_review" ><fmt:message key="label.feedback" bundle="${rb}"/> </a>
    <input type="hidden" name="command" value="language">
    <input type="submit" class="lan3" name="langv" value="<fmt:message key="label.vk" bundle="${rb}"/>" />
    </form>
    <input type="hidden" name="command" value="language">
    <input type="submit" class="lan3" name="langv" value="<fmt:message key="label.facebook" bundle="${rb}"/>">
    </form>
</div>

</body>
</html>
