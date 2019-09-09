<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message key="title.deposit" bundle="${rb}"/></title>
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
        <h3 class="h3login"><fmt:message key="label.choose_dep" bundle="${rb}"/> </h3><br/><br/>
        <form class="loginForm" method="post" action="/deposit">
            <input type="hidden" name="command" value="deposit">
            <p>
                <!-- sum -->
                <label for="loginField" class="formText"><fmt:message key="label.sum" bundle="${rb}"/> * </label>
                <input autofocus id="loginField" class="formInput" type="number" name="sum"
                       value="" placeholder="<fmt:message key="placeholder.sum" bundle="${rb}"/>"
                       required="true">
            </p></br><br/>
            <!-- gender -->
            <p class="formText">
                <fmt:message key="label.type_dep" bundle="${rb}"/> *<br/><br/>
                <label for="type1" class="gender"> VISA/MasterCard</label>
                <input autofocus id="type1" type="radio" name="type"  value="visa" checked>
                <label for="type2" class="gender">WebMoney</label>
                <input autofocus id="type2" type="radio" name="type" value="webmoney"><br/><br/>
                <label for="type3" class="gender">Kiwi</label>
                <input autofocus id="type3" type="radio" name="type" value="kiwi">
                <label for="type4" class="gender">Skrill</label>
                <input autofocus id="type4" type="radio" name="type" value="skrill">
                <br/>
                <input type="submit" value="Pay" class="login-button"/>
            </p></br><br/>

            <div class="formInput">
                ${depositMessage}
            </div>
            <br/><br/>
        </form>
    </div>
</div>
<!-- registration block -->
<div class="blockReg">
    <h4 class="h3login"><fmt:message key="label.backmain" bundle="${rb}"/>
        <a class="registration" href='<c:out value="${currentPage}"/>'>
            <fmt:message key="label.back" bundle="${rb}"/></a>
    </h4>
</div>

<div class="languageLogin">
    <form class="language" name="lanEn" method="post" action="/lang_deposit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan1" name="langv" value="EN">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang_deposit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan2" name="langv" value="RU">
    </form>
    <form class="language" name="lanEn" method="post" action="/lang_deposit">
        <input type="hidden" name="command" value="language">
        <input type="submit" class="lan3" name="langv" value="BY">
    </form>
</div>
</body>
</html>
