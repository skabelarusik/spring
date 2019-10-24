<#import "/spring.ftl" as spring/>
<html>
<body>
<div class="calcBlock2">
    <form class="adminBlock" method="post" action="/bucket/add">
        <h4 class="adminBlock"><@spring.message "label.addproduct" /></h4>
        <p>
            <label for="nameProduct" class="formText2"><@spring.message "label.name" /></label>
            <input type="text" name="nameProduct" maxlength="35" autofocus id="nameProduct"
                   data-required="true" value=""  class="formInput4" placeholder="Input name">
        </p>
        <p>
            <label for="count" class="formText2"><@spring.message "label.name" /></label>
            <input type="text" name="portions" maxlength="35" autofocus id="count"
                   data-required="true" value=""  class="formInput4" placeholder="Input portions">
        </p>
        <input type="submit" class="login-button" value="<@spring.message "button.insert" />"/>

        <br/>
        ${messageProduct}
    </form>
    <form class="changePageForm" method="post" action="/bucket/clear">
        <input type="hidden" name="command" value="delete_product_bucket">
        <input type="submit"  value="<@spring.message "button.clear" />" class="login-button"/>
    </form>
    <form id="calculator" class="adminBlock" action="/bucket/calculate" method="post">
        <p>
            <c:if test="${listProductsBucket}">
                <table border="1" width="220" class="userTable" cellspacing="0" cellpadding="4">
                    <td width="80" class="prod3">PRODUCT</td>
                    <td width="30px" class="prod3">CAL.</td>
                    <td width="30px" class="prod3">PORT.</td>
                    <td width="60px" class="prod3">DELETE</td></table>
            </c:if>
            <c:forEach items="${listProductsBucket}" var="product">
                <table border="1" width="220px" class="userTable" cellspacing="0" cellpadding="4">
                    <tr>
                        <td width="80">${product.product}</td>
                        <td width="30px">${product.calories}</td>
                        <td width="30px">${product.portions}</td>
                        <td width="60px" class="prod3">
                            <form class="changePageForm" method="post" action="/bucket/delete">
                                <input type="hidden" name="command" value="delete_product_id_bucket">
                                <input type="hidden" name="id" value="${product.id}">
                                <input type="submit"  value="<@spring.message "button.delete" />" class="uploadButtonBucket"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:forEach>
            <br/>
        <form class="changePageForm" method="post" action="/clear_bucket">
            <input type="hidden" name="command" value="calculate_calories_bucket">
            <input type="submit" class="login-button" value="<@spring.message "button.calculate" />">
        </form>

        Result: ${result}
        </p>
    </form>
</div>
</body>
</html>