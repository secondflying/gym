/*
 *  用户列表
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var UserView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
		delUrl: GYM.ContextRoot + "/manager/user/delete",
		initialize: function(option){
			GYM.extend(this,option);
			this.render();
		},
		getForm:function(){
			if(this.form == null){
				 var CoachForm = require('./CoachForm'); 
				 this.form = new CoachForm();
			}
			return this.form;
		},
		render:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/user/list",
				method:"GET",
				queryParams:{},
				columns:[[
	                {field:'id',hidden:true},
			        {field:'name',title:'姓名',width:200,align:'center',formatter:function(value,row,index){
						if(!value){
							return "--"
						}else{
							return value
						}
			        }},
			        {field:'nickname',title:'昵称',width:200,align:'center',formatter:function(value,row,index){
			        	if(!value){
							return "--"
						}else{
							return value
						}
			        }},
			        {field:'phone',title:'联系电话',width:200,align:'center'},
					{field:'age',title:'年龄',width:100,align:'center',formatter:function(value,row,index){
						if(value == 0){
							return "--"
						}else{
							return value
						}
			        }},
					{field:'sex',title:'性别',width:100,align:'center',formatter:function(value,row,index){
						if(!value){
							return "--"
						}else{
							return value
						}
			        }},
					{field:'weight',title:'体重',width:100,align:'center',formatter:function(value,row,index){
						if(!value){
							return "--"
						}else{
							return value + "kg";
						}
			        }},
					{field:'height',title:'身高',width:100,align:'center',formatter:function(value,row,index){
						if(!value){
							return "--"
						}else{
							return value + "cm";
						}
						
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
//			        	html += '<span class="o-view o-edit">编辑</span>';
			        	html += '<span class="o-view o-delete">删除</span>';
			        	html += '</div>';
					    return html;
				    }}
			    ]],
				onLoadSuccess : function(data){
					$(".o-edit").off("click");
				    $(".o-edit").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.edit(id);
					});
				    
				    $(".o-delete").off("click");
				    $(".o-delete").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.del(id);
					});
				    
			    },
			    toolbar:[{
			    	id:'btnreload',
			    	text:'刷新',
			    	iconCls:'icon-reload',
			    	handler:function(){
				    	that.reload();
			    	}
			    }]
		    }
		    this.initGrid(setting); 
	    },
	    initGrid:function(setting){
			var that = this;
			var defaultSetting = {
				 method:"GET",
				 pageSize:20,
				 pageList:[20,50,100],
				 singleSelect:true,
				 pagination:true,
				 rownumbers:true,
				 striped:true,
				 fitColumns:true,
				 loadMsg:"请稍候...",
				 frozenColumns:[[]],
				 toolbar: that.toolbar,
				 columns:[[]]
			};
			this.setting = GYM.extend(defaultSetting,setting); 
			$('#'+ this.elemId).datagrid(this.setting);
			this.Message = {
			    addSuccess:"添加成功",
			    addFailue:"添加失败",
			    editSuccess:"保存成功",
			    editFailue:"保存失败",
			    delSuccess:"删除成功",
			    delFailue:"删除失败"
			}
		}
	});
	module.exports =  UserView;
});	