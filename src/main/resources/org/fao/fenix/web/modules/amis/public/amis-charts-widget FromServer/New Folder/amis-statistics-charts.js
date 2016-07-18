(function() {

// Localize jQuery variable
var jQuery;
var statisticsHighchart;

/******** Load jQuery if not present *********/
if (window.jQuery === undefined || window.jQuery.fn.jquery !== '1.8.2') {
    var script_tag = document.createElement('script');
    script_tag.setAttribute("type","text/javascript");
    script_tag.setAttribute("src",
        "http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js");
    if (script_tag.readyState) {
      script_tag.onreadystatechange = function () { // For old versions of IE
          if (this.readyState == 'complete' || this.readyState == 'loaded') {
              scriptLoadHandler();
          }
      };
    } else { // Other browsers
      script_tag.onload = scriptLoadHandler;
    }
    // Try to find the head, otherwise default to the documentElement
   // (document.getElementsByTagName("head")[0] || document.documentElement).appendChild(script_tag);
    
        var mainWidgetScript = null;
        var allScripts = document.getElementsByTagName("script");
         
    	for (var j=0;j<allScripts.length;j++) {
    	    if (allScripts[j].src=='http://statistics.amis-outlook.org/data/amis-charts-widget/amis-statistics-charts.js') {
    	    	  mainWidgetScript = allScripts[j];
    	    	  break;
    	    	      
    	    } else {
    	       
    	    }
    	  }
    	  
    	  if(mainWidgetScript!=null){
    	   (document.getElementsByTagName("head")[0] || document.documentElement).insertBefore(script_tag, mainWidgetScript);
    	  }
	  
	  
	  
} else {
    // The jQuery version on the window is the one we want to use
      
    jQuery = window.jQuery;
    main();
}

/******** Called once jQuery has loaded ******/
function scriptLoadHandler() {
    // Restore $ and window.jQuery to their previous values and store the
    // new jQuery in our local jQuery variable
    jQuery = window.jQuery.noConflict(true);
    // Call our main function
    
    main(); 
}

/******** Our main function ********/
function main() { 
console.log(jQuery)
    jQuery(document).ready(function($) { 
              console.log("MAIN start 1 ");
              
              
              	window.SESSION_COOKIE_NAME = 'null';


              /******* Load Highcharts JS *******/
              
              <!-- AMIS STATS HIGH Charts Scrips added before amis-statistics-charts.js ADDED HERE -->
	    
	        var headID = document.getElementsByTagName("head")[0];         
	    	var newScript = document.createElement('script');
	    	newScript.type = 'text/javascript';
	    	newScript.src = 'http://statistics.amis-outlook.org/data/amis-charts-widget-test/highcharts/js/highcharts.src.js';
	    	
     
    	if (newScript.readyState) {
    		        newScript.onreadystatechange = function () { // For old versions of IE
    		            if (this.readyState == 'complete' || this.readyState == 'loaded') {
    		                getData();
    		            }
    		        };
    	                 } 
    	                 else { // Other browsers
    	                       console.log(" On load DAta")
    	                     
    			       newScript.onload = getData;
    	                 //newScript.onload = test;
    	                 
    	                 }
    	  
	 
    
                                  var newScript1 = document.createElement('script');
    				       	newScript1.type = 'text/javascript';
    				       	newScript1.src = 'http://statistics.amis-outlook.org/data/amis-charts-widget-test/highcharts/js/modules/exporting.src.js';
    				       		
    				       		
	                                 
	    	
	  
                console.log("MAIN start 2 ");
            
              
                
             
		
		<!-- Cross-Domain scripts -->
		
		console.log("MAIN start 3 ");
		var domainScript1 = document.createElement('script');
		domainScript1.type = 'text/javascript';
		domainScript1.src = 'http://statistics.amis-outlook.org/data/amis-charts-widget-test/xdomain/jquery.xdomain.js';
		
		var domainScript = document.createElement('script');
		domainScript.type = 'text/javascript';
		domainScript.src = 'http://statistics.amis-outlook.org/data/amis-charts-widget-test/xdomain/browser.detect.min.js';
					    		
	        console.log("MAIN start 4 ");         
		
	          var scripts = document.getElementsByTagName("script");
	          
	          console.log("MAIN start 5 scripts.length "+scripts.length);
	          
	          var scriptElement = null;
	    	  for (var i=0;i<scripts.length;i++) {
	    	     if (scripts[i].src=='http://statistics.amis-outlook.org/data/amis-charts-widget-test/amis-statistics-charts.js') {
	    	        console.log("MATCH = "+ i,scripts[i].src)
	    	        scriptElement = scripts[i];
	    	        break;
	    	       // document.head.insertBefore(p, document.head.firstChild);
	    	        
	    	       // headID.insertBefore(scripts[i], );
	    	       // headID.insertBefore(newScript1, scripts[i]);
	    	       // headID.insertBefore(domainScript1, scripts[i]);
	    	       // headID.insertBefore(domainScript, domainScript1);
	               } else {
	                console.log("else = "+ i,scripts[i].src)
	               }
	            }
	  if(mainWidgetScript!=null){
	  //headID.insertBefore(newScript1, scriptElement);
 	  //headID.insertBefore(newScript, newScript1); 
 	   headID.insertBefore(newScript, mainWidgetScript);
           headID.insertBefore(newScript1, mainWidgetScript); 
	                        
 	  
	  }
	
	  
	  

	  function getData() {  
	   /******* Create and Load Charts into HTML *******/
	   console.log("getData START ")
               //var productcode =   $("#amis_chart_container").children(":first").attr("id");
               
               var elementHolder = null;
               
                  console.log(" getData") 
            
               
               var productcode = null;
	     	    
	     	    $('#amis_chart_container > table').each(function () { 
	     	      if($(this).attr("id")!=null) {
	     	       console.log("id =  "+$(this).attr("id"));
	     	         productcode = $(this).attr("id");
	     	       
	     	      }
	     	    
	         });
	          
               console.log("productcode =  "+productcode);
	       
	       if(productcode==null || typeof productcode =='undefined'){
		 alert("Please check if the id has been set to a product code");
	       } else {
	       try{
	      	getDataAndCharts(productcode);
	      	}
	      	catch(error){
	      	console.log("error = "+ error.message)
	      	}
	      }	
        }
        
         function getDataAndCharts(cropcode) { 
         
                              var urlNew = 'http://statistics.amis-outlook.org/data/amis-statistics-jsonp.jsp?&productcode='+cropcode + "&callback=?";
	      
	                     
	 		        $.ajax({
	 		            type:       "GET",
	 		            url:        urlNew,
	 		            async: false,
	                             jsonpCallback: 'jsonCallback',
	 			     contentType: "application/json",
	                             dataType: 'jsonp',
	 		             success:    function(jsondata) {	
	 		         	
	    		               console.log("Success = "+jsondata);
	    		               console.log("Success = "+jsondata.end_date);
	    		               
	    		               var time_period = jsondata.start_date + " - " + jsondata.end_date;
	    		               
	    		               var productionData = jsondata.data[0];
	    		               var exportsData = jsondata.data[1];
	    		               var importsData = jsondata.data[2];
	    		               
	    		               console.log(productionData.amis_prod_data + ' | '+productionData.non_amis_prod_data);
	    		               
	    		              
	                              
	    		              var productionChartData= createChartData(productionData.amis_prod_data, productionData.non_amis_prod_data); 
	    		             // createChart(jsondata.product_name + " "+ jsondata.prod_name, time_period, productionChartData, 'chart_production_'+cropcode, jsondata.prod_unit);
	    		              
	    		              var importChartData= createChartData(importsData.amis_imports_data, importsData.non_amis_imports_data);
	    		             // createChart(jsondata.product_name + " "+ jsondata.import_name, time_period, importChartData, 'chart_imports_'+cropcode, jsondata.import_unit);
	    		             
	    		              var exportChartData= createChartData(exportsData.amis_exports_data, exportsData.non_amis_exports_data);
	    		             // createChart(jsondata.product_name + " "+ jsondata.export_name, time_period, exportChartData, 'chart_exports_'+cropcode, jsondata.export_unit);
	     
	        		       
	    		               
	    		            },
	    		            error: function (request, status, error) {
	    			              console.log("getDataAndCharts Error request "+request);
					      console.log("getDataAndCharts Error status "+status);
					      console.log("getDataAndCharts Error error "+ error);

	                                }
	    		        });
	    		        
	    		        
	                    };
	    
	    		
	    		function createChart(title, date, chartData, divId, unit) {
	    		  
	    		  window['var' + statisticsHighchart + divId] = new Highcharts.Chart({
	    						            chart: {
	    						                renderTo: divId,
	    						                plotBackgroundColor: null,
	    						                plotBorderWidth: null,
	    						                plotShadow: false,
	    						                margin: [40, 0, 40, 0]
	    						            },
	    						            colors: ['#376092', '#FFC000'],
	    						            credits: {
	    							    	enabled: true,
	    							    	href: "http://statistics.amis-outlook.org/data/index.html",
	    							    	text: "Source: AMIS Statistics"
	    						            },
	    						            title: {
	    						                text: title ,
	    						                 align: 'center',
	    								 style: {
	    								    font: 'bold 16px "Trebuchet MS", Verdana, sans-serif',
	    								    color: '#000000'
	    							         }
	    						            },
	    						            subtitle: {
	    							    	text: date + ' average',
	    							    	align: 'center',
	    							    	style: {font: 'normal 13px "Trebuchet MS", Verdana, sans-serif',
	    							    	        color: '#000000'
	    							    	}
	    							    },
	    						            tooltip: {
	    						        	     formatter: function() {
	    								           return ''+ this.point.name +': '+ this.point.y + ' '+ unit;
	    								           //return ''+ this.series.name +''+
	               // this.x +': '+ Highcharts.numberFormat(this.y, 0, ',') +' millions';
	    		                                                     }
	    		                                                    
	    						            	    
	    						            },
	    						            plotOptions: {
	    						                pie: {
	    						              
	    						                    allowPointSelect: true,
	    						                    cursor: 'pointer',
	    						                    slicedOffset: 0,
	    						                    dataLabels: {
	    								                            enabled: false,
	    								                           color: '#000000',
	    								                           connectorColor: '#000000',
	    								                            formatter: function() {
	    								                                return  Math.round(this.percentage) +' %';
	    								                            }
	    				                                    },		    
	    						                    showInLegend: true
	    						                }
	    						            },
	    						          
	    						           series: [{
	    						               type: 'pie',
	    						               dataLabels: {
	    							                enabled: true,
	    							                formatter: function() {
	    							                      return '<b>'+Math.round(this.percentage) + ' %'+'</b>';
	    							                },
	    
	    							                 distance: -30,
	    							                 color:'black'
	    		                                                },
	    		                                                
	    						               data:[]
	    						               
	    						         
	    						            }],
	    						            
	    						             exporting: {
	    							            buttons: {
	    							                exportButton: {
	    							                    y: 40
	    							                },
	    							                printButton: {
	    								            y: 40
	    							                }
	    							            }
	                                                                  }
	    						            
	    						            
	    						            
	    				             })
	    				        
	    	               console.log("Chart Create = "+ chartData); 
	    		        window['var' + statisticsHighchart + divId].series[0].setData(chartData);
	    		     
	    		}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		function createChartData(amis_value, non_amis_value) {
	    		        var chartDataArray=[];
	    					       		var dataValue = [];
	    					       				 dataValue.push("AMIS");
	    					       				 dataValue.push(parseFloat(amis_value));
	    					       			       	 chartDataArray.push(dataValue); 
	    					       			       	 dataValue = [];
	    					       				 dataValue.push("Non-AMIS");
	    					       				 dataValue.push(parseFloat(non_amis_value));	
	    				 chartDataArray.push(dataValue); 
	    				 
	    		  return chartDataArray;		 
	    		
		}
        
    });
    
    
}

})(); // We call our anonymous function immediately