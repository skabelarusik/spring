<#import "/spring.ftl" as spring/>
<html>
<head>
    <style type="text/css">
        <#include "/css/style.css">
    </style>
    <title><@spring.message "title.curator"/></title>
    <#setting classic_compatible=true>
</head>
<body>
<div class="wrapper">
    <script type="text/javascript">
        alert("HELLO CURATOR!!!")
    </script>

    <header>
        <#include "header.ftl">
    </header>

    <div class="languageMain">
        <form class="language" method="get" action="/lang/en">
            <input type="hidden" name="currentPage" value="curator">
            <input type="submit" class="lan1" name="langv" value="EN">
        </form>
        <form class="language" method="get" action="/lang/ru">
        <input type="hidden" name="currentPage" value="curator">
            <input type="submit" class="lan2" name="langv" value="RU">
        </form>
        <form class="language" method="get" action="/lang/by">
            <input type="submit" class="lan3" name="langv" value="BY">
            <input type="hidden" name="currentPage" value="curator">
        </form>
    </div>

    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
            <h3><@spring.message "label.welcome"/>, ${user.login}</h3>

            <!-- avatar -->
            <div class="icon">
                <img src="${user.avatar}" class="icon1"/>
            </div>
            <h5><@spring.message "label.avatar"/></h5>
            <div class="uploadFile">
                <form method="post" class="invisibleForm" action="/UploadServlet" enctype="multipart/form-data">
                    <label for="changeAva" class="uploadButton"><@spring.message "button.selectfile"/></label>
                    <input style="opacity: 0; z-index: -1;" type="file" id="changeAva" name="file" />
                    <input type="submit" value="<@spring.message "button.upload"/>"
                    size="10" class="login-button" /><br/>
                    ${errorDownload}
                </form>
            </div>
            <div class="message">
            <a href="/user/update"><@spring.message "button.edit"/></a>
        </div>
        <div class="message">
            <h4><@spring.message "label.message"/></h4>
            <h5><a href="/message/input"><@spring.message "label.input"/></a></h5>
            <h5><a href="/message/output"><@spring.message "label.output"/></a></h5>
            <h5><a href="/message/send"><@spring.message "label.sendmessage"/> </a></h5>
        </div>
    </div>

        <div class="block2">
            <div class="searchheader">
                <form class="adviceForm" method="post" action="/product/search">
                <input type="hidden" name="currentPage" value="curator">
                    <p><input  class="adminBlock" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                        <input  class="adminBlock" type="submit" value="Найти"></p>
                </form>
            </div>
            <h4><br/><@spring.message "label.logoutmess"/> </h4>
            <a href="/logout" class="aBlock2"><h5><@spring.message "label.logout"/></h5></a>
            <br/>

            <!-- panel users -->
             <div class="wrapperBlock2">
                            <div class="blockUsers">
                                <div class="productBlock">
                                    <h4 class="adminBlock"><@spring.message "label.showuser"/></h4>
                                    <a href="/user/select"><h5 class="adminBlock"><@spring.message "button.show"/></h5></a><br/>
                                    <h4 class="adminBlock"><@spring.message "label.showuserstatus"/></h4>
                        <form class="adminBlock" method="post" action="/user/select_by_status">
                            <p>
                                <label for="user" class="adviceForm"> <@spring.message "label.user"/></label>
                                <input autofocus id="user" type="radio" name="role"  value="user" checked>
                                <label for="superuser" class="adviceForm"> <@spring.message "label.superuser"/></label>
                                <input autofocus id="superuser" type="radio" name="role" value="superuser"></p>
                            <p>
                                <label for="curator" class="adviceForm"> <@spring.message "label.curator"/></label>
                                <input autofocus id="curator" type="radio" name="role" value="curator">
                                <label for="admin" class="adviceForm"> <@spring.message "label.admin"/></label>
                                <input autofocus id="admin" type="radio" name="role" value="admin"></br>
                            </p>
                            <input type="submit" value="<@spring.message "button.show" />" class="login-button"/>
                        </form>
                        ${wrongData}<br/>

                        <!-- select user by gender -->
                        <h4 class="adminBlock"><@spring.message "label.showusergender" /></h4>
                        <form class="adminBlock" method="post" action="/user/select_by_gender">
                            <p>
                                <label for="sex1" class="adviceForm"> <@spring.message "label.sexmale"/></label>
                                <input autofocus id="sex1" type="radio" name="gender"  value="male" checked>
                                <label for="sex2" class="adviceForm"> <@spring.message "label.sexfemale" /></label>
                                <input autofocus id="sex2" type="radio" name="gender" value="female"></p>
                            <input type="submit" value="<@spring.message "button.show" />" class="login-button"/>
                        </form> ${wrongData}<br/>
                    </div><br/>

                    <!-- panel learner -->
                    <div class="blockLearner">
                        <h4 class="adminBlock"><@spring.message "label.showprogname" /></h4>
                        <a href="/program_name/select_curator"><h5 class="adminBlock">
                                <@spring.message "label.show" /></h5></a>
                        <h4 class="adminBlock"><@spring.message "label.showdeleteprograme" /></h4>
                        <a href="/program_name/select_del_curator"><h5 class="adminBlock">
                                <@spring.message "label.show" /></h5></a><br/>
                        ${msgSelectPrName}

                    <!-- add new program name -->
                    <form class="adminBlock" method="post" action="/program_name/add">
                    <input type="hidden" name="currentPage" value="curator">
                        <h4 class="adminBlock"><@spring.message "label.addprogname" /></h4>
                        <p>
                            <label for="newProgramName" class="formText2"><@spring.message "label.progname" /></label>
                            <input type="text" name="nameProgramName" maxlength="35" autofocus id="newProgramName"
                                   data-required="true" value=""  class="formInput2" placeholder="Input name">
                        </p>
                        <p>
                            <label for="costProgramName" class="formText2"><@spring.message "label.costprog"/></label>
                            <input type="number" name="costProgramName" maxlength="4" autofocus id="costProgramName"
                                   data-required="true" value=""  class="formInput2" placeholder="Input cost">
                        </p>
                        <p>
                            <label for="durProg" class="formText2"><@spring.message "label.durprog" /></label>
                            <input type="number" name="durProgramName" maxlength="3" autofocus id="durProg"
                                   value=""  class="formInput2" placeholder="Input duration">
                        </p>
                        <input type="submit" class="login-button" value="<@spring.message "button.insert" />"/><br/>
                        ${messageProgramName}
                    </form><br/>

                    <h4 class="adminBlock"><fmt:message key="label.mysubscriber" bundle="${rb}"/></h4>
                    <a href="/subscription/select_curator"><h5 class="adminBlock">
                            <@spring.message "label.show"/></h5></a><br/>
                    </form></div>
                    <br/>
                </div>

                <!-- panel product -->
                <div class="anotherBlock">
                    <div class="productBlock">
                        <h4 class="adminBlock"><@spring.message "label.productbox" /></h4>
                        <a href="/product/select">
                            <h5 class="adminBlock"><@spring.message "label.selectproduct" /> </h5>
                        </a><br/>
                        <form class="adminBlock" method="post" action="/product/select_by_calories">
                        <input type="hidden" name="currentPage" value="curator">
                            <p>
                                <label for="minCalories" class="formText2"><@spring.message "label.mincalor"/></label>
                                <input type="number" name="minCalories" maxlength="4" autofocus id="minCalories"
                                       data-required="true" value=""  class="formInput2" placeholder="Input min calories">
                            </p>
                            <p>
                                <label for="maxCalories" class="formText2"><@spring.message "label.maxcalor" /></label>
                                <input type="number" name="maxCalories" maxlength="4" autofocus id="maxCalories"
                                       data-required="true" value=""  class="formInput2" placeholder="Input max calories">
                            </p>
                            <input type="submit" class="login-button" value="<@spring.message "button.show"/>"/>
                        </form><br/>
                        ${messageWrongProduct}

                        <!-- update product -->

                        <form class="adminBlock" method="post" action="/product/update">
                        <input type="hidden" name="currentPage" value="curator">
                            <h4 class="adminBlock"><@spring.message "label.updateproduct"/></h4>
                            <p>
                                <label for="idproduct" class="formText2">Id</label>
                                <input type="number" name="idproduct" maxlength="9" autofocus id="idproduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input id">
                            </p>
                            <p>
                                <label for="nameProduct" class="formText2"><@spring.message "label.name" /></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="caloriesProduct" class="formText2"><@spring.message "label.calor"/></label>
                                <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input calories">
                            </p>
                            <p>
                                <label for="proteinsProduct" class="formText2"><@spring.message "label.proteins" /></label>
                                <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="fatsProduct" class="formText2"><@spring.message "label.fats" /></label>
                                <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="carbsProduct" class="formText2"><@spring.message "label.carbs" /></label>
                                <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <input type="submit" class="login-button" value="<@spring.message "button.update"/>"/><br/>
                            ${messageUpdateProduct}
                        </form>

                        <!-- delete product -->
                        <form class="adminBlock" method="post" action="/product/delete">
                        <input type="hidden" name="currentPage" value="curator">
                            <h4 class="adminBlock"><@spring.message "label.deleteproduct" /></h4>
                            <p>
                                <label for="idProd" class="formText2">Id</label>
                                <input type="number" name="id" maxlength="9" autofocus id="idProd"
                                       data-required="true" value=""  class="formInput2" placeholder="Input id">
                            </p>
                            <p>
                                <label for="nameProd" class="formText2"><@spring.message "label.name" /></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProd"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <input type="submit" value="<@spring.message "button.delete" />" class="login-button"/>
                            ${messageDeleteProduct}
                        </form>

                        <!-- add product -->
                        <form class="adminBlock" method="post" action="/product/add">
                        <input type="hidden" name="currentPage" value="curator">
                            <h4 class="adminBlock"><@spring.message "label.addproduct" /></h4>
                            <p>
                                <label for="nameNewProduct" class="formText2"><@spring.message "label.name" /></label>
                                <input type="text" name="nameProduct" maxlength="35" autofocus id="nameNewProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input name">
                            </p>
                            <p>
                                <label for="caloriesNewProduct" class="formText2"><@spring.message "label.calor" /></label>
                                <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesNewProduct"
                                       data-required="true" value=""  class="formInput2" placeholder="Input calories">
                            </p>
                            <p>
                                <label for="proteinsNewProduct" class="formText2"><@spring.message "label.proteins" /></label>
                                <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="fatsNewProduct" class="formText2"><@spring.message "label.fats" /></label>
                                <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <p>
                                <label for="carbsNewProduct" class="formText2"><@spring.message "label.carbs" /></label>
                                <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsNewProduct"
                                       value=""  class="formInput2" placeholder="Input proteins">
                            </p>
                            <input type="submit" class="login-button" value="<@spring.message "button.insert" />"/><br/>
                            ${messageInsertProduct}
                        </form>
                    </div>

                    <div class="blockAdvice">
                        <#include "calculator_calories.ftl"/><br/>
                    </div>

                    <!-- block advices -->
                    <div class="blockAdvice">
                        <h4 class="adminBlock"><@spring.message "label.advicebox" /></h4>
                        <a href="/advice/select"><h5 class="adminBlock">
                                <@spring.message "label.selectadvice" /> </h5></a><br/>
                        <form class="adviceForm" method="post" action="/advice/add">
                        <input type="hidden" name="currentPage" value="curator">
                            <h4 class="adminBlock">
                                <@spring.message "label.addadvice" /></h4>
                            <input type="text" name = "advice" placeholder="message" id="advice" value="" class="formInputAdmin"/>
                            <input type="submit" class="login-button" value="<@spring.message "button.insert" />"/>
                            <br/>${addmessage}
                        </form>

                    </div>
                </div>

            </div>
        </div>
        <div class="block3">

            <h4><@spring.message "label.topcurator" /></h4>
            <h3>TEMP</h3>
            <br/>
            <h4><@spring.message "label.oursponsors" /></h4>
            <div class="icon">
                <img src="/views/picture/oracle.png" class="icon1">
            </div>
            <br/>
            <div class="icon">
                <img src="/picture/mysql.jpg" class="icon1">
            </div>
            <br/>
            <div class="icon">
                <img src="picture/tomcat.png" class="icon1">
            </div>
        </div>
    </div>
    <footer>
        <#include "footer.ftl">
    </footer>
</div>

</div>

</body>
</html>