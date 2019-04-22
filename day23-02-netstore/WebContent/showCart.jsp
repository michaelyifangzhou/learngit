<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ include file="/header.jsp" %>

   <<c:if test="${empty sessionScope.cart.items }">
   <h3>您没有购买商品</h3>
   </c:if>
   <<c:if test="${!empty sessionScope.cart.items }">
   <h3>您购买商品如下</h3>
   <table border="1" width="550">
   <tr>
   	<td>书名</td>
   	<td>数量</td>
   	<td>单价</td>
   	<td>小计</td>
   	<td>操作</td>
   </tr>
   <c:forEach items="${sessionScope.cart.items }" var="me" varStatus="vs">
     <tr class="${vs.index%2==0?'odd':'even' }">
   	<td>${me.value.book.name }</td>
   	<td><input type="text" id="quantity" value="${me.value.quantity }" onchange="changeNum(this,${me.value.quantity },'${me.value.book.id }')" size="2"></td>
   	<td>${me.value.book.price }</td>
   	<td>${me.value.money }</td>
   	<td>
   	<a href="javascript:delOneItem('${me.key }')">删除</a>
   	</td>
   </tr>
   </c:forEach>
   <tr>
   	<td colspan="5">
   	<a href="javascript:delAllItems()">清空购物车</a>
   		总数量：${sessionScope.cart.totalQuantity }
   		总金额：${sessionScope.cart.totalMoney }
   		<a href="${pageContext.request.contextPath}/client/ClientServlet?op=genOrder">去结算</a>
   	</td>
   </tr>
   </table>
   </c:if>
   <<script type="text/javascript">
<!--

//-->
		function delAllItems(){
			var sure=window.confirm("确定清空吗？");
			if(sure){
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delAllItems";
			}
		}
		function delOneItem(bookId){
			var sure=window.confirm("确定删除吗？");
			if(sure){
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delOneItem&bookId="+bookId;
			}
		}
		function changeNum(inputObj,oldNum,bookId){
			var sure=window.confirm("确定修改数量吗");
			if(sure){
				//alert("yes");
				var num=inputObj.value;
				if(!/^[1-9][0-9]*$/.test(num)){
					alert("not correct");
					return;
				}
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=changeNum&num="+num+"&bookId="+bookId;
				
			}else{
				inputObj=oldNum;
				//alert("no");
				
			}
		}
	</script>
</body>
</html>