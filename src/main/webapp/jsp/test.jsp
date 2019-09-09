<h1><fmt:message key="label.result" bundle="${rb}" /></h1>

<c:forEach items="${listProduct}" var="product">
    <table border="1">
        <tr>
            <td class="prod1">${product.name}</td>
            <td class="prod2">${product.calories}</td>
            <td class="prod2"><c:if test="${product.proteins != -1}">${product.proteins}</c:if></td>
            <td class="prod2">${product.fats}</td>
            <td class="prod2">${product.carbs}</td>
        </tr>
    </table>
</c:forEach>

<c:forEach items="${listUser}" var="user">
    <table width="90%" class="userTable" cellspacing="0" cellpadding="4" border="1">
        <tr>
            <td width="3%">${user.id}</td>
            <td width="19%">${user.login}</td>
            <td width="17%">${user.name}</td>
            <td width="17%">${user.surname}</td>
            <td width="7%">${user.role}</td>
            <td width="7%">${user.gender}</td>
            <td width="10%">${user.birthDate}</td>
            <td width="10%">${user.registrDate}</td>
        </tr>
    </table>
</c:forEach>

<c:forEach items="${listMessage}" var="message">
    <table border="1">
        <tr>
            <td >${message.sender}</td>
            <td>${message.recipient}</td>
            <td>${message.text}</td>
            <td>${message.topik}</td>
        </tr>
    </table>
</c:forEach>

<c:forEach items="${listAdvices}" var="advice">
    <table border="1">
        <tr>
            <td>${advice.id}</td>
            <td>${advice.text}</td>
        </tr>
    </table>
</c:forEach>

<c:forEach items="${listReview}" var="review">
    <table border="1">
        <tr>
            <td>${review.id}</td>
            <td>${review.name}</td>
            <td>${review.date}</td>
            <td> ${review.text}</td>
        </tr>
    </table>
</c:forEach>

<h4 class="h3login"><fmt:message key="label.back" bundle="${rb}"/>
    <a class="registration" href='<c:out value="${currentPage}"/>'>
        <fmt:message key="label.back" bundle="${tb}"/></a>
</h4>