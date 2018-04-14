/*
 * 服务编辑模块
 * 
 */
define(function(require, exports, module){
    'use strict';

    var BaseForm = require('./../../../ext/baseForm');
	var CoachManagerForm = BaseForm.extend({
		idFieldId:"levelForm-id",
		formId:"levelForm",
		dlgId:"levelDlg",
		containerId:"levelDlgCon",
		afterCommit:null,
		easyUIFields:[],
		addURL: GYM.ContextRoot + "/manager/coach/updateLevel",
		editURL:GYM.ContextRoot + "/manager/coach/detail",
		initialize : function(containerId){
            
			if(containerId){
				this.containerId = containerId;
			}
			var html = require("./../template/levelForm.html");
			var that = this;
			this.render(this.containerId,html,function(context){
				that.initForm();
			});
		},
		
		initForm:function(){
			 var that = this;
			 
		},
		beforeShowDlg:function(id){
			
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
	    	 
	    }
	});

	module.exports =  CoachManagerForm;
});	
