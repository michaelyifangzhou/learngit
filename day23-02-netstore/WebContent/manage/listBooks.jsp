<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/manage/header.jsp" %>
<<table border="1" width="838">
  <tr>
    <th>序号</th>
    <th>书名</th>
    <th>作者</th>
    <th>单价</th>
    <th>描述</th>
    <th>所属分类</th>
    <th>图片</th>
    <th>操作</th>
  </tr>
<c:forEach items="${page.records }" var="b" varStatus="vs">
<tr class="${vs.index%2==0?'odd':'even' }">
    <th>${vs.count}</th>
   <td>${b.name}</td>
   <td>${b.author}</td>
   <td>${b.price}</td>
   <td>${b.description}</td>
   <td>${b.category.name}</td>
   <td>
   <img alt="${b.filename }" src="${pageContext.request.contextPath }/images/${b.path}/${b.filename}">
   </td>
    <th>
	<a href="#">修改</a>
	<a href="#">删除</a>
   </th>
  </tr>
</c:forEach>
<tr>
<td colspan="8">
<%@ include file="/common/page.jsp" %>
</td>
</tr>
</table>


</body>
</html>