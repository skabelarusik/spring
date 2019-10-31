<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.result"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperLogin">
    <div class="wrapperLoginInner">
        <h1>Program name list</h1>

        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Curator</th>
                <th>Cost</th>
                <th>Duration</th>
                <#if role != "SUPERUSER">
                <th>Action</th>
                </#if>
                <#if role = "CURATOR">
                    <th>UPDATE</th>
                    <th>EDIT PROGRAM</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list programName as program>
                <tr>
                    <td width="20px">${program.id}</td>
                    <td width="100px">${program.name}</td>
                    <td width=100px">${program.curator}</td>
                    <td width="80px">${program.cost}</td>
                    <td width="80px">${program.duration}</td>
                    <#if role == "USER">
                    <td class="prod3" width="60px">
                        <form class="changePageForm" method="post" action="/subscription/buy">
                            <input type="hidden" name="id" value="${program.id}">
                            <input type="hidden" name="name" value="${program.name}">
                            <input type="hidden" name="duration" value="${program.duration}">
                            <input type="hidden" name="cost" value="${program.cost}">
                            <input type="submit"  value="BUY" class="uploadButtonTable"/>
                        </form>
                    </td>
                    </#if>
                    <#if role == "ADMIN" || role = "CURATOR">
                    <td class="prod3" width="60px">
                        <form class="changePageForm" method="post" action="/program_name/delete">
                            <input type="hidden" name="id" value="${program.id}">
                            <input type="hidden" name="buttonName" value="${buttonName}">
                            <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                        </form>
                    </td>
                    </#if>
                    <#if role = "CURATOR">
                    <td class="prod3" width="60px">
                        <form class="changePageForm" method="get" action="/program_name/update">
                            <input type="hidden" name="id" value="${program.id}">
                            <input type="hidden" name="name" value="${program.name}">
                            <input type="hidden" name="duration" value="${program.duration}">
                            <input type="hidden" name="cost" value="${program.cost}">
                            <input type="submit"  value="UPDATE" class="uploadButtonTable"/>
                        </form>
                    </td>
                    <td class="prod3" width="60px">
                        <form class="changePageForm" method="get" action="/program/select">
                            <input type="hidden" name="name" value="${program.name}">
                            <input type="submit"  value="UPDATE" class="uploadButtonTable"/>
                        </form>
                    </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>

        <h3>${messageProgram}</h3>

        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
<br/><br/>
<br/>
<br/>
<br/>

    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>


