<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="jquery-ui.min.css">
		<script src="external/jquery/jquery.js"></script>
		<script src="jquery-ui.min.js"></script>
		<script  type="text/javascript">
		var childWindow=null;
		
		$(function() {
	        //Create mainWindow Event
	        $(document).bind('mainWindow', function(e, message){
	            $("#mainMessage").val(message);
	        });
	        //Define Popup opener
	        $("#hotlink-popup").click(function(){
	            childWindow = window.open('hotpay.html',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=no,width=600, height=280,top=200,left=200"); 
	        });
		});

		function parent_disable(){
			if(childWindow && !childWindow.closed)
				childWindow.focus();
		}
		
		</script>
	</head>
	<body onFocus="parent_disable();" onclick="parent_disable();">
		Order Id:
		<br/>
		Publisher Id :
		<br/>
		<button id="hotlink-popup">Hotlink Topup</button><br/>
		<br/>
		Final Response : <input type="text" id="mainMessage" /><br/>
	</body>
</html>
