<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.07.2019
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
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
        <c:if test="${mess == 4}">
        alert("Wrong, please check it");
        </c:if>
    </script>

    <h1><fmt:message key="label.result" bundle="${rb}" /></h1>
    <br/>

    <!-- table product -->
    <c:if test="${not empty listProducts}">
        <form class="changePageForm" method="post" action="/select_all_product">
            <input type="hidden" name="command" value="select_all_products">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
            <input type="radio" class="lan3" name="type" value="INCREASE_CALORIES"><small>INCR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_CALORIES"><small>DECR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_NAME"><small>INCR(NAME)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_NAME"><small>DECR(NAME)</small>
        </form>
    </c:if>

    <c:forEach items="${listProducts}" var="product">
        <table border="1" width="60%" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="20px">${product.id}</td>
                <td width="100px">${product.name}</td>
                <td width="30px">${product.calories}</td>
                <td width="20px"><c:if test="${product.proteins != -1}">${product.proteins}</c:if></td>
                <td width="20px"><c:if test="${product.fats != -1}"> ${product.fats}</c:if></td>
                <td width="20px"><c:if test="${product.carbs != -1}"> ${product.carbs}</c:if></td>
            </tr>
        </table>
    </c:forEach>

    <!-- table product by calories -->
    <c:if test="${not empty listProductsByCalories}">
        <form class="changePageForm" method="post" action="/select_all_product">
            <input type="hidden" name="minCalories" value="${minCalories}">
            <input type="hidden" name="maxCalories" value="${maxCalories}">
            <input type="hidden" name="command" value="select_product">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
            <input type="radio" class="lan3" name="type" value="INCREASE_CALORIES"><small>INCR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_CALORIES"><small>DECR(CAL)</small>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_NAME"><small>INCR(NAME)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_NAME"><small>DECR(NAME)</small>
        </form>
    </c:if>

    <c:forEach items="${listProductsByCalories}" var="product">
        <table border="1" width="60%" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="20px">${product.id}</td>
                <td width="100px">${product.name}</td>
                <td width="30px">${product.calories}</td>
                <td width="20px"><c:if test="${product.proteins != -1}">${product.proteins}</c:if></td>
                <td width="20px"><c:if test="${product.fats != -1}"> ${product.fats}</c:if></td>
                <td width="20px"><c:if test="${product.carbs != -1}"> ${product.carbs}</c:if></td>
            </tr>
        </table>
    </c:forEach>

    <!-- table users -->
    <c:if test="${not empty listUser}">
        <form class="changePageForm" method="post" action="/select_users">
            <input type="hidden" name="command" value="select_user">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
            <input type="radio" class="lan3" name="type" value="INCREASE_AGE"><small>INCR(AGE)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_AGE"><small>DECR(AGE)</small>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_LOGIN"><small>INCR(LOGIN)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_LOGIN"><small>DECR(LOGIN)</small>
        </form>
        ${messageProgramName}
    </c:if>

    <c:forEach items="${listUser}" var="user">
        <table width="700px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="20px">${user.id}</td>
                <td width="100px">${user.login}</td>
                <td width=100px">${user.name}</td>
                <td width="100px">${user.surname}</td>
                <td width="80px">${user.role}</td>
                <td width="60px">${user.gender}</td>
                <td width="70px">${user.birthDate}</td>
                <td width="70px">${user.registrDate}</td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/send_message">
                        <input type="hidden" name="command" value="user_message">
                        <input type="hidden" name="recipient" value="${user.login}">
                        <input type="submit"  value="message" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

    <!-- table users by gender -->
    <c:if test="${not empty listUserGender}">
        <form class="changePageForm" method="post" action="/select_users">
            <input type="hidden" name="command" value="select_user_gender">
            <input type="hidden" name="gender" value="${gender}">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_LOGIN"><small>INCR(LOGIN)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_LOGIN"><small>DECR(LOGIN)</small>
        </form>
    </c:if>

    <c:forEach items="${listUserGender}" var="user">
        <table width="700px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="20px">${user.id}</td>
                <td width="100px">${user.login}</td>
                <td width=100px">${user.name}</td>
                <td width="100px">${user.surname}</td>
                <td width="80px">${user.role}</td>
                <td width="60px">${user.gender}</td>
                <td width="70px">${user.birthDate}</td>
                <td width="70px">${user.registrDate}</td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/send_message">
                        <input type="hidden" name="command" value="user_message">
                        <input type="hidden" name="recipient" value="${user.login}">
                        <input type="submit"  value="message" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

    <!-- table users by role -->
    <c:if test="${not empty listUserRole}">
        <form class="changePageForm" method="post" action="/select_users">
            <input type="hidden" name="command" value="select_user_status">
            <input type="hidden" name="role" value="${role}">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
            <input type="radio" class="lan3" name="type" value="NOTHING"><small>NOTHING</small>
            <input type="radio" class="lan3" name="type" value="INCREASE_LOGIN"><small>INCR(LOGIN)</small>
            <input type="radio" class="lan3" name="type" value="DECREASE_LOGIN"><small>DECR(LOGIN)</small>
        </form>
    </c:if>

    <c:forEach items="${listUserRole}" var="user">
        <table width="700px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="20px">${user.id}</td>
                <td width="100px">${user.login}</td>
                <td width=100px">${user.name}</td>
                <td width="100px">${user.surname}</td>
                <td width="80px">${user.role}</td>
                <td width="60px">${user.gender}</td>
                <td width="70px">${user.birthDate}</td>
                <td width="70px">${user.registrDate}</td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/send_message">
                        <input type="hidden" name="command" value="user_message">
                        <input type="hidden" name="recipient" value="${user.login}">
                        <input type="submit"  value="message" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>


    <c:forEach items="${listAdvices}" var="advice">
        <table border="1" width="500px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td class="prod3" width="30px">${advice.id}</td>
                <td class="prod3" width="400px">${advice.text}</td>
                <td class="prod3" width="60px">
                    <form class="changePageForm" method="post" action="/delete_advice">
                        <input type="hidden" name="command" value="delete_advice">
                        <input type="hidden" name="id" value="${advice.id}">
                        <input type="submit"  value="delete" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

    <c:forEach items="${listReview}" var="review">
        <table border="1" width="560px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td class="prod3" width="20px">${review.id}</td>
                <td class="prod3" width="140px">${review.name}</td>
                <td class="prod3" width="80px">${review.date}</td>
                <td class="prod3" width="260px"> ${review.text}</td>
                <td class="prod3" width="60px">
                    <form class="changePageForm" method="post" action="/delete_review">
                        <input type="hidden" name="command" value="delete_review">
                        <input type="hidden" name="id" value="${review.id}">
                        <input type="hidden" name="buttonName" value="${buttonName}">
                        <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>


    <c:forEach items="${listSubscr}" var="subs">
        <table border="1" width="60%" class="tableTab">
            <tr>
                <td width="3%">${subs.id}</td>
                <td width="17%">${subs.subscriber}</td>
                <td width="17%">${subs.curator}</td>
                <td width="10%">${subs.start}</td>
                <td width="10%">${subs.finish}</td>
            </tr>
        </table>
    </c:forEach>
    ${messageSubsc}

    <!-- table programs name -->
    <c:if test="${not empty listPrograms}">
        <form class="changePageForm" method="post" action="/select_programs_name">
            <input type="hidden" name="command" value="select_all_programs_name">
            <input type="hidden" name="type" value="${type}">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
        </form>
        ${messageProgramName}
    </c:if>



    <c:forEach items="${listPrograms}" var="programs">
        <table border="1" width="550px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="40px">${programs.id}</td>
                <td width="260px">${programs.name}</td>
                <td width="70px">${programs.curator}</td>
                <td width="40px">${programs.duration}</td>
                <td width="40px">${programs.cost}</td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/delete_program_name">
                        <input type="hidden" name="command" value="delete_program_name">
                        <input type="hidden" name="id" value="${programs.id}">
                        <input type="hidden" name="buttonName" value="${buttonName}">
                        <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

    <!-- table curator list -->

    <c:if test="${not empty listProgramsCurator}">
        <form class="changePageForm" method="post" action="/select_programs_name">
            <input type="hidden" name="command" value="select_curator_programs">
            <input type="hidden" name="type" value="${type}">
            <c:if test="${not empty previousPage}"> <input type="submit" class="lan3" name="nextPage" value="${previousPage}"></c:if>
            <input type="submit" class="lan3" name="nextPage" value="${currPage}">
            <c:if test="${not empty nextPage}"><input type="submit" class="lan3" name="nextPage" value="${nextPage}"></c:if>
        </form>
        ${messageProgramName}
    </c:if>

    <c:forEach items="${listProgramsCurator}" var="programs">
        <table border="1" width="550px" class="userTable" cellspacing="0" cellpadding="4" border="1">
            <tr>
                <td width="40px">${programs.id}</td>
                <td width="260px">${programs.name}</td>
                <td width="40px">${programs.duration}</td>
                <td width="40px">${programs.cost}</td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/select_curator_programs">
                        <input type="hidden" name="command" value="delete_program_name">
                        <input type="hidden" name="id" value="${programs.id}">
                        <input type="hidden" name="buttonName" value="${buttonName}">
                        <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                    </form>
                </td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/update_curator_programs">
                        <input type="hidden" name="listProgramsCurator" value="${listProgramsCurator}">
                        <input type="hidden" name="command" value="edit_program_name">
                        <input type="hidden" name="id" value="${programs.id}">
                        <input type="hidden" name="duration" value="${programs.duration}">
                        <input type="hidden" name="cost" value="${programs.cost}">
                        <input type="hidden" name="name" value="${programs.name}">
                        <input type="submit"  value="update" class="uploadButtonTable"/>
                    </form>
                </td>
                <td width="60px">
                    <form class="changePageForm" method="post" action="/edit_curator_program_component">
                        <input type="hidden" name="command" value="edit_component_program">
                        <input type="hidden" name="id" value="${programs.id}">
                        <input type="hidden" name="name" value="${programs.name}">
                        <input type="submit" value="edit" class="uploadButtonTable">
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>
    ${messageProgramName}
    <br/>
    <h4 class="h3login"><a class="registration" href='<c:out value="${currentPage}"/>'>
            <fmt:message key="label.backmain" bundle="${rb}"/></a>
    </h4>

</div>
</body>
</html>
