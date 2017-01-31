
function MarketMonitorChartView(model, chartelements) {
    this._model = model;
    this._chartelements = chartelements;
    this._divId = this._chartelements.chartDivId;
    this._chartObject;
    this._sessionId = this._chartelements.chartSessionId;
    this._chartConfig;
    this._chartSources;
    this._chartYAxisOptions;
    this._chartXAxisOptions;
    this._chartLegendVisibility;
    this._chartSeriesOptions;
    this._chartHideDataExport = this._chartelements.chartHideDataExport;
    this._chartTimeFrame = "";
    this._getAllChartRangeSelectorButtons = true;
    this._chartRangeSelectorButtons = [];
    this._chartHideSeries = [];
    this._isCompareTimeChart = false;
    this._chartXAxisStartMonth;

    this._chartEnableTooltip = true;
     

    if (typeof this._chartelements.chartXAxisStartMonth != 'undefined'){
        this._chartXAxisStartMonth = this._chartelements.chartXAxisStartMonth;
    }

    if (typeof this._chartelements.chartType != 'undefined'){
        if(this._chartelements.chartType == 'COMPARE_YEARS')
            this._isCompareTimeChart = true;
    }

    if (typeof this._chartelements.chartTimeFrame != 'undefined'){
         this._chartTimeFrame = this._chartelements.chartTimeFrame;
         this._getAllChartRangeSelectorButtons = false;
    }
    
    if(typeof this._chartelements.chartRangeSelectorButtons != 'undefined'){
       this._chartRangeSelectorButtons = this._chartelements.chartRangeSelectorButtons; 
    }
    
    if(typeof this._chartelements.chartHideSeries != 'undefined'){
         this._chartHideSeries = [].concat(this._chartelements.chartHideSeries); 
    }
    
    if(typeof this._chartelements.chartEnableTooltip != 'undefined'){
         this._chartEnableTooltip = this._chartelements.chartEnableTooltip; 
    } 
    
   
    
}

MarketMonitorChartView.prototype = {


    getModel : function () {
        return this._model;
    },
    
    getHiddenChartSeries : function () {
        return this._chartHideSeries;
    },
    
    showHighStockChart : function () {
        this.loadHighStockWidgetChart();
    },
    
    showHighChart : function () {
         this.loadHighChartWidget();
    },
    
    loadHighStockWidgetChart : function () {

         W.loadWidget('org.fao.wHighstocks.1.0', document.getElementById(this._divId), this._chartConfig, this._sessionId, false, this.wChartSuccess, this.wChartError);
    },
         
   loadHighChartWidget : function () {
         W.loadWidget('org.fao.wChart2', document.getElementById(this._divId), this._chartConfig, this._sessionId, false, this.wChartSuccess, this.wChartError);
    },
    
    getChartRangeSelectorButtons : function () {
         return [].concat(this._chartRangeSelectorButtons);
            
    },
    
     getRangeType : function (type) {  
       if(type=='m'){
         return 'month';
       } else if('y'){
         return 'year';
       } 
                
    },


   createHighStocksMultipleAxesChartOptions : function(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel){
       var thisMarketMonitorChartView = this;

       var indicator_codes = this.getModel().getDomainIndicators();
       var market_codes = this.getModel().getIndicatorMarkets();
       var domain_table_code = this.getModel().getDomainTableCode();
       var domain_name = this.getModel().getDomainName();
       var domain_source = this.getModel().getDomainSource();
       var chart_div_id = this._divId;

       var sources = this.getChartSources(sourceUrlArray, sourceArray);
       var yAxisOptions = this.getChartYAxisOptions(unitArray);
       var updatedSeriesOptions = this.setChartSeriesYAxisPosition(seriesOptions, unitArray);
       var showLegend = this.getChartLegendVisibility(seriesOptions);
       var showAllRangeSelectorButtons = this.getAllChartRangeSelectorButtons();

       var chartMarginBottom = 50;

       if (showLegend) {
           chartMarginBottom = 90;
           //navigatorTop = 240;
       }

       var chartMarginTop = 55;

       //if(baseperiod.length > 0) {
       // chartMarginTop = 65;
       // }

       var navigatorTop = 280;

       var chart_title = "";

       var sub_title = "";

       // calculate navigatorTop using other chart size related parameters
       if ($('#' + chart_div_id).height() != null) {
           var chart_height = $('#' + chart_div_id).height();
           var chart_area = chart_height - (chartMarginTop + chartMarginBottom);
           navigatorTop = chart_area + 20;

           //  console.log('chart_height = '+chart_height + ' | chartMarginTop = '+chartMarginTop + ' | chartMarginBottom = '+ chartMarginBottom + ' chart_area = '+chart_area + ' navigatorTop '+navigatorTop);
       }


       var enableExportButtons = true;
       var enableNavigator = true;
       var inputBoxesEnabled = true;

       var rangeSelectorInputDateFormat = '%b %e, %Y';
       var rangeInputEditDateFormat = '%b %e, %Y';

       if (periodicity == "Monthly") {
           rangeSelectorInputDateFormat = '%b %Y';
           rangeInputEditDateFormat = '%b %Y';
           inputBoxesEnabled = false;
       }


       if (this.getModel().getDomainTableCode() == 'EST_DATABASE') {
           chart_title += 'International ' + seriesOptions[0].indicator + ' Prices ' + " (" + periodicity + ")";
       } else {
           chart_title += ' ' + this.getModel().getDomainName() + " (" + periodicity + ")";
       }


       var rangeOptions = [];
       var buttonOptions = [];

       if (!showAllRangeSelectorButtons) {
           sub_title = 'From ' + startdatelabel + ' to ' + enddatelabel + ' <br/>' + baseperiod;
           //if(baseperiod.length > 0) {
           // chartMarginTop = 65;
           // }

           enableExportButtons = false;
           enableNavigator = false;
           // navigatorTop = 280;

           var charttimeframe = this.getChartTimeFrame();

           if (this._chartRangeSelectorButtons.length > 0) {
               $.each(this._chartRangeSelectorButtons, function (index, value) {

                   var ct = '';
                   var ty = '';

                   if (value != charttimeframe) {
                       ct = value.substring(0, value.length - 1);
                       ty = thisMarketMonitorChartView.getRangeType(value.substring(value.length - 1));
                   } else {
                       ty = 'all';
                   }

                   buttonOptions[index] = {
                       type: ty,
                       count: ct,
                       text: value
                   };

                   if (value == charttimeframe) {
                       // buttonOptions[index].remove(1);
                   }


               });
           } else {
               buttonOptions[0] = {
                   type: 'all',
                   text: this.getChartTimeFrame()
               };
           }


           rangeOptions = {
               enabled: true,
               inputEnabled: false,
               selected: 0,
               buttons: buttonOptions

           };

       } else {

           sub_title = baseperiod;

           var baseCount = 1;
           var baseText = '1m';

           var add10yr = false;

           if (periodicity == "Monthly") {
               add10yr = true;

               baseCount = 6;
               baseText = '6m';
           }

           rangeOptions = {
               inputDateFormat: rangeSelectorInputDateFormat,
               inputEditDateFormat: rangeInputEditDateFormat,
               enabled: true,
               inputEnabled: inputBoxesEnabled,
               selected: 1,

               buttons: [
                   {
                       type: 'month',
                       count: baseCount,
                       text: baseText
                   },
                  {
                        type: 'year',
                        count: 1,
                        text: '1y'
                    },
                    {
                        type: 'ytd',
                        text: 'YTD'
                    },
                    //{
                    //    type: 'year',
                    //    count: 2,
                    //    text: '2y'
                   // },
                    {
                        type: 'year',
                        count: 5,
                        text: '5y'
                    },
                    {
                        type: 'all',
                        text: 'All'
                    }
                ]
            };

        }

         if (add10yr) {

            var tenYr = {type: 'year',
                count: 10,
                text: '10y' };
            rangeOptions.buttons.splice(rangeOptions.buttons.length - 1, 0, tenYr);

        }

        var exportButtonTitle = "<u>Chart Data to Excel</u>";

        var chartHideDataExport = this._chartHideDataExport;
        if (chartHideDataExport) {
            exportButtonTitle = " ";
        }

        var markerHoverState = true;
        if (this._chartEnableTooltip == false) {
            markerHoverState = false;
        }

        this._chartConfig = {
            chart: {
                zoomType: 'x',
                plotBorderWidth: 1,
                borderWidth: 2,
                marginBottom: chartMarginBottom,
                marginTop: chartMarginTop,
                spacingBottom: 20,
                alignTicks: false
                //height: 370
            },

            navigator: {
                enabled: enableNavigator,
                top: navigatorTop
            },

            scrollbar: {
                enabled: enableNavigator
            },
            credits: {
                enabled: true,
                href: 'javascript:;',
                position: {
                    align: 'left',
                    x: 10,
                    y: -5
                },
                style: {
                    cursor: 'default',
                    color: '#413839',
                    fontSize: '10px'
                },
                text: sources
            },


            title: {
                text: chart_title,
                align: 'left',
                style: {
                    font: 'bold 14px "Trebuchet MS", Verdana, sans-serif',
                    color: '#000000'
                }
            },

            subtitle: {
                text: sub_title,
                align: 'left',
                style: {
                    font: 'bold 11px "Trebuchet MS", Verdana, sans-serif'

                }
            },

            legend: {
                enabled: showLegend,
                verticalAlign: 'bottom'
                //align: "right",
                //layout: "vertical",
                //y: 100,
                //verticalAlign: "top"
            },


            navigation: {
                buttonOptions: {

                    theme: {
                        'stroke-width': 1,
                        stroke: 'silver',
                        r: 0,
                        states: {
                            hover: {
                                fill: '#F4F3F3'
                            },
                            select: {
                                stroke: '#039',
                                fill: '#F4F3F3'
                            }
                        }
                    }

                }
            },

            tooltip: {
                shared: false,
                enabled: this._chartEnableTooltip,
                formatter: function () {

                    //for(var key in this.series) {
                    //   console.log('key: ' + key + '\n' + 'value: ' + this[key]);
                    // }

                    var s = thisMarketMonitorChartView.formatUnSharedToolTipLabel(thisMarketMonitorChartView, this.series, this.x, this.y, periodicity, false);
                    return s;
                }
            },

            plotOptions: {
                line: {
                    marker: {
                        states: {
                            hover: {
                                enabled: markerHoverState
                            }
                        }
                    }
                }

            },

            exporting: {
                enabled: enableExportButtons,
                buttons: {
                    contextButton: {
                        enabled: false

                    },
                    exportButton: {
                        text: 'Download',
                        _titleKey: 'downloadButtonTitle',
                        menuItems: [
                            {
                                text: exportButtonTitle,
                                onclick: function () {
                                    //var minDate = $('input.highcharts-range-selector:eq(0)').val();
                                    //var maxDate = $('input.highcharts-range-selector:eq(1)').val();
                                    var minDate = $('input.highcharts-range-selector:eq(0)', $('#' + chart_div_id)).val();
                                    var maxDate = $('input.highcharts-range-selector:eq(1)', $('#' + chart_div_id)).val();

                                    if (minDate == null && maxDate == null) {
                                        minDate = startdate;
                                        maxDate = enddate;
                                    }


                                    var title = this.options.title.text + ' ' + this.options.subtitle.text
                                    var exportUrl = "";
                                    if (domain_source == 'AMIS-STATISTICS' || domain_source == 'FAO-EST') {
                                        if (market_codes != null)
                                            exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-est-data-export.jsp?indicatorcode=" + indicator_codes + "&marketcode=" + market_codes + "&mindate=" + minDate + "&maxdate=" + maxDate + "&title=" + title;
                                        else
                                            exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-data-export.jsp?indicatorcode=" + indicator_codes + "&domaintable=" + domain_table_code + "&mindate=" + minDate + "&maxdate=" + maxDate + "&title=" + title;
                                    }
                                    else if (domain_source == 'FRED') {
                                        var newTitle = 'AMIS Market Monitor: '+ this.options.title.text;
                                        exportUrl="http://fenixapps.fao.org/fenix-fred/rest/series/export/excel?series_id="+indicator_codes+"&start_date=" + minDate + "&end_date=" + maxDate+"&title="+newTitle+"&api_key=dab0b2bdc1c6816c598ba62118eda19d"
                                    }

                                    if (!chartHideDataExport)
                                        window.open(exportUrl);
                                }
                            },
                            // {
                            // text: '&nbsp;',
                            //	separator: true
                            // },

                            {
                                text: 'Chart as PNG image',
                                onclick: function () {
                                    this.exportChart();
                                }

                            },
                            {
                                text: 'Chart as JPEG image',
                                onclick: function () {
                                    this.exportChart({
                                        type: 'image/jpeg'
                                    });
                                }
                            },
                            {
                                text: 'Chart to PDF document',
                                onclick: function () {
                                    this.exportChart({
                                        type: 'application/pdf'
                                    });
                                }
                            },
                            {
                                text: 'Chart as SVG vector image',
                                onclick: function () {
                                    this.exportChart({
                                        type: 'image/svg+xml'
                                    });
                                }

                            }
                        ]
                    },
                    printButton: {
                        text: 'Print',
                        _titleKey: 'printButtonTitle',
                        onclick: function () {
                            this.print();
                        }
                    }
                }
            },

            rangeSelector: rangeOptions,


            yAxis: yAxisOptions,


            series: updatedSeriesOptions

        }
    },

    createCompareHighStocksMultipleAxesChartOptions : function(seriesOptions, sourceUrlArray, baseperiod, periodicity, unitArray, sourceArray, startdate, enddate, startdatelabel, enddatelabel){
        var thisMarketMonitorChartView = this;

        var indicator_codes = this.getModel().getDomainIndicators();
        var market_codes = this.getModel().getIndicatorMarkets();
        var domain_table_code = this.getModel().getDomainTableCode();
        var domain_name = this.getModel().getDomainName();
        var domain_source = this.getModel().getDomainSource();
        var chart_div_id = this._divId;

        var sources = this.getChartSources(sourceUrlArray, sourceArray);
        var yAxisOptions = this.getChartYAxisOptions(unitArray);
        var updatedSeriesOptions = this.setChartSeriesYAxisPosition(seriesOptions, unitArray);
        //var updatedSeriesOptions = seriesOptions;

        var showLegend = this.getChartLegendVisibility(seriesOptions);
        var showAllRangeSelectorButtons = this.getAllChartRangeSelectorButtons();
        var timeSpan =this._chartTimeFrame;

        var chartMarginBottom = 50;


        if (showLegend) {
            chartMarginBottom = 90;
            //navigatorTop = 240;
        }

        var chartMarginTop = 55;

        //if(baseperiod.length > 0) {
        // chartMarginTop = 65;
        // }

        var navigatorTop = 280;

        var chart_title = "";

        var sub_title = "";

        // calculate navigatorTop using other chart size related parameters
        if ($('#' + chart_div_id).height() != null) {
            var chart_height = $('#' + chart_div_id).height();
            var chart_area = chart_height - (chartMarginTop + chartMarginBottom);
            navigatorTop = chart_area + 20;

            //  console.log('chart_height = '+chart_height + ' | chartMarginTop = '+chartMarginTop + ' | chartMarginBottom = '+ chartMarginBottom + ' chart_area = '+chart_area + ' navigatorTop '+navigatorTop);
        }


        var enableExportButtons = true;
        var enableNavigator = true;
        var inputBoxesEnabled = true;

        var rangeSelectorInputDateFormat = '%b %e, %Y';
        var rangeInputEditDateFormat = '%b %e, %Y';

        if (periodicity == "Monthly") {
            rangeSelectorInputDateFormat = '%b %Y';
            rangeInputEditDateFormat = '%b %Y';
            inputBoxesEnabled = false;
        }


        if (this.getModel().getDomainTableCode() == 'EST_DATABASE') {
            chart_title += ' International ' + seriesOptions[0].indicator + ' Prices ';
        } else {
            if( this.getModel().getDomainCode() == '4' &&  (seriesOptions[0].indicator).indexOf("FAO") == -1)  {
                chart_title +=  'FAO '+  seriesOptions[0].indicator + ' '+ this.getModel().getDomainName();
            }
            else if( this.getModel().getDomainCode() == '5' &&  (seriesOptions[0].indicator).indexOf("Grain") == -1) {
                if((seriesOptions[0].indicator).indexOf("Grain") == -1)
                    chart_title +=   'GOI: '+ seriesOptions[0].indicator + ' '+ this.getModel().getDomainName();
                else
                    chart_title +=   'IGC '+ seriesOptions[0].indicator + ' '+ this.getModel().getDomainName();
            }
            else if( this.getModel().getDomainCode() == '2') {
                chart_title +=   'Price of '+ seriesOptions[0].indicator;
            }
            else {
                chart_title +=   seriesOptions[0].indicator + ' '+ this.getModel().getDomainName();
            }

        }


        var rangeOptions = [];
        var buttonOptions = [];

        if(!this._isCompareTimeChart){
        if (!showAllRangeSelectorButtons) {
            sub_title = 'From ' + startdatelabel + ' to ' + enddatelabel + ' <br/>' + baseperiod;
            //if(baseperiod.length > 0) {
            // chartMarginTop = 65;
            // }

            enableExportButtons = false;
            enableNavigator = false;
            // navigatorTop = 280;

            var charttimeframe = this.getChartTimeFrame();

            if (this._chartRangeSelectorButtons.length > 0) {
                $.each(this._chartRangeSelectorButtons, function (index, value) {

                    var ct = '';
                    var ty = '';

                    if (value != charttimeframe) {
                        ct = value.substring(0, value.length - 1);
                        ty = thisMarketMonitorChartView.getRangeType(value.substring(value.length - 1));
                    } else {
                        ty = 'all';
                    }

                    buttonOptions[index] = {
                        type: ty,
                        count: ct,
                        text: value
                    };

                    if (value == charttimeframe) {
                        // buttonOptions[index].remove(1);
                    }


                });
            } else {
                buttonOptions[0] = {
                    type: 'all',
                    text: this.getChartTimeFrame()
                };
            }


            rangeOptions = {
                enabled: false,
                inputEnabled: false,
                selected: 0,
                buttons: buttonOptions

            };

        } else {
            sub_title = baseperiod;

            var baseCount = 1;
            var baseText = '1m';

            var add10yr = false;

            if (periodicity == "Monthly") {
                add10yr = true;

                baseCount = 6;
                baseText = '6m';
            }

            rangeOptions = {
                inputDateFormat: rangeSelectorInputDateFormat,
                inputEditDateFormat: rangeInputEditDateFormat,
                enabled: true,
                inputEnabled: inputBoxesEnabled,
                selected: 1,

                buttons: [
                    {
                        type: 'month',
                        count: baseCount,
                        text: baseText
                    },
                    {
                        type: 'year',
                       count: 1,
                       text: '1y'
                   },
                   {
                       type: 'year',
                       count: 2,
                       text: '2y'
                   },
                   {
                       type: 'year',
                       count: 5,
                       text: '5y'
                   },

                   {
                       type: 'all',
                       text: 'All'
                   }
               ]
           };

       }

       if (add10yr) {

           var tenYr = {type: 'year',
               count: 10,
               text: '10y' };
           rangeOptions.buttons.splice(rangeOptions.buttons.length - 1, 0, tenYr);

       }
        } else {
            sub_title = baseperiod;
            enableNavigator = false;
        }

       var exportButtonTitle = "<u>Chart Data to Excel</u>";

       var chartHideDataExport = this._chartHideDataExport;
       if (chartHideDataExport) {
           exportButtonTitle = " ";
       }

       var markerHoverState = true;
       if (this._chartEnableTooltip == false) {
           markerHoverState = false;
       }

       this._chartConfig = {
           chart: {
               zoomType: 'x',
               plotBorderWidth: 1,
               borderWidth: 2,
               marginBottom: chartMarginBottom,
               marginTop: chartMarginTop,
               spacingBottom: 20,
               alignTicks: false
               //height: 370
           },

           navigator: {
               enabled: enableNavigator,
               top: navigatorTop
           },

           scrollbar: {
               enabled: enableNavigator
           },
           credits: {
               enabled: true,
               href: 'javascript:;',
               position: {
                   align: 'left',
                   x: 10,
                   y: -5
               },
               style: {
                   cursor: 'default',
                   color: '#413839',
                   fontSize: '10px'
               },
               text: sources
           },


           title: {
               text: chart_title,
               align: 'left',
               style: {
                   font: 'bold 14px "Trebuchet MS", Verdana, sans-serif',
                   color: '#000000'
               }
           },

           subtitle: {
               text: sub_title,
               align: 'left',
               style: {
                   font: 'bold 11px "Trebuchet MS", Verdana, sans-serif'

               }
           },

           legend: {
               enabled: showLegend,
               verticalAlign: 'bottom'
               //align: "right",
               //layout: "vertical",
               //y: 100,
               //verticalAlign: "top"
           },


           navigation: {
               buttonOptions: {

                   theme: {
                       'stroke-width': 1,
                       stroke: 'silver',
                       r: 0,
                       states: {
                           hover: {
                               fill: '#F4F3F3'
                           },
                           select: {
                               stroke: '#039',
                               fill: '#F4F3F3'
                           }
                       }
                   }

               }
           },

           tooltip: {
               shared: true,
               enabled: this._chartEnableTooltip,
               formatter: function () {

                   //for(var key in this.series) {
                   //   console.log('key: ' + key + '\n' + 'value: ' + this[key]);
                   // }

                     var s = thisMarketMonitorChartView.formatSharedToolTipLabel(thisMarketMonitorChartView, this.points, this.x, periodicity, true);
                   // var s = thisMarketMonitorChartView.formatUnSharedToolTipLabel(thisMarketMonitorChartView, this.series, this.x, this.y, periodicity, true);
                   return s;
               }
           },

           plotOptions: {
               line: {
                   marker: {
                       states: {
                           hover: {
                               enabled: markerHoverState
                           }
                       }
                   }
               }

           },

           exporting: {
               enabled: enableExportButtons,
               buttons: {
                   contextButton: {
                       enabled: false

                   },
                   exportButton: {
                       text: 'Download',
                       _titleKey: 'downloadButtonTitle',
                       menuItems: [
                           {
                               text: exportButtonTitle,
                               onclick: function () {
                                   //var minDate = $('input.highcharts-range-selector:eq(0)').val();
                                   //var maxDate = $('input.highcharts-range-selector:eq(1)').val();
                                   var minDate = $('input.highcharts-range-selector:eq(0)', $('#' + chart_div_id)).val();
                                   var maxDate = $('input.highcharts-range-selector:eq(1)', $('#' + chart_div_id)).val();

                                   if (minDate == null && maxDate == null) {
                                       minDate = startdate;
                                       maxDate = enddate;
                                   }


                                   var title = this.options.title.text + ' ' + this.options.subtitle.text
                                   var exportUrl = "";
                                   if (domain_source == 'AMIS-STATISTICS' || domain_source == 'FAO-EST') {
                                       if (market_codes != null)
                                           exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-est-compare-data-export.jsp?indicatorcode=" + indicator_codes + "&marketcode=" + market_codes + "&timespan=" + timeSpan + "&title=" + title;
                                       else
                                           exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-compare-data-export.jsp?indicatorcode=" + indicator_codes + "&domaintable=" + domain_table_code + "&timespan=" + timeSpan + "&title=" + title;
                                   }
                                   else if (domain_source == 'FRED') {
                                       var newTitle = 'AMIS Market Monitor: '+ this.options.title.text;
                                       exportUrl="http://fenixapps.fao.org/fenix-fred/rest/series/export/excel?series_id="+indicator_codes+"&start_date=" + minDate + "&end_date=" + maxDate+"&title="+newTitle+"&api_key=dab0b2bdc1c6816c598ba62118eda19d"
                                   }

                                   if (!chartHideDataExport)
                                       window.open(exportUrl);
                               }
                           },
                           // {
                           // text: '&nbsp;',
                           //	separator: true
                           // },

                           {
                               text: 'Chart as PNG image',
                               onclick: function () {
                                   this.exportChart();
                               }

                           },
                           {
                               text: 'Chart as JPEG image',
                               onclick: function () {
                                   this.exportChart({
                                       type: 'image/jpeg'
                                   });
                               }
                           },
                           {
                               text: 'Chart to PDF document',
                               onclick: function () {
                                   this.exportChart({
                                       type: 'application/pdf'
                                   });
                               }
                           },
                           {
                               text: 'Chart as SVG vector image',
                               onclick: function () {
                                   this.exportChart({
                                       type: 'image/svg+xml'
                                   });
                               }

                           }
                       ]
                   },
                   printButton: {
                       text: 'Print',
                       _titleKey: 'printButtonTitle',
                       onclick: function () {
                           this.print();
                       }
                   }
               }
           },

           rangeSelector: rangeOptions,

            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: { // don't display the dummy year
                    month: '%b',
                    year: '%b'
                },
                startOnTick: true
                //endOnTick: true

           },

           yAxis: yAxisOptions,


           series: updatedSeriesOptions

       }
   },
    
    formatUnSharedToolTipLabel : function(thisMarketMonitorChartView, series, x, y, periodicity, isCompareChart){
                            var point_name_exists = false;
                            var s = "";
                            
                            
        			
        		        var unit = "";
        			
        			//if(typeof series.name != "undefined")  {
			        //   s += '<b>'+series.name + '</b> ';
        			//}
        			
        			    
			          
                            
        			   
        			 $.each(series.points, function(i, point) {
        			 
        			// console.log('point =  '  + point);
        			 
        			    $.each(point, function(i, pointItem) {
        			    // console.log('pointItem =  '  + pointItem);
        			    
        			    if(pointItem!=null) {
				      if(typeof pointItem.unit != "undefined")  {
					   unit = pointItem.unit;
					  
				      } else {
					 unit = series.options.tooltip.valueSuffix;
					 
				      } 
                                  
                                   }
        			     });
				           			 
				 });
				 
				  var dp = thisMarketMonitorChartView.formatDecimal(unit);
				  
				  s += '<span style="color:'+series.color+'; font-weight:bold;">'+series.name+'</span><br/>';
                             
        if(isCompareChart){
 		                   if(periodicity=="Weekly") {
		     				 s+= "Week ending ";
                s+= Highcharts.dateFormat('%b %e', x)+"<br/>";
            } else if(periodicity=="Daily"){
                s+= Highcharts.dateFormat('%b %e', x)+"<br/>";
            }
            else if(periodicity=="Monthly"){
                s+= Highcharts.dateFormat('%B', x)+"<br/>";
            }
        }
        else {
        if(periodicity=="Weekly") {
            s+= "Week ending ";
		     				 s+= Highcharts.dateFormat('%A, %b %e, %Y', x)+"<br/>";
		     				} else if(periodicity=="Daily"){
		     				 s+= Highcharts.dateFormat('%A, %b %e, %Y', x)+"<br/>";
		     				}
		     				else if(periodicity=="Monthly"){
		     				  s+= Highcharts.dateFormat('%B %Y', x)+"<br/>";	
		     				}
		     
		     				else if(periodicity=="Annual") {
		     				 s+= Highcharts.dateFormat('%Y', x)+"<br/>";
		         			}
        }

		         			s+=Highcharts.numberFormat(y, dp) + ' ' +unit;
                            
     		
     		 
     		 return s;
        			
    },
   
    formatSharedToolTipLabel : function(thisMarketMonitorChartView, points, x, periodicity, isCompareChart){
                        var point_name_exists = false;
                        var s = "";
                        
        if(isCompareChart){
                        if(periodicity=="Weekly") {
                s+= "Week ending ";
                s+= Highcharts.dateFormat('%b %e', x)+"<br/>";
				} else if(periodicity=="Daily"){
                s+= Highcharts.dateFormat('%b %e', x)+"<br/>";
				}
				else if(periodicity=="Monthly"){
                s+= Highcharts.dateFormat('%B', x)+"<br/>";
				}
        }
        else {
            if(periodicity=="Weekly") {
                s+= "Week ending ";
                s+= Highcharts.dateFormat('%A, %b %e, %Y', x)+"<br/>";
            } else if(periodicity=="Daily"){
                s+= Highcharts.dateFormat('%A, %b %e, %Y', x)+"<br/>";
            }
            else if(periodicity=="Monthly"){
                s+= Highcharts.dateFormat('%B %Y', x)+"<br/>";
            }

				else if(periodicity=="Annual") {
                s+= Highcharts.dateFormat('%Y', x)+"<br/>";
    			}
        }


    			 $.each(points, function(i, point) {
            if(typeof point.name != "undefined")  {
    				return point_name_exists = true;
    			      }
    			 });
    
    			 if(point_name_exists){
    			   s = "";
    			 }
    			 
    			 var unit = "";
    			 
    			 
    			 
    			 $.each(points, function(i, point) {
            if(typeof point.name != "undefined")  {
                s += '<b>'+point.name + '</b> ';
    			      }
    			      
            if(typeof point.unit != "undefined")  {
                unit = point.unit;
			      } else {
				 unit = this.series.options.tooltip.valueSuffix;
			      }

			      var dp = thisMarketMonitorChartView.formatDecimal(unit);

			      s += '<span style="color:'+point.series.color+'">'+point.series.name+'</span>: '+Highcharts.numberFormat(point.y, dp) + ' ' +unit+'<br/>';
			    });
 		 
 		
 		 
 		 return s;
    			
    },
    
     formatToolTipLabelOriginal : function(thisMarketMonitorChartView, points, x, periodicity){
                            var point_name_exists = false;
                            var s = "";
                            
                            if(periodicity=="Weekly") {
    				 s+= "<b>Week ending ";
    				 s+= Highcharts.dateFormat('%A, %b %e, %Y', x)+"</b><br/>";
    				} else if(periodicity=="Daily"){
    				 s+= "<b>"+Highcharts.dateFormat('%A, %b %e, %Y', x)+"</b><br/>";
    				}
    				else if(periodicity=="Monthly"){
    				  s+= "<b>"+Highcharts.dateFormat('%B %Y', x)+"</b><br/>";	
    				}
    
    				else if(periodicity=="Annual") {
    				 s+= "<b>"+Highcharts.dateFormat('%Y', x)+"</b><br/>";
        			}
                            
        			 $.each(points, function(i, point) {
        			      if(typeof this.point.name != "undefined")  {
        				return point_name_exists = true;
        			      }
        			 });
        
        			 if(point_name_exists){
        			   s = "";
        			 }
        			 
        			 var unit = "";
        			 
        			 
        			 $.each(points, function(i, point) {
        			      if(typeof this.point.name != "undefined")  {
    			          s += '<b>'+this.point.name + '</b> ';
        			      }
        			      
    			      if(typeof this.point.unit != "undefined")  {
    				   unit = this.point.unit;
    			      } else {
    				 unit = this.series.options.tooltip.valueSuffix;
    			      }
    
    			      var dp = thisMarketMonitorChartView.formatDecimal(unit);
    
    			      s += '<span style="color:'+point.series.color+'">'+point.series.name+'</span>: '+Highcharts.numberFormat(point.y, dp) + ' ' +unit+'<br/>';
    			    });
     		 
     		
     		 
     		 return s;
        			
    },
    
     formatStartDateEndDateLabel : function(startdate, endate, periodicity){
                            var s = "";
                            
                            if(periodicity=="Weekly") {
    				 s+= "Week ending ";
    				 s+= Highcharts.dateFormat('%A, %b %e, %Y', startdate)+" - ";
    				 s+= Highcharts.dateFormat('%A, %b %e, %Y', enddate);
    				} else if(periodicity=="Daily"){
    				 s+= Highcharts.dateFormat('%A, %b %e, %Y', startdate)+" - ";
    				 s+= Highcharts.dateFormat('%A, %b %e, %Y', enddate);
    				}
    				else if(periodicity=="Monthly"){
    				  s+= "<b>"+Highcharts.dateFormat('%B %Y', x)+"</b><br/>";	
    				}
    
    				else if(periodicity=="Annual") {
    				  s+= Highcharts.dateFormat('%Y', startdate)+" - ";
    				  s+= Highcharts.dateFormat('%Y', enddate);
        			}
     		 
     		 return s;
        			
    },
    
    formatDecimal : function(unit){
         if(unit=='%') {
          return 0;   
         } else if(unit==' '){ // Index values
           return 1;
         }
         else {
           return 2;
         }  
   },
    
   					   
     createHighChartsMultipleAxesChartOptions : function(seriesOptions, sourceUrlArray, unitArray, sourceArray, xAxisCategories, divId, title, jsondataseries){
    	    
    	                     var sources = this.getChartSources(sourceUrlArray, sourceArray);
    	                     var yAxisOptions = this.getChartYAxisOptions(unitArray);     
    	                     var updatedSeriesOptions = this.setChartSeriesYAxisPosition(seriesOptions, unitArray);    
    	                     var showLegend = this.getChartLegendVisibility(seriesOptions); 
    	        	 	
    	          	        	 	 
    	    		    this._chartConfig = {
    	    
    	    					    chart: {
    	    						zoomType: 'xy',
    	    						renderTo: divId,
    	    						plotBorderWidth: 1,
    							borderWidth: 2,
    							marginTop: 50,
    							//marginBottom: 95,
    							spacingBottom: 20,
    							alignTicks: false
    							
    	    					    },
    	    					    
    	    					    
    	    					     credits: {
    							enabled: true,
    							href: "javascript:;",
    							position: {
								align: 'left',
								x: 10,
								y: -5
				                       },
    							//position: {
    							//	align: 'left',
    							//	x: 50,
    							//	verticalAlign: 'bottom',
    							//	y: -65
    							//},
    						
							style: {
								cursor: 'default',
								color: '#413839',
								fontSize: '10px'
							},
							text: sources

    			                            },
    	    					   
    	    					    title: {
    	    						text: title,
    	    						align: 'left',
    	    						style: {
    	    						    font: 'bold 14.5px "Trebuchet MS", Verdana, sans-serif',
    	    						    color: '#000000'
    	    						   
    	    						}
    	    					    },
    	    					    legend: {
    						     enabled: showLegend
    						               						    				    
    		                                    },
    	    					    navigation: {
							   buttonOptions: {
							       theme: {
								   'stroke-width': 1,
								   stroke: 'silver',
								   r: 0,
								   states: {
								       hover: {
									   fill: '#F4F3F3'
								       },
								       select: {
									   stroke: '#039',
									   fill: '#F4F3F3'
								       }
								   }
							       }

							   }
                                                        },
    	    					      exporting: {
						       buttons: {
							  contextButton: {
									   enabled: false  
								       },
								       exportButton: {
									   text: 'Download',
									   _titleKey: 'downloadButtonTitle',
									   menuItems: [
										{
										 text: '<u>Chart Data to Excel</u>',
										 onclick: function () {
										 var title = this.options.title.text;

										var exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-json-data-export.jsp?series="+JSON.stringify(jsondataseries)+"&categories="+xAxisCategories+"&title="+title;

										 window.open(exportUrl);							   
										}


									       },
									       //{
										//text: '',							       	
										//separator: true
									       //},

									       {
										 text: 'Chart as PNG image',
										 onclick: function () {
										   this.exportChart();
										 }

									       }, {
										 text: 'Chart as JPEG image',
										 onclick: function () {
										   this.exportChart({
										     type: 'image/jpeg'
										   });
										 }
									       }, {
										 text: 'Chart to PDF document',
										 onclick: function () {
										   this.exportChart({
										     type: 'application/pdf'
										   });
										 }
									       }, {
										 text: 'Chart as SVG vector image',
										 onclick: function () {
										   this.exportChart({
										     type: 'image/svg+xml'
										   });
										 }

									       }
									    ]
								       },
								       printButton: {
									   text: 'Print',
									   _titleKey: 'printButtonTitle',
									   onclick: function () {
									       this.print();
									   }
								      }
							     }
						  },
    	    					    tooltip: {
    							formatter: function() {
    								    var s = 
    								    '<span style="color:'+
    								       (this.series.color == '#CCCCCC' ? '#5E5E5E' : ' '+ this.series.color);	
    						                     s+= ';">';
    								     s+= (this.series.options.shortname != '' ? this.series.options.shortname : this.series.name);
    								     s+= '</span><br/>';
    								     //s+=';">'+this.series.name +'</span><br/>';
    								     s += this.x +': '+ this.y +
    								    ($.trim(this.series.options.tooltip.valueSuffix) == 'Percent' ? ' %' : ' '+ this.series.options.tooltip.valueSuffix);
    								
    								return s;
    															
    							}
    						    },
    						    plotOptions: {
    							column: {
    							    stacking: 'normal'
    							}
    	    					    },
    	    
    	    					    xAxis: [{
    	    						categories: xAxisCategories,
    	    						labels: {
							                    style: {
							                        fontSize: '10px'
							                    }
                                                         }
    	    					   }],
    	    					   
    	    					   
    	    					    yAxis: yAxisOptions,
    	    					    
    						   
    			                            series: updatedSeriesOptions
    	    					    
    	    					   
    	    					 }           
    },
    
       createHighChartsMultipleAxesDateTimeChartOptions : function(seriesOptions, sourceUrlArray, unitArray, sourceArray, xAxisCategories, divId, title, jsondataseries){
        	    
        	                     var sources = this.getChartSources(sourceUrlArray, sourceArray);
        	                     var yAxisOptions = this.getChartYAxisOptions(unitArray);   
        	                     
        	                     var xAxisOptions = this.getChartXAxisOptions(xAxisCategories);   
        	                     
        	                     var updatedSeriesOptions = this.setChartSeriesYAxisPosition(seriesOptions, unitArray);    
        	                     var showLegend = this.getChartLegendVisibility(seriesOptions); 
        	        	 	
        	        	 	
        	        	 	
        	        	  	
        	        	    
        	        	  	        	 	 
        	    		    this._chartConfig = {
        	    
        	    					    chart: {
        	    						zoomType: 'xy',
        	    						renderTo: divId,
        	    						plotBorderWidth: 1,
        							borderWidth: 2,
        							marginTop: 60,
        							spacingBottom: 5,
        							alignTicks: false
        							
        	    					    },
        	    					     credits: {
        							enabled: true,
        							href: "javascript:;",
        							position: {
        								align: 'left',
        								x: 50,
        								verticalAlign: 'bottom',
        								y: -75
        							},
        							style: {
        								cursor: 'pointer',
        								color: '#413839',
        								fontSize: '10px'
        
        							},
        							text: sources
        			                            },
        	    					   
        	    					    title: {
        	    						text: title ,
        	    						align: 'left',
        	    						style: {
        	    						    font: 'bold 15px "Trebuchet MS", Verdana, sans-serif',
        	    						    color: '#000000'
        	    						   
        	    						}
        	    					    },
        	    					    legend: {
        						     enabled: showLegend
        						               						    				    
        		                                    },
        	    					    navigation: {
    							   buttonOptions: {
    							       theme: {
    								   'stroke-width': 1,
    								   stroke: 'silver',
    								   r: 0,
    								   states: {
    								       hover: {
    									   fill: '#F4F3F3'
    								       },
    								       select: {
    									   stroke: '#039',
    									   fill: '#F4F3F3'
    								       }
    								   }
    							       }
    
    							   }
                                                            },
        	    					      exporting: {
    						       buttons: {
    							  contextButton: {
    									   enabled: false  
    								       },
    								       exportButton: {
    									   text: 'Download',
    									   _titleKey: 'downloadButtonTitle',
    									   menuItems: [
    										{
    										 text: '<u>Chart Data to Excel</u>',
    										 onclick: function () {
    										 var title = this.options.title.text;
    
    										var exportUrl = "http://statistics.amis-outlook.org/data/amis-market-monitor/jsp/amis-mm-json-data-export.jsp?series="+JSON.stringify(jsondataseries)+"&categories="+xAxisCategories+"&title="+title;
    
    										 window.open(exportUrl);							   
    										}
    
    
    									       },
    									       //{
    										//text: '',							       	
    										//separator: true
    									       //},
    
    									       {
    										 text: 'Chart as PNG image',
    										 onclick: function () {
    										   this.exportChart();
    										 }
    
    									       }, {
    										 text: 'Chart as JPEG image',
    										 onclick: function () {
    										   this.exportChart({
    										     type: 'image/jpeg'
    										   });
    										 }
    									       }, {
    										 text: 'Chart to PDF document',
    										 onclick: function () {
    										   this.exportChart({
    										     type: 'application/pdf'
    										   });
    										 }
    									       }, {
    										 text: 'Chart as SVG vector image',
    										 onclick: function () {
    										   this.exportChart({
    										     type: 'image/svg+xml'
    										   });
    										 }
    
    									       }
    									    ]
    								       },
    								       printButton: {
    									   text: 'Print',
    									   _titleKey: 'printButtonTitle',
    									   onclick: function () {
    									       this.print();
    									   }
    								      }
    							     }
    						  },
        	    					    tooltip: {
        							formatter: function() {
        								return ''+
        								this.x +': '+ 
        								this.series.name
        								+ ' '+ this.y +
        								($.trim(this.series.options.tooltip.valueSuffix) == 'Percent' ? ' %' : ' '+ this.series.options.tooltip.valueSuffix); 
        															
        							}
        						    },
        						    plotOptions: {
        							column: {
        							    stacking: 'normal'
        							}
        	    					    },
        	    
        	    					  xAxis: {
							                  type: 'datetime',
							                  dateTimeLabelFormats: { // don't display the dummy year
							                      month: '%e. %b',
							                      year: '%b'
							                  }
                                                           },
        	    					    yAxis: yAxisOptions,
        						   
        			                            series: updatedSeriesOptions
        	    					    
        	    					   
        	    					 }  
        	    					 
        	    					 
        	    					 

    },
    
     
    
    getChartConfig : function(){
          return this._chartConfig;
    },
    
    
    setSessionId : function(sessionId){
          this._sessionId = sessionId;
    },
    
    getChartTimeFrame : function(){
         return this._chartTimeFrame;
    },
    
    getIsCompareTimeChart : function(){
        return this._isCompareTimeChart;
    },

    getChartXAxisStartMonth : function(){
        return this._chartXAxisStartMonth;
    },


    getAllChartRangeSelectorButtons : function(){
         return this._getAllChartRangeSelectorButtons;
    },
    
    
    getChartSources : function(sourceUrlArray,sourceArray) {
	     var sources = "Source";

	     if(sourceArray.length > 1) {
	       sources +="(s)";
	     }

	     sources +=": ";

	     for(var k = 0; k < sourceArray.length; k++){
		//var source_url = sourceUrlArray[k];

		// if(source_url=='')
		 //	source_url = "javascript:;";

		//sources += '<a href="'+source_url+'" target="blank">'+sourceArray[k]+'</a>';
		sources += sourceArray[k];

		if(k < sourceArray.length-1)
			sources += '; '

	     }


	 this._chartSources = sources;

	 return this._chartSources;
    },
    
    
      getChartYAxisOptions : function(unitArray) {
      
      
    	    var yAxisOptions = []; 
    	    
    	    
        
	            	 	 var setOppositeAxis = false;
	            	 	 var setGridLineWidth = 1;        
	        		                            
	            	 	 for(var i = 0; i < unitArray.length; i++){
	            	 	 
	            	 	  if(i!=0){
	        		 	setOppositeAxis = true;
	        		 	setGridLineWidth = 0; // gridLineWidth = 0 +  chart.alignTicks=false allows the opposite Y axis scale intervals to be independant
	        		  } else {
	        		     setGridLineWidth = 1;
	        		  }
	        		   
	        		 
	            	 	  yAxisOptions[i] = {title: {text: unitArray[i], style: {color: '#000000'}},
	        		 	labels: {formatter: function() {
	        		 	  // add thousand separator to Y-Axis label
	        		 	  if(this.value >= 1000)
	        		 	  return Highcharts.numberFormat(this.value,0, '.', ','); 
	        		 	  else
	        		 	  return this.value
	        		 	}, style: {color: '#000000'}},
	        		 	opposite: setOppositeAxis,
	        		 	gridLineWidth: setGridLineWidth	
	        		   }
	        					                    
	            	 	 
    	   }
    
                  
             
    	 this._chartYAxisOptions = yAxisOptions;
    
    	 return this._chartYAxisOptions;
        },
        
         getChartXAxisOptions : function(categories) {
	    	    var xAxisOptions = []; 
	    	    
	    	   
		     xAxisOptions[0] = {
			//categories: categories
			type: 'datetime'
		   }
		        					                    
	  
	 
	    	 this._chartXAxisOptions = xAxisOptions;
	    
	    	 return this._chartXAxisOptions;
        },
        
         getChartLegendVisibility : function(seriesOptions) {
	    	   var showLegend = true;
		           	 	 
        if(seriesOptions.length == 1 && !this._isCompareTimeChart){
		           	 	     showLegend = false;
		           	 	 }
    	 	 
	    
	    	 this._chartLegendVisibility = showLegend;
	    
	    	 return this._chartLegendVisibility;
        },
        
         setChartSeriesYAxisPosition : function(seriesOptions, unitArray) {
		    	 for(var j = 0; j < seriesOptions.length; j++){
		    	    	if(typeof seriesOptions[j] != 'undefined') {
					//var suffix = seriesOptions[j].tooltip.valueSuffix;
					var suffix = seriesOptions[j].units;
					var suffix_trim = $.trim(suffix);

					if($.inArray(suffix_trim, unitArray) != -1 ) {
						seriesOptions[j].yAxis = $.inArray(suffix_trim, unitArray);
					}
					
				}
				
			 }	
		    
		     this._chartSeriesOptions = seriesOptions;
		     
		     return  this._chartSeriesOptions;
		    	 
        },
        
    
    createChartData : function(values, categories) {
       
      		var chartDataArray= new Array(categories.length);
      
      		for (var i=0;i<values.length;i++)
      		{
      		  var dataValue = new Array(2);
      		  dataValue[0] = categories[i];
      		  dataValue[1] = parseFloat(values[i]);
      
      
      		  chartDataArray[i] = dataValue;						       	
      		}
      
      
      		return chartDataArray;		 
      
	},
    
    
    
  
       
    wChartSuccess: function(w) {
    	 Highcharts.setOptions({
		     lang: {
			 printButtonTitle: "Print Chart",
			 downloadButtonTitle: "Download Chart"
		     }
                 });	
               
        
      
        
       if(w.chart!=null){

           
         var chartDiv = w.chart.renderTo;



       setTimeout(function(){

      	  		$('input.highcharts-range-selector', $('#'+chartDiv.id)).datepicker();

		 	             var date_format = 'yy-mm-dd';
		 	             var charttitle = w.chart.title.text;


				     var charttitle = w.chart.title.text;

				    // console.log(w.chart.title.text);


		 	              $.datepicker.setDefaults({
				     	 	          //  showButtonPanel: enableShowButtonPanel,

			     	 	                     beforeShow: function(input,inst) {
			     	 	                     var thisChartId = this.parentNode.parentNode.id;
			     	 	                     var thisChart = $('#'+thisChartId).highcharts();
			     	 	                     var thisChartMinDate = $.datepicker.formatDate('yy-mm-dd', new Date(thisChart.xAxis[0].dataMin));
			     	 	                     var thisChartMaxDate = $.datepicker.formatDate('yy-mm-dd', new Date(thisChart.xAxis[0].dataMax)) ;


			     	 	                     var enableChangeMonth = true;
							     var enableChangeYear = true;
				     			     var enableShowButtonPanel = false;
				                             var date_format = 'yy-mm-dd';


			     	 	                     if(thisChart.title.text.indexOf("Monthly") > 1) {
							     	  $('.ui-datepicker-calendar').css({'display': 'none'});
							     		enableShowButtonPanel = true;
							     	  } else {
							     	   $('.ui-datepicker-calendar').css({'display': 'block'});
		 	                                         }

		 	              		 	      //  $('input.highcharts-range-selector:eq(0)').val();
		 	              		 	      //  $('input.highcharts-range-selector:eq(1)').val();

			     	 	                       $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "dateFormat", date_format);
			     	 	                       $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "changeMonth", enableChangeMonth);
			     	 	                       $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "changeYear", enableChangeYear);
			     	 	                      // $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "showButtonPanel", enableShowButtonPanel);

			     	 	                     $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "maxDate", thisChartMaxDate);
			     	 	                     $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker("option", "minDate", thisChartMinDate);




                                                              // scroll to the correct chart
				                              $(window).scrollTop($(this.parentNode).offset().top);

				                               // set position of calendar
				                                                  var calendar = inst.dpDiv;

				                                                   setTimeout(function() {
										              calendar.position({
										                  my: 'right top',
										                  at: 'right bottom',
										                  collision: 'none',
										                  of: input
										              });
                                                                                   }, 1);

							                     },


							     //onClose: function(dateText, inst) {
							     //       console.log('dateText = '+ dateText);

							        //    var thisChartId = this.parentNode.parentNode.id;
							        //    var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
								//    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();

								   //  console.log('month = '+ month);
								  //   console.log('year = '+ year);

								  //  $('input.highcharts-range-selector', $('#'+thisChartId)).datepicker('setDate', new Date(year, month, 1));

							   //  },

				     	 	             onSelect: function(dateText) {
				     	 	                $(".ui-datepicker a").removeAttr("href");

				     	 	              //From date


				     	 	             // if(this == $(this.parentNode).find('input').eq(0)){
				     	 	            //  if(this==$('input.highcharts-range-selector:eq(0)', $('#'+chartDiv.id)).datepicker()){

				     	 	              //}
				     	 	              //To date
				     	 	             // else if(this== $('input.highcharts-range-selector:eq(1)', $('#'+chartDiv.id)).datepicker()){

				     	 	             // else if(this== $(this.parentNode).find('input').eq(1)){
				     	 	             // }

				     	 	            //  console.log("HELLO input " +  $('input.highcharts-range-selector:eq(0)', $('#'+chartDiv.id)).datepicker());

				     	 	            //  console.log("HELLO selected " + dateText);

				     				 //$(this).change();

				     	 	                this.onchange();
				     	 	                this.onblur();
	 	            }});



			    		// $('input.highcharts-range-selector:eq(0)', $('#'+chartDiv.id)).datepicker({
			    	        	//numberOfMonths: 2,

			    	        	//onSelect: function(selected) {


			    	          	//$('input.highcharts-range-selector:eq(1)', $('#'+chartDiv.id)).datepicker("option","minDate", selected)
			    	        	//}
			    	    	//});
			    	    	//$('input.highcharts-range-selector:eq(1)', $('#'+chartDiv.id)).datepicker({
			    	        	//numberOfMonths: 2,
			    	        	//onSelect: function(selected) {
			    	        	//   $('input.highcharts-range-selector:eq(0)', $('#'+chartDiv.id)).datepicker("option","maxDate", selected)
			    	        	//}
	    	//});

    },0)

    }    
   
    },
    
    wChartError: function(e) {
    	alert("Error function called, error: " + e);
    }
            
};