<html>
<head>
	<link rel="stylesheet" href="jquery-ui.min.css">
	<script src="external/jquery/jquery.js"></script>
	<script src="jquery-ui.min.js"></script>
	<title>Express Top-up</title>
     <script type="text/javascript">
     $(document).ready(function(){
		//Define Complete Button
        $("#childButton").click(function(){
            window.opener.$(window.opener.document).trigger('mainWindow', 'Express Top-up Complete');
            //window.opener.location.href = "http://localhost:7080/payment/index.html"
            self.close();
        });
     });
     </script>	

</head>
<body>

Express Top up.<br/>
<input type="button" value="Complete" id="childButton"/>

</body>
</html>