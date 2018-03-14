<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>健身管理平台</title>
	<%@ include file="../common/css.jsp"%>
	<%@ include file="../common/jslib.jsp"%>
	<script type="text/javascript">
		$(document).ready(function(){
			seajs.use("${pageContext.request.contextPath}/module/menu/js/Menu");
			
			$("#headerMenu ul li").click(function(){
				if($(this).hasClass("selected")){
					return false;
				}
				$("#headerMenu ul li").removeClass("selected");
				$(this).addClass("selected");
				var index = $(this).index();
				GYM.Event.trigger("menuView/render", index);
			});
	    }); 
	</script>
</head>
<body class="easyui-layout">
    <div region="north" border="false" style="height:88px;width:100%;">
		<div class="header">
			<div class="header-l">
	     		<div class="logo">GYM运维管理平台</div>
	     		<div class="headerLogin">
					<ul>
						<li><span class="headtime" id="headtime">2018年2月6日</span></li>
				        <li><i class="icon-user"></i><a href="javascript:void(0);"><sec:authentication property="principal.name" /></a></li>
				        <li><a href="${pageContext.request.contextPath}/manager/j_spring_security_logout">退出</a></li>
				    </ul>
				</div>
	     		<div id="headerMenu" class="header-menu">
	     			<ul>
	     				<li class="li1 selected">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">店面管理</div>
	     				</li>
	     				<li class="li3">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">教练管理</div>
	     				</li>
	     				<li class="li1">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">用户管理</div>
	     				</li>
	     				<li class="li5">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">用户管理</div>
	     				</li>
	     				<li class="li6">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">用户管理</div>
	     				</li>
	     				<li class="li7">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">用户管理</div>
	     				</li>
	     			</ul>
	     		</div>
	     	</div>
	     	<div class="header-r"></div>
		</div>
   	</div>
    
    <div region="west"  collapsible="false" border="false" style="width:200px;border-right: 1px solid #c8cfd5;">
        <div id="leftMenu" class="sortList">
        	<!-- 
			<div class="l-title"><i></i>店面管理</div>
			<div class="sortItem">
				<h3>
					<a href="javascript:void(0);">健身房管理</a>
					<b></b>
				</h3>
				<ul>
					<li class="selected"><i class="subi"></i><a id="" href="javascript:void(0);" >健身房</a></li>
					<li><i class="subi"></i><a id="" href="javascript:void(0);" >教练管理</a></li>
				</ul>
			</div>
			<div class="sortItem">
				<h3>
					<a href="javascript:void(0);">管理信息</a>
					<b></b>
				</h3>
				<ul>
					<li><i class="subi"></i><a id="" href="javascript:void(0);" >新闻公告</a></li>
					<li><i class="subi"></i><a id="" href="javascript:void(0);" >新闻公告</a></li>
				</ul>
			</div>
			<div class="sortItem">
				<h3>
					<a href="javascript:void(0);">管理信息</a>
					<b></b>
				</h3>
				<ul>
					<li><i class="subi"></i><a id="" href="javascript:void(0);" >新闻公告</a></li>
					<li><i class="subi"></i><a id="" href="javascript:void(0);" >新闻公告</a></li>
				</ul>
			</div>
			 -->
		</div>
    </div>
    <div id="mainPanel"  region="center"  collapsible="false"  border="false">
	    <iframe id="linkPage" src="${pageContext.request.contextPath}/manager/club/index" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
    </div>
</body>
</html>