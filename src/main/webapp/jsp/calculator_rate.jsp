<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message" var="rb"  />
<html>
<body>
<div class="calcBlock">
    <form id="calculator" class="adminBlock" action="/calc" method="post">
        <input type="hidden" name="command" value="calculate_calories">
        <h4 class="adminBlock"><fmt:message key="title.ratecalories" bundle="${rb}"/></h4>
        <label for="age" class="adminBlock"><fmt:message key="label.inputage" bundle="${rb}"/></label><input name="age" class="whiteBlock" id="age"><br/>
        <label for="weight" class="adminBlock"><fmt:message key="label.inputweight" bundle="${rb}"/></label><input name="weight" class="whiteBlock" id="weight"><br/>
        <label for="height" class="adminBlock"><fmt:message key="label.inputheight" bundle="${rb}"/></label> <input name="height" class="whiteBlock" id="height" ><br/>
        <p class="adminBlock">
            <fmt:message key="label.gender" bundle="${rb}"/> *<br/>
                    <label for="sex1" class="gender"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                    <input autofocus id="sex1" type="radio" name="gender"  value="Male" checked>
                    <label for="sex2" class="gender"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                    <input autofocus id="sex2" type="radio" name="gender" value="Female"></p></br>

        <p class="adminBlock">
                <fmt:message key="label.active" bundle="${rb}"/> <br/>
                <input autofocus id="act1" type="radio" name="active"  value="act1" checked><br/>
                <label for="act1" class="gender"> <fmt:message key="radio.active1" bundle="${rb}"/></label>
                <input autofocus id="act2" type="radio" name="active" value="act2">
                <label for="act2" class="gender"> <fmt:message key="radio.active2" bundle="${rb}"/></label><br/>
                <input autofocus id="act3" type="radio" name="active"  value="act3" >
                <label for="act3" class="gender"> <fmt:message key="radio.active3" bundle="${rb}"/></label>
                <br/>
                <input autofocus id="act4" type="radio" name="active" value="act4">
                <label for="act4" class="gender"> <fmt:message key="radio.active4" bundle="${rb}"/></label>
        </p></br>
            <input type="submit" class="login-button" value="<fmt:message key="button.calculate" bundle="${rb}"/>">
            Result: ${result_calories}
        </p>
    </form>
</div>
</body>
</html>