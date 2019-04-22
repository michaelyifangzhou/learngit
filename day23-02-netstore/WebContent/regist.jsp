<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/header.jsp" %>
<form action="${pageContext.request.contextPath }/client/ClientServlet?op=registCustomer" method="post">
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
<td>昵称：</td>
<td>
<input name="nickname">
</td>
</tr>
<tr>
<td>电话号码：</td>
<td>
<input name="phonenum">
</td>
</tr>
<tr>
<td>地址：</td>
<td>
<input name="address">
</td>
</tr>
<tr>
<td>邮箱：</td>
<td>
<input name="email">
</td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="保存">
</td>

</tr>
</table>
</form>

</body>
</html>