<html>
    <head>
        <title>TEST</title>
            <!-- jQuery Core -->
            <script src="resources/js/lib/jquery-1.12.0.min.js"></script>

            <!-- Bootstrap Core -->
            <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" />
            <script src="resources/bootstrap/js/bootstrap.min.js"></script>

            <!-- EPAM UUI JavaScript Core -->
            <script src="resources/js/uui-core.min.js" type="text/javascript"></script>

            <!-- EPAM UUI Styles Core -->
            <!-- Import the uui-all.less file in your custom LESS file -->
            @import 'uui-all.less';
            <!-- Your custom Styles -->
            <link rel="stylesheet/less" type="text/css" href="less/custom-styles.less" />

            <!-- LESS JavaScript Core -->
            <script src="resources/js/lib/less.js" type="text/javascript"></script>

            <!-- Scroll for UUI Sidebar -->
            <link rel="stylesheet" href="resources/css/lib/components/jquery.mCustomScrollbar.min.css" />
            <script src="resources/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>
    </head>
    <body>
    <!-- top menu -->
        <div>
        <h1>TEST</H1>
        // Default button
        <button class="uui-button">Default button</button>
        </div>

<input type="text" class="uui-form-element" placeholder="Type ahead" />

<script>
    var source = ["Alabama","Alaska","Arizona","Arkansas","California",
        "Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa",
        "Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi",
        "Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota",
        "North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota",
        "Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"];

    $('.uui-form-element').uui_autocomplete({
        source: source,
        items: 4
    });
</script>
    </body>
</html>


