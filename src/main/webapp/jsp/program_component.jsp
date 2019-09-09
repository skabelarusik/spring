<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message key="title.result" bundle="${rb}"/></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head>
<body>
<div class="wrapResult">
    <script type="text/javascript">
        <c:if test="${mess == 1}">
        alert("Congratulations, the data has been updated!");
        </c:if>
        <c:if test="${mess == 2}">
        alert("Congratulations, data added!");
        </c:if>
        <c:if test="${mess == 3}">
        alert("Congratulations, data deleted!");
        </c:if>
    </script>

    <!-- add components to program -->
    <form class="adminBlockEditPr" method="post" action="/controller_curator">
        <input type="hidden" name="command" value="add_product_to_program">
        <input type="hidden" name="idProgram" value="${name}">
        <input type="hidden" name="programComponent" value="${programComponent}">
        <h4 class="adminBlockEditPr"><fmt:message key="label.addcomponentprog" bundle="${rb}"/>&nbsp;${name}</h4>
        <p>
            <input type="text" name="numProduct" maxlength="35" autofocus id="numProduct"
                   data-required="true" value=""  class="formInput4" placeholder="Input cost">
            <label for="numProduct" class="whiteBlock"><fmt:message key="label.product" bundle="${rb}"/></label>
        </p>
        <p>
            <input type="text" name="portions" maxlength="4" autofocus id="portions"
                   data-required="true" value=""  class="formInput4" placeholder="Input portions">
            <label for="portions" class="whiteBlock"><fmt:message key="label.durprog" bundle="${rb}"/></label>

        </p>
        <select name="day" class="whiteBlock">
            <option>MONDAY</option>
            <option>TUESDAY</option>
            <option>WEDNESDAY</option>
            <option>THURSDAY</option>
            <option>FRIDAY</option>
            <option>SATURDAY</option>
            <option>SUNDAY</option>
            <option>WEEKDAYS</option>
            <option>WEEKEND</option>
            <option>ALL</option>
        </select>
        <select name="time" class="whiteBlock">
            <option>BREAKFAST</option>
            <option>SECOND_BREAKFAST</option>
            <option>LUNCH</option>
            <option>SECOND_LUNCH</option>
            <option>DINNER</option>
            <option>SECOND_DINNER</option>
        </select>
        <input type="submit" class="login-button" value="<fmt:message key="button.insert" bundle="${rb}"/>"/><br/>
        ${messageInsertProduct}
    </form>
    <br/>
    <h1><fmt:message key="label.result" bundle="${rb}" /></h1>
    <c:forEach items="${programComponent}" var="program">
        <input type="hidden" name="index" value="0">
        <table border="1" width="600px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td class="prod3" width="30px">${program.id}</td>
                <td class="prod3" width="200px">${program.programName}</td>
                <td class="prod3" width="100px">${program.product}</td>
                <td class="prod3" width="30px">${program.portions}</td>
                <td class="prod3" width="80px">${program.time}</td>
                <td class="prod3" width="70px">${program.day}</td>
                <td class="prod3" width="60px">
                    <form class="changePageForm" method="post" action="/delete_curator_program_product">
                        <input type="hidden" name="command" value="delete_product_program">
                        <input type="hidden" name="id" value="${program.id}">
                        <input type="submit"  value="delete" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>
    <br/>
    <a href="${currentPage}"/><h4 class="h3login">
    <fmt:message key="label.backmain" bundle="${rb}"/></h4></a>
    <br/>

</div>

</body>
</html>
