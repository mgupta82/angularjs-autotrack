<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="jquery-ui.min.css">
	<script src="external/jquery/jquery.js"></script>
	<script src="jquery-ui.min.js"></script>
		
	<script>
	$(document).ready(function() {
	$( "#userForm" ).submit(function( event ) {
		  // Stop form from submitting normally
		  event.preventDefault();
		  $.post("http://localhost:9082/hotlink/next.jsp", $( "#userForm" ).serialize() ,  function( data ) {
			    var content = $($.parseHTML(data)).filter("#content"); 
			    $( "#result" ).empty().append( content );
			});
		});
	});
	</script>		
</head>

<body>
<div id="result">
	<form id="userForm">
	  <fieldset>
	    <label for="name">Name</label>
	    <input type="text" name="name" id="name">
	    <label for="password">Password</label>
	    <input type="password" name="password" id="password">
	    <input type="submit" value="submit">
	  </fieldset>
	</form>
</div>
</body>
</html>