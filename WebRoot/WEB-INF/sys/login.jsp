<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<script type="text/javascript">
	/**
	 *验证账号登录信息
	 */
	function validate() {
		$.ajax({
			type : "POST",
			url : "validate/check?json",
			data : {
				"account" : $("#account").val(),
				"password" : $("#password").val(),
				"autoLogin": $("#autoLogin").val()
			},
			success : function(data) {
				if (data.success == "true") {
					$("#loginForm").submit();
				}
				if (data.success == "false") {
					$("#warn").attr("class", "alert alert-warning show");
					$("#warn").fadeOut(3000);
				}
			},
			dataType : "json"

		});
	}
	var a=0;
	function getAuto(){
		if(a==0) a=1;
		else if(a==1) a=0;
		$("#autoLogin").attr("value",a);
	}
</script>

<%@ include file="head.html"%>
<title><h2><center>登 录</center></h2></title>
</head>
<body>
	<div class="col-md-4"></div>
	<div class="col-md-4">

		<br> <br> <br>

		<form action="javascript:validate()">
			<h2 class="form-signin-heading">登陆</h2>
			<input type="text" id="account" class="form-control" name="account"
				placeholder="Account"> <br> <input type="password"
				id="password" class="form-control" name="password"
				placeholder="Password"> <br>
			<div class="checkbox">
				<label> <input type="checkbox" value="1" onclick="getAuto()">记住我(忽略)
				</label>
			</div>
			<button id="button" class="btn btn-lg btn-primary btn-block"
				type="submit" onclick="validate()">Sign in</button>
			<input type="hidden" id="autoLogin" name="autoLogin" value="0">	
		</form>
		<form id="loginForm" class="form-signin" action="validate/login">
			
		</form>

		<br>
		<div id="warn" class="alert alert-warning hidden">
			<strong>请输入正确的账号或密码！</strong>
		</div>
	</div>
	<div class="col-md-4"></div>
</body>

</html>
