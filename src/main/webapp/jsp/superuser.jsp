<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="message" var="rb" />
<html>
<head>
    <title><fmt:message bundle="${ rb }" key="title.user" /></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
</head><body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <div class="languageMain">
        <form class="language" name="lanEn" method="post" action="/langsuperuser">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan1" name="langv" value="EN">
        </form>
        <form class="language" name="lanEn" method="post" action="/langsuperuser">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan2" name="langv" value="RU">
        </form>
        <form class="language" name="lanEn" method="post" action="/langsuperuser">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan3" name="langv" value="BY">
        </form>
    </div>
    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
            <h3><fmt:message key="label.welcome" bundle="${rb}"/>, ${login}</h3>
            <div class="icon">
                <img src="/jsp/picture/user1.jpg" class="icon1"/>
            </div>
            <div class="message">
                <h4>Сообщения</h4>
                <h5><a href="/controller?command=inputmessage">Входящие</a></h5>
                <h5><a href="/controller?command=outputmessage">Отправленные</a></h5>
                <h5><a href="#">Важные</a></h5>
            </div>
        </div>
        <!-- center block -->
        <div class="block2">
            <h4><fmt:message key="label.logoutmess" bundle="${rb}"/> </h4>
            <a href="/controller?command=logout"><h5><fmt:message key="label.logout" bundle="${rb}"/></h5></a>
            <br/>

            <h4>Посмотреть список всех юзеров</h4>
            <a href="/controller?command=select_user"><h5>SelectAllUsers</h5></a>
            <br/>
            <h4>Посмотреть список юзеров по параметрам</h4>
            <form class="userForm" method="post" action="/result">
                <input type="hidden" name="command" value="select_user">
                <p>
                    <label for="minAge" class="formText">Min callories</label>
                    <input type="number" name="minAge" maxlength="3" autofocus id="minAge"
                           data-required="true" value=""  class="formInput" placeholder="Input min age">
                </p>
                <p>
                    <label for="maxAge" class="formText">Min callories</label>
                    <input type="number" name="maxAge" maxlength="3" autofocus id="maxAge"
                           data-required="true" value=""  class="formInput" placeholder="Input max age">
                </p>
                <p>
                    <label for="sex1"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                    <input autofocus id="sex1" type="radio" name="gender"  value="Male">
                    <label for="sex2"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                    <input autofocus id="sex2" type="radio" name="gender" value="Female"></p></br>
                </p>
                <p>
                    <label for="role1"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                    <input autofocus id="role1" type="radio" name="gender"  value="USER">
                    <label for="role2"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                    <input autofocus id="role2" type="radio" name="gender" value="SUPERUSER"></p>
                <label for="role3"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                <input autofocus id="role3" type="radio" name="gender"  value="CURATOR">
                <label for="role4"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                <input autofocus id="role4" type="radio" name="gender" value="ADMIN"></p>
                </p>
                <br/>
                <input type="submit" value="<fmt:message key="button.show" bundle="${rb}"/>" class="login-button"/>

            </form>

        </div>

        <div class="block3">
            <h4>Наши спонсоры:</h4>
            <div class="icon">
                <img src="/jsp/picture/oracle.png" class="icon1">
            </div>
            <div class="icon">
                <img src="/jsp/picture/mysql.jpg" class="icon1">
            </div>
            <div class="icon">
                <img src="/jsp/picture/tomcat.png" class="icon1">
            </div>
        </div>
    </div>
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</div>


</body>
</html>