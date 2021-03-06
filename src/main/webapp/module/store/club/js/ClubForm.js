/*
 * 服务编辑模块
 * 
 */
define(function(require, exports, module){
    'use strict';

    var BaseForm = require('./../../../ext/baseForm');
	var ClubForm = BaseForm.extend({
		idFieldId:"clubForm-id",
		formId:"clubForm",
		dlgId:"clubDlg",
		containerId:"clubDlgCon",
		afterCommit:null,
		easyUIFields:[],
		addURL: GYM.ContextRoot + "/manager/club/save",
		editURL:GYM.ContextRoot + "/manager/club/detail",
		coachsURL:GYM.ContextRoot + "/manager/coach/list",
		coachsToClubYRL: GYM.ContextRoot + "/manager/coach/coachsToClub",
		uploadImgUrl: GYM.ContextRoot + "/manager/file/upload",
		saveImgUrl: GYM.ContextRoot + "/manager/file/saveimg",
		delImgUrl: GYM.ContextRoot + "/manager/file/delimg",
		cate: "club",
		initialize : function(containerId){
            
			if(containerId){
				this.containerId = containerId;
			}
			var html = require("./../template/clubForm.html");
			var that = this;
			this.render(this.containerId,html,function(context){
				that.initForm();
			});
			
			var bindCoach = require("./../template/bindCoach.html");
			$("#bindCoachDlgCon").html(bindCoach);
			$.parser.parse("#bindCoachDlgCon");
			
			$("#bindCoachDlg").dialog({
     	        closed:true,
     	        width:450,
     	        modal: true,
     	        buttons:[{
 					text:'确定',
 					iconCls:'icon-ok',
 					handler:function(){
 						that.saveBc();
 						$("#bindCoachDlg").dialog('close');
 					}
 				},{
 					text:'取消',
 					iconCls:'icon-cancel',
 					handler:function(){
 						$("#bindCoachDlg").dialog('close');
 					}
 				}]
			});
			
		},
		
		initForm:function(){
			 var that = this;
			 
		},
		isValidURL:function(url){
			var validUrl = url.replace(/localhost/i,"127.0.0.1");
			var flag = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(validUrl);
			 
			return  flag;
		},
		beforeShowDlg:function(id){
			
		},
		afterShowDlg: function(id){
			var x = 116.400244;
			var y = 39.92556;
			var map = new BMap.Map("mapContainer");
			var point = new BMap.Point(x,y);
			map.centerAndZoom(point, 10);
			map.enableScrollWheelZoom();
			map.addControl(new BMap.NavigationControl());
			
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
			marker.enableDragging();
			
			$("#clubX").numberbox('setValue',x);
			$("#clubY").numberbox('setValue',y);
			
			//拖拽地图时触发事件
			marker.addEventListener("dragend", function(e) {
				$("#clubX").numberbox('setValue',e.point.lng);
				$("#clubY").numberbox('setValue',e.point.lat);
			});
		},
		submit:function(){
		   var that = this;
		   var callBack = that.afterCommit;
		  
		   $("#"+ that.formId).form('submit',{
	 			url:that.addURL,
	 			onSubmit:function(param){
	 				var flag = $(this).form('validate');
	 				if(flag){
	 					$("#" + that.dlgId).dialog('close'); 
	 				}
	 				return  flag;
	 			},
	 			success:function(data){
	 				var result = $.parseJSON(data);
	 				if(callBack && typeof callBack == "function"){
	   					callBack(result);
	   				}
	 			}
	 		});
		},
        afterRenderForm:function(data){
	    	 
	    },
	    showBindCoachDlg: function(clubid){
	    	$('#bindCoachDlg').dialog('setTitle',"教练管理");
			$('#bindCoachDlg').dialog('open');
			$("#clubId").val(clubid);
			this.queryUnUseCoachs(clubid);
			this.queryInUseCoachs(clubid);
	    },
	    queryUnUseCoachs: function(clubid){
	    	var that = this;
	    	$("#bcAddList").html("");
	    	$.ajax({
	             url: this.coachsURL,
	             data: {filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ=-1", page: 1, rows: 999},
	             dataType: "json",
	             type:"GET",
	             success: function(result){
	            	 if(result.status == "ok"){	
	         			var html = '';
	         			for(var i=0;i<result.rows.length;i++){
	         				var name = result.rows[i].name;
	         				var oid = result.rows[i].id;
	         				html += '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="选择"></i></li>';
	         			}
	         			$("#bcAddList").html(html);
	         			that.bindBcAddUI();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"查询失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    },
	    queryInUseCoachs: function(clubid){
	    	var that = this;
	    	$("#bcDelList").html("");
	    	$.ajax({
	             url: this.coachsURL,
	             data: {filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ="+clubid, page: 1, rows: 999},
	             dataType: "json",
	             type:"GET",
	             success: function(result){
	            	 if(result.status == "ok"){	
	         			var html = '';
	         			for(var i=0;i<result.rows.length;i++){
	         				var name = result.rows[i].name;
	         				var oid = result.rows[i].id;
	         				html += '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="移除"></i></li>';
	         			}
	         			$("#bcDelList").html(html);
	         			that.bindBcDelUI();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"查询失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    },
	    bindBcAddUI: function(){
	    	var that = this;
	    	$("#bcAddList").find("li i").unbind("click");
 			$("#bcAddList").find("li i").click(function(){
 				var name = $(this).parent().attr("name");
 				var oid = $(this).parent().attr("oid");
 				var addHtml = '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="移除"></i></li>';
 				$("#bcDelList").append(addHtml);
 				$(this).parent().remove();
 				that.bindBcDelUI();
 			});
	    },
	    bindBcDelUI: function(){
	    	var that = this;
	    	$("#bcDelList").find("li i").unbind("click");
 			$("#bcDelList").find("li i").click(function(){
 				var name = $(this).parent().attr("name");
 				var oid = $(this).parent().attr("oid");
 				var delHtml = '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="选择"></i></li>';
 				$("#bcAddList").append(delHtml);
 				$(this).parent().remove();
 				that.bindBcAddUI();
 			});
	    },
	    saveBc: function(){
	    	var clubId = $("#clubId").val();
	    	var coachIds = [];
	    	$("#bcDelList li").each(function(){
	    		var oid = $(this).attr("oid");
	    		coachIds.push(oid);
	    	});
	    	$.ajax({
	             url: this.coachsToClubYRL,
	             data: {clubId: clubId, coachIds: coachIds.join(",")},
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存成功",
	   					    timeout:3000
		   				  }) 
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    }
	});

	module.exports =  ClubForm;
});	
