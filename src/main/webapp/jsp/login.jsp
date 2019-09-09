<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message key="title.login" bundle="${rb}"/> </title>
    <style>
        <%@include file="css/style.css"%>
    </style>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

</head>
<body>
<header class="headLogin">
</header>
<!-- top menu -->
<div class="headName">
    <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
        <small class="headH2small">&nbsp; - your best helper!</small></h2>
</div>

<div class="wrapperLogin">

    <div class="blockLogin">
        <!-- Login form -->
        <h3 class="h3login"><fmt:message key="label.enterlogin" bundle="${rb}"/> </h3><br/><br/>
        <form class="loginForm" method="post" action="/controller">
            <input type="hidden" name="command" value="login">
            <p>
            <label for="loginField" class="formText"><fmt:message key="label.login" bundle="${rb}"/> </label>
            <input type="text" name="login" maxlength="45" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
                   data-required="true" value=""  class="formInput" placeholder="<fmt:message key="input.login" bundle="${rb}"/>">
                <br/><small class="smallField"><fmt:message key="input.logininfo" bundle="${rb}"/> </small>
            </p>
                <br/><br/>
            <p>
            <label for="passwordField" class="formText"><fmt:message key="label.password" bundle="${rb}"/>  </label>
            <input autofocus id="passwordField" class="formInput" type="password" placeholder="<fmt:message key="input.password" bundle="${rb}"/>"
                   data-required="true" name="password" value="" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}" >
                <br/><small class="smallField"><fmt:message key="input.passinfo" bundle="${rb}"/></small>
            </p><br/><br/>
            <input type="submit" value="<fmt:message key="button.login" bundle="${rb}"/>" class="login-button"/>
            <div class="formInput">
                ${errorLoginPassMessage}
            </div>
            <br/>
            <br/>
        </form>
    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <c:url value="/jsp/register.jsp" var="reg" />
    <h4 class="h3login"><fmt:message key="label.withoutacc" bundle="${rb}"/>
        <a class="registration" href='<c:out value="${reg}"/>'>Register</a>
    </h4>
</div>
<!-- block buttom -->

</div>

<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/login">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/login">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/login">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
    <a class="extra" href="/jsp/about_service.jsp" ><fmt:message key="label.service" bundle="${rb}"/> </a>
    <a class="extra" href="/controller?command=show_review" ><fmt:message key="label.feedback" bundle="${rb}" /> </a>
    <input type="hidden" name="command" value="language">
    <input type="submit" class="lan3" name="langv" value="<fmt:message key="label.vk" bundle="${rb}"/>" />
    <input type="hidden" name="command" value="language">
    <input type="button" class="lan3" name="langv" value="<fmt:message key="label.facebook" bundle="${rb}"/>">
</div>
</body>
</html>