<%@page import="BookSystem.book.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
		
			Book book = (Book)request.getAttribute("BookInfo") ;
		
		%>
		<!--  通过表单展示此书信息 -->
		<form action="UpdateBook">
				书号:<input type="text" name="bookNo" value="<%=book.getBookNo()%>"  readonly="readonly"/><br/>		
				书名:<input type="text" name="bookName" value="<%=book.getBookName()%>"/><br/>
				<input type="submit" value="修改"/>
				<a href="back">返回</a>
				
		</form>
		
</body>
</html>