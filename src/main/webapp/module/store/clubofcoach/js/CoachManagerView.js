/*
 *  健身房教练管理列表
 **/
define(function(require, exports, module){
    'use strict';
    
	var ClubView = GYM.Class.create({
		coachToClubURL: GYM.ContextRoot + "/manager/coach/coachToClub",
		coachOffClubURL: GYM.ContextRoot + "/manager/coach/coachOffClub",
		clubId: null,
		initialize: function(option){
			GYM.extend(this,option);
			this.clubId = GYM.clubId;
			this.renderNoUse();
			this.renderInUse();
		},
		getForm:function(){
			if(this.form == null){
				 var CoachManagerForm = require('./CoachManagerForm'); 
				 this.form = new CoachManagerForm();
			}
			return this.form;
		},
		renderNoUse:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/coach/list",
				method:"GET",
				queryParams:{filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ=-1"},
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
			        {field:'level',title:'星级',width:150,align:'center',formatter:function(value,row,index){
						if(row.level){
							return row.level + "星";
						}else{
							return "--"
						}
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	html += '<span class="o-view o-add-coach">教练分配</span>';
					    return html;
				    }}
			    ]],
				onLoadSuccess : function(data){
					$(".o-add-coach").off("click");
				    $(".o-add-coach").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.coachToClub(that.clubId, id);
					});
			    }
		    }		   
		    this.initNoUseGrid(setting); 
	    },
	    initNoUseGrid:function(setting){
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
			$('#noUseGrid').datagrid(this.setting);
		},
		reloadNoUseData: function(){
			$('#noUseGrid').datagrid("load");
		},
		coachToClub: function(clubId, coachId){
			var that = this;
			$.ajax({
	             url: this.coachToClubURL,
	             data: {clubId: clubId, coachId: coachId},
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"操作成功",
	   					    timeout:3000
		   				  });
	            		 that.reloadNoUseData();
	            		 that.reloadInUseData();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"操作失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
		},
		renderInUse:function(){
			var that = this;
			var setting = {
				url: GYM.ContextRoot + "/manager/coach/list",
				method:"GET",
				queryParams:{filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ="+this.clubId},
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
			        {field:'level',title:'星级',width:150,align:'center',formatter:function(value,row,index){
						if(row.level){
							return row.level + "星";
						}else{
							return "--"
						}
			        }},
			        {field:'op',title:'操作',width:200,align:'center',formatter:function(value,row,index){
			        	var html  = '<div id="'+ row.id +'">';
			        	html += '<span class="o-view o-edit-level">编辑星级</span>';
			        	html += '<span class="o-view o-del-coach">教练移除</span>';
					    return html;
				    }}
			    ]],
				onLoadSuccess : function(data){
					$(".o-edit-level").off("click");
				    $(".o-edit-level").on("click",function(){
						 var id = $(this).parent().attr("id");
						 that.editLevel(id);
					});
				    $(".o-del-coach").off("click");
				    $(".o-del-coach").on("click",function(){
						 var id = $(this).parent().attr("id");
						 var id = $(this).parent().attr("id");
						 that.coachOffClub(id);
					});
			    }
		    }		   
		    this.initInUseGrid(setting); 
	    },
	    initInUseGrid:function(setting){
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
			$('#inUseGrid').datagrid(this.setting);
		},
		reloadInUseData: function(){
			$('#inUseGrid').datagrid("load");
		},
		coachOffClub: function(coachId){
			var that = this;
			$.ajax({
	             url: this.coachOffClubURL,
	             data: {coachId: coachId},
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"操作成功",
	   					    timeout:3000
		   				  });
	            		 that.reloadNoUseData();
	            		 that.reloadInUseData();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"操作失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
		},
		editLevel: function(id){
			var that = this;
			var commit = function(result){
				 if(result.status == "ok"){	
					$.messager.show({
				    	title:'提示',
					    msg:"操作成功",
					    timeout:3000
   				    });
					that.reloadInUseData();
				 }else{
				   $.messager.show({
				    	title:'提示',
					    msg:"操作失败："+ result.message,
					    timeout:3000
				   }) 
				}
			};
			this.getForm().showDlg(id, commit);
		}
	});
	module.exports =  ClubView;
});	