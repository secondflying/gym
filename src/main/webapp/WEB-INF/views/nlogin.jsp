<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="" scope="request" />
<jsp:include page="./includes/indexheader.jsp" />

<div class="container">
	<c:url value="/nzd/j_spring_security_check" var="nloginUrl" />
	<form id="login-form" action="${nloginUrl}" class="form-signin" method="post">
		<h2 class="form-signin-heading">农资店登录</h2>
		<c:if test="${param.error != null}">
			<div class="alert alert-error">
				登录失败：
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</c:if>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<!-- <div class="alert alert-success">您已退出系统</div> -->
		</c:if>
		<label for="username" class="sr-only">用户名：</label>
		<input type="text" id="username" name="username" class="form-control" placeholder="输入手机号" required autofocus>
		<label for="password" class="sr-only">密码：</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="输入密码" required>
		<div class="checkbox">
			<label> <label class="checkbox"> <input type="checkbox" checked="checked"
						name="_spring_security_remember_me">下次自动登录 </label>
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
	</form>

</div>
<jsp:include page="./includes/footer.jsp" />