<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function check()//onsubmit： return true：表单正常提交   return false:表单终止提交
	{
		var sno = $("#sno").val();
		var sname = $("#sname").val();
		var sage = $("#sage").val();
		var saddress = $("#saddress").val();
		if (!(sno > 0 && sno < 100000)) {
			alert("书学号有误！必须是1-100000")
			return false;
		}
		if (!(sname.length > 1 & sname.length < 10)) {
			alert("书姓名长度有误！必须是2-10位")
			return false;
		}

		return true;
	}

	$(document).ready(function() {
	});
</script>


<title>Insert title here</title>
</head>
<body>
	<form action="AddBook" method="get"
		onsubmit="return check()">
		书号：<input type="text" name="bookNo" id="bookNo" /><br /> 
		书名：<input type="text" name="bookName" id="bookName" /><br /> 
		<input type="submit" value="新增" /><br /><a href="back">返回</a>
	</form>
</body>
</html>