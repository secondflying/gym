<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>健身管理平台</title>
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui/themes/icon.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
    <div region="north" border="false" style="height:88px;width:100%;">
		<div class="header">
			<div class="header-l">
	     		<div class="logo">GYM运维管理平台</div>
	     		<div class="headerLogin">
					<ul>
						<li><span class="headtime" id="headtime">2018年2月6日</span></li>
				        <li><i class="icon-user"></i><a href="javascript:void(0);">管理员</a></li>
				        <li><a href="${pageContext.request.contextPath}/login/logout.do">退出</a></li>
				    </ul>
				</div>
	     		<div class="header-menu">
	     			<ul>
	     				<li class="li2">
	     					<div class="menu_bg"></div>
	     					<div class="menu-txt">健身房</div>
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
			<div class="l-title"><i></i>健身房</div>
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
		</div>
    </div>
    <div id="mainPanel"  region="center"  collapsible="false"  border="false">
	    <iframe id="linkPage" src="${pageContext.request.contextPath}/manager/club/index" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
    </div>
</body>
</html>