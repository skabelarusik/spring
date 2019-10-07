<#import "/spring.ftl" as spring/>

<html>
<body>
<div id="wrapperFooter">
    <div id="footerAuthor">
        <@spring.message "label.footer" />
    </div>
    <div id="footerReviews">
        <h4><a href="/review/send" class="adminBlock"><@spring.message "label.sendreview"/></a></h4>
        <p class = "footersize"><@spring.message "build.timestamp"/></p>
    </div>
</div>
</body>
</html>
