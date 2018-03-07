/*
 *  商品管理入口
 **/
define(function(require, exports, module){
    'use strict';
    
    var CommodityView = require('./CommodityView');
    var commodityView =  new CommodityView();
    
    var SearchView =  require('./SearchView');
	var searchView = new SearchView(commodityView);
    
});	