/*
 *  健身房管理入口
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var ClubView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
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
					{field:'description',title:'描述',width:300,align:'center',formatter:function(value,row,index){
			        	
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	html += '<span class="o-view o-edit">编辑</span>';
			        	html += '<span class="o-view o-delete">删除</span>';
			        	html += '</div>';
					    return html;   		       		
				    }}
			    ]],
				onLoadSuccess : function(data){
					$(".mc-recommend").off("click");
				    $(".mc-recommend").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.recommend(id);
					});
				    
				    $(".mc-view").off("click");
				    $(".mc-view").on("click",function(){
						 var id = $(this).parent().attr("id");
						 var svrType= $(this).parent().attr("svrType");
						 that.view(id,svrType);
					
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
			    },
			    {
			    	id:'btnview',
			    	text:'编辑',
			    	iconCls:'icon-edit',
			    	handler:function(){
			    		that.getForm().showSTHSetWin();
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
			    addSuccess:"保存成功",
			    addFailue:"保存失败",
			}
		}
	});
	module.exports =  ClubView;
});	