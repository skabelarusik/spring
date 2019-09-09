<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="customtag" %>
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="message" var="rb" />
<%@page import="by.epam.crackertracker.entity.MealDay"%>


<html>
<head>
    <title><fmt:message bundle="${ rb }" key="title.curator"/></title>
    <style>
        <%@include file="css/style.css"%>
    </style>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">
</head>
<body>
<div class="wrapper">
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
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <!-- language panel -->
    <div class="languageMain">
        <form class="language" name="lanEn" method="post" action="/lang_curator">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan1" name="langv" value="EN">
        </form>
        <form class="language" name="lanEn" method="post" action="/lang_curator">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan2" name="langv" value="RU">
        </form>
        <form class="language" name="lanEn" method="post" action="/lang_curator">
            <input type="hidden" name="command" value="language">
            <input type="submit" class="lan3" name="langv" value="BY">
        </form>
    </div>

    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
            <h3><fmt:message key="label.welcome" bundle="${rb}"/>, ${login}</h3>

            <!-- avatar -->
            <div class="icon">
                <img src=${avatar} class="icon1"/>
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
                <a href="/jsp/editing.jsp" class="invisibleForm"><fmt:message key="button.edit" bundle="${rb}"/></a>
            </div>
            <div class="message">
                <h4><fmt:message key="label.message" bundle="${rb}"/></h4>
                <h5><a href="/controller_curator?command=input_message" class="invisibleForm"><fmt:message key="label.input" bundle="${rb}"/></a></h5>
                <h5><a href="/controller_curator?command=output_message" class="invisibleForm"><fmt:message key="label.output" bundle="${rb}"/></a></h5>
                <h5><a href="/jsp/message.jsp" class="invisibleForm"><fmt:message key="label.sendmessage" bundle="${rb}"/> </a></h5>
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
            <h4><fmt:message key="label.logoutmess" bundle="${rb}"/> </h4>
            <a href="/controller_curator?command=logout" class="aBlock2"><h5><fmt:message key="label.logout" bundle="${rb}"/></h5></a>
            <br/>

            <!-- panel users -->
            <div class="wrapperBlock2">
                <div class="blockUsers">
                    <div class="productBlock">
                        <h4 class="adminBlock"><fmt:message key="label.showuser" bundle="${rb}"/> </h4>
                        <a href="/controller_curator?command=select_user"><h5 class="adminBlock"><fmt:message key="button.show"
                                                                                                            bundle="${rb}"/></h5></a><br/>
                        <h4 class="adminBlock"><fmt:message key="label.showuserstatus" bundle="${rb}"/></h4>
                        <form class="adminBlock" method="post" action="/controller_curator">
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
                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="select_user_gender">
                            <p>
                                <label for="sex1" class="adviceForm"> <fmt:message key="label.sexmale" bundle="${rb}"/></label>
                                <input autofocus id="sex1" type="radio" name="gender"  value="male" checked>
                                <label for="sex2" class="adviceForm"> <fmt:message key="label.sexfemale" bundle="${rb}"/></label>
                                <input autofocus id="sex2" type="radio" name="gender" value="female"></p>
                            <input type="submit" value="<fmt:message key="button.show" bundle="${rb}"/>" class="login-button"/>
                        </form><br/>
                    </div>

                    <!-- panel learner -->
                    <div class="blockLearner">
                        <h4 class="adminBlock"><fmt:message bundle="${rb}" key="label.showprogname" /></h4>
                        <a href="/controller_curator?command=select_curator_programs&type=1"><h5 class="adminBlock">
                            <fmt:message key="label.show" bundle="${rb}"/></h5></a>
                        <h4 class="adminBlock"><fmt:message bundle="${rb}" key="label.showdeleteprograme" /></h4>
                        <a href="/controller_curator?command=select_curator_programs&type=0"><h5 class="adminBlock">
                            <fmt:message key="label.show" bundle="${rb}"/></h5></a><br/>
                        ${msgSelectPrName}

                        <!-- add new program name -->
                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="add_program_name">
                            <h4 class="adminBlock"><fmt:message key="label.addprogname" bundle="${rb}"/></h4>
                            <p>
                                <label for="newProgramName" class="formText2"><fmt:message key="label.progname" bundle="${rb}"/></label>
                                <input type="text" name="nameProgramName" maxlength="35" autofocus id="newProgramName"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="costProgramName" class="formText2"><fmt:message key="label.costprog" bundle="${rb}"/></label>
                                <input type="number" name="costProgramName" maxlength="4" autofocus id="costProgramName"
                                       data-required="true" value=""  class="formInput2" placeholder="Input cost">
                            </p>
                            <p>
                                <label for="durProg" class="formText2"><fmt:message key="label.durprog" bundle="${rb}"/></label>
                                <input type="number" name="durProgramName" maxlength="3" autofocus id="durProg"
                                       value=""  class="formInput2" placeholder="Input duration">
                            </p>
                            <input type="submit" class="login-button" value="<fmt:message key="button.insert" bundle="${rb}"/>"/><br/>
                            ${messageProgramName}
                        </form><br/>

                        <!-- add components to program -->
                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="add_product_to_program">
                            <h4 class="adminBlock"><fmt:message key="label.addcomponentprog" bundle="${rb}"/></h4>
                            <p>
                                <label for="idProgram" class="formText2"><fmt:message key="label.progname" bundle="${rb}"/></label>
                                <input type="text" name="idProgram" maxlength="40" autofocus id="idProgram"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="numProduct" class="formText2"><fmt:message key="label.product" bundle="${rb}"/></label>
                                <input type="text" name="numProduct" maxlength="35" autofocus id="numProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input cost">
                            </p>
                            <p>
                                <label for="portions" class="formText2"><fmt:message key="label.durprog" bundle="${rb}"/></label>
                                <input type="text" name="portions" maxlength="4" autofocus id="portions"
                                       data-required="true" value=""  class="formInput2" placeholder="Input portions">
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
                    </div>


                    <!-- subscribers block -->
                    <div class="blockLearner">
                        <h4 class="adminBlock"><fmt:message key="label.mysubscriber" bundle="${rb}"/></h4>
                        <a href="/controller_curator?command=select_all_curator_subs"><h5 class="adminBlock">
                            <fmt:message key="label.show" bundle="${rb}"/></h5></a>
                    </div><br/>
                </div>

                <!-- panel product -->
                <div class="anotherBlock">
                    <div class="productBlock">
                        <h4 class="adminBlock"><fmt:message key="label.productbox" bundle="${rb}"/></h4>
                        <a href="/controller_curator?command=select_all_products">
                            <h5 class="adminBlock"><fmt:message key="label.selectproduct" bundle="${rb}"/> </h5>
                        </a><br/>
                        <form class="adminBlock" method="post" action="/controller_curator">
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

                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="update_product">
                            <h4 class="adminBlock"><fmt:message key="label.updateproduct" bundle="${rb}"/></h4>
                            <p>
                                <label for="idproduct" class="formText2">Id</label>
                                <input type="number" name="idproduct" maxlength="9" autofocus id="idproduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input id">
                            </p>
                            <p>
                                <label for="nameProduct" class="formText2"><fmt:message key="label.name" bundle="${rb}"/></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="caloriesProduct" class="formText2"><fmt:message key="label.calor" bundle="${rb}"/></label>
                                <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input calories">
                            </p>
                            <p>
                                <label for="proteinsProduct" class="formText2"><fmt:message key="label.proteins" bundle="${rb}"/></label>
                                <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="fatsProduct" class="formText2"><fmt:message key="label.fats" bundle="${rb}"/></label>
                                <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="carbsProduct" class="formText2"><fmt:message key="label.carbs" bundle="${rb}"/></label>
                                <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <input type="submit" class="login-button" value="<fmt:message key="button.update" bundle="${rb}"/>"/><br/>
                            ${messageUpdateProduct}
                        </form>
                        <!-- delete product -->
                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="delete_product">
                            <h4 class="adminBlock"><fmt:message key="label.deleteproduct" bundle="${rb}"/></h4>
                            <p>
                                <label for="idProd" class="formText2">Id</label>
                                <input type="number" name="id" maxlength="9" autofocus id="idProd"
                                       data-required="true" value=""  class="formInput2" placeholder="Input id">
                            </p>
                            <p>
                                <label for="nameProd" class="formText2"><fmt:message key="label.name" bundle="${rb}"/></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProd"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <input type="submit" value="<fmt:message key="button.delete" bundle="${rb}"/>" class="login-button"/>
                            ${messageDeleteProduct}
                        </form>

                        <!-- add product -->
                        <form class="adminBlock" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="add_product">
                            <h4 class="adminBlock"><fmt:message key="label.addproduct" bundle="${rb}"/></h4>
                            <p>
                                <label for="nameNewProduct" class="formText2"><fmt:message key="label.name" bundle="${rb}"/></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameNewProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="caloriesNewProduct" class="formText2"><fmt:message key="label.calor" bundle="${rb}"/></label>
                                <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesNewProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input calories">
                            </p>
                            <p>
                                <label for="proteinsNewProduct" class="formText2"><fmt:message key="label.proteins" bundle="${rb}"/></label>
                                <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="fatsNewProduct" class="formText2"><fmt:message key="label.fats" bundle="${rb}"/></label>
                                <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="carbsNewProduct" class="formText2"><fmt:message key="label.carbs" bundle="${rb}"/></label>
                                <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <input type="submit" class="login-button" value="<fmt:message key="button.insert" bundle="${rb}"/>"/><br/>
                            ${messageInsertProduct}
                        </form>
                    </div>


                    <div class="blockAdvice">
                        <h4 class="adminBlock"><fmt:message key="label.advicebox" bundle="${rb}"/></h4>
                        <a href="/controller_curator?command=select_advice"><h5 class="adminBlock">
                            <fmt:message key="label.selectadvice" bundle="${rb}"/> </h5></a><br/>
                        <form class="adviceForm" method="post" action="/controller_curator">
                            <input type="hidden" name="command" value="add_advice">
                            <h4 class="adminBlock">
                                <fmt:message key="label.addadvice" bundle="${rb}"/></h4>
                            <input type="text" name = "advice" placeholder="message" id="advice" value="" class="formInputAdmin"/>
                            <input type="submit" class="login-button" value="<fmt:message key="button.insert" bundle="${rb}"/>"/>
                            <br/>${addmessage}
                        </form>
                    </div>
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