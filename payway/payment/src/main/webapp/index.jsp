<!DOCTYPE html>
<html>
	<head>
		<script src="external/jquery/jquery.js"></script>
		<script src="jquery-ui.min.js"></script>	
		
		<script  type="text/javascript">
			var childWindow=null;
		
			/*var eventMethod = window.addEventListener ? "addEventListener" : "attachEvent";
			var eventer = window[eventMethod];
			var messageEvent = eventMethod == "attachEvent" ? "onmessage" : "message";
	
			eventer(messageEvent,function(e) {
				alert('parent received message!');
				var key = e.message ? "message" : "data";
				var data = e[key];
			},false);	*/	

			$(document).ready(function(){
				/*$.receiveMessage(
					function(e){
						alert( e.data );
					},
					'http://Lenovo:8080/maxis/index.jsp'
				);	*/
				
		        $("#hotlink-popup").click(function(){
					childWindow = window.open('maxispay.html',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes,width=600, height=280,top=200,left=200"); 
					//$(document).bind('mainWindow', function(e, message){ alert(message); });						
		        });				
				
			});
			
			var closeCheckInterval = 500; //Check every 500 ms.
			var closeCheck = setInterval(function () {timerCheck()}, closeCheckInterval);
			
			function timerCheck(){
			    try {
			    	if(childWindow!=null && childWindow.closed){
			    		clearInterval(closeCheck);
			    		alert('closed');
			    	}   
			    } catch (ex) {closeCheck = setInterval(function () {timerCheck()}, closeCheckInterval); }			
			}

	
			function localClick(){
				childWindow = window.open('http://punhjw118801l.ad.infosys.com:8080/hotlink/topup.jsp',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes,width=600, height=280,top=200,left=200");
				//$(document).bind('mainWindow', function(e, message){ alert(message); }); 
			}
			
			function sendMessage(){
				childWindow.postMessage("Hello",'*');
			}
			

		</script>
	</head>
	<body>

		<button id="hotlink-popup">Cross Domain</button><br/>
		<button type="button" id="local-popup" onclick="javascript:localClick()">Same Domain</button><br/>
		<button type="button" id="send-popup" onclick="javascript:sendMessage()">Send Message</button><br/>
		<input type="text" id="mainMessage" /><br/>
	</body>
</html>
