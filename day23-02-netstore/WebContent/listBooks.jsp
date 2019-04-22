<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/header.jsp" %>
<a href="${pageContext.request.contextPath }">所有分类</a>
<c:forEach items="${cs }" var="c" >
<a href="${pageContext.request.contextPath }/client/ClientServlet?op=showCategoryBooks&categoryId=${c.id}">${c.name }</a>
</c:forEach>
<<table border="1" width="438">
 <tr>
 <c:forEach items="${page.records }" var="b">
 <td>
    <img alt="${b.filename }" src="${pageContext.request.contextPath }/images/${b.path}/${b.filename}">
   书名:${b.name }<br>
   作者:${b.author }<br>
  单价: ${b.price }<br>
  <a href="${pageContext.request.contextPath }/client/ClientServlet?op=showBookDetail&bookId=${b.id}">去看看</a>
 </td>
 </c:forEach>
 </tr>

<tr>
<td colspan="3">
<%@ include file="/common/page.jsp" %>
</td>
</tr>
</table>


</body>
</html>