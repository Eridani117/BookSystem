<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
</head>

<frameset rows="25%,*">
    <frame src="${pageContext.request.contextPath}/background/head.jsp"/>

    <frameset cols="15%,*">
        <frame src="${pageContext.request.contextPath}/background/left"/>
        <frame src="${pageContext.request.contextPath}/background/body" name="body"/>
    </frameset>
</frameset>

</html>
