/**
 * jQuery EasyUI --- 功能扩展
 * author:daidd
 * 2016.5.25
 * Copyright (c) 2015-2099
 */
(function($) {

	/**
	 * 新增 validatebox 校验规则
	 * 
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		url:{
			validator : function(value, param) {
				var validValue = value.replace(/localhost/i,"127.0.0.1");
				return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(validValue);
			},
			message : '请输入有效的URL地址'
		},
		picture:{
			validator : function(value, param) {
				return /(.jpg|.bmp|.png|.gif|.ps|.jpeg)$/i.test(value);
			},
			message : '图片文件的扩展名必须为jpg,bmp,jpeg,gif,ps格式'
		},
		pdf:{
			validator : function(value, param) {
				return /(.pdf)$/i.test(value);
			},
			message : 'pdf文件的扩展名必须为.pdf格式'
		},
		excel:{
			validator : function(value, param) {
				return /(.xls|.xlsx)$/i.test(value);
			},
			message : 'EXCEL文件名无效,扩展名必须为xls,xlsx格式'
		},
		word:{
			validator : function(value, param) {
				return /(.doc|.docx)$/i.test(value);
			},
			message : 'Word文件名无效,扩展名必须为doc,docx格式'
		},
		bpmn:{
			validator : function(value, param) {
				return /(.bpmn)$/i.test(value);
			},
			message : '文件名不合法,文件的扩展名必须为.bpmn格式'
		},
		confirmPasswd:{
    	        validator: function(value,param){
    	            return value == $(param[0]).val();
    	        },
    	        message: '输入的密码不一致!'
		}
	});
	
    
})(jQuery);