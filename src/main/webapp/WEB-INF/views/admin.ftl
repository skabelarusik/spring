<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.admin"/></title>
<#include "/WEB-INF/views/static/template.ftl">
</head>
<body>

<#include "/WEB-INF/views/static/header_main.ftl">

<div class="wrapperLoginAdmin">

    <div class="languageMain">
        <div class="uui-input-group horizontal">
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="admin">
                <button class="uui-button small lime-green" name="langv" value="BY">BY</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="admin">
                <button class="uui-button small raspberry" name="langv" value="RU">RU</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="admin">
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
            <div class="uploadFile">
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
                    <input type="hidden" name="currentPage" value="admin">
                    <input  class="adminBlock2" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                    <button class="uui-button small dark-gray"><@spring.message "button.search"/></button>
                </form>
            </div>
            <br/>

            <!-- panel users -->
            <div class="wrapperBlock2">
                <div class="blockUsers">
                    <div class="productBlock">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showuser"/></h6>
                        <a href="/user/select"><h5 class="styleMessageSmall"><@spring.message "button.show"/></h5></a>
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showuserstatus"/></h6>
                        <form class="adminBlock" method="post" action="/user/select_by_status">
                            <p>
                                <label for="user" class="adviceForm"> <@spring.message "label.user"/></label>
                                <input autofocus id="user" type="radio" name="role"  value="user" checked>
                                <label for="superuser" class="adviceForm"> <@spring.message "label.superuser"/></label>
                                <input autofocus id="superuser" type="radio" name="role" value="superuser">

                                <label for="curator" class="adviceForm"> <@spring.message "label.curator"/></label>
                                <input autofocus id="curator" type="radio" name="role" value="curator">
                                <label for="admin" class="adviceForm"> <@spring.message "label.admin"/></label>
                                <input autofocus id="admin" type="radio" name="role" value="admin"></br>
                            </p>
                            <button class="uui-button blue small left-icon">
                                <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
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
                                <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
                            </button>
                        </form> ${wrongData}<br/>

                        <!-- update role user -->
                        <form class="adminBlock" method="post" action="/user/update_role">
                            <input type="hidden" name="currentPage" value="admin">
                            <h6 class="styleMessageTitleSmall"><@spring.message "label.updateroleuser" /></h6>
                            <p><label for="idRole" class="formText2">Id</label>
                                <input type="text" name = "id" id="idRole"  value="" placeholder="id" class="formInput2"/></p>
                            <br/>
                            <p>
                                <label for="userRole" class="adviceForm"> <@spring.message "label.user" /></label>
                                <input autofocus id="userRole" type="radio" name="role"  value="user" checked>
                                <label for="superuserRole" class="adviceForm"> <@spring.message "label.superuser"/></label>
                                <input autofocus id="superuserRole" type="radio" name="role" value="superuser">
                                <label for="curatorRole" class="adviceForm"> <@spring.message "label.curator"/></label>
                                <input autofocus id="curatorRole" type="radio" name="role" value="curator">
                                <label for="adminRole" class="adviceForm"> <@spring.message "label.admin" /></label>
                                <input autofocus id="adminRole" type="radio" name="role" value="admin"></br>
                            </p>
                            <button class="uui-button blue small left-icon">
                                <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.update" />
                            </button>                        </form>
                       ${updateMessage}
                        <br/>
                    </div>

                    <!-- panel review -->
                    <div class="blockAdvice">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.review"/></h6>
                        <a href="/review/show"><h5 class="styleMessageSmall">SelectAllReview</h5></a>
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.reviewdeleted"/></h6>
                        <a href="/review/show_del"><h5 class="styleMessageSmall">SelectAllReview</h5></a>
                        <!-- delete review -->
                        <form class="adminBlock" method="post" action="/review/delete_by_id">
                            <h6 class="styleMessageTitleSmall"><@spring.message "label.deletereview" /></h6>
                            <p><label for="idRev" class="formText2">Id</label>
                                <input type="text" name = "id" id="idRev"  value="" placeholder="id" class="formInput2"/></p>
                            <input type="hidden" name="currentPage" value="admin">
                            <br/>
                            <button class="uui-button blue small left-icon">
                                <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.delete" />
                            </button>                            ${messageReview}
                        </form>
                        <br/>
                    </div>

                    <!-- panel subs -->
                    <div class="blockLearner">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showprogname" /></h6>
                        <a href="/program_name/select"><h5 class="styleMessageSmall">
                                <@spring.message "label.show" /></h5></a>
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showdeleteprograme" /></h6>
                        <a href="/program_name/select_del"><h5 class="styleMessageSmall">
                                <@spring.message "label.show" /></h5></a>

                        <h6 class="styleMessageTitleSmall"><@spring.message "label.showsub" /></h6>
                        <a href="/subscription/select"><h5 class="styleMessageSmall">
                                <@spring.message "label.show" /></h5></a>
                    </div>
                    <br/>
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
                            <input type="hidden" name="currentPage" value="admin">
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

                    <!-- block advices -->
                    <div class="blockAdvice">
                        <h6 class="styleMessageTitleSmall"><@spring.message "label.advicebox" /></h6>
                        <a href="/advice/select"><h6 class="styleMessageSmall">
                                <@spring.message "label.selectadvice" /> </h6></a><br/>
                        <form class="adviceForm" method="post" action="/advice/add">
                            <input type="hidden" name="currentPage" value="admin">
                            <h6 class="styleMessageTitleSmall">
                                <@spring.message "label.addadvice" /></h6>
                            <input type="text" name = "advice" placeholder="message" id="advice" value="" class="formInputAdmin"/>
                            <br/><br/>
                            <button class="uui-button blue small left-icon">
                                <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.insert" />
                            </button>
                            <br/>
                            ${addmessage}
                        </form>

                    </div>
                </div>

            </div>
        </div>
        <div class="block3">
            <h4>Информация аккаунта</h4>
            <h6 class="styleMessage">Статус : ${role}</h6>
            <h6 class="styleMessage">Баланс : ${balance} $</h6>
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

<#--    footer   -->
<#include "static/footer.ftl">


</body>
</html>