/*
 *  商品订单列表
 **/
define(function(require, exports, module){
    'use strict';
    
    var BaseGridView = require('./../../../ext/baseGridView');
	var commodityOrderView = GYM.Class.create(BaseGridView, {
		elemId:"dataGrid",
		delUrl: GYM.ContextRoot + "/manager/commodityOrder/delete",
		initialize: function(option){
			GYM.extend(this,option);
			this.render();
		},
		getForm:function(){
			if(this.form == null){
				 var CommodityOrderForm = require('./CommodityOrderForm'); 
				 this.form = new CommodityOrderForm();
			}
			return this.form;
		},
		render:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/commodityOrder/list",
				method:"GET",
				queryParams:{},
				columns:[[
	                {field:'id',hidden:true},
			        {field:'commodity',title:'商品名称',width:200,align:'center',formatter:function(value,row,index){
						return row.commodity.name;
			        }},
			        {field:'user',title:'用户',width:200,align:'center',formatter:function(value,row,index){
			        	return row.user.name;
			        }},
			        {field:'num',title:'订单数量',width:200,align:'center'},
					{field:'time',title:'创建时间',width:200,align:'center'},
					{field:'state',title:'订单状态',width:100,align:'center',formatter:function(value,row,index){
						if(value == 1){
							return "正在发货";
						}else if(value == 2){
							return "已发货";
						}else if(value == 3){
							return "已完成";
						}else if(value == 4){
							return "已退货";
						}else if(value == 5){
							return "未支付";
						}else if(value == 6){
							return "已支付";
						}
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
	module.exports =  commodityOrderView;
});	