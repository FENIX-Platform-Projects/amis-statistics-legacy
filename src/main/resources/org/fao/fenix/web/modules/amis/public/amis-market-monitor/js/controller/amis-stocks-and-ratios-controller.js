
function StocksAndRatiosController(domain_type_model, view) {
    this._domain_type_model = domain_type_model;
    this._view = view;
    
    var _this = this;
}


StocksAndRatiosController.prototype = {
    getData : function () {
    	var indicators = this._domain_type_model.getDomainIndicators();
    	if (typeof indicators !== 'undefined' && indicators.length > 0) {
	 // the array is defined and has at least one element 
		this.getDataAndCharts(indicators);	 	      	
        } else {
	   alert("Please check if the id has been set to a product code");
	}
        
    },
    
      getDataAndCharts : function(indicatorsArray) {          
                     
                     var chart_div_id =this._view._divId;
					 var callbackIdentifier = this._view.getSessionId();

		     $('#'+chart_div_id).append('<div style="text-align:center"><img src="http://statistics.amis-outlook.org/data/amis-market-monitor/images/loading-chart.gif"/></div>');
   
             	     var index = 0;
    	 	     var seriesOptions = [];
    	 	     var unitArray = [];
    	 	     var sourceArray = [];
    	 	     var sourceUrlArray = [];
    	 	     var seriescolors = ['#CCCCCC', '#4572A7', '#000000', '#FF9900'];
    	 	     var server_script_name = this._domain_type_model.getDomainServerScriptName();
    	 	     var domain_table_code = this._domain_type_model.getDomainTableCode();
    	 	     var timeSpan = this._view.getChartTimeFrame();
    	 	     
    	 	     
    	 	     
    	 	     var current_view = this._view;
    	 	     
    	 	      
    	 	    function next() {
    	 	 
    	 	         if (index < indicatorsArray.length ) {
    	 	             var indicatorcode = indicatorsArray[index];
    	 	             var urlNew = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/"+server_script_name+".jsp?indicatorcode="+indicatorcode + "&domaintablecode="+domain_table_code+"&timespan="+timeSpan+"&cb="+callbackIdentifier+"&callback=?";
    	 	         
    	 	          
    	 	             index++;
    	 	                 	 	      
    	 	             
    	 	              $.ajax({
    				     type:       "GET",
    				     url:        urlNew,
    				     async: false,
    				     jsonpCallback: 'jsonCallback'+callbackIdentifier+'_'+indicatorcode,
    				     contentType: "application/json",
    				     dataType: 'jsonp',
    				     success:    function(jsondata) {	
    					   var categories = jsondata.categories;
    					   var product = jsondata.product;
    					   var seriesData = jsondata.dataseries;
    					   
    					  $.each(seriesData, function(i, serie) {
    					  
    					  if($.inArray(serie.units, unitArray) == -1 ){
    					      unitArray.push(serie.units);
    					   }
    
    					    if($.inArray(serie.source, sourceArray) == -1 ){
    					      sourceArray.push(serie.source);
    					   } 
    
    					   if($.inArray(serie.sourceurl, sourceUrlArray) == -1 ){
    						sourceUrlArray.push(serie.sourceurl);
    		                           } 
    		                           
    		                           var chartformat;
    		                            if(serie.chartformat!=null)
    					   	chartformat = serie.chartformat;
    					    else
    					    chartformat = '';
    					    
    					    
    					     var seriesshortname;
					        if(serie.indicatorshortname!=null)
					           seriesshortname = serie.indicatorshortname;
					     else
    					        seriesshortname = '';
    					    
    		                          
    		                           
    					     seriesOptions[i] = {
    						name: serie.indicator,
    						shortname: seriesshortname,
    						data: serie.data,
    						type: serie.charttype,
    						color: seriescolors[i],
    						dashStyle:chartformat,
    						units: serie.units,
    						yAxis: 0,
    						tooltip: {
    						  valueSuffix: ' '+ serie.units
    					       }
    					     };
    					 
                                              });
                              
                                                                                
                                             current_view.createHighChartsMultipleAxesChartOptions(seriesOptions, sourceUrlArray, unitArray, sourceArray, categories, 'chart_stocks_'+indicatorcode, product + " Stocks and Ratios", seriesData);
					     current_view.showHighChart();
					    
					    
    					   next();
    				    },
    				    error: function (request, status, error) {
    					    next();
    				    }
    	 		     });	 		              
    	 	         }
    	 	     }
    	 	     next();           
         }
  
};