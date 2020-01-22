<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 15.06.2019
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
    <title>InsertData</title>
    <link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<a href="ui.jsp">Interface</a>
<form action="InsertSensorData" method="get">
    <input type="text" name="sensor">
    <input type="text" name="value">
    <input type="text" name="type">
    <input type="submit">
</form>
</body>
</html>
