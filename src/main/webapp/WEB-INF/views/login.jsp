<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<header>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>健身管理平台</title>
	<link href="${pageContext.request.contextPath}/css/login/style_log.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/userpanel.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/jquery.ui.all.css">
</header>
<body class="login">
	<div class="login_m">
		<div class="login_logo" style="font-size: 26px;font-weight: bolder;color: #1355ad">健身管理平台</div>
		<div class="login_boder">
			<c:url value="/manager/j_spring_security_check" var="loginUrl" />
			<form id="login-form" action="${loginUrl}" class="form-signin" method="post">
				<div class="login_padding">
				  <h2>用户名</h2>
				  <label>
				    <input type="text" id="username" name="username" class="txt_input txt_input2" onfocus="if (value ==&#39;用户名&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;用户名&#39;}" value="用户名">
				  </label>
				  <h2>密码</h2>
				  <label>
				    <input type="password" id="password" name="password" class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******">
				  </label>
				  <c:if test="${param.error != null}">
				  		<div style="color: red;">
				  			登录失败:
				  			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
								<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
				  		</div>
				  </c:if>
				  <c:if test="${param.logout != null}">
				  </c:if>
				  <div class="rem_sub" style="margin-top: 15px;">
					  <div class="rem_sub_l">
					  	<input type="checkbox" name="_spring_security_remember_me" style="margin-bottom: 2px;vertical-align: middle;">
					   	<label for="checkbox">记住</label>
					  </div>
				      <label>
				      	<input type="submit" class="sub_button" name="button" value="登录" style="opacity: 0.7;margin-right:10px;">
				      </label>
				  </div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
