/*
 *  菜单管理
 **/
define(function(require, exports, module){
    'use strict';
    
    var index = $("#headerMenu ul li").index($("#headerMenu ul li.selected"));
    var MenuView = require('./MenuView');
    var menuView =  new MenuView({index: index});
    
});