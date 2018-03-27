<!doctype html>
<html>
<head>
<title>监狱局警察人事系统</title>
<style>
html {
	height: 100%;
	overflow: hidden;
}

body {
	position: relative;
	width: 100%;
	height: 100%;
	background: url("/tmp/image/banner_03.png") no-repeat center center
		fixed;
	background-blend-mode: darken;
}

#wrapper {
	width: 50%;
	height: 100%;
	bottom: 0;
	margin-top: auto;
	margin-left: auto;
	margin-right: auto;
	clear: both;
	min-width: 400px;
	overflow: hidden;
}

#loginWindow {
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	margin-top: 30.2%;
	padding-left: 3em;
	padding-right: 3em;
	padding-bottom: 2em;
	border: solid #eee 1px;
	border-radius: 5px;
	overflow: hidden;
	width: 0;
	height: 0;
}

.input-group {
	margin-top: 2em;
	margin-bottom: 2em;
	opacity: 0;
}

.page-header {
	opacity: 0;
	position: relative;
}

.left {
	float: right;
	font-size: 1.5em;
}

.btn {
	display: block;
	margin-left: auto;
	margin-right: auto;
	opacity: 0;
}

.bns-03 {
	
}
</style>
<!-- CSS statics -->
<link rel="stylesheet" type="text/css" href="/tmp/css/bootstrap.min.css"></link>
<script type="text/javascript" src="/tmp/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/tmp/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="/tmp/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/tmp/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#loginWindow').animate({
			'width' : '61.8%'
		}, 500).delay(30).animate({
			'height' : '300px'
		}, 500);
		$('.page-header, .input-group, .btn').delay(850).animate({
			'opacity' : '100'
		}, 7000);
	});
</script>
</head>
<body>
   <div>
       <img src="/tmp/image/loginlogo.png" />
   </div>
	<form action="/loginCheck" id='loginForm' method="POST">
	<div id="wrapper" class="bns-03">
		<div id="loginWindow">
			<div class="page-header">
				<p class="left">
					<!-- <span class="glyphicon glyphicon-lock" aria-hidden="true"></span> -->
					<img src="/tmp/image/ywgklogo.png" />
				</p>
				<h1>用户登录</h1>

			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1"><span
					class="glyphicon glyphicon-user"></span></span> <input type="text" name="username" id="username"
					class="form-control" placeholder="用户名" />
			</div>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon2"><span
					class="glyphicon glyphicon-lock"></span></span> <input type="password" name="password" id="password"
					class="form-control" placeholder="密码" />
			</div>
			<div class="form-group">
				<button class="btn btn-primary" type="submit">登录</button>
			</div>
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
	</form>
</body>
</html>

