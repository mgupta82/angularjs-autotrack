<html>

<head><title>Form based Authentication</title></head>

<body>
	<form method="POST" action='<%= response.encodeURL("j_security_check") %>' >
	  <table cellpadding="2" border="0" cellspacing="0">
	    <tr>
	      <td align="right">Username:</td>
	      <td align="left"><input type="text" name="j_username" size="9"></td>
	    </tr>
	    <tr>
	      <td align="right">Password:</td>
	      <td align="left"><input type="password" name="j_password" size="9"></td>
	    </tr>
	    <tr>
	      <td align="right"><input type="submit" value="Log In"></td>
	      <td align="left"><input type="reset"></td>
	    </tr>
	  </table>
	</form>
</body>


</html>