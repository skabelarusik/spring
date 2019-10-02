<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="customtag" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="message" var="rb" />

<html>
<head>
    <title><fmt:message bundle="${ rb }" key="title.user"/></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

</head>
<body>
<script type="text/javascript">
    <c:if test="${mess == 5}">
    alert("<fmt:message key="alert.regist" bundle="${rb}"/>");
    </c:if>
    <c:if test="${mess == 4}">
    alert("<fmt:message key="alert.check" bundle="${rb}"/>");
    </c:if>
    <c:if test="${mess == 6}">
    alert("<fmt:message key="alert.welcome" bundle="${rb}"/>");
    </c:if>
    <c:if test="${mess == 7}">
    alert("<fmt:message key="alert.wronglogin" bundle="${rb}"/>");
    </c:if>
</script>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <!-- language panel -->
    <div class="languageMain">
        <form class="language" name="lanEn" method="post" action="/lang_user">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan1" name="langv" value="EN">
        </form>
        <form class="language" name="lanEn" method="post" action="/lang_user">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan2" name="langv" value="RU">
        </form>
        <form class="language" name="lanEn" method="post" action="/lang_user">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan3" name="langv" value="BY">
        </form>
    </div>

    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
            <h3><fmt:message key="label.welcome" bundle="${rb}"/> ${login}</h3>

            <!-- avatar -->
            <div class="icon">
                <img src="${avatar}" class="icon1"/>
            </div>
            <h5><fmt:message key="label.avatar" bundle="${rb}"/></h5>
            <div class="uploadFile">
                <form method="post" class="invisibleForm" action="/UploadServlet" enctype="multipart/form-data">
                    <label for="changeAva" class="uploadButton"><fmt:message key="button.selectfile" bundle="${rb}"/></label>
                    <input style="opacity: 0; z-index: -1;" type="file" id="changeAva" name="file" />
                    <input type="submit" value="<fmt:message key="button.upload" bundle="${rb}"/>"
                           size="10" class="login-button" /><br/>
                    ${errorDownload}
                </form>
            </div>
            <div class="message">
                <a href="/controller_user?command=update_profile" class="invisibleForm"><fmt:message key="button.edit" bundle="${rb}"/></a>
            </div>
            <div class="message">
                <h4><fmt:message key="label.message" bundle="${rb}"/></h4>
                <h5><a href="/controller_user?command=input_message" class="invisibleForm"><fmt:message key="label.input" bundle="${rb}"/></a></h5>
                <h5><a href="/controller_user?command=output_message" class="invisibleForm"><fmt:message key="label.output" bundle="${rb}"/></a></h5>
                <h5><a href="/controller_user?command=send_message" class="invisibleForm"><fmt:message key="label.sendmessage" bundle="${rb}"/> </a></h5>
            </div>

        </div>
        <!-- center block -->
        <div class="block2">
            <div class="searchheader">
                <form class="adviceForm" method="post" action="/controller">
                    <input type="hidden" name="command" value="search_product">
                    <p><input  class="whiteBlock" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                        <input  class="adminBlock" type="submit" value="Найти"></p>
                </form>
            </div>
            <h4><br/><fmt:message key="label.logoutmess" bundle="${rb}"/> </h4>
            <a href="/controller_user?command=logout" class="aBlock2"><h5><fmt:message key="label.logout" bundle="${rb}"/></h5></a>
            <br/>
            <!-- panel users -->
            <div class="wrapperBlock2">
                <div class="blockUsers">
                    <!-- panel calculate -->
                    <div class="productBlock">
                        <jsp:include page="calculator.jsp"/><br/>
                        <jsp:include page="calculator_rate.jsp"/><br/>
                    </div>
                    <div class="blockLearner">
                        <h4 class="adminBlock"><fmt:message key="label.showuser" bundle="${rb}"/> </h4>
                        <a href="/controller_user?command=select_user"><h5 class="adminBlock"><fmt:message key="button.show"
                                                                                                              bundle="${rb}"/></h5></a><br/>
                        <h4 class="adminBlock"><fmt:message key="label.showuserstatus" bundle="${rb}"/></h4>
                        <form class="adminBlock" method="post" action="/controller_user">
                            <input type="hidden" name="command" value="select_user_status">
                            <p>
                                <label for="user" class="adviceForm"> <fmt:message key="label.user" bundle="${rb}"/></label>
                                <input autofocus id="user" type="radio" name="role"  value="user" checked>
                                <label for="superuser" class="adviceForm"> <fmt:message key="label.superuser" bundle="${rb}"/></label>
                                <input autofocus id="superuser" type="radio" name="role" value="superuser"></p>
                            <p>
                                <label for="curator" class="adviceForm"> <fmt:message key="label.curator" bundle="${rb}"/></label>
                                <input autofocus id="curator" type="radio" name="role" value="curator">
                                <label for="admin" class="adviceForm"> <fmt:message key="label.admin" bundle="${rb}"/></label>
                                <input autofocus id="admin" type="radio" name="role" value="admin"></br>
                            </p>
                            <input type="submit" value="<fmt:message key="button.show" bundle="${rb}"/>" class="login-button"/>
                        </form>
                        ${wrongData}<br/>


                        <!-- select user by gender -->
                        <h4 class="adminBlock"><fmt:message key="label.showusergender" bundle="${rb}"/></h4>
                        <form class="adminBlock" method="post" action="/controller_user">
                            <input type="hidden" name="command" value="select_user_gender">
                            <p>
                                <label for="sex1" class="adviceForm"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                                <input autofocus id="sex1" type="radio" name="gender"  value="male" checked>
                                <label for="sex2" class="adviceForm"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                                <input autofocus id="sex2" type="radio" name="gender" value="female"></p>
                            <input type="submit" value="<fmt:message key="button.show" bundle="${rb}"/>" class="login-button"/>
                        </form><br/>

                        <h4 class="adminBlock"><fmt:message key="label.reqcurator" bundle="${rb}"/> </h4>
                        <a href="/controller_user?command=select_user_status&role=admin"><h5 class="adminBlock"><fmt:message key="button.click"
                                                                                                              bundle="${rb}"/></h5></a><br/>

                    </div>

                <br/>
                </div>


                <!-- panel product -->
                <div class="anotherBlock">
                    <div class="productBlock">
                        <h4 class="adminBlock"><fmt:message key="label.productbox" bundle="${rb}"/></h4>
                        <a href="/controller_user?command=select_all_products">
                            <h5 class="adminBlock"><fmt:message key="label.selectproduct" bundle="${rb}"/> </h5>
                        </a><br/>
                        <!-- select products by callories -->
                        <form class="adminBlock" method="post" action="/controller_user">
                            <input type="hidden" name="command" value="select_product">
                            <p>
                                <label for="minCalories" class="formText2"><fmt:message key="label.mincalor" bundle="${rb}"/></label>
                                <input type="number" name="minCalories" maxlength="4" autofocus id="minCalories"
                                       data-required="true" value=""  class="formInput2" placeholder="Input min calories">
                            </p>
                            <p>
                                <label for="maxCalories" class="formText2"><fmt:message key="label.maxcalor" bundle="${rb}"/></label>
                                <input type="number" name="maxCalories" maxlength="4" autofocus id="maxCalories"
                                       data-required="true" value=""  class="formInput2" placeholder="Input max calories">
                            </p>
                            <input type="submit" class="login-button" value="<fmt:message key="button.show" bundle="${rb}"/>"/>
                        </form><br/>
                    </div>

                    <!-- user calculator -->
                    <div class="blockAdvice">
                        <jsp:include page="calculator_calories.jsp"/><br/>
                    </div>

                    <div class="blockAdvice">

                        <h4 class="adminBlock"><fmt:message bundle="${rb}" key="label.showprogname" /></h4>
                        <a href="/controller_user?command=select_all_programs_name&type=1"><h5 class="adminBlock">
                            <fmt:message key="label.show" bundle="${rb}"/></h5></a><br/>

                        <h4 class="adminBlock"><fmt:message bundle="${rb}" key="label.showsub" /></h4>
                        <a href="/controller_user?command=show_history_subscription"><h5 class="adminBlock">
                            <fmt:message key="label.show_history_subs" bundle="${rb}"/></h5></a>                    </div>
                    <br/>
                </div>
            </div>
        </div>
        <div class="block3">
            <h4><fmt:message key="label.topcurator" bundle="${rb}"/></h4>
            <ctg:table-curators rows="${mapCurator.size}" head="${mapCurator.size}">
                ${mapCurator.curator}
            </ctg:table-curators>
            <br/>
            <h4><fmt:message key="label.oursponsors" bundle="${rb}"/></h4>
            <div class="icon">
                <img src="/jsp/picture/oracle.png" class="icon1">
            </div>
            <br/>
            <div class="icon">
                <img src="/jsp/picture/mysql.jpg" class="icon1">
            </div>
            <br/>
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