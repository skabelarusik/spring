<!DOCTYPE html>
<head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title>LOGIN PAGE</title>
        <#setting classic_compatible=true>
</head>
<body>

<header class="headLogin">
</header>
<div class="headName">
    <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
        <small class="headH2small">&nbsp; - your best helper!</small></h2>
</div>
<div class="wrapperReview">
    <c:url value="/jsp/login.jsp" var="login" />
    <h4 class="h3login"><fmt:message key="label.backlogin" bundle="${rb}"/>
        <a class="linkreg" href='<c:out value="${login}"/>'><fmt:message key="label.back" bundle="${rb}"/></a>
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
