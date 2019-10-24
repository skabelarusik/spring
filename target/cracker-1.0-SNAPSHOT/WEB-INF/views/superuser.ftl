<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.admin"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>
<body>

<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperLoginAdmin">

    <!-- language panel -->
    <div class="languageMain">
        <div class="uui-input-group horizontal">
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="superuser">
                <button class="uui-button small lime-green" name="langv" value="BY">BY</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="superuser">
                <button class="uui-button small raspberry" name="langv" value="RU">RU</button>
            </form>
            <form class="language" method="post" action="/lang">
                <input type="hidden" name="currentPage" value="superuser">
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
                    <input type="hidden" name="currentPage" value="superuser">
                    <input  class="adminBlock2" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                    <button class="uui-button small dark-gray"><@spring.message "button.search"/></button>
                </form>
            </div>
            <br/>

           <div class="wrapperBlock2">
                <div class="blockUsers">
                    <!-- panel calculate -->
                    <div class="productBlock">
                        <#include "calculator/calculator.ftl"/><br/>
                        <#include "calculator/calculator_rate.ftl"/><br/>
                    </div>

                    <div class="blockLearner">
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

                        <h6 class="styleMessageTitleSmall"><@spring.message "label.reqcurator" /> </h6>
                        <a href="/user/request"><h5 class="adminBlock"><@spring.message "button.click"/></h5></a><br/>

                    </div><br/>
                </div>
                    <!-- panel product -->
                    <div class="anotherBlock">
                        <div class="productBlock">
                            <h6 class="styleMessageTitleSmall"><@spring.message "label.productbox" /></h6>
                            <a href="/product/select">
                                <h5 class="styleMessageSmall"><@spring.message "label.selectproduct" /> </h5>
                            </a>
                            <form class="adminBlock" method="post" action="/product/select_by_calories">
                                <input type="hidden" name="currentPage" value="superuser">
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
                                <p><br/><br/><br/>
                                    <button class="uui-button blue small left-icon">
                                        <img src="/resources/images/icons/no-results.png" alt="" /><@spring.message "button.show" />
                                    </button>
                                </p>
                            </form>
                            ${messageWrongProduct}
                        </div>


                        <!-- user calculator -->
                        <div class="blockAdvice">
                            <#include "calculator/calculator_calories.ftl"/><br/>
                        </div>


                        <!-- block advices -->
                        <div class="blockAdvice">
                            <h6 class="styleMessageTitleSmall"><@spring.message "label.showprogname" /></h6>
                            <a href="/program_name/select"><h5 class="styleMessageSmall">
                                    <@spring.message "label.show" /> </h5></a><br/>

                            <h6 class="styleMessageTitleSmall">
                                <@spring.message "label.showsub" /></h6>
                            <a href="/subscription/show_history"><h5 class="styleMessageSmall">
                                    <@spring.message "label.show_history_subs" /></h5></a>
                        </div>
                        <br/>

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
<#--    footer   -->
<#include "static/footer.ftl">

</body>
</html>