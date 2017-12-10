<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>free read</title>
	<link rel="stylesheet"
		  href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script
			src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
			src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="container"><%-- 添加container类后会有很大的边距 --%>
	<div id="header" class="row">
		<div class="col">
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div id="body" class="row">
		<div class="col-md-2"><tiles:insertAttribute name="left" /></div>
		<div class="col-md-10"><tiles:insertAttribute name="main" /></div>
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</div>
</body>
</html>