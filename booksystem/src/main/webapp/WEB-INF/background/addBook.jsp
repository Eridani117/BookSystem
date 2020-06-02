<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加图书</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/background/addBook" method="post" enctype="multipart/form-data">

    <table border="1px" width="30%">
         
        <tr>
            <td> 图书名称：</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td> 作者：</td>
            <td><input type="text" name="author"></td>
        </tr>
        <tr>
            <td> 图书价钱：</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <select name="category_id">
                    <c:forEach items="${list}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        
        <tr>
            <td> 上传图片</td>
            <td><input type="file" name="image"></td>
        </tr>
          
        <tr>
            <td>详细描述</td>
            <td><textarea name="description"></textarea></td>
        </tr>
        
        <tr>
            <td>
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
