
(function(){
	/**
     * Namespace: GYM
     * 所有的GYM成员的命名空间
     */
	GYM= window.GYM || {};
	GYM.ContextRoot="";
	GYM.extend = function(destination, source) {
	    destination = destination || {};
	    if (source) {
	        for (var property in source) {
	            var value = source[property];
	            if (value !== undefined) {
	                destination[property] = value;
	            }
	        }

	        var sourceIsEvt = typeof window.Event == "function"
	                          && source instanceof window.Event;

	        if (!sourceIsEvt
	           && source.hasOwnProperty && source.hasOwnProperty("toString")) {
	            destination.toString = source.toString;
	        }
	    }
	    return destination;
	};
})();
