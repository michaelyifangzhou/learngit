<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/header.jsp" %>
<form action="${pageContext.request.contextPath }/client/ClientServlet?op=loginCustomer" method="post">
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