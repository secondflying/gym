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
	    	function getParam(param){
	    		var local = document.location.search.substring(1);
	    	    var splits = local.split("&");
	    	    for (var i = 0; i < splits.length; i++) {
	    	        var sp = splits[i];
	    	        if (sp.indexOf(param + "=") == 0) {
	    	            var val = sp.substring(param.length + 1);
	    	            return decodeURIComponent(val);
	    	        }
	    	    }
	    	}
	    	GYM.clubId = getParam("id");
	    	seajs.use("${pageContext.request.contextPath}/module/store/clubofcoach/js/CoachManager");
	    });    
    </script>
</head>
<body class="easyui-layout">
	<div id="c-header" region="north" collapsible="false" border="false">
		<div class="ch-position">位置：</div>
		<div class="ch-txt">店面管理<i></i><a href="${pageContext.request.contextPath}/manager/club/index" class="linkof">健身房</a><i></i>教练管理</div>
	</div>
	<div region="center" collapsible="false" border="false">
    	<div class="easyui-layout" fit="true" style="margin: 10px;">
            <div data-options="region:'north',title:'可分配教练列表'" collapsible="false" border="false" style="padding:5px;height: 50%;padding-right: 20px;">
	             <table id="noUseGrid" fit="true">
					
				</table>
            </div>
            <div data-options="region:'center',title:'已分配教练列表'" border="false" style="padding:5px;height: 50%;padding-right: 20px;padding-bottom: 16px;">
				<table id="inUseGrid" fit="true" >
					
				</table> 
		    </div>
		    <div id="levelDlgCon"></div>
       </div>
   	</div>
</body>
</html>

