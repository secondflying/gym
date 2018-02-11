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
		initialize : function(containerId){
            
			if(containerId){
				this.containerId = containerId;
			}
			var html = require("./../template/clubForm.html");
			var that = this;
			this.render(this.containerId,html,function(context){
				that.initForm();
			});
		},
		
		initForm:function(){
			 var that = this;
			 $("#clubForm-level").combobox({
        	    data:[{
        	        "id":1,
        	        "text":"1",
        	        "selected":true
        	    },{
        	        "id":2,
        	        "text":"2"
        	    },{
        	        "id":3,
        	        "text":"3"
        	    },{
        	        "id":4,
        	        "text":"4"
        	    },{
        	        "id":5,
        	        "text":"5"
        	    },{
        	        "id":6,
        	        "text":"6"
        	    },{
        	        "id":7,
        	        "text":"7"
        	    },{
        	        "id":8,
        	        "text":"8"
        	    },{
        	        "id":9,
        	        "text":"9"
        	    },{
        	        "id":10,
        	        "text":"10"
        	    },{
        	        "id":11,
        	        "text":"11"
        	    },{
        	        "id":12,
        	        "text":"12"
        	    },{
        	        "id":13,
        	        "text":"13"
        	    },{
        	        "id":14,
        	        "text":"14"
        	    },{
        	        "id":15,
        	        "text":"15"
        	    },{
        	        "id":16,
        	        "text":"16"
        	    }],
        	    editable:false,
        	    value:"1"
        	 });
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
	    	 
	    }
	});

	module.exports =  ClubForm;
});	
