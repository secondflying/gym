<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@ include file="../common/css.jsp"%>
    <%@ include file="../common/jslib.jsp"%>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	seajs.use("${pageContext.request.contextPath}/module/store/club/js/Main");
	    });    
    </script>
</head>
<body class="easyui-layout">
	<div id="c-header" region="north" collapsible="false" border="false">
		<div class="ch-position">位置：</div>
		<div class="ch-txt">店面管理<i></i>健身房<i></i>列表</div>
	</div>
	<div region="center" collapsible="false" border="false">
    	<div class="easyui-layout" fit="true">
    		<!-- 
            <div region="north"  collapsible="false" border="false" split="false">
	            
            </div>
             -->
            <div region="center" id="main-content" border="false" style="padding:10px 10px 5px 10px;">
				<table id="dataGrid" fit="true" >
					
				</table> 
		    </div>
		    <div id="clubDlgCon"></div> 
       </div>
   	</div>
</body>
</html>

