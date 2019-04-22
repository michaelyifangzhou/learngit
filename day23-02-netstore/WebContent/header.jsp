<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<br>
<br>
<h1>欢迎登录</h1>
<br>
<br>
<a href="${pageContext.request.contextPath }">首页</a>
<c:if test="${sessionScope.customer==null }">
<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
<a href="${pageContext.request.contextPath }/regist.jsp">免费注册</a>
</c:if>
<c:if test="${sessionScope.customer!=null }">
欢迎登录:${sessionScope.customer.nickname}
<a href="${pageContext.request.contextPath }/client/ClientServlet?op=logoutCustomer"> 注销</a>
</c:if>
<a href="${pageContext.request.contextPath }/client/ClientServlet?op=showCustomerOrders">我的订单</a>
<a href="${pageContext.request.contextPath }/showCart.jsp">购物车</a>
<br>
<a href="${pageContext.request.contextPath }">所有分类</a>