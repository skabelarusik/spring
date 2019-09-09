<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2019
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8" %>
<html>
<title>Error Page</title>
<body>
<br/>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
<br/>
Message from exception: ${pageContext.exception.message}
<br/>
${error}
<a href="${currentPage}">
    Back
</a>
</body></html>