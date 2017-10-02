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
	登录成功...........
	<br>
	<br>
	<button id="logout">退出</button>
	<br>
	<br>
	<button id="newpage1">newpage 1</button>
	<br>
	<br>
	<button id="newpage2">newpage 2</button>
  </body>
  
  <script type="text/javascript">
  $('#logout').click(function() {
	$.ajax({ 
		type: "post", 
		url: "<%=request.getContextPath()%>" + "/logout.json", 
		data: {}, 
		dataType: "json", 
		success: function(data) { 
			if(data.success == false){
				alert(data.errorMsg);
			}else{
				alert("logout success"); 
				//登录成功
			    window.location.href = "<%=request.getContextPath()%>" +  "/login.html";
			}
		},
		error: function(data) { 
			alert("invoke failure...."); 
		}
	});
  });
  
   $('#newpage1').click(function() {
	$.ajax({ 
		type: "post", 
		url: "<%=request.getContextPath()%>" + "/newpage1.json", 
		data: {}, 
		dataType: "json", 
		success: function(data) { 
			if(data.success == false){
				alert(data.errorMsg);
			}else{
			    window.location.href = "<%=request.getContextPath()%>" +  "/newpage1.html";
			}
		},
		error: function(data) { 
			alert("调用失败...."); 
		}
	});
  });
  
   $('#newpage2').click(function() {
	$.ajax({ 
		type: "post", 
		url: "<%=request.getContextPath()%>" + "/newpage2.json", 
		data: {}, 
		dataType: "json", 
		success: function(data) { 
			if(data.success == false){
				alert(data.errorMsg);
			}else{
			    window.location.href = "<%=request.getContextPath()%>" +  "/newpage2.html";
			}
		},
		error: function(data) { 
			alert("调用失败...."); 
		}
	});
  });
  </script>
</html>
