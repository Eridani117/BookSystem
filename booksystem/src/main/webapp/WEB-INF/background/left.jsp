<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="#">分类管理</a>
<ul>	
	<li><a href="${pageContext.request.contextPath}/background/addCategory" target="body">添加分类</a></li>
	<li><a href="${pageContext.request.contextPath}/background/ShowCategory" target="body">查看分类</a></li>
</ul>

<br>
<br>
<a href="#">图书管理</a>
<ul>
	<li><a href="${pageContext.request.contextPath}/background/addBook" target="body">添加图书</a></li>
	<li><a href="${pageContext.request.contextPath}/background/ShowBook" target="body">查看图书</a></li>
</ul>
<br>
<br>
<a href="#">订单管理</a><br>
<ul>
	<li><a href="${pageContext.request.contextPath}/background/OrderFalse" target="body">待处理订单</a></li>
	<li><a href="${pageContext.request.contextPath}/background/OrderTrue" target="body">已发货订单</a></li>
</ul>
<br>
<br>

</body>
</html>
