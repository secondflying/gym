/*
 * 服务编辑模块
 * 
 */
define(function(require, exports, module){
    'use strict';

    var BaseForm = require('./../../../ext/baseForm');
	var CoachForm = BaseForm.extend({
		idFieldId:"coachForm-id",
		formId:"coachForm",
		dlgId:"coachDlg",
		containerId:"coachDlgCon",
		afterCommit:null,
		easyUIFields:[],
		addURL: GYM.ContextRoot + "/manager/coach/save",
		editURL:GYM.ContextRoot + "/manager/coach/detail",
		checkURL: GYM.ContextRoot + "/manager/coach/check",
		initialize : function(containerId){
            
			if(containerId){
				this.containerId = containerId;
			}
			var html = require("./../template/coachForm.html");
			var that = this;
			this.render(this.containerId,html,function(context){
				that.initForm();
			});
			
			var checkForm = require("./../template/checkForm.html");
			$("#checkDlgCon").html(checkForm);
			$.parser.parse("#checkDlgCon");
			
			$("#checkDlg").dialog({
     	        closed:true,
     	        width:360,
     	        modal: true,
     	        buttons:[{
 					text:'确定',
 					iconCls:'icon-ok',
 					handler:function(){
 						that.saveCheck();
 						$("#checkDlg").dialog('close');
 					}
 				},{
 					text:'取消',
 					iconCls:'icon-cancel',
 					handler:function(){
 						$("#checkDlg").dialog('close');
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
//			var showId = id ? id:0;
//			 $("#image-svr").attr("src",MyGeoway.ContextRoot + "/serviceManager/getImage.do?id=" + showId + "&dc=" + Math.random());
//			 $("#serverForm-imageUrl").val("");
			
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
	    showCheckDlg: function(coachid, checkCommit){
	    	$("#checkForm").form("reset");
	    	this.checkCommit = checkCommit;
	    	$('#checkDlg').dialog('setTitle',"教练审核");
			$('#checkDlg').dialog('open');
			$("#checkForm-id").val(coachid);
	    },
	    saveCheck: function(){
	    	var that = this;
	    	$("#checkForm").form('submit',{
	 			url:that.checkURL,
	 			onSubmit:function(param){
	 				var flag = $(this).form('validate');
	 				return  flag;
	 			},
	 			success:function(data){
	 				var result = $.parseJSON(data);
	 				if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"审核成功",
	   					    timeout:3000
		   				  });
	            		 that.checkCommit();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"审核失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	 			}
	 		});
	    }
	});

	module.exports =  CoachForm;
});	
