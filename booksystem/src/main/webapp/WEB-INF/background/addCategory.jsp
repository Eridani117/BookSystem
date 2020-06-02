<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加分类</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/background/addCategory" method="post">

    分类名称：<input type="text" name="name"><br>
    分类描述：<textarea name="description"></textarea><br>
    <input type="submit" value="提交">

</form>

</body>
</html>
