<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<head>
    <style type="text/css">
        <#include "css/style.css">
    </style>
    <title><@spring.message "title.user"/></title>
    <#setting classic_compatible=true>
</head>
<body>
<div class="wrapper">
    <header>
        <#include "header.ftl">
    </header>

    <!-- language panel -->

    <div class="languageMain">
        <form class="language" method="get" action="/lang/en">
        <input type="hidden" name="currentPage" value="user">
            <input type="submit" class="lan1" name="langv" value="EN">
        </form>
        <form class="language" method="get" action="/lang/ru">
        <input type="hidden" name="currentPage" value="user">
            <input type="submit" class="lan2" name="langv" value="RU">
        </form>
        <form class="language" method="get" action="/lang/by">
        <input type="hidden" name="currentPage" value="user">
            <input type="submit" class="lan3" name="langv" value="BY">
        </form>
    </div>

    <div class="wrapperBlocks">
        <!-- left column -->
        <div class="block1">
            <h3><@spring.message "label.welcome"/>, ${user.login}</h3>

            <!-- avatar -->
            <div class="icon">
                <img src="${user.path}" class="icon1"/>
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
                <h4><@spring.message "label.message" /></h4>
                <h5><a href="/message/input"><@spring.message "label.input"/></a></h5>
                <h5><a href="/message/output"><@spring.message "label.output"/></a></h5>
                <h5><a href="/message/send_mes"><@spring.message "label.sendmessage"/> </a></h5>
            </div>
        </div>

        <div class="block2">
            <div class="searchheader">
                <form class="adviceForm" method="post" action="/product/search">
                <input type="hidden" name="currentPage" value="user">
                    <p><input  class="adminBlock" type="search" name="text" value="" placeholder="Поиск продуктов по сайту">
                        <input  class="adminBlock" type="submit" value="Найти"></p>
                </form>
                ${wrongSearch}
            </div>
            <h4><br/><@spring.message "label.logoutmess"/> </h4>
            <a href="/logout" class="aBlock2"><h5><@spring.message "label.logout"/></h5></a>
            <br/>

            <!-- panel users -->
            <div class="wrapperBlock2">
                <div class="blockUsers">
                    <!-- panel calculate -->
                    <div class="productBlock">
                        <#include "calculator.ftl"/><br/>
                        <#include "calculator_rate.ftl"/><br/>
                    </div>

                    <div class="blockLearner">
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

                        <h4 class="adminBlock"><@spring.message "label.reqcurator" /> </h4>
                        <a href="/user/request"><h5 class="adminBlock"><@spring.message "button.click"/></h5></a><br/>

                    </div><br/></div>

                <!-- panel product -->
                <div class="anotherBlock">
                    <div class="productBlock">
                        <h4 class="adminBlock"><@spring.message "label.productbox" /></h4>
                        <a href="/product/select">
                            <h5 class="adminBlock"><@spring.message "label.selectproduct" /> </h5>
                        </a><br/>
                        <!-- select products by callories -->
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
                            <input type="submit" class="login-button" value="<@spring.message "button.show"/>"/>
                        </form><br/>
                        ${messageWrongProduct}
                    </div>


                    <!-- user calculator -->
                    <div class="blockAdvice">
                        <#include "calculator_calories.ftl"/><br/>
                    </div>


                    <!-- block advices -->
                    <div class="blockAdvice">
                        <h4 class="adminBlock"><@spring.message "label.showprogname" /></h4>
                        <a href="/advice/select"><h5 class="adminBlock">
                                <@spring.message "label.show" /> </h5></a><br/>

                            <h4 class="adminBlock">
                                <@spring.message "label.showsub" /></h4>
                            <a href="/subscription/show_history"><h5 class="adminBlock">
                                    <@spring.message "label.show_history_subs" /></h5></a>
                    </div>
                    <br/>

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
                <img src="/picture/oracle.png" class="icon1">
            </div>
            <br/>
            <div class="icon">
                <img src="/picture/mysql.jpg" class="icon1">
            </div>
            <br/>
            <div class="icon">
                <img src="/picture/tomcat.png" class="icon1">
            </div>
        </div>

    <footer>
        <#include "footer.ftl">
    </footer>
</div>

</div>

</body>
</html>