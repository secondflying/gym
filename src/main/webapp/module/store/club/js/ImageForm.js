/*
 * 图片管理模块
 * 
 */
define(function(require, exports, module){
    'use strict';

    var BaseForm = require('./../../../ext/baseForm');
	var ImageForm = BaseForm.extend({
		editURL:GYM.ContextRoot + "/manager/club/detail",
		uploadImgUrl: GYM.ContextRoot + "/manager/file/upload",
		saveImgUrl: GYM.ContextRoot + "/manager/file/saveimg",
		delImgUrl: GYM.ContextRoot + "/manager/file/delimg",
		cate: "club",
		initialize : function(){
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
			
			$('#serviceFile').filebox({
	            buttonText: '选择图片',
	            validType: 'picture',
	            required: true
	        })
		},
		
		beforeShowDlg:function(id){
			
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
						
						$("#imgList ul li i").off("click");
						$("#imgList ul li i").click(function(){
							var url = $(this).attr("url");
							$.messager.confirm('警告','确定删除该图片?',function(r){
							    if(r){
							    	that.delImage(url);
							    }  
							});  
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
	 					that.saveImage(img, clubid);
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
		saveImage: function(img, clubid){
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
		   				that.renderImgs(clubid);
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
		delImage: function(url){
			var that = this;
			$.ajax({
	             url: that.delImgUrl,
	             data: {url: url},
	             dataType: "json",
	             type:"POST",
	             success: function(result){
	            	 if(result.status == "ok"){	
	            		 var clubid = $("#image-clubId").val();
	            		 that.renderImgs(clubid);
	   				 }else{
	   				   $.messager.show({
	   				    	title:'提示',
	   					    msg:"删除失败："+ result.message,
	   					    timeout:3000
	   				   }) 
	   				 }
	             }
	        });
		}
	});

	module.exports =  ImageForm;
});	