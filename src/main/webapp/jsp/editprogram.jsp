
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

        <form class="loginForm" method="post" action="/controller_curator">
            <input type="hidden" name="command" value="update_program">
            <input type="hidden" name="id" value="${id}">
             <p><label for="name" class="formText"><fmt:message key="label.progname" bundle="${rb}"/></label>
                <input autofocus id="name" class="formInput" type="text" minlength="2" maxlength="40"
                       name="name" value="${name}"/></p></br>
            <p><label for="duration" class="formText"><fmt:message key="label.durprog" bundle="${rb}"/></label>
                <input autofocus id="duration" class="formInput" type="number"
                       name="duration" maxlength="45" value="${duration}"/></p></br>
            <p><label for="cost" class="formText"><fmt:message key="label.costprog" bundle="${rb}"/></label>
                <input type="text" class="formInput" name="cost" min="1920-01-01" value="${cost}" id="cost"/>
                <br/><br/>
            </p>
            ${mess}
            <input type="submit" value="update" class="login-button"/>
            <br/><br/>
        </form>

        <!-- back -->
    </div><br/>
    <a href="${currentPage}"/><h4 class="h3login">
        <fmt:message key="label.backmain" bundle="${rb}"/></h4></a>
    <br/>
</div>
</body>
</html>
