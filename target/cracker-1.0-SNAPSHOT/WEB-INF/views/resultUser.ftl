<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.result"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperLogin">
    <div class="wrapperUserResultInner">
        <h1>Program name list</h1>

        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Role</th>
                <th>Gender</th>
                <th>BirthDate</th>
                <th>registrDate</th>
                <th>Message</th>
            </tr>
            </thead>
            <tbody>

            <#list listUser as user>
                <tr>
                    <td width="20px">${user.id}</td>
                    <td width="100px">${user.login}</td>
                    <td width=100px">${user.name}</td>
                    <td width="100px">${user.surname}</td>
                    <td width="80px">${user.role}</td>
                    <td width="60px">${user.gender}</td>
                    <td width="70px">${user.birthDate}</td>
                    <td width="70px">${user.registrDate}</td>
                    <td width="60px">
                        <form class="changePageForm" method="post" action="/message/send_mes">
                            <input type="hidden" name="command" value="user_message">
                            <input type="hidden" name="recipient" value="${user.login}">
                            <input type="submit"  value="message" class="uploadButtonTable"/>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <h3>${messageProgram}</h3>

        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
    </div>
</div>
<#include "static/footer.ftl">
</body>
</html>




