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
        <h1>Advices</h1>
        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Text</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list listAdvice as advice>
                <tr>
                    <td width="20px">${advice.id}</td>
                    <td width="100px">${advice.text}</td>
                    <td class="prod3" width="60px">
                        <#if role == "ADMIN" && role != null>
                        <form class="changePageForm" method="post" action="/advice/delete">
                            <input type="hidden" name="id" value="${advice.id}">
                            <input type="hidden" name="currentPage" value="resultAdvice">
                            <input type="submit"  value="delete" class="uploadButtonTable"/>
                        </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            <h3>${addmessagedelete}</h3>
            </tbody>
        </table>


        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>
