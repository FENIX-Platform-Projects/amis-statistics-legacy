
function BasicHighStocksController(domain_type_model, view) {
    this._domain_type_model = domain_type_model;
    this._view = view;
    
    var _this = this;
}


BasicHighStocksController.prototype = {
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
      
        $('#'+chart_div_id).append('<div style="text-align:center"><img src="http://statistics.amis-outlook.org/data/amis-market-monitor/images/loading-chart.gif"/></div>');
    	    
      
       var index = 0;
       var seriesOptions = [];
       var current_view = this._view;
       var server_script_name = this._domain_type_model.getDomainServerScriptName();
       var domaintablecode = this._domain_type_model.getDomainTableCode();
       var indicatormarkets = this._domain_type_model.getIndicatorMarkets();    
       var timeSpan = this._view.getChartTimeFrame();
       var indicatorsArrayLength = this._domain_type_model.getDomainIndicators().length;

       var domain_source = this._domain_type_model.getDomainSource();

      // var indicatorsArrayLength =  indicatorsArray.length;	 
       

	   var seriescolors = this._view.getSeriesColours();
	   // var seriescolors = ['#2A5AA6', '#F8B624', '#07A02E', '#636363', '#DD3218']; //AMIS Outlook principle colours - blue, yellow, green, dark grey, red

	   var serieslinestyle = this._view.getSeriesLineStyle();


        var unitArray = [];
	  var sourceArray = [];
	  var sourceUrlArray = [];

      var isCompareTimeChart = this._view.getIsCompareTimeChart();


     indicatorsIterator(index, domain_source, seriesOptions, current_view, "OUTER_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isCompareTimeChart);

  }


 }

function indicatorsIterator(index, domain_source, seriesOptions, current_view, calledFrom, indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart) {

    if (index < indicatorsArrayLength ) {

        var indicatorcode = indicatorsArray[index];
        //var urlNew = "https://localhost:8080/fenix-web/data/amis-market-monitor/jsp/"+server_script_name+".jsp?indicatorcode="+indicatorcode+"&domaintablecode="+domaintablecode+"&timespan="+timeSpan;
        var seriesvisibility = true;

        if(current_view.getHiddenChartSeries().length > 0) {
            if($.inArray(indicatorcode, current_view.getHiddenChartSeries()) > -1){
                seriesvisibility = false;
            }
        }


        var ajaxUrl = "";
        var callbackIdentifier = current_view.getSessionId();

        if(domain_source == 'AMIS-STATISTICS' || domain_source == 'FAO-EST'){
           ajaxUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/"+server_script_name+".jsp?indicatorcode="+indicatorcode+"&domaintablecode="+domaintablecode+"&timespan="+timeSpan;

            if(isTimeComparisonChart && typeof current_view.getChartXAxisStartMonth() != 'undefined')
             ajaxUrl += "&startMonth="+current_view.getChartXAxisStartMonth();

           if(indicatormarkets!=null)
               ajaxUrl += "&marketcode="+indicatormarkets[index]+"&cb="+callbackIdentifier+"&callback=?";//urlNew += "&marketcode="+indicatormarkets[index]+"&callback=?";
           else
               ajaxUrl += "&cb="+callbackIdentifier+"&callback=?";

            if(isTimeComparisonChart)
                makeAMISCompareAjaxCall(index, domain_source, ajaxUrl, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart);
            else
            makeAMISAjaxCall(index, domain_source, ajaxUrl, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart);


        } else if(domain_source=='FRED'){
            ajaxUrl = "http://fenixapps.fao.org/fenix-fred/rest/series/"+indicatorcode+"/data";

            makeFREDAjaxCall(index, domain_source, ajaxUrl, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart);

        }

     }

}

function makeAMISAjaxCall(index, domain_source, url, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart){

   var seriesStyle = 'solid'; //defaultStyle

    if(serieslinestyle !== undefined){
		   for(var k = 0; k < serieslinestyle.length; k++){
		        var itm = serieslinestyle[k];
					if(itm.hasOwnProperty(indicatorcode)) {
						 seriesStyle = itm[indicatorcode];
				}
			}
	}

    $.ajax({
        type:       "GET",
        url:        url,
        async: false,
        jsonpCallback: 'jsonCallback'+callbackIdentifier+'_'+indicatorcode,
        dataType: 'jsonp',
        success:    function(jsondata) {
            var source = jsondata.source;
            var sourceurl = jsondata.sourceurl;
            var periodicity = jsondata.periodicity;
            var baseperiod = jsondata.baseperiod;
            var units = jsondata.units;
            var startdate = jsondata.startdate;
            var enddate = jsondata.enddate;
            var startdatelabel = jsondata.startdatelabel;
            var enddatelabel = jsondata.enddatelabel;
            // var visible = jsondata.visible;
            // var seriesvisibility = true;

            var seriesName = "";

            if(indicatormarkets!=null && indicatormarkets.length > 1){
                seriesName = jsondata.baseperiod;
                baseperiod = "";

            } else{
                seriesName = jsondata.indicator;
            }


            if(sourceurl==null){
                sourceurl = "";

            }

            if(baseperiod==null){
                baseperiod = "";
            }

            if($.inArray(units, unitArray) == -1 ){
                unitArray.push(units);
            }

            if($.inArray(source, sourceArray) == -1 ){
                sourceArray.push(source);
            }

            if($.inArray(sourceurl, sourceUrlArray) == -1 ){
                sourceUrlArray.push(sourceurl);
            }

           seriesOptions[index] = {
                name: seriesName,
                indicator: jsondata.indicator,
                data: jsondata.data,
                color: seriescolors[index],
				dashStyle: seriesStyle,
                units: units,
                yAxis: 0,
                tooltip: {
                    valueSuffix: ' '+ units
                },
                visible: seriesvisibility

            };




            if (index+1 == indicatorsArray.length) {

                current_view.createHighStocksMultipleAxesChartOptions(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel);
                current_view.showHighStockChart();


            }


            index++;

            indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONSUCCESS_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)
            //next(index, seriesOptions, current_view, "ONSUCCESS_NEXT");

        },
        error: function (request, status, error) {
            index++;
            // console.log("request = "+ request.response);
            //  console.log("status = "+ status);
            //  console.log("error = "+ error);
            indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONFAILURE_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)

            // next(index, seriesOptions, current_view, "ONFAILURE_NEXT");
        }
    });
}



function makeAMISCompareAjaxCall(index, domain_source, url, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart){
	var seriesStyle = 'solid'; //defaultStyle

    if(serieslinestyle !== undefined){
		   for(var k = 0; k < serieslinestyle.length; k++){
		        var itm = serieslinestyle[k];
					if(itm.hasOwnProperty(indicatorcode)) {
						 seriesStyle = itm[indicatorcode];
				}
			}
	}

    $.ajax({
        type:       "GET",
        url:        url,
        async: false,
        jsonpCallback: 'jsonCallbackCompare'+callbackIdentifier+'_'+indicatorcode,
        dataType: 'jsonp',
        success:    function(jdata) {
           // console.log(' jdata.indicators length '+ jdata.indicators.length);
            $.each(jdata.indicators, function(indicatorIndex, jsondata){
              //  console.log(' jsondata.source '+ jsondata.source);
            var source = jsondata.source;
            var sourceurl = jsondata.sourceurl;
            var periodicity = jsondata.periodicity;
            var baseperiod = jsondata.baseperiod;
            var units = jsondata.units;
            var startdate = jsondata.startdate;
            var enddate = jsondata.enddate;
            var startdatelabel = jsondata.startdatelabel;
            var enddatelabel = jsondata.enddatelabel;
                var visible = false;
            // var visible = jsondata.visible;
            // var seriesvisibility = true;

            var seriesName = "";

            if(indicatormarkets!=null && indicatormarkets.length > 1){
                seriesName = jsondata.baseperiod;
                baseperiod = "";

            } else{
                    seriesName = jsondata.series;
            }


            if(sourceurl==null){
                sourceurl = "";

            }

            if(baseperiod==null){
                baseperiod = "";
            }

            if($.inArray(units, unitArray) == -1 ){
                unitArray.push(units);
            }

            if($.inArray(source, sourceArray) == -1 ){
                sourceArray.push(source);
            }

            if($.inArray(sourceurl, sourceUrlArray) == -1 ){
                sourceUrlArray.push(sourceurl);
            }

                if(indicatorIndex == jdata.indicators.length-2 || indicatorIndex == jdata.indicators.length-1){  // last two years
                    visible = true;
                }

                seriesOptions[indicatorIndex] = {
                name: seriesName,
                    series: jsondata.series,
                indicator: jsondata.indicator,
                data: jsondata.data,
                    color: seriescolors[indicatorIndex],
					dashStyle: seriesStyle,
                units: units,
                yAxis: 0,
                tooltip: {
                    valueSuffix: ' '+ units
                },

                    visible: visible

            };

               // console.log("seriesOptions["+indicatorIndex+"].data"+ seriesOptions[indicatorIndex].data);

               // if (index+1 == indicatorsArray.length) {
                 if(indicatorIndex+1 == jdata.indicators.length){
                    // console.log("IN ... create chart");
                     current_view.createHighStocksMultipleAxesChartOptions(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel);
                     //current_view.createCompareHighStocksMultipleAxesChartOptions(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel);
                current_view.showHighStockChart();
                 }

                //}


            index++;

                indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONSUCCESS_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)
            //next(index, seriesOptions, current_view, "ONSUCCESS_NEXT");

            });

        },
        error: function (request, status, error) {
            index++;
            // console.log("request = "+ request.response);
            //  console.log("status = "+ status);
            //  console.log("error = "+ error);
            indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONFAILURE_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)

            // next(index, seriesOptions, current_view, "ONFAILURE_NEXT");
        }
    });
}

function makeFREDAjaxCall(index, domain_source, url, callbackIdentifier, indicatorcode, current_view, timeSpan, domaintablecode, server_script_name, indicatormarkets, indicatorsArray, unitArray, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, seriesvisibility, seriesOptions, indicatorsArrayLength, isTimeComparisonChart){
  var apiKey = 'dab0b2bdc1c6816c598ba62118eda19d';
  var startDate = '2000-01-01';
   // $.support.cors = true;

   var seriesStyle = 'solid'; //defaultStyle

    if(serieslinestyle !== undefined){
		   for(var k = 0; k < serieslinestyle.length; k++){
		        var itm = serieslinestyle[k];
					if(itm.hasOwnProperty(indicatorcode)) {
						 seriesStyle = itm[indicatorcode];
				}
			}
	}

    $.ajax({
        type:      "GET",
        url:        url,
        data: {api_key: apiKey, start_date: startDate, convert_date_to_epoch: true},
        dataType:'json',
        success:    function(jsondata) {
            //var source = jsondata.series.source;
            var source = jsondata.series.disclaimer;
            var sourceurl = jsondata.series.sourceurl;
            var periodicity = jsondata.series.periodicity;
            var baseperiod = jsondata.series.baseperiod;
            var units = jsondata.series.units;
            var startdate = jsondata.series.startdate;
            var enddate = jsondata.series.enddate;
            var startdatelabel = jsondata.series.startdatelabel;
            var enddatelabel = jsondata.series.enddatelabel;

            var seriesName =  jsondata.series.indicator;

            if(sourceurl==null){
                sourceurl = "";

            }

            if(baseperiod==null){
                baseperiod = "";
            }

            if($.inArray(units, unitArray) == -1 ){
                unitArray.push(units);
            }

            if($.inArray(source, sourceArray) == -1 ){
                sourceArray.push(source);
            }

            if($.inArray(sourceurl, sourceUrlArray) == -1 ){
                sourceUrlArray.push(sourceurl);
            }

            seriesOptions[index] = {
                name: seriesName,
                indicator: jsondata.series.indicator,
                data: jsondata.data,
                color: seriescolors[index],
				dashStyle: seriesStyle,
                units: units,
                yAxis: 0,
                tooltip: {
                    valueSuffix: ' '+ units
                },
                visible: seriesvisibility

            };


            if (index+1 == indicatorsArray.length) {
                current_view.createHighStocksMultipleAxesChartOptions(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel);
                current_view.showHighStockChart();


            }


            index++;

            indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONSUCCESS_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)
            //next(index, seriesOptions, current_view, "ONSUCCESS_NEXT");

        },
        error: function (request, status, error) {
            index++;
            // console.log("request = "+ request.response);
            // console.log("status = "+ status);
            // console.log("error = "+ error);
            indicatorsIterator(index, domain_source, seriesOptions, current_view, "ONFAILURE_NEXT", indicatorsArrayLength, indicatorsArray, indicatormarkets, timeSpan, unitArray, domaintablecode, server_script_name, sourceArray, sourceUrlArray, seriescolors, serieslinestyle, isTimeComparisonChart)

            // next(index, seriesOptions, current_view, "ONFAILURE_NEXT");
        }
    });
}
;