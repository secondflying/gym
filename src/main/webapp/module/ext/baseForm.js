/*
 * 表单基类
 * 
 */
define(function(require, exports, module){
    'use strict';

	var BaseForm = GYM.Class.create({
		formId:"",
		findData:null,
		containerId:"",
		dlgId:"",
		idFieldId:"",
		afterCommit:null,
		easyUIFields:[],
		editURL:"",
		addURL:"",
		addTitle:"新增",
		editTitle:"编辑",
		runModel:"new", //new:新增  edit:"编辑"
		initialize : function(){

		},
		
		render:function(containerId,innerHtml,callBack){
			var that = this;
			if($("#" + containerId).length > 0){
				$("#" + containerId).html(innerHtml);
			}else{
				$(document.body).append(innerHtml);
			}
			
			$.parser.onComplete = function(context){
				 if(callBack && typeof callBack == "function"){
						callBack(context);
				 }
				 that.initDlg();
			}
			$.parser.parse("#" + containerId);
           
		},
		
		initDlg:function(){
			 var that = this;
			 $("#" + that.dlgId).dialog({
	     	        closed:true,
	     	        width:that.width,
	     	        modal:true,
	     	        buttons:[{
	 					text:'确定',
	 					iconCls:'icon-save',
	 					handler:function(){
	 						that.submit();
	 					}
	 				},{
	 					text:'取消',
	 					iconCls:'icon-cancel',
	 					handler:function(){
	 						$("#" +  that.dlgId).dialog('close');
	 					}
	 				}]
				 }); 
		},
		
		beforeShowDlg:function(id){
			//$("#avalPass").attr("checked",true);
		},
		resetDlgForm:function(){
			//$("#" + this.formId).form("clear");
			$("#" + this.formId).form("reset");
		},
		showDlg:function(id,afterCommit,notClear){
			this.afterCommit = afterCommit;
			var that = this;
			
			if(!notClear){
				that.resetDlgForm();
			}
			
			this.beforeShowDlg(id);
			this.findData  = null;
			if(id){
				 this.runModel = "edit";
				 $("#" + this.idFieldId).val(id);
				 $('#'+  that.dlgId).dialog('setTitle',this.editTitle);
	    		 this.renderForm(id);
			}else{
				 this.runModel = "new";
				 $("#" + this.idFieldId).val(id);
		    	 $('#'+  that.dlgId).dialog('setTitle',this.addTitle);
			}
			$('#'+  that.dlgId).dialog('center');
			$('#'+ that.dlgId).dialog('open'); 
		},
		
		renderForm:function(id){
	    	 
	         var that = this;
	    	 var queryUrl =  that.editURL + "?id=" + id + "&dc=" + Math.random();
	    	 $.getJSON(queryUrl, function (result) {
				if (result.status.toLowerCase() == "ok") {
					var data = result.data;
					that.findData = data;
					$("#" + that.formId).form("load",data);
					that.afterRenderForm(data);
				} 
			});
	    	 
	     },
	     
	     afterRenderForm:function(data){
	    	 
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
		}
		

	});

	module.exports =  BaseForm;
});	
