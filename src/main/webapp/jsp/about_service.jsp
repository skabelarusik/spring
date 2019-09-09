<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message key="title.service" bundle="${rb}"/> </title>
    <style>
        <%@include file="css/style.css"%>
    </style>
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
        <h3 class="h3login"><fmt:message key="text.aboutservice" bundle="${rb}"/> </h3><br/><br/>

    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <c:url value="/jsp/register.jsp" var="reg" />
    <h4 class="h3login"><fmt:message key="label.backlogin" bundle="${rb}"/>
        <a class="registration" href='<c:out value="${reg}"/>'>
            <fmt:message key="label.back" bundle="${rb}"/></a>
    </h4>
</div>

<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/lang_service">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang_service">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang_service">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
</div>
</body>
</html>