<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看所有图书</title>
</head>
<body>
<c:if test="${empty(page.list)}">

    暂时还没有任何图书哦

</c:if>

<c:if test="${!empty(page.list)}">

   <table border="1px">
       <tr>
           <td>书名</td>
           <td>作者</td>
           <td>价钱</td>
           <td>描述</td>
           <td>图片</td>
           <td>操作</td>
       </tr>

       <c:forEach var="book" items="${page.list}" >
           <tr>
               <td>${book.name}</td>
               <td>${book.author}</td>
               <td>${book.price}</td>
               <td>${book.description}</td>
               <td style="width: 80px;"><a href="${pageContext.request.contextPath}/image/${book.image}">查看图片</a></td>
               <td style="width: 40px;">
                   <a href="${pageContext.request.contextPath}/background/deleteBook?id=${book.id}">删除</a>
                   <!--  <a href="#">修改</a>-->
               </td>
           </tr>
       </c:forEach>



   </table>
    <br>
    <jsp:include page="page.jsp"/>

</c:if>





</body>
</html>
