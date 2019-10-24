<%@ page isErrorPage="true" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="message" var="rb" />
<html>
<title><fmt:message key="label.error" bundle="${rb}"/></title><body>
<br/>
Request from ${pageContext.errorData.requestURI} is failed
<br/>

<br/>
<h4 class="alert-heading"><fmt:message key="label.sorry" bundle="${rb}"/></h4>
<hr/>
<p><fmt:message key="message.error" bundle="${rb}"/></p>
<div><span class="code">  ${pageContext.errorData.statusCode}</span></div>
<div class="error_message">${pageContext.request.getAttribute("message")}</div><hr/>
<a href="${currentPage}">
    Back
</a>
</body></html>