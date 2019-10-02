
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message" var="rb"  />
<html>
<body>
<div class="calcBlock">
    <form id="calculator" class="adminBlock" action="/calc" method="post">
        <input type="hidden" name="command" value="calculate">
        <p>
            <h4 class="adminBlock"><fmt:message key="title.indexweight" bundle="${rb}"/></h4>
            <label for="weight" class="adminBlock"><fmt:message key="label.inputweight" bundle="${rb}"/></label><input name="weight" class="whiteBlock" id="weight"><br/>
            <label for="height" class="adminBlock"><fmt:message key="label.inputheight" bundle="${rb}"/></label> <input name="height" class="whiteBlock" id="height" ><br/>
            <br/><input type="submit" class="login-button" value="<fmt:message key="button.calculate" bundle="${rb}"/>">
            Result: ${result}
        </p>
    </form>
    </div>
</body>
</html>