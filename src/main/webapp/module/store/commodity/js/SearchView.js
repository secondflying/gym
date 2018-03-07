/*
 * 搜索模块
 * 
 */
define(function(require, exports, module){
    'use strict';
    
	var SearchView = GYM.Class.create({
		view:null,
		initialize : function(clubView){
			 this.view = clubView;
			 var that = this;
			 
			 $("#btnSeach").click(function(){
				 var filterParam = "";
	    		 var name = $("#txtName").val();
	    		 if($.trim(name).length > 0){
	    			 filterParam += ";Q_name_S_LK=" + $.trim(name);
	    		 }
	  
	    		 var address = $("#txtAddress").val();
	    		 if($.trim(address).length > 0){
	    			 filterParam += ";Q_address_S_LK=" + $.trim(address);
	    		 }
	  
	    		 that.view.load({
	    			 filterParam: filterParam
	    		 });
	    	 });
			 
			 $("#btnReset").click(function(){
				 $("#seachContent").form('reset');
				 $("#btnSeach").trigger("click");
			 });
		}
	});

	module.exports =  SearchView;
});	
