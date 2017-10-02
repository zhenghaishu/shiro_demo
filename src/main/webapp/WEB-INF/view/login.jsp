<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js"></script>
  </head>
  
  <body>
	username: <input type="text" id="username"><br><br>  
	password: <input type="password" id="password"><br><br>
	<button id="loginbtn">登录</button>
  </body>
  
 <script type="text/javascript">
 $('#loginbtn').click(function() {
	var param = {
		username : $("#username").val(),
		password : $("#password").val()
	};
	$.ajax({ 
		type: "post", 
		url: "<%=request.getContextPath()%>" + "/checkLogin.json", 
		data: param, 
		dataType: "json", 
		success: function(data) { 
			if(data.success == false){
				alert(data.errorMsg);
			}else{
				//登录成功
			    window.location.href = "<%=request.getContextPath()%>" +  "/loginsuccess.html";
			}
		},
		error: function(data) { 
			alert("请求登录失败...."); 
		}
	});
 });
 </script>
</html>
