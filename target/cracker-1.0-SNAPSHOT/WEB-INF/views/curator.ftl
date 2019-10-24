<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.curator"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>
<body>
<#include "/WEB-INF/views/static/header_main.ftl">

<div class="wrapperLoginCurator">

    <div class="languageMain">
        <div class="uui-input-group horizontal">
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="curator">
                <button class="uui-button small lime-green" name="langv" value="BY">BY</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="curator">
                <button class="uui-button small raspberry" name="langv" value="RU">RU</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="curator">
                <button class="uui-button small orange" name="langv" value="US">EN</button>
            </form>
        </div>
    </div>


    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
                <h4><@spring.message "label.welcome"/>, ${login}</h4>

                <!-- avatar -->
                <div class="iconMain">
                    <img src="${user.path}" class="icon1"/>
                </div>
                <h6 class="styleMessageTitle"><@spring.message "label.avatar"/></h6>
                <div class="uploadFile">
                    <form method="post" class="invisibleForm" action="/UploadServlet" enctype="multipart/form-data">
                        <label for="changeAva" class="uploadButton"><@spring.message "button.selectfile"/></label>
                        <input style="opacity: 0; z-index: -1;" type="file" id="changeAva" name="file" />
                        <input type="submit" value="<@spring.message "button.upload"/>"
                               size="10" class="login-button" /><br/>
                        ${errorDownload}
                    </form>
                </div>
                <div class="messageMain">
                    <h6 class="styleMessage"><a href="/user/update"><@spring.message "button.edit"/></a></h6>
                </div>
                <div class="messageMain">
                    <h6 class="styleMessageTitle"><@spring.message "label.message" /></h6>
                    <h6 class="styleMessage"><a href="/message/input"><@spring.message "label.input"/></a></h6>
                    <h6 class="styleMessage"><a href="/message/output"><@spring.message "label.output"/></a></h6>
                    <h6 class="styleMessage"><a href="/message/send_mes"><@spring.message "label.sendmessage"/> </a></h6>
                </div>

                <div class="input-append bootstrap-timepicker uui-timepicker time-button">
                    <input id="timepicker" type="text" class="uui-form-element" />
                    <span class="add-on uui-button">
                     <i class="fa fa-clock-o"></i>
                </span>
                </div>

                <script>
                    $('#timepicker').uui_timepicker({ color: 'dark-gray' });
                </script>
            </div>

        <div class="block2">
            <div class="searchheader">
                <form class="adviceForm" method="post" action="/product/search">
                    <input type="hidden" name="currentPage" value="curator">
                    <input  class="adminBlock2" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                    <button class="uui-button small dark-gray"><@spring.message "button.search"/></button>
                </form>
            </div>

            <!-- panel users -->
             <div class="wrapperBlock2">
                            <div class="blockUsers">
                                <div class="productBlock">
                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.showuser"/></h6>
                                    <a href="/user/select"><h5 class="styleMessageSmall"><@spring.message "button.show"/></h5></a>
                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.showuserstatus"/></h6>
                                    <form class="adminBlock" method="post" action="/user/select_by_status">
                                        <p>    <label for="user" class="adviceForm"> <@spring.message "label.user"/></label>
                                            <input autofocus id="user" type="radio" name="role"  value="user" checked>
                                            <label for="superuser" class="adviceForm"> <@spring.message "label.superuser"/></label>
                                            <input autofocus id="superuser" type="radio" name="role" value="superuser">

                                            <label for="curator" class="adviceForm"> <@spring.message "label.curator"/></label>
                                            <input autofocus id="curator" type="radio" name="role" value="curator">
                                            <label for="admin" class="adviceForm"> <@spring.message "label.admin"/></label>
                                            <input autofocus id="admin" type="radio" name="role" value="admin"></br>
                                        </p>
                                        <button class="uui-button blue small left-icon">
                                            <img src="/myresources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
                                        </button>
                                    </form>

                                    ${wrongData}<br/>
                                    <!-- select user by gender -->
                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.showusergender" /></h6>
                                    <form class="adminBlock" method="post" action="/user/select_by_gender">
                                        <p>
                                            <label for="sex1" class="adviceForm"> <@spring.message "label.sexmale"/></label>
                                            <input autofocus id="sex1" type="radio" name="gender"  value="male" checked>
                                            <label for="sex2" class="adviceForm"> <@spring.message "label.sexfemale" /></label>
                                            <input autofocus id="sex2" type="radio" name="gender" value="female"></p>
                                        <button class="uui-button blue small left-icon">
                                            <img src="/myresources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
                                        </button>
                                    </form> ${wrongData}<br/>
                                </div>

                    <!-- panel learner -->
                    <div class="blockLearner">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showprogname" /></h6>
                        <a href="/program_name/select_curator"><h5 class="styleMessageSmall">
                                <@spring.message "label.show" /></h5></a>
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showdeleteprograme" /></h6>
                        <a href="/program_name/select_del_curator"><h5 class="styleMessageSmall">
                                <@spring.message "label.show" /></h5></a><br/>
                        ${msgSelectPrName}

                    <!-- add new program name -->
                    <form class="adminBlock" method="post" action="/program_name/add">
                    <input type="hidden" name="currentPage" value="curator">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.addprogname" /></h6>
                        <p>
                            <input type="text" name="nameProgramName" maxlength="35" autofocus id="newProgramName"
                                   data-required="true" value=""  class="formInput2" placeholder="Input name">
                            <label for="newProgramName" class="formText2"><@spring.message "label.progname" /></label>

                        </p>
                        <p>
                            <input type="number" name="costProgramName" maxlength="4" autofocus id="costProgramName"
                                   data-required="true" value=""  class="formInput2" placeholder="Input cost">
                            <label for="costProgramName" class="formText2"><@spring.message "label.costprog"/></label>

                        </p>
                        <p>
                            <input type="number" name="durProgramName" maxlength="3" autofocus id="durProg"
                                   value=""  class="formInput2" placeholder="Input duration">
                            <label for="durProg" class="formText2"><@spring.message "label.durprog" /></label>

                        </p><br/><br/><br/><br/>
                        <button class="uui-button blue small left-icon">
                            <img src="/myresources/images/icons/no-results.png" alt="" /><@spring.message "button.insert" />
                        </button>
                        ${messageProgramName}
                    </form><br/>

                    <h6 class="styleMessageTitleSmall"><@spring.message "label.mysubscriber" /></h6>
                    <a href="/subscription/select_curator"><h5 class="adminBlock">
                            <@spring.message "label.show"/></h5></a><br/>
                    </form></div>
                    <br/>

                                <!-- block advices -->
                                <div class="blockAdvice">
                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.advicebox" /></h6>
                                    <a href="/advice/select"><h6 class="styleMessageSmall">
                                            <@spring.message "label.selectadvice" /> </h6></a><br/>
                                    <form class="adviceForm" method="post" action="/advice/add">
                                        <input type="hidden" name="currentPage" value="curator">
                                        <h6 class="styleMessageTitleSmall">
                                            <@spring.message "label.addadvice" /></h6>
                                        <input type="text" name = "advice" placeholder="message" id="advice" value="" class="formInputAdmin"/>
                                        <br/><br/>
                                        <button class="uui-button blue small left-icon">
                                            <img src="/myresources/images/icons/no-results.png" alt="" /><@spring.message "button.insert" />
                                        </button>
                                        <br/>
                                        ${addmessage}
                                    </form>
                                    <br/>
                                </div>
                </div>

                <!-- panel product -->
                <div class="anotherBlock">
                    <div class="productBlockAdmin">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.productbox" /></h6>
                                        <a href="/product/select">
                                                                    <h5 class="styleMessageSmall"><@spring.message "label.selectproduct" /> </h5>
                                        </a>
                                        <form class="adminBlock" method="post" action="/product/select_by_calories">
                                                                    <input type="hidden" name="currentPage" value="admin">
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
                                        <p><br/><br/><br/><br/><br/>
                                                                    <button class="uui-button blue small left-icon">
                                                                        <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
                                        </button>
                                        </p>
                                        </form>
                                        ${messageWrongProduct}

                                        <!-- update product -->
                                        <form class="adminBlock" method="post" action="/product/update">
                                                                    <input type="hidden" name="currentPage" value="admin">
                                                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.updateproduct"/></h6>
                                        <p>
                                        <input type="number" name="idproduct" maxlength="9" autofocus id="idproduct"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input id">
                                                                        <label for="idproduct" class="formText2">Id</label>

                                        </p>
                                        <p>
                                        <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProduct"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input name">
                                                                        <label for="nameProduct" class="formText2"><@spring.message "label.name" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesProduct"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input calories">
                                                                        <label for="caloriesProduct" class="formText2"><@spring.message "label.calor"/></label>

                                        </p>
                                        <p>
                                        <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="proteinsProduct" class="formText2"><@spring.message "label.proteins" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="fatsProduct" class="formText2"><@spring.message "label.fats" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="carbsProduct" class="formText2"><@spring.message "label.carbs" /></label>
                                        </p><br/><br/><br/><br/><br/><br/><br/><br/><br/>

                                                                    <button class="uui-button blue small left-icon">
                                                                        <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.update" />
                                        </button>

                                        ${messageUpdateProduct}
                                        </form>
                                        <!-- delete product -->
                                        <form class="adminBlock" method="post" action="/product/delete">
                                                                    <input type="hidden" name="currentPage" value="admin">
                                                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.deleteproduct" /></h6>
                                        <p>
                                        <input type="number" name="id" maxlength="9" autofocus id="idProd"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input id">
                                                                        <label for="idProd" class="formText2">Id</label>

                                        </p>
                                        <p>
                                        <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProd"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input name">
                                                                        <label for="nameProd" class="formText2"><@spring.message "label.name" /></label>

                                        </p><br/><br/><br/>
                                                                    <button class="uui-button blue small left-icon">
                                                                        <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.delete" />
                                        </button>
                                        ${messageDeleteProduct}
                                        </form>

                                        <!-- add product -->
                                        <form class="adminBlock" method="post" action="/product/add">
                                                                    <input type="hidden" name="currentPage" value="curator">
                                                                    <h6 class="styleMessageTitleSmall"><@spring.message "label.addproduct" /></h6>
                                        <p>
                                        <input type="text" name="nameProduct" maxlength="35" autofocus id="nameNewProduct"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input name">
                                                                        <label for="nameNewProduct" class="formText2"><@spring.message "label.name" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="caloriesProduct" maxlength="9" autofocus id="caloriesNewProduct"
                                                                               data-required="true" value=""  class="formInput2" placeholder="Input calories">
                                                                        <label for="caloriesNewProduct" class="formText2"><@spring.message "label.calor" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="proteinsProduct" maxlength="9" autofocus id="proteinsNewProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="proteinsNewProduct" class="formText2"><@spring.message "label.proteins" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="fatsProduct" maxlength="9" autofocus id="fatsNewProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="fatsNewProduct" class="formText2"><@spring.message "label.fats" /></label>

                                        </p>
                                        <p>
                                        <input type="number" name="carbsProduct" maxlength="9" autofocus id="carbsNewProduct"
                                                                               value=""  class="formInput2" placeholder="Input proteins">
                                                                        <label for="carbsNewProduct" class="formText2"><@spring.message "label.carbs" /></label>

                                        </p><br/><br/><br/><br/><br/><br/><br/>
                                                                    <button class="uui-button blue small left-icon">
                                                                        <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.insert" />
                                        </button><br/>
                                                                    ${messageInsertProduct}
                                        </form>
                                        </div>

                    <div class="blockAdvice">
                        <#include "calculator/calculator_calories.ftl"/><br/>
                    </div>


                </div>

            </div>
        </div>
                    <div class="block3">
                        <h4>Информация аккаунта</h4>
                        <h6 class="styleMessage">Статус : ${user.role}</h6>
                        <h6 class="styleMessage">Баланс : ${user.balance} $</h6>
                        <h4><@spring.message "label.topcurator" /></h4>
                        <h3>TEMP</h3>
                        <br/>
                        <h4><@spring.message "label.oursponsors" /></h4>
                        <div class="iconMain">
                            <img src="/picture/oracle.png" class="icon1">
                        </div>
                        <br/>
                        <div class="iconMain">
                            <img src="/picture/mysql.jpg" class="icon1">
                        </div>
                        <br/>
                        <div class="iconMain">
                            <img src="/picture/tomcat.png" class="icon1">
                        </div>

                    </div>

             </div>
        </div>

</div>
<#include "static/footer.ftl">

</body>
</html>