<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<body>
<div class="calcBlock2">
    <form class="adminBlock" method="post" action="/controller_curator">
        <input type="hidden" name="command" value="add_product_bucket">
        <h4 class="adminBlock"><fmt:message key="label.addproduct" bundle="${rb}"/></h4>
        <p>
            <label for="nameProduct" class="formText2"><fmt:message key="label.name" bundle="${rb}"/></label>
            <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProduct"
                   data-required="true" value=""  class="formInput4" placeholder="Input name">
        </p>
        <p>
            <label for="count" class="formText2"><fmt:message key="label.name" bundle="${rb}"/></label>
            <input type="text" name="portions" maxlength="35" autofocus id="count"
                   data-required="true" value=""  class="formInput4" placeholder="Input portions">
        </p>
        <input type="submit" class="login-button" value="<fmt:message key="button.insert" bundle="${rb}"/>"/>

        <br/>
        ${messageProduct}
    </form>
    <form class="changePageForm" method="post" action="/clear_bucket">
        <input type="hidden" name="command" value="delete_product_bucket">
        <input type="submit"  value="<fmt:message key="button.clear" bundle="${rb}"/>" class="login-button"/>
    </form>
    <form id="calculator" class="adminBlock" action="/calc" method="post">
        <input type="hidden" name="command" value="calculate_calories">
        <p>
            <c:if test="${not empty listProductsBucket}">
        <table border="1" width="220" class="userTable" cellspacing="0" cellpadding="4">
            <td width="80" class="prod3">PRODUCT</td>
            <td width="30px" class="prod3">CAL.</td>
            <td width="30px" class="prod3">PORT.</td>
            <td width="60px" class="prod3">DELETE</td></table>
        </c:if>
            <c:forEach items="${listProductsBucket}" var="product">
        <table border="1" width="220px" class="userTable" cellspacing="0" cellpadding="4">
            <tr>
                <td width="80">${product.product}</td>
                <td width="30px">${product.calories}</td>
                <td width="30px">${product.portions}</td>
            <td width="60px" class="prod3">
                <form class="changePageForm" method="post" action="/send_message">
                    <input type="hidden" name="command" value="delete_product_id_bucket">
                    <input type="hidden" name="id" value="${product.id}">
                    <input type="submit"  value="<fmt:message key="button.delete" bundle="${rb}"/>" class="uploadButtonBucket"/>
                </form>
            </td>
            </tr>
        </table>
        </c:forEach>
        <br/>
        <form class="changePageForm" method="post" action="/clear_bucket">
            <input type="hidden" name="command" value="calculate_calories_bucket">
            <input type="submit" class="login-button" value="<fmt:message key="button.calculate" bundle="${rb}"/>">
        </form>

        Result: ${result}
        </p>
    </form>
</div>
</body>
</html>