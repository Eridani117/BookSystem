<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>前台页面</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    
  </head>

   <frameset rows="25%,*">
    <frame src="${pageContext.request.contextPath}/client/head" name="head"/>
    <frame src="${pageContext.request.contextPath}/client/index" name="body"/>
  </frameset>


</html>
