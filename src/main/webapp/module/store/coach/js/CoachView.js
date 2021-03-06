/*
 *  健身房列表
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var CoachView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
		delUrl: GYM.ContextRoot + "/manager/coach/delete",
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
				url: GYM.ContextRoot + "/manager/coach/list",
				method:"GET",
				queryParams:{},
				columns:[[
	                {field:'id',hidden:true},
			        {field:'name',title:'名称',width:200,align:'center'},
			        {field:'phone',title:'联系电话',width:200,align:'center'},
					{field:'age',title:'年龄',width:100,align:'center'},
					{field:'sex',title:'性别',width:100,align:'center'},
					{field:'weight',title:'体重',width:100,align:'center',formatter:function(value,row,index){
						if(value){
							return value + "kg";
						}else{
							return "--";
						}
			        }},
					{field:'height',title:'身高',width:100,align:'center',formatter:function(value,row,index){
						if(value){
							return value + "cm";
						}else{
							return "--";
						}
			        }},
			        {field:'state',title:'审核状态',width:100,align:'center',formatter:function(value,row,index){
						if(value == 0){
							return "未审核";
						}else if(value == 1){
							return "审核通过";
						}else if(value == -1){
							return "审核不通过";
						}
			        }},
			        {field:'clubid',title:'所属健身房',width:150,align:'center',formatter:function(value,row,index){
						if(row.club != null){
							return row.club.name;
						}else{
							return "--"
						}
			        }},
			        {field:'level',title:'星级',width:150,align:'center',formatter:function(value,row,index){
						if(row.level){
							return row.level + "星";
						}else{
							return "--"
						}
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	if(row.state == 0){
			        		html += '<span class="o-view o-check">审核</span>';
			        	}
			        	html += '<span class="o-view o-edit">编辑</span>';
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
				    
				    $(".o-check").off("click");
				    $(".o-check").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.check(id);
					});
				    
			    },
			    toolbar:[{
			    	id:'btnreload',
			    	text:'刷新',
			    	iconCls:'icon-reload',
			    	handler:function(){
				    	that.reload();
			    	}
			    },
			    {
			    	id:'btnview',
			    	text:'新增',
			    	iconCls:'icon-add',
			    	handler:function(){
			    		that.add();
			    	}
			    }
			    ]
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
		},
		check: function(id){
			var that = this;
			var checkCommit = function(){
				that.reload();
			};
			this.getForm().showCheckDlg(id, checkCommit);
		}
	});
	module.exports =  CoachView;
});	