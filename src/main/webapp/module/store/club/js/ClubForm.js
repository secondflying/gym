/*
 * 服务编辑模块
 * 
 */
define(function(require, exports, module){
    'use strict';

    var BaseForm = require('./../../../ext/baseForm');
	var ClubForm = BaseForm.extend({
		idFieldId:"clubForm-id",
		formId:"clubForm",
		dlgId:"clubDlg",
		containerId:"clubDlgCon",
		afterCommit:null,
		easyUIFields:[],
		addURL: GYM.ContextRoot + "/manager/club/save",
		editURL:GYM.ContextRoot + "/manager/club/detail",
		coachsURL:GYM.ContextRoot + "/manager/coach/list",
		coachsToClubYRL: GYM.ContextRoot + "/manager/coach/coachsToClub",
		uploadImgUrl: GYM.ContextRoot + "/manager/file/upload",
		saveImgUrl: GYM.ContextRoot + "/manager/file/saveimg",
		cate: "club",
		initialize : function(containerId){
            
			if(containerId){
				this.containerId = containerId;
			}
			var html = require("./../template/clubForm.html");
			var that = this;
			this.render(this.containerId,html,function(context){
				that.initForm();
			});
			
			var imageForm = require("./../template/imageForm.html");
			$("#imageDlgCon").html(imageForm);
			$.parser.parse("#imageDlgCon");
			
			$("#imageFormDlg").dialog({
     	        closed:true,
     	        width:546,
     	        height: 350,
     	        modal: true,
     	        buttons:[{
 					text:'取消',
 					iconCls:'icon-cancel',
 					handler:function(){
 						$("#imageFormDlg").dialog('close');
 					}
 				}]
			});
			
			var bindCoach = require("./../template/bindCoach.html");
			$("#bindCoachDlgCon").html(bindCoach);
			$.parser.parse("#bindCoachDlgCon");
			
			$("#bindCoachDlg").dialog({
     	        closed:true,
     	        width:450,
     	        modal: true,
     	        buttons:[{
 					text:'确定',
 					iconCls:'icon-ok',
 					handler:function(){
 						that.saveBc();
 						$("#bindCoachDlg").dialog('close');
 					}
 				},{
 					text:'取消',
 					iconCls:'icon-cancel',
 					handler:function(){
 						$("#bindCoachDlg").dialog('close');
 					}
 				}]
			});
			
			$('#serviceFile').filebox({
	            buttonText: '选择图片',
	            validType: 'picture',
	            required: true
	        })
		},
		
		initForm:function(){
			 var that = this;
			 
		},
		isValidURL:function(url){
			var validUrl = url.replace(/localhost/i,"127.0.0.1");
			var flag = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(validUrl);
			 
			return  flag;
		},
		beforeShowDlg:function(id){
//			var showId = id ? id:0;
//			 $("#image-svr").attr("src",MyGeoway.ContextRoot + "/serviceManager/getImage.do?id=" + showId + "&dc=" + Math.random());
//			 $("#serverForm-imageUrl").val("");
			
		},
		submit:function(){
		   var that = this;
		   var callBack = that.afterCommit;
		  
		   $("#"+ that.formId).form('submit',{
	 			url:that.addURL,
	 			onSubmit:function(param){
	 				var flag = $(this).form('validate');
	 				if(flag){
	 					$("#" + that.dlgId).dialog('close'); 
	 				}
	 				return  flag;
	 			},
	 			success:function(data){
	 				var result = $.parseJSON(data);
	 				if(callBack && typeof callBack == "function"){
	   					callBack(result);
	   				}
	 			}
	 		});
		},
        afterRenderForm:function(data){
	    	 
	    },
	    showBindCoachDlg: function(clubid){
	    	$('#bindCoachDlg').dialog('setTitle',"教练管理");
			$('#bindCoachDlg').dialog('open');
			$("#clubId").val(clubid);
			this.queryUnUseCoachs(clubid);
			this.queryInUseCoachs(clubid);
	    },
	    queryUnUseCoachs: function(clubid){
	    	var that = this;
	    	$("#bcAddList").html("");
	    	$.ajax({
	             url: this.coachsURL,
	             data: {filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ=-1", page: 1, rows: 999},
	             dataType: "json",
	             type:"GET",
	             success: function(result){
	            	 if(result.status == "ok"){	
	         			var html = '';
	         			for(var i=0;i<result.rows.length;i++){
	         				var name = result.rows[i].name;
	         				var oid = result.rows[i].id;
	         				html += '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="选择"></i></li>';
	         			}
	         			$("#bcAddList").html(html);
	         			that.bindBcAddUI();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"查询失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    },
	    queryInUseCoachs: function(clubid){
	    	var that = this;
	    	$("#bcDelList").html("");
	    	$.ajax({
	             url: this.coachsURL,
	             data: {filterParam: "Q_status_N_EQ=0;Q_state_N_EQ=1;Q_clubid_N_EQ="+clubid, page: 1, rows: 999},
	             dataType: "json",
	             type:"GET",
	             success: function(result){
	            	 if(result.status == "ok"){	
	         			var html = '';
	         			for(var i=0;i<result.rows.length;i++){
	         				var name = result.rows[i].name;
	         				var oid = result.rows[i].id;
	         				html += '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="移除"></i></li>';
	         			}
	         			$("#bcDelList").html(html);
	         			that.bindBcDelUI();
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"查询失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    },
	    bindBcAddUI: function(){
	    	var that = this;
	    	$("#bcAddList").find("li i").unbind("click");
 			$("#bcAddList").find("li i").click(function(){
 				var name = $(this).parent().attr("name");
 				var oid = $(this).parent().attr("oid");
 				var addHtml = '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="移除"></i></li>';
 				$("#bcDelList").append(addHtml);
 				$(this).parent().remove();
 				that.bindBcDelUI();
 			});
	    },
	    bindBcDelUI: function(){
	    	var that = this;
	    	$("#bcDelList").find("li i").unbind("click");
 			$("#bcDelList").find("li i").click(function(){
 				var name = $(this).parent().attr("name");
 				var oid = $(this).parent().attr("oid");
 				var delHtml = '<li oid="'+oid+'" name="'+name+'">'+name+'<i title="选择"></i></li>';
 				$("#bcAddList").append(delHtml);
 				$(this).parent().remove();
 				that.bindBcAddUI();
 			});
	    },
	    saveBc: function(){
	    	var clubId = $("#clubId").val();
	    	var coachIds = [];
	    	$("#bcDelList li").each(function(){
	    		var oid = $(this).attr("oid");
	    		coachIds.push(oid);
	    	});
	    	$.ajax({
	             url: this.coachsToClubYRL,
	             data: {clubId: clubId, coachIds: coachIds.join(",")},
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存成功",
	   					    timeout:3000
		   				  }) 
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
	    },
	    /*图片管理*/
	    showImageFormDlg: function(clubid){
	    	$('#imageFormDlg').dialog('setTitle',"图片管理");
			$('#imageFormDlg').dialog('open');
			$("#image-clubId").val(clubid);
			var that = this;
			$("#imageFormBtn").unbind("click");
			$("#imageFormBtn").click(function(){
				that.uploadImg(clubid);
			});
			$("#imgList").html("");
			this.renderImgs(clubid);
	    },
	    renderImgs: function(clubid){
	    	var that = this;
	    	$.ajax({
	             url: this.editURL,
	             data: {id: clubid},
	             dataType: "json",
	             type:"GET",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		var data = {list: result.result.images || []};
						var imageList = require("./../template/imageList.html");
						var render = template.compile(imageList);
						var htmlstr =  render(data);
						$("#imgList").html(htmlstr);
						
						$("#imgList ul li").off("mouseover");
						$("#imgList ul li").mouseover(function(){
							$(this).find("i").show();
						});
						$("#imgList ul li").off("mouseleave");
						$("#imgList ul li").mouseleave(function(){
							$(this).find("i").hide();
						});
						
						$("#imgList ul li").off("click");
						$("#imgList ul li").click(function(){
							that.delImage();
						});
						$(".fancybox-effects-b").fancybox({
							openEffect  : 'none',
							closeEffect	: 'none',
							helpers : {
								title : {
									type : 'over'
								}
							}
						});
	   				 }else{
	   					$.messager.show({
	   				    	title:'提示',
	   					    msg:"获取图片失败："+ result.message,
	   					    timeout:3000
	   				    }) 
	   				 }
	             }
	        });
	    },
	    //图片上传到OSS服务器
	    uploadImg: function(clubid){
	    	var that = this;
	    	$("#imageForm").form('submit',{
	 			url:that.uploadImgUrl,
	 			onSubmit:function(param){
	 				var flag = $(this).form('validate');
	 				return  flag;
	 			},
	 			success:function(data){
	 				$('#serviceFile').filebox('clear');
	 				var result = $.parseJSON(data);
	 				if(result.status == "ok"){
	 					//返回图片名，再将图片关联信息存入图片表
	 					var img = {
 							cate: that.cate,
 							url: result.result,
 							cid: clubid
	 					}
	 					that.saveImage(img);
	 				}else{
	 					$.messager.show({
	   				    	title:'提示',
	   					    msg:"上传失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	 				}
	 			}
	    	});
		},
		saveImage: function(img){
			var that = this;
			$.ajax({
	             url: that.saveImgUrl,
	             data: img,
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存成功",
	   					    timeout:3000
		   				  }) 
		   				  
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"保存失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
		},
		delImage: function(){
			
		}
	});

	module.exports =  ClubForm;
});	
