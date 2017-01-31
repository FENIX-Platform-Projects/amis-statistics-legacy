(function () {

    $(document).ready(function() {

        var chart_script_tag;
        var ensureJS;
        var ensureCSS;
        var ensureCSS1;
        var dependenciesJS = ["http://statistics.amis-outlook.org/data/amis-market-monitor/js/model/domainmodel.js", "http://statistics.amis-outlook.org/data/amis-market-monitor/js/view/chartview.js", "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js", "http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/xdr.js"];
        var ensureJSLoaded = false;

        include_CSS("http://statistics.amis-outlook.org/data/amis-market-monitor/plugin/jquery-ui-datepicker/css/custom-theme/jquery-ui-1.10.2.custom.min.css");

        include_ensureJS("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/head.min.js");

        include_ensureJS("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/jQuery.XDomainRequest.js");

        if(dependenciesJS.length > 3)
            dependenciesJS.pop();

        if($("#amis_mm_p_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");
            onEnsureScriptLoad(dependenciesJS, "PRICES");
        }
        else  if($("#amis_mm_oi_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");
            onEnsureScriptLoad(dependenciesJS, "FERTILIZER");
        }
        else  if ($("#amis_mm_stu_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-stocks-and-ratios-controller.js");
            onEnsureScriptLoad(dependenciesJS, "STOCKS");
        }
        else  if ($("#amis_mm_ofe_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");
            onEnsureScriptLoad(dependenciesJS, "OIL_FREIGHTS_ETHANOL");
        }
        else  if ($("#amis_mm_fred_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");
            onEnsureScriptLoad(dependenciesJS, "FRED");
        }
        else  if ($("#amis_mm_ethanol_charts").length > 0){
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");
            onEnsureScriptLoad(dependenciesJS, "ETHANOL");
        }

    });


    function include_ensureJS(file) {
        var html_doc = document.getElementsByTagName('head')[0];
        ensureJS = document.createElement('script');
        ensureJS.setAttribute('type', 'text/javascript');
        ensureJS.setAttribute('src', file);
        html_doc.appendChild(ensureJS);

        return false;
    }


    function include_CSS(file, file2) {
        var html_doc = document.getElementsByTagName('head')[0];
        ensureCSS = document.createElement('link');
        ensureCSS.setAttribute('rel', 'stylesheet');
        ensureCSS.setAttribute('href', file);
        html_doc.appendChild(ensureCSS);


        ensureCSS1 = document.createElement('link');
        ensureCSS1.setAttribute('rel', 'stylesheet');
        ensureCSS1.setAttribute('href', file2);

        html_doc.appendChild(ensureCSS1);

        return false;
    }


    function onEnsureScriptLoad(urlControllers, charttype) {
        ensureJS.onreadystatechange = function () {
            if (ensureJS.readyState == 'complete') {
                loadEnsureScriptHandler(urlControllers, charttype);
            }
        }

        ensureJS.onload = function () {
            loadEnsureScriptHandler(urlControllers, charttype);
        }
    }


    function loadEnsureScriptHandler(controllerUrls, charttype){
        if (typeof head !== 'undefined') {

            $.getScript("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/json3.min.js", function(data, textStatus, jqxhr)
            {
                head.js(controllerUrls[0],controllerUrls[1], controllerUrls[2], controllerUrls[3], function() {

                    if(charttype == "FERTILIZER"){

                        loadFertilizerCharts();
                    }
                    else if(charttype == "STOCKS"){

                        loadStocksCharts();
                    }
                    else if(charttype == "PRICES"){

                        loadPricesCharts();
                    }
                    else if(charttype =="OIL_FREIGHTS_ETHANOL"){

                        loadOilFreightsEthanolCharts();
                    }
                    else if(charttype =="FRED"){

                        loadFREDCharts();
                    }
                    else if(charttype =="ETHANOL"){

                        loadEthanolCharts();
                    }
                });
            });


        } else {


        }

    }


    function loadFertilizerCharts(){
        var model;
        var view;

        if ($("#amis_mm_oi_chart_FP").length > 0){
            model =  new MarketMonitorDomainModel('Fertilizer Prices-FOB', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC','GCFPDAGE', 'GCFPURBS', 'GCFPPRM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_oi_chart_FP',
                'chartSessionId' : 'FP',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }

        //Compare by Year charts:
        if ($("#amis_mm_oi_chart_FP_GCFPPGVC").length > 0){
            model =  new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_oi_chart_FP_GCFPPGVC',
                'chartSessionId' : 'FP_GCFPPGVC',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false

            });

           new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPDAGE").length > 0){
            model =  new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPDAGE'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_oi_chart_FP_GCFPDAGE',
                'chartSessionId' : 'FP_GCFPDAGE',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'showChartDatatoExcelOption': false,
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPURBS").length > 0){
            model =  new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPURBS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_oi_chart_FP_GCFPURBS',
                'chartSessionId' : 'FP_GCFPURBS',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPPRM").length > 0){
            model =  new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPRM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_oi_chart_FP_GCFPPRM',
                'chartSessionId' : 'FP_GCFPPRM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }
    }

    function loadPricesCharts(){
        var model;
        var view;

        if ($("#amis_mm_p_chart_HVW").length > 0){
            model =   new MarketMonitorDomainModel('Wheat Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVW'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_HVW',
                'chartSessionId' : 'HVW',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }
        if ($("#amis_mm_p_chart_HVM").length > 0){

            model =   new MarketMonitorDomainModel('Maize Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_HVM',
                'chartSessionId' : 'HVM',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }
        if ($("#amis_mm_p_chart_HVR").length > 0){

            model =   new MarketMonitorDomainModel('Rough Rice Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVR'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_HVR',
                'chartSessionId' : 'HVR',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }
        if ($("#amis_mm_p_chart_HVS").length > 0){

            model =   new MarketMonitorDomainModel('Soybeans Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVS'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_HVS',
                'chartSessionId' : 'HVS',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();

        }
        if ($("#amis_mm_p_chart_FPI").length > 0){
            model =   new MarketMonitorDomainModel('FAO Food Price Index and Sub-Indices', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI','FPIC','FPIOF','FPIS','FPIM','FPID'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI',
                'chartSessionId' : 'FPI',
                'chartHideSeries':['FPIC','FPIOF','FPIS','FPIM','FPID'],
                'chartShowDateInputBoxes' : false

            });

            new BasicHighStocksController(model, view).getData();

        }

        //Compare By Year Charts
        if ($("#amis_mm_p_chart_FPI_FPI").length > 0){
            model =   new MarketMonitorDomainModel('(by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPI',
                'chartSessionId' : 'FPI_FPI',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIC").length > 0){
            model =   new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPIC',
                'chartSessionId' : 'FPI_FPIC',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIOF").length > 0){
            model =   new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIOF'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPIOF',
                'chartSessionId' : 'FPI_FPIOF',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIS").length > 0){
            model =   new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPIS',
                'chartSessionId' : 'FPI_FPIS',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

             new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIM").length > 0){
            model =   new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPIM',
                'chartSessionId' : 'FPI_FPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });

             new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPID").length > 0){
           model =   new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPID'], null, 'amis-mm-compare-indicators');
           view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_FPI_FPID',
                'chartSessionId' : 'FPI_FPID',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false
            });
            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_IGC").length > 0){

            model =   new MarketMonitorDomainModel('IGC Grains and Oilseeds Index and sub-Indices', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI','WPI', 'MPI', 'RPI', 'SPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC',
                'chartSessionId' : 'IGC'
            });
            new BasicHighStocksController(model, view).getData();

        }

        //IGC Charts by Year
        if ($("#amis_mm_p_chart_IGC_GOI").length > 0){

            model =   new MarketMonitorDomainModel('(by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC_GOI',
                'chartSessionId' : 'IGC_IGC',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false

            });
            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_IGC_WPI").length > 0){

            model =   new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['WPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC_WPI',
                'chartSessionId' : 'IGC_WPI',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false

            });
            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_IGC_MPI").length > 0){

            model =   new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['MPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC_MPI',
                'chartSessionId' : 'IGC_MPI',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false

            });
           new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_IGC_RPI").length > 0){

            model =   new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['RPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC_RPI',
                'chartSessionId' : 'IGC_RPI',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes' : false

            });
           new BasicHighStocksController(model, view).getData();

        }


        if ($("#amis_mm_p_chart_IGC_SPI").length > 0){

            model =   new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['SPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_IGC_SPI',
                'chartSessionId' : 'IGC_SPI',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS'
            });
            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_2301").length > 0){

            model =  new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2301', '2301'], ['16', '17'], 'amis-mm-est-prices');

            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_2301',
                'chartSessionId' : '2301'
            });

            new BasicHighStocksController(model, view).getData();
        }
        if ($("#amis_mm_p_chart_2302").length > 0){

            model =  new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2302', '2302'], ['3', '44'], 'amis-mm-est-prices');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_2302',
                'chartSessionId' : '2302'

            });

            new BasicHighStocksController(model, view).getData();

        }
        if ($("#amis_mm_p_chart_2311").length > 0){

            var model =  new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2311', '2311'], ['14', '25'], 'amis-mm-est-prices');
            var view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_2311',
                'chartSessionId' : '2311'
            });

            var chart4 = new BasicHighStocksController(model, view).getData();
        }
        if ($("#amis_mm_p_chart_2303").length > 0){
            model =  new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2303'], ['4'], 'amis-mm-est-prices');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_2303',
                'chartSessionId' : '2303'
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_WFSMA").length > 0){
            model =   new MarketMonitorDomainModel('Wheat Futures Prices and Simple Moving Average', '10', 'AMIS_WHEAT_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEW','CMEWSMA30','CMEWSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_WFSMA',
                'chartSessionId' : 'WFSMA',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_SFSMA").length > 0){
            model =   new MarketMonitorDomainModel('Soybean Futures Prices and Simple Moving Average', '10', 'AMIS_SOYBEAN_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMES','CMESSMA30','CMESSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_SFSMA',
                'chartSessionId' : 'SFSMA',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_RFSMA").length > 0){
            model =   new MarketMonitorDomainModel('Rice Futures Prices and Simple Moving Average', '10', 'AMIS_RICE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMER','CMERSMA30','CMERSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_RFSMA',
                'chartSessionId' : 'RFSMA',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_MFSMA").length > 0){
            model =   new MarketMonitorDomainModel('Maize Futures Prices and Simple Moving Average', '10', 'AMIS_MAIZE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEM','CMEMSMA30','CMEMSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_MFSMA',
                'chartSessionId' : 'MFSMA',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }


        //Compare By Year Chart
        if ($("#amis_mm_p_chart_WFSMA_COMPARE").length > 0){
            model =   new MarketMonitorDomainModel('(by year)', '10', 'AMIS_WHEAT_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEW'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_p_chart_WFSMA_COMPARE',
                'chartSessionId' : 'WFSMA_COMPARE',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'showChartDatatoExcelOption': false
            });
            new BasicHighStocksController(model, view).getData();
        }

    }


    function loadStocksCharts(){

        var model =   new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['1'], null, 'amis-mm-stocks-and-ratios');
        var view = new MarketMonitorChartView(model, {
            'chartDivId' : 'amis_mm_stu_chart_1',
            'chartSessionId' : '1'

        });

        new StocksAndRatiosController(model, view).getData();

        var model2 =  new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['5'], null, 'amis-mm-stocks-and-ratios');
        var view2 = new MarketMonitorChartView(model2, {
            'chartDivId' : 'amis_mm_stu_chart_5',
            'chartSessionId' : '5'

        });

       new StocksAndRatiosController(model2, view2).getData();


        var model3 =  new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['4'], null, 'amis-mm-stocks-and-ratios');
        var view3 = new MarketMonitorChartView(model3, {
            'chartDivId' : 'amis_mm_stu_chart_4',
            'chartSessionId' : '4'

        });

        new StocksAndRatiosController(model3, view3).getData();

        var model4 =  new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['6'], null, 'amis-mm-stocks-and-ratios');
        var view4 = new MarketMonitorChartView(model4, {
            'chartDivId' : 'amis_mm_stu_chart_6',
            'chartSessionId' : '6'

        });

       new StocksAndRatiosController(model4, view4).getData();

    }


    function loadOilFreightsEthanolCharts(){
        var model;
        var view;

        if ($("#amis_mm_ofe_chart_OFI").length > 0){
            model =   new MarketMonitorDomainModel('Ocean Freight Indices', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI','BHI','BPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_ofe_chart_OFI',
                'chartSessionId' : 'OFI',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false

            });

           new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_ofe_chart_EFP").length > 0){

            model =  new MarketMonitorDomainModel('Chicago Ethanol Futures Prices', '9', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['EFP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_ofe_chart_EFP',
                'chartSessionId' : 'EFP',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        //Compare By Year Charts
        if ($("#amis_mm_ofe_chart_OFI_BDI").length > 0){
            model =   new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_ofe_chart_OFI_BDI',
                'chartSessionId' : 'OFI_BDI',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_OFI_BHI").length > 0){
            model =   new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BHI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_ofe_chart_OFI_BHI',
                'chartSessionId' : 'OFI_BHI',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false

            });

            new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_ofe_chart_OFI_BPI").length > 0){
            model =   new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_ofe_chart_OFI_BPI',
                'chartSessionId' : 'OFI_BPI',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false

            });

           new BasicHighStocksController(model, view).getData();
        }


    }

    function loadFREDCharts(){
        var model;
        var view;

        if ($("#amis_mm_fred_chart_COP").length > 0){
            model =   new MarketMonitorDomainModel('Crude Oil Prices', '2', 'FRED', 'FRED', ['MCOILWTICO', 'MCOILBRENTEU'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_fred_chart_COP',
                'chartSessionId' : 'COP'

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_fred_chart_DI").length > 0){
            model =   new MarketMonitorDomainModel('Dollar Indices', '1', 'FRED', 'FRED', ['TWEXMPA', 'TWEXBPA'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_fred_chart_DI',
                'chartSessionId' : 'DI'
            });

            new BasicHighStocksController(model, view).getData();
        }

    }

    function loadEthanolCharts(){
        var model;
        var view;

        if ($("#amis_mm_e_chart_EPM").length > 0){
            model =   new MarketMonitorDomainModel('US ethanol production margin', '11', 'AMIS_ETHANOL_MARGIN_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_e_chart_EPM',
                'chartSessionId' : 'EPM',
                'chartTimeFrame': '',
                'chartRangeSelectorButtons': ['YTD','1y','5y','all'],
                'addBaseLine':{'value': 0, 'color': 'black'},
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_e_chart_EC").length > 0){
            model =   new MarketMonitorDomainModel('US ethanol production pace, capacity and annual mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP','ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_e_chart_EC',
                'chartSessionId' : 'EC',
                'chartTimeFrame': '',
                'chartRangeSelectorButtons': ['YTD','1y','5y','all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{'ETHMAP':'Dash'}],
                'chartShowDateInputBoxes' : false
            });

            new BasicHighStocksController(model, view).getData();
        }

        /* test chart - showing all components visible by default (Date To and From input boxes, export buttons (Print/Download) and Navigator bar) */
        if ($("#amis_mm_e_chart_EC_1").length > 0){
            model =   new MarketMonitorDomainModel('US Ethanol Production Pace, Capacity and Annual Mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP','ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_e_chart_EC_1',
                'chartSessionId' : 'EC_1',
                'chartTimeFrame': '',
                'chartRangeSelectorButtons': ['YTD','1y','5y','all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{'ETHMAP':'Dash'}]
            });

            new BasicHighStocksController(model, view).getData();
        }

        /* test chart - with hidden Date To and From input boxes, hidden export buttons (Print/Download) and hidden Navigator bar */
        if ($("#amis_mm_e_chart_EC_2").length > 0){
            model =   new MarketMonitorDomainModel('US Ethanol Production Pace, Capacity and Annual Mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP','ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId' : 'amis_mm_e_chart_EC_2',
                'chartSessionId' : 'EC_2',
                'chartTimeFrame': '',
                'chartRangeSelectorButtons': ['YTD','1y','5y','all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{'ETHMAP':'Dash'}],
                'chartShowDateInputBoxes' : false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });

            new BasicHighStocksController(model, view).getData();
        }


    }

    function loadDomainJSScript(url){
        chart_script_tag = document.createElement('script');
        chart_script_tag.setAttribute("type","text/javascript");
        chart_script_tag.setAttribute("src", url);

        // Try to find the head, otherwise default to the documentElement
        (document.getElementsByTagName("head")[0] || document.documentElement).appendChild(chart_script_tag);
    }

    //}
    //else {

    // }

})(); // We call our anonymous function immediately