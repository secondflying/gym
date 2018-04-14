/*
 *  健身房列表
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var ClubView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
		delUrl: GYM.ContextRoot + "/manager/club/delete",
		initialize: function(option){
			GYM.extend(this,option);
			this.render();
		},
		getForm:function(){
			if(this.form == null){
				 var ClubForm = require('./ClubForm'); 
				 this.form = new ClubForm();
			}
			return this.form;
		},
		getImageForm: function(){
			if(this.imageForm == null){
				 var ImageForm = require('./ImageForm'); 
				 this.imageForm = new ImageForm();
			}
			return this.imageForm;
		},
		render:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/club/list",
				method:"GET",
				queryParams:{},
				columns:[[
	                {field:'id',hidden:true},
			        {field:'name',title:'名称',width:200,align:'center'},
			        {field:'phone',title:'电话',width:200,align:'center'},
					{field:'address',title:'地址',width:250,align:'center'},
			        {field:'op',title:'操作',width:400,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	html += '<span class="o-view o-coach">教练管理</span>';
			        	html += '<span class="o-view o-img">照片管理</span>';
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
				    
				    $(".o-coach").off("click");
				    $(".o-coach").on("click",function(){
						 var id = $(this).parent().attr("id");
						 //that.bindCoach(id);
						 var url = GYM.ContextRoot + "/manager/club/coachManager?id="+id;
						 window.location.href = url;
					});
				    
				    $(".o-img").off("click");
				    $(".o-img").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.showImageManager(id);
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
		},
		bindCoach: function(id){
			this.getForm().showBindCoachDlg(id);
		},
		showImageManager: function(id){
			this.getImageForm().showImageFormDlg(id);
		}
	});
	module.exports =  ClubView;
});	