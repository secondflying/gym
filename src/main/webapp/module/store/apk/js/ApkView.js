/*
 *  Apk列表
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var ApkView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
		initialize: function(option){
			GYM.extend(this,option);
			this.render();
		},
		getForm:function(){
			if(this.form == null){
				 var ApkForm = require('./ApkForm'); 
				 this.form = new ApkForm();
			}
			return this.form;
		},
		render:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/apk/list",
				method:"GET",
				queryParams:{},
				columns:[[
	                {field:'id',hidden:true},
			        {field:'url',title:'文件名',width:200,align:'center',formatter:function(value,row,index){
			        	if(!value){
							return "--"
						}else{
							return value
						}
			        }},
			        {field:'version',title:'版本',width:200,align:'center',formatter:function(value,row,index){
			        	if(!value){
							return "--"
						}else{
							return value
						}
			        }},
					{field:'time',title:'更新时间',width:100,align:'center',formatter:function(value,row,index){
			        	if(!value){
							return "--"
						}else{
							return value
						}
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	html += '<span class="o-view o-edit">更新文件</span>';
			        	if(row.url){
			        		var downUrl = "http://image2.gebanban.com/apk/" + row.url;
			        		html += '<a class="o-view" href="'+downUrl+'" target="_blank">下载</span>';
			        	}
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
	module.exports =  ApkView;
});	