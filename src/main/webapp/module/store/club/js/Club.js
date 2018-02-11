/*
 *  健身房管理入口
 **/
define(function(require, exports, module){
    'use strict';
    
    var ClubView = require('./ClubView');
    var clubView =  new ClubView();
    
    var SearchView =  require('./SearchView');
	var searchView = new SearchView(clubView);
    
});	