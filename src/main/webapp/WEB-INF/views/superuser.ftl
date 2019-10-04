<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <style type="text/css">
            <#include "/css/style.css">
        </style>
        <title>SUPERUSER PAGE</title>
    </head>

<body>
<div class="wrapper">
    <script type="text/javascript">
        alert("HELLO SUPERUSER!!!")
    </script>

    <div class="wrapperBlocks">
    <h1>HI, ${login}</h1>
    <h1>${name}</h1>
    <h1>${balance}</h1>

           <h1><a href="/users">go to Users</a></h1>
    </div>
</div>

</body>
</html>