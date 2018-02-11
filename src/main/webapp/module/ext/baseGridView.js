/*
 * 用户编辑模块
 * 
 */
define(function(require, exports, module){
    'use strict';
    
	var BaseGridView = GYM.Class.create({
		elemId:"dataGrid",
		toobar:"#tb",
		setting:{},
		form:null,
		delUrl:"",
		initialize : function(option){
		   MyGeoway.extend(this,option); 
           this.init();
		},
		
		
		initGrid:function(setting){
		   this.toolbar = [{
				iconCls: 'icon-add',
				text:"新增",
				handler: function(){
					that.add();
				}
			}];
			var that = this;
			var defaultSetting = {
			      method:"get",
			      pageSize:20,
			      pageList:[20,50,100],
			      singleSelect:true,
			      pagination:true,
				  rownumbers:true,
				  striped:true,
				  fitColumns:true,
				  loadMsg:"查询中,请稍候...",	
				  emptyMsg:"没有查询到对应的记录!",
				  frozenColumns:[[]],
				  toolbar: that.toolbar,
				  columns:[[]]
			}
			
			this.setting = MyGeoway.extend(defaultSetting,setting); 
			
			 $('#'+ this.elemId).datagrid(this.setting);
			 
			 this.Message = {
			    addSuccess:"添加成功",
			    addFailue:"添加失败",
			    editSuccess:"保存成功",
			    editFailue:"保存失败",
			    delSuccess:"删除成功",
			    delFailue:"删除失败"
			  }
		},
		
		add:function(){
			
		    this.messageSuccess = this.Message.addSuccess;
		    this.messageFailue = this.Message.addFailue;
		    var that = this;
		    var tip = function(result){
		    	that.tips(result);
		    }

			this.getForm().showDlg("",tip);

		},
		
		edit:function(id){
			 this.messageSuccess = this.Message.editSuccess;
			 this.messageFailue = this.Message.editFailue;
			 var that = this;
		     var tip = function(result){
		    	that.tips(result);
		     }
		     
		     this.getForm().showDlg(id,tip);
		},
		
		del:function(ids){
			 this.messageSuccess = this.Message.delSuccess;
			 this.messageFailue = this.Message.delFailue;

			 var that = this;
			 var tip = function(result){
			    	that.tips(result);
			 }
	    	 $.messager.confirm('确认', '确定删除?', function(r){
	 			if (r){
	 				 $.ajax({
	 		             url: that.delUrl,
	 		             data: {ids:ids},
	 		             dataType: "json",
	 		             type:'post',
	 		             success: function(result){
	 		            	tip(result);
	 		             }
	 		         });
	 			 }
	 		 });
			
	
		},
		
		load:function(queryParams){
			var that = this;
			if(queryParams){
				$('#'+ that.elemId).datagrid("load",queryParams);
			}else{
				$('#'+ that.elemId).datagrid("load");
			}
		},
		
		reload:function(queryParams){
			var that = this;
			if(queryParams){
				$('#'+ this.elemId).datagrid("reload",queryParams);
			}else{
				$('#'+ this.elemId).datagrid("reload");
			}
		},
		
		refresh:function(){
			this.reload();
		},

		tips:function(result){
		    var that = this;
			if(result.status.toLowerCase() == "ok"){
				     that.refresh();
					 $.messager.show({
  				    	title:'提示',
  					    msg:that.messageSuccess,
  					    timeout:3000
  				      });
			 }else{
					$.messager.show({
				    	title:'提示',
					    msg: that.messageFailue + ":"+ result.message,
					    timeout:3000
				    });
		       }
	     }});	
	
	  module.exports =  BaseGridView;
});	
