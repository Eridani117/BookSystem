<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
<style type="text/css">
#body {
	position: relative;
}

#category {
	border: 1px solid #000;
	position: absolute;
	width: 300px;
	height: 400px;
	float: left;
	left: 200px;
	top: 70px;;
}

#bookandpages {
	border: 1px solid #000000;
	position: absolute;
	width: 780px;
	height: 538px;;
	float: left;
	left: 500px;
	margin-left: 50px;
}

#books {
	margin-left: 50px;
	margin-top: 30px;
}

#image {
	float: left;
	margin-right: 40px;
}

#bookinfo {
	float: left;
}

#page {
	height: 62px;
	width: 780px;
	position: fixed;
	margin-left: 549px;
	margin-top: 477px;
	text-align: center;
	line-height: 50px;
}
</style>
</head>
<body>

	<div id="body">
		<div id="category">
			书籍分类 : <br>
			${inf}
			<c:forEach items="${categories}" var="categories">
				<li><a
					href="${pageContext.request.contextPath}/client/ListBook?category_id=${categories.id}">${categories.name}</a>
				</li>
			</c:forEach>
		</div>

		<div id="bookandpages">
			<c:forEach items="${page.list}" var="book">
				<div id="books">

					<div id="image">
						<img src="${pageContext.request.contextPath}/image/${book.image}"
							width="83px" height="118px">
					</div>
					<div id="bookinfo">
						<li>书名：${book.name}</li>
						<li>价格：${book.price}</li>
						<li>作者：${book.author}</li>
						<li><a
							href="${pageContext.request.contextPath}/BuyServlet?book_id=${book.id}">购买</a></li>

					</div>


				</div>
				<%--这里要清除浮动，十分重要！--%>
				<div style="clear: both"></div>
			</c:forEach>

		</div>
		<div id="page">
			<jsp:include page="/client/page.jsp" />
		</div>
	</div>
</body>
</html>
