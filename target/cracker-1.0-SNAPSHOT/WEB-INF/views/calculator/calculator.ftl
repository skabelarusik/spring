<#import "/spring.ftl" as spring/>
<html>
<body>
<div class="calcBlock">
    <form id="calculator" class="adminBlock" action="/calculate" method="post">
        <p>
        <h6 class="styleMessageTitle"><@spring.message "title.indexweight" /></h6>
        <label for="weight" class="adminBlock"><@spring.message "label.inputweight"/></label><input name="weight" class="whiteBlock" id="weight"><br/>
        <label for="height" class="adminBlock"><@spring.message "label.inputheight" /></label> <input name="height" class="whiteBlock" id="height" ><br/>
        <br/><input type="submit" class="login-button" value="<@spring.message "button.calculate" />">
        Result: ${result}
        </p>
    </form>
</div>
</body>
</html>