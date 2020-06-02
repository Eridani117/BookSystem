<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>

<style type="text/css">
#body {
	position: relative;
}

.btn-primary {
	position:absolute;
	right:400px;
	top:110px
}

</style>
<script type="text/javascript">
	function login() {
		//得到输入框的数据
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;

		//跳转到相对应的Servlet上
		window.location.href = "${pageContext.request.contextPath}/UserServlet?method=login&username="
				+ username + "&password=" + password;
	}

	function register() {

		//跳转到注册页面
		window.location.href = "${pageContext.request.contextPath}/client/register.jsp";
	}
</script>
</head>
<body style="text-align: center" id="body">
	<h1>欢迎来到购物中心</h1>

	<a href="${pageContext.request.contextPath}/client/index" target="body">首页</a>
	<a href="${pageContext.request.contextPath}/client/listCart" target="body">查看购物车</a>
	<a href="${pageContext.request.contextPath}/client/LookOrder" target="body">查看订单</a>

	<form class="" method="post" action="${pageContext.request.contextPath}/logout" target="_parent">
        <button class="btn btn-primary btn-sm" type="submit">Logout</button>
      </form>
      
<!-- 
	<c:if test="${user==null}">
		<div id="User">
			用户名：<input type="text" id="username"> 密码：<input
				type="password" id="password">
			<button name="login" onclick="login()">登陆</button>
			<button name="register" onclick="register()">注册</button>
		</div>
	</c:if>

	<c:if test="${user!=null}">
		<div id="User">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：${user.username}&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="${pageContext.request.contextPath}/UserServlet?method=Logout">注销</a>
		</div>
	</c:if>
 -->
</body>
</html>
