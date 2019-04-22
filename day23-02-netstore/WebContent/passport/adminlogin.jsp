<%@ page language="java" 
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Util.js"></script>
<title>YFZ网站</title>
</head>
<body>
<h1>管理登录</h1>
<form action="${pageContext.request.contextPath }/privilege/PrivilegeServlet" method="post">
<table border="1" width="538">
<tr>
<td>用户名：</td>
<td>
 <input type="text" name="username">
</td>
</tr>
<tr>
<td>密码：</td>
<td>
 <input type="password" name="password">
</td>
</tr>

<tr>
<td colspan="2">
<input type="submit" value="登录">
</td>

</tr>
</table>
</form>

</body>
</html>