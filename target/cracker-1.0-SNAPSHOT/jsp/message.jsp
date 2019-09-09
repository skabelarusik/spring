
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message key="title.review" bundle="${rb}"/></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<header class="headLogin">
</header>
<div class="headName">
    <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
        <small class="headH2small">&nbsp; - your best helper!</small></h2>
</div>
<div class="wrapperReview">
    <h4 class="h3login"><a class="smallField"
                           href='<c:out value="${currentPage}"/>'><fmt:message key="label.back" bundle="${rb}"/></a>
    </h4>
    <br/>
    <div class="review">
        <form class="messageForm" method="post" action="/send">
            <input type="hidden" name="command" value="send_message">
            <h4><fmt:message key="label.sendmessage" bundle="${rb}"/></h4>
        <p>
        <label for="loginField" class="formText3"><fmt:message key="label.login" bundle="${rb}"/> </label>
        <input type="text" name="login" maxlength="16" autofocus id="loginField" pattern="^[(\w)-]{3,16}"
               data-required="true" value="${recipient}"  class="formInput4" placeholder="<fmt:message key="input.login" bundle="${rb}"/>">
        </p><br/>
        <p>
            <label for="topikField" class="formText3"><fmt:message key="label.topik" bundle="${rb}"/> </label>
            <input autofocus id="topikField" class="formInput4" type="text" placeholder="<fmt:message key="input.topik" bundle="${rb}"/>"
                   data-required="true" name="topic" value="" maxlength="40">
        </p><br/>
        <p>
            <label for="textField" class="formText3"><fmt:message key="label.text" bundle="${rb}"/> </label>
            <textarea autofocus id="textField" class="formInput3" type="text"
                      placeholder="<fmt:message key="input.text" bundle="${rb}"/>"
                      data-required="true" name="text" value="" maxlength="500"></textarea>
        </p>
        <br/>
        <input type="submit" value="<fmt:message key="button.send" bundle="${rb}"/>" class="login-button"/>
            ${wrongData}
        </form>
    </div>

    <!-- input message -->
    <c:forEach items="${listMessageInput}" var="message">
    <form method="post" action="/message">
        <div class="review">
            <h5>${message.sender} | ${message.localDate}</h5>
            <h5>${message.topik}</h5>
            <h6>${message.text}</h6>
            <form class="loginForm" method="post" action="/controller">
                <input type="hidden" name="command" value="delete_message">
                <input type="hidden" name="type" value="input">
                <input type="hidden" name="id" value="${message.id}">
                <input type="submit"  value="delete" class="login-button"/>
            ${addmessagedelete}
            </form>
        </div>
    </form>
    </c:forEach>
    <c:if test="${not empty listMessageInput}">
        <form class="changePageForm" method="post" action="/message">
            <input type="hidden" name="command" value="input_message">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
        </form>
    </c:if>

    <!-- output message -->
    <c:forEach items="${listMessageOutput}" var="messageOut">
        <div class="review">
            <h5>${messageOut.recipient} | ${messageOut.localDate}</h5>
            <h5>${messageOut.topik}</h5>
            <h6>${messageOut.text}</h6>
            <form class="loginForm" method="post" action="/controller">
                <input type="hidden" name="command" value="delete_message">
                <input type="hidden" name="type" value="output">
                <input type="hidden" name="id" value="${messageOut.id}">
                <input type="submit"  value="delete" class="login-button"/>
                ${addmessagedelete}
            </form>
        </div>
    </c:forEach>

    <c:if test="${not empty listMessageOutput}">
        <form class="changePageForm" method="post" action="/message">
            <input type="hidden" name="command" value="output_message">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
        </form>
    </c:if>
</div>
</body>
</html>
