/*
 *  菜单管理
 **/
define(function(require, exports, module){
    'use strict';
    
    var MenuView = GYM.Class.create({
    	initialize: function(option){
    		this.subscribe();
			this.render(option.index);
		},
		subscribe: function(){
			var that = this;
			GYM.Event.on("menuView/render",function(index){
				that.render(index);
			});
		},
		render: function(index){
			if(index == 0){
				this.renderStroe();
			}
		},
		renderStroe: function(){
			var html = require("./../template/stroeMenu.html");
		    $("#leftMenu").html(html);
		    $("#leftMenu .sortItem").find("ul li").unbind("click");
		    $("#leftMenu .sortItem").find("ul li").click(function(){
		    	$("#leftMenu .sortItem").find("ul li").removeClass("selected");
		    	$(this).addClass("selected");
		    	var sort = $(this).attr("sort");
		    	var url;
		    	if(sort == "store/club"){
		    		url = GYM.ContextRoot + "/manager/club/index";
		    	}else if(sort == "store/coach"){
		    		url = GYM.ContextRoot + "/manager/coach/index";
		    	}else if(sort == "store/course"){
		    		url = GYM.ContextRoot + "/manager/course/index";
		    	}else if(sort == "store/user"){
		    		url = GYM.ContextRoot + "/manager/user/index";
		    	}else if(sort == "store/commodity"){
		    		url = GYM.ContextRoot + "/manager/commodity/index";
		    	}
		    	$("#linkPage").attr("src", url);
		    });
		}
    });
	module.exports =  MenuView;
});