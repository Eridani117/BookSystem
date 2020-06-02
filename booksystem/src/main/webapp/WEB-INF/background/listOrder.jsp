<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>

	<c:if test="${empty(list)}">

    还没有任何订单哦！

</c:if>

	<c:if test="${!empty(list)}">

		<table border="1px">

			<tr>
				<td>下单人：</td>
				<td>订单时间</td>
				<td>订单状态</td>
				<td>订单价钱</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${list}" var="order">
				<tr>
					<td>${order.user_id}</td>
					<td>${order.date}</td>
					<td>${order.state==false?"未发货":"已发货"}</td>
					<td>${order.price}</td>
					<td><a
						href="${pageContext.request.contextPath}/background/showOrderItem?order_id=${order.id}">查看详细信息</a>
						<c:if test="${order.state==false}">
							<a
								href="${pageContext.request.contextPath}/background/orderSendOut?id=${order.id}">发货</a>
						</c:if></td>
				</tr>
			</c:forEach>


		</table>


	</c:if>

</body>
</html>
