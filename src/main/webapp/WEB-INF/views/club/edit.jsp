<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<body>

	<h2>健身房编辑界面</h2>
	经度：
	<input id="x" path="x" name='x' placeholder="输入经度" />
	纬度：
	<input id="y" path="y" name='y' placeholder="输入纬度" />

	<div id="mapContainer" style="width: 500px; height: 300px; border: solid 1px #cccccc"></div>


</body>

<script src="${pageContext.request.contextPath}//assets/jquery/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script>
	$(document).ready(function() {
		var map = new BMap.Map("mapContainer");
		var point = new BMap.Point(118.798632, 36.858719); // 创建点坐标
		map.centerAndZoom(point, 10);
		map.enableScrollWheelZoom();
		map.addControl(new BMap.NavigationControl());

		drawMarker(116.3, 39.9);

		function drawMarker(x, y) {
			var point = new BMap.Point(x, y);
			var marker = new BMap.Marker(point);
			marker.enableDragging();
			map.addOverlay(marker);

			$("#x").val(x);
			$("#y").val(y);

			//拖拽地图时触发事件
			marker.addEventListener("dragend", function(e) {
				$("#x").val(e.point.lng);
				$("#y").val(e.point.lat);
			});
			map.centerAndZoom(point, 12);
		}

	});
</script>
</html>

