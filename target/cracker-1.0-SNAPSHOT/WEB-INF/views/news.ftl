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

                              <div class="uui-timeline">
                            <div class="date-wrapper">
                                <div class="line">
                                    <p class="date">21-10-2019</p>
                        </div>
                        </div>
                        <ul class="timeline-wrapper">
                                <li class="timeline-body">
                                    <div class="line">
                                        <div class="item-wrapper">
                                            <div class="timeline-section icon">
                                                <span class="uui-icon timeline-icon">
                                                    <i class="fa fa-bell-o"></i>
                        </span>
                        </div>
                        <div class="timeline-section desk">
                                                <div class="timeline-desk">
                                                    <p class="title">Привет</p>
                        <p class="message">Был создан данный проект</p>
                        <p class="time"><span>16:00 pm</span></p>
                        </div>
                        </div>
                        </div>
                        </div>
                        </li>
                                ...
                                <li class="timeline-body">
                                    <div class="line">
                                        <div class="item-wrapper">
                                            <div class="timeline-section icon">
                                                <span class="uui-icon timeline-icon">
                                                    <i class="fa fa-bell-o"></i>
                        </span>
                        </div>
                        <div class="timeline-section desk">
                                                <div class="timeline-desk">
                                                    <p class="title">Реклама?</p>
                        <p class="message">Так-то это строка для теста, но вполне могла быть Ваша реклама</p>
                        <p class="time"><span>11:45 pm</span></p>
                        </div>
                        </div>
                        </div>
                        </div>
                        </li>
                        </ul>
                        <div class="date-wrapper">
                                <div class="line">
                                    <p class="date">20 октября 2019</p>
                        </div>
                        </div>
                        <ul class="timeline-wrapper">
                                ...
                            </ul>
                        <div class="date-wrapper">
                                <div class="line">
                                    <p class="date">19 октября 2019</p>
                        </div>
                        </div>
                        </div>


<h3>${messageProgram}</h3>

<br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
</h4>
</div>
</div>
<#include "static/footer.ftl">
</body>
</html>


