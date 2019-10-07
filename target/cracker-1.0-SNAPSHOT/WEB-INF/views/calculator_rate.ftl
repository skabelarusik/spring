<#import "/spring.ftl" as spring/>
<html>
<body>
<div class="calcBlock">
    <form id="calculator" class="adminBlock" action="/calculate_calories" method="post">
        <h4 class="adminBlock"><@spring.message "title.ratecalories" /></h4>
        <label for="age" class="adminBlock"><@spring.message "label.inputage"/></label><input name="age" class="whiteBlock" id="age"><br/>
        <label for="weight" class="adminBlock"><@spring.message "label.inputweight"/></label><input name="weight" class="whiteBlock" id="weight"><br/>
        <label for="height" class="adminBlock"><@spring.message "label.inputheight"/></label> <input name="height" class="whiteBlock" id="height" ><br/>
        <p class="adminBlock">
            <@spring.message "label.gender" /> *<br/>
            <label for="sex1" class="gender"> <@spring.message "label.sexmale"/></label>
            <input autofocus id="sex1" type="radio" name="gender"  value="Male" checked>
            <label for="sex2" class="gender"> <@spring.message "label.sexfemale"/></label>
            <input autofocus id="sex2" type="radio" name="gender" value="Female"></p></br>

        <p class="adminBlock">
            <@spring.message "label.active" /> <br/>
            <input autofocus id="act1" type="radio" name="active"  value="act1" checked><br/>
            <label for="act1" class="gender"> <@spring.message "radio.active1" /></label>
            <input autofocus id="act2" type="radio" name="active" value="act2">
            <label for="act2" class="gender"> <@spring.message "radio.active2" /></label><br/>
            <input autofocus id="act3" type="radio" name="active"  value="act3" >
            <label for="act3" class="gender"> <@spring.message "radio.active3" /></label>
            <br/>
            <input autofocus id="act4" type="radio" name="active" value="act4">
            <label for="act4" class="gender"> <@spring.message "radio.active4" /></label>
        </p></br>
        <input type="submit" class="login-button" value="<@spring.message "button.calculate" />">
        Result: ${result_calories}
        </p>
    </form>
</div>
</body>
</html>