<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.result"/></title>
<#include "/WEB-INF/views/static/template.ftl">
</head>
<body>
<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperResult">
    <div class="wrapperLoginInner">
              <div class="blockLogin"><br/>
                            <!-- Login form -->
                <h3 class="h3login">add product</h3><br/><br/>
                    <form class="loginForm" method="post" action="/program/add">
                    <p>
                    <input type="hidden" name="name" value="${name}">
                    <input type="text" name="product" maxlength="45" autofocus id="loginField"
                                                               data-required="true" value=""  class="formInput" />
                                                        <label for="loginField" class="formText">Name product</label>
                    </p><br/>
                    <p>
                            <input type="text" name="portions" maxlength="45" autofocus id="loginField"
                                  data-required="true" value=""  class="formInput" />
                            <label for="loginField" class="formText">Portions</label>
                    </p><br/>
                    <h6>DAY</h6>
                    <div class="uui-input-group vertical">
                           <p class="uui-radio">
                            <input type="radio" name="day" value="MONDAY" id="a1" checked />
                                  <label for="a1">MONDAY</label>
                            <input type="radio" name="day" value="TUESDAY" id="a2" />
                                   <label for="a2">TUESDAY</label>
                            <input type="radio" name="day" value="WEDNESDAY" id="a3" />
                                  <label for="a3">WEDNESDAY</label>
                            <input type="radio" name="day" value="THURSDAY" id="a4" />
                                   <label for="a4">THURSDAY</label>
                            <input type="radio" name="day" value="FRIDAY" id="a5" />
                                   <label for="a5">FRIDAY</label>
                            <input type="radio" name="day" value="SATURDAY" id="a6" />
                                  <label for="a6">SATURDAY</label>
                            <input type="radio" name="day" value="SUNDAY" id="a7" />
                                   <label for="a7">SUNDAY</label>
                            </p>
                    </div>
                    <h6>TIME</h6>
                        <div class="uui-input-group vertical">
                                                     <p class="uui-radio">
                            <input type="radio" name="time" value="BREAKFAST" id="b1" checked />
                                  <label for="b1">BREAKFAST</label>
                            <input type="radio" name="time" value="SECOND_BREAKFAST" id="b2" />
                                   <label for="b2">SECOND_BREAKFAST</label>
                            <input type="radio" name="time" value="LUNCH" id="b3" />
                                  <label for="b3">LUNCH</label>
                            <input type="radio" name="time" value="SECOND_LUNCH" id="b4" />
                                   <label for="b4">SECOND_LUNCH</label>
                            <input type="radio" name="time" value="DINNER" id="b5" />
                                   <label for="b5">DINNER</label>
                            <input type="radio" name="time" value="SECOND_DINNER" id="b6" />
                                  <label for="b6">SECOND_DINNER</label>
                        </p>
                        </div>
                        <br/><br/>
                                <input type="submit" value="INSERT" class="login-button"/>
                                    <br/>
                        ${messageAdd}
                    </form>
                </div>
        <h1>Program component list</h1>

<table class="uui-table stripe">
            <thead>
<tr>
<th>Id</th>
<th>Name</th>
<th>Product</th>
<th>Portions</th>
<th>Time</th>
<th>Day</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<#list listPrograms as program>
<tr>
<td width="20px">${program.id}</td>
<td width="100px">${program.programName}</td>
<td width=100px">${program.product}</td>
<td width="100px">${program.portions}</td>
<td width="80px">${program.time}</td>
<td width="60px">${program.day}</td>
<td class="prod3" width="60px">
        <form class="changePageForm" method="get" action="/program/delete">
              <input type="hidden" name="id" value="${program.id}">
              <input type="hidden" name="name" value="${program.programName}">
              <input type="submit"  value="DELETE" class="uploadButtonTable"/>
        </form>
</td>
</tr>
</#list>
</tbody>
</table>
${wrongData}
<br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
</h4>
</div>
</div>
</body>
</html>
