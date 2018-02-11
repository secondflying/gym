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
			var html = require("./../template/stroeMenu.html");
		    $("#leftMenu").html(html);
		    
		}
    });
	module.exports =  MenuView;
});