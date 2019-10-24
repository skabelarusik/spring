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
        <h1>Review list</h1>
        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Date</th>
                <th>Text</th>
                <th>Action</th>
            </tr>
            </tr>
            </thead>
            <tbody>
            <#list reviewList as review>
                <tr>
                    <td width="20px">${review.id}</td>
                    <td width="100px">${review.name}</td>
                    <td width=100px">${review.date}</td>
                    <td width="100px">${review.text}</td>
                    <#if role == "ADMIN">
                        <td class="prod3" width="60px">
                            <form class="changePageForm" method="post" action="/review/delete">
                                <input type="hidden" name="id" value="${review.id}">
                                <input type="hidden" name="buttonName" value="${buttonName}">
                                <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
<#--            <#list reviewList2 as review>-->
<#--                <tr>-->
<#--                    <td width="20px">${review.id}</td>-->
<#--                    <td width="100px">${review.name}</td>-->
<#--                    <td width=100px">${review.date}</td>-->
<#--                    <td width="100px">${review.text}</td>-->
<#--                </tr>-->
<#--            </#list>-->
            </tbody>
        </table>
        <h3>${messageReview}</h3>
        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>


