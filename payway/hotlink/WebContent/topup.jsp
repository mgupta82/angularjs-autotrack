<html>

<head>
<title>Top-up</title>
	<script src="external/jquery/jquery.js"></script>
	<script src="jquery-ui.min.js"></script>
     <script type="text/javascript">
     $(document).ready(function(){
		//Define Complete Button
        $("#childButton").click(function(){
            window.opener.$(window.opener.document).trigger('mainWindow', 'Top-up Complete');
            //window.opener.location.reload(true);
            self.close();
        });
     });   
     
     </script>	
</head>

<body>
Top-up<br/>
<input type="button" value="Complete" id="childButton"/>
</body>


</html>