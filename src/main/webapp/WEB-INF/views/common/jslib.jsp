<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.cookie.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/myGeoway.debug.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/myGeoway.class.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/easyui/locale/easyui-lang-zh_CN.js"></script>  

<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/dict.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/seajs/seajs-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/seajs/seajs-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/artTemplate/template.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/artTemplate/extensions/template-syntax.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manager/main.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/json/json2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/easyui/easyui-extend.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery/slimScroll/jquery.slimscroll.min.js"></script>


<script type="text/javascript">
        MyGeoway.ContextRoot = "${pageContext.request.contextPath}";
	    $(document).ready(function(){
	    	MyGeoway.Main.init();
	    	//initSkins();
	   }); 
	    
	    function initSkins(){
	    	
	    	var skin = $.cookie("press-manager-skin");
	    	if(skin == "green"){
	    		$("#skincss").attr("href", MyGeoway.ContextRoot + "/css/skin-green.css");
	    	}
	    	
	    	
	    	$("#skin-blue").click(function() {
	    		$("#skincss").attr("href", MyGeoway.ContextRoot + "/css/skin-blue.css");
	    		$.cookie("press-manager-skin", "default", {
	    			expires : 365,
	    			path : "/"
	    		});
	    	});

	    	$("#skin-green").click(function() {
	    		$("#skincss").attr("href", MyGeoway.ContextRoot + "/css/skin-green.css");
	    		$.cookie("press-manager-skin", "green", {
	    			expires : 365,
	    			path : "/"
	    		});
	    	});
	    }
 </script>