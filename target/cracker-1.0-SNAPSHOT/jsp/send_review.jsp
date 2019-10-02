
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
<script type="text/javascript">
    <c:if test="${mess == 2}">
    alert("Congratulations, data added!");
    </c:if>
    <c:if test="${mess == 4}">
    alert("Wrong, please check it");
    </c:if>
</script>
<header class="headLogin">
</header>
<div class="headName">
    <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
        <small class="headH2small">&nbsp; - your best helper!</small></h2>
</div>
    <div class="wrapperReview">
        <h4 class="h3login"><fmt:message key="label.back" bundle="${rb}"/>
            <a class="registration" href='<c:out value="${currentPage}"/>'>
                <fmt:message key="label.back" bundle="${rb}"/></a>
        </h4>
        <div class="blockMessage">
            <form class="loginForm" method="post" action="/controller_admin">
                <input type="hidden" name="command" value="send_review">
                <h4><fmt:message key="label.sendmessage" bundle="${rb}"/></h4>
                <p>
                    <label for="textField" class="formText3"><fmt:message key="label.text" bundle="${rb}"/> </label>
                    <textarea autofocus id="textField" class="formInput3" type="text"
                              placeholder="<fmt:message key="input.text" bundle="${rb}"/>"
                              data-required="true" name="text" value="" maxlength="500"></textarea>
                </p>
                <br/>
                <input type="submit" value="<fmt:message key="button.send" bundle="${rb}"/>" class="login-button"/>
                ${messageReview}
            </form>
        </div>
        <h4 class="h3login">
            <a class="extra" href="/controller?command=load_review" >
                <fmt:message key="label.showallreview" bundle="${rb}" /> </a>
        </h4>

    <c:forEach items="${listReview}" var="review">
        <div class="review">
            <h4>${review.name}</h4>
            <h6>${review.date}</h6>
            ${review.text}
        </div>
    </c:forEach>
    </div>
</body>
</html>
