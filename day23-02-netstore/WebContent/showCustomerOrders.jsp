<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/header.jsp" %>

   <table border="1" width="666">
   <tr>
   <td>订单编号</td>
   <td>数量</td>
    <td>金额</td>
    <td>状态</td>
    <td>明细</td>
   </tr>
   <c:forEach items="${os }" var="o" varStatus="vs">
   <tr class="${vs.index%2==0?'odd':'even' }">
   <td>${o.ordernum }</td>
   <td>${o.quantity }</td>
    <td>${o.money }</td>
    <td>
    <c:choose>
    <c:when test="${o.status==0 }">
            未付款：<a href="${pageContext.request.contextPath }/pay.jsp?ordernum=${o.ordernum}&money=${o.money}">付款</a>
    </c:when>
    <c:when test="${o.status==1 }">
         已付款：
    </c:when>
    <c:when test="${o.status==2 }">
         已发货：
    </c:when>
    </c:choose>
    </td>
    <td>
    <a href="javascript:alert('略')">查看明细</a>
    </td>
   </tr>
   </c:forEach>
   </table>
</body>
</html>