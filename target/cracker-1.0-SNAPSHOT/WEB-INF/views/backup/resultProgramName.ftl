<#import "/spring.ftl" as spring/>

<html>
<head>
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title><@spring.message "title.service"/></title>
        <#setting classic_compatible=true>
    </head>
    <body>
    <header class="headLogin">
    </header>
    <div class="headName">
        <h2 class="headH2">&nbsp;&nbsp;&nbsp;&nbsp;CrackerTracker
            <small class="headH2small">&nbsp; - your best helper!</small></h2>
    </div>
    <div class="wrapResult">
        <div class="wrapperReview">
        <h1>Program name list</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Curator</th>
                <th>Cost</th>
                <th>Duration</th>
            </tr>
        <#list programName as program>
            <tr>
                <td width="20px">${program.id}</td>
                <td width="100px">${program.name}</td>
                <td width=100px">${program.curator}</td>
                <td width="80px">${program.cost}</td>
                <td width="80px">${program.duration}</td>
                <td class="prod3" width="60px">
                    <form class="changePageForm" method="post" action="/program_name/delete">
                       <input type="hidden" name="id" value="${message.id}">
                       <input type="hidden" name="buttonName" value="${buttonName}">
                       <input type="submit"  value="${buttonName}" class="uploadButtonTable"/>
                   </form>
                </td>
             </tr>
        </#list>
        </table>
    </div>
        <br/>
    <h4 class="h3login"><a class="registration" href='/back'>
            <@spring.message "label.backmain" /></a>
    </h4>

    </div>
    </body>
</html>