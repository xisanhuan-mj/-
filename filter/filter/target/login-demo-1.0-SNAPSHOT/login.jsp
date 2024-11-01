<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form action="login" method="post">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">登录</button>
</form>
<!-- 显示错误信息 -->
<c:if test="${param.error != null}">
    <p style="color:red;">用户名或密码错误，请重试。</p>
</c:if>
</body>
</html>
