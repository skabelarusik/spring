<#import "/spring.ftl" as spring/>
<html>
<head>
<title><@spring.message "title.login"/></title>
<#include "/WEB-INF/views/static/template_main.ftl">
</head>

<body>
<#include "/WEB-INF/views/static/header.ftl">
<div class="wrapperLogin">
                 <div class="wrapperLoginInner">
                       <div class="blockLogin2">
                       <!-- Login form -->

<div id="uuiCarousel" class="uui-carousel carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#uuiCarousel" data-slide-to="0" class="active"></li>
<li data-target="#uuiCarousel" data-slide-to="1"></li>
<li data-target="#uuiCarousel" data-slide-to="2"></li>
</ol>
<div class="carousel-inner" role="listbox">
        <div class="item active">
            <div class="carousel-caption">
                <h3><@spring.message "slide.three"/></h3>
</div>
</div>
<div class="item">
            <div class="carousel-caption">
                 <h3><@spring.message "slide.one"/></h3>
</div>
</div>
<div class="item">
            <div class="carousel-caption">
                 <h3><@spring.message "slide.two"/></h3>
</div>
</div>
</div>
<a class="left carousel-control" href="#uuiCarousel" role="button" data-slide="prev">
        <i class="fa fa-chevron-left"></i>
</a>
<a class="right carousel-control" href="#uuiCarousel" role="button" data-slide="next">
        <i class="fa fa-chevron-right"></i>
</a>
</div>
<script>
$('.uui-carousel').carousel({
        interval: 2000
    });
</script>

<a href="/user/main" class="uui-button raspberry">ВОЙТИ!</a>

</div>
<!-- registration block -->
<div class="blockReg">
                             <a class="aTagCrackerLogin" href="/user/registration">
                             <h5 class="h3login"><@spring.message "label.withoutacc"/></h5></a>
</div>


<div class="languageLogin">
                            <div class="uui-input-group horizontal">

                                 <form class="language" method="post" action="/lang">
                                    <input type="hidden" name="currentPage" value="index">
                                    <button class="uui-button small" name="langv" value="BY">BY</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="index">
                                                                <button class="uui-button small" name="langv" value="RU">RU</button>
</form>
<form class="language" method="post" action="/lang">
                                                                <input type="hidden" name="currentPage" value="index">
                                                                <button class="uui-button small" name="langv" value="US">EN</button>
</form>
</div>
</div>
</div>
</div>
<#include "/WEB-INF/views/static/footer_main.ftl">
</body>
</html>