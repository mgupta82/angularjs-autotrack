<html>
<head>
	<link rel="stylesheet" href="jquery-ui.min.css">
	<script src="external/jquery/jquery.js"></script>
	<script src="jquery-ui.min.js"></script>
<title>Login Successfull</title>
</head>
<body>
<div id="content">
Welcome <b><%= request.getParameter("name") %></b>
<br/>User : <%=request.getUserPrincipal().getName()%>
</div>
</body>
</html>