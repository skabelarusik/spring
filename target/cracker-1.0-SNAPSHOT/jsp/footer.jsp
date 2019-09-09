<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.07.2019
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message" var="rb"  />
<html>
<body>
    <div id="wrapperFooter">
        <div id="footerAuthor">
            <fmt:message key="label.footer" bundle="${rb}"/>
        </div>
        <div id="footerReviews">
            <h4><a href="/jsp/send_review.jsp" class="adminBlock"><fmt:message key="label.sendreview"
                   bundle="${rb}"/></a></h4>
        </div>
    </div>
</body>
</html>
