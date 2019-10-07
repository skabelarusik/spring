
<#import "/spring.ftl" as spring/>

<html>
<head>
</head>
<body>

<div class="nameheader" id="crackhead">
    <h3>Tracker Cracker</h3>
    <h5>It is the best of fit applications</h5>
</div>

<div class="infoheader">
    <small>${advice}</small>
</div>
<div id="userHeader" class="adminBlock">
    <h5 class="adminBlock"><small>${user.role}</small>, ${user.balance}$</h5>
    <h6><a href="/user/deposit" class="adminBlock">Пополнить баланс?</a></h6>
</div>

</body>
</html>
