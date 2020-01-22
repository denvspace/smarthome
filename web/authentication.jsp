<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 20.04.2019
  Time: 6:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
    <title>SmartNet</title>
    <link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<a href="index.jsp">Главная</a>
<body>
<form>
    <h1>Авторизация</h1>
    <div class="inset">
        <p>
            <input type="text" name="name" id="name" placeholder="Логин">
        </p>
        <p>
            <input type="password" name="password" id="password" placeholder="Пароль">
        </p>
        <p>
            <input type="checkbox" name="remember" id="remember">
            <label for="remember">Запомнить меня на 14 дней</label>
        </p>
    </div>
    <p class="p-container">
        <span><a href="restorePass">Забыли пароль?</a></span>
        <input type="submit" name="go" id="go" value="Login" formaction="checkAuthentication" formmethod="post">
    </p>
</form>
</body>

</html>