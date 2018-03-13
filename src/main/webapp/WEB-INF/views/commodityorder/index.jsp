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
	    	seajs.use("${pageContext.request.contextPath}/module/store/commodityorder/js/CommodityOrder");
	    });    
    </script>
</head>
<body class="easyui-layout">
	<div id="c-header" region="north" collapsible="false" border="false">
		<div class="ch-position">位置：</div>
		<div class="ch-txt">店面管理<i></i>商品订单管理<i></i>列表</div>
	</div>
	<div region="center" collapsible="false" border="false">
    	<div class="easyui-layout" fit="true">
            <div region="north"  collapsible="false" border="false" split="false">
	             <div style="position:relative;margin:0 10px;padding:10px;">
		            <form id="seachContent" method="post">
		                 <table class="searchContent">
		                    <tr>
								<td>
									<label>名称：</label>
								</td>
								<td class="nr">
									<input class="easyui-textbox" id="txtName" style="height:24px;width: 180px;">
								</td>
							</tr>
		                 </table>
		             </form>
		             <div style="position:absolute;left:240px;top:12px;" class="yw-search-wrap">
		                 <button id="btnSeach" >查 询</button>
		                 <button id="btnReset" >重 置</button>
		            </div>	            
	             </div>
            </div>
            <div region="center" id="main-content" border="false" style="padding:0 10px 5px 10px;">
				<table id="dataGrid" fit="true" >
					
				</table> 
		    </div>
		    <div id="coDlgCon"></div>
       </div>
   	</div>
</body>
</html>

