(function() {

    $(document).ready(function() {

        var chart_script_tag;
        var ensureJS;
        var ensureCSS;
        var ensureCSS1;
        var dependenciesJS = ["http://statistics.amis-outlook.org/data/amis-market-monitor/js/model/domainmodel.js", "http://statistics.amis-outlook.org/data/amis-market-monitor/js/view/chartview.js", "https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js", "http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/xdr.js"];
        var ensureJSLoaded = false;
        var chartTypeArry = [];


        include_CSS("http://statistics.amis-outlook.org/data/amis-market-monitor/plugin/jquery-ui-datepicker/css/custom-theme/jquery-ui-1.10.2.custom.min.css");

        include_ensureJS("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/head.min.js");

        include_ensureJS("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/jQuery.XDomainRequest.js");

        if (dependenciesJS.length > 3)
            dependenciesJS.pop();


        if ($("#amis_mm_stu_charts").length > 0) {
            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-stocks-and-ratios-controller.js");
            onEnsureScriptLoad(dependenciesJS, "STOCKS");
        } else {

            dependenciesJS.push("http://statistics.amis-outlook.org/data/amis-market-monitor/js/controller/amis-basic-hstocks-controller.js");


            if ($("#amis_mm_p_charts").length > 0) {
                chartTypeArry.push("PRICES");
            }
            if ($("#amis_mm_oi_charts").length > 0) {
                chartTypeArry.push("FERTILIZER");
            }

            if ($("#amis_mm_ofe_charts").length > 0) {
                chartTypeArry.push("OIL_FREIGHTS_ETHANOL");
            }
            if ($("#amis_mm_fred_charts").length > 0) {
                chartTypeArry.push("FRED");
            }
            if ($("#amis_mm_ethanol_charts").length > 0) {
                chartTypeArry.push("ETHANOL");
            }

            onEnsureScriptLoadArray(dependenciesJS, chartTypeArry);

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


    function onEnsureScriptLoadArray(urlControllers, charttypes) {
        ensureJS.onreadystatechange = function() {
            if (ensureJS.readyState == 'complete') {
                loadEnsureScriptHandlerArray(urlControllers, charttypes);
            }
        }

        ensureJS.onload = function() {
            loadEnsureScriptHandlerArray(urlControllers, charttypes);
        }
    }

    function onEnsureScriptLoad(urlControllers, charttype) {
        ensureJS.onreadystatechange = function() {
            if (ensureJS.readyState == 'complete') {
                loadEnsureScriptHandler(urlControllers, charttype);
            }
        }

        ensureJS.onload = function() {
            loadEnsureScriptHandler(urlControllers, charttype);
        }
    }


    function loadEnsureScriptHandlerArray(controllerUrls, charttypes) {
        if (typeof head !== 'undefined') {


            $.getScript("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/json3.min.js", function(data, textStatus, jqxhr) {
                head.js(controllerUrls[0], controllerUrls[1], controllerUrls[2], controllerUrls[3], function() {
                    for (var j = 0; j < charttypes.length; j++) {
                        var charttype = charttypes[j];
                        if (charttype == "FERTILIZER") {

                            loadFertilizerCharts();
                        }

                        if (charttype == "PRICES") {

                            loadPricesCharts();
                        }
                        if (charttype == "OIL_FREIGHTS_ETHANOL") {
                            loadOilFreightsEthanolCharts();
                        }
                        if (charttype == "FRED") {

                            loadFREDCharts();
                        }
                        if (charttype == "ETHANOL") {

                            loadEthanolCharts();
                        }
                    }


                });
            });


        } else {


        }

    }

    function loadEnsureScriptHandler(controllerUrls, charttype) {

        if (typeof head !== 'undefined') {


            $.getScript("http://statistics.amis-outlook.org/data/amis-market-monitor/js/util/json3.min.js", function(data, textStatus, jqxhr) {
                head.js(controllerUrls[0], controllerUrls[1], controllerUrls[2], controllerUrls[3], function() {

                    if (charttype == "STOCKS") {

                        loadStocksCharts();
                    }
                });
            });
        }

    }


    function loadFertilizerCharts() {
        var model;
        var view;

        if ($("#amis_mm_oi_chart_FP_M").length > 0) {
            model = new MarketMonitorDomainModel('Fertilizer Prices-FOB', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC', 'GCFPDAGE', 'GCFPURBS', 'GCFPPRM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_M',
                'chartSessionId': 'FPM',
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_D").length > 0) {
            model = new MarketMonitorDomainModel('Fertilizer Prices-FOB', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC', 'GCFPDAGE', 'GCFPURBS', 'GCFPPRM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_D',
                'chartSessionId': 'FPD',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': true
            });

            new BasicHighStocksController(model, view).getData();
        }

        //Compare by Year charts:
        if ($("#amis_mm_oi_chart_FP_GCFPPGVC_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPPGVC_M',
                'chartSessionId': 'FP_GCFPPGVCM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPPGVC_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPGVC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPPGVC_1M',
                'chartSessionId': 'FP_GCFPPGVC1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPDAGE_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPDAGE'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPDAGE_M',
                'chartSessionId': 'FP_GCFPDAGEM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'showChartDatatoExcelOption': false,
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPDAGE_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPDAGE'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPDAGE_1M',
                'chartSessionId': 'FP_GCFPDAGE1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'showChartDatatoExcelOption': false,
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPURBS_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPURBS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPURBS_M',
                'chartSessionId': 'FP_GCFPURBSM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPURBS_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPURBS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPURBS_1M',
                'chartSessionId': 'FP_GCFPURBS1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPPRM_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPRM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPPRM_M',
                'chartSessionId': 'FP_GCFPPRMM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_oi_chart_FP_GCFPPRM_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '2', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['GCFPPRM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_oi_chart_FP_GCFPPRM_1M',
                'chartSessionId': 'FP_GCFPPRM1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

    }

    function loadPricesCharts() {
        var model;
        var view;

        if ($("#amis_mm_p_chart_HVW_M").length > 0) {
            model = new MarketMonitorDomainModel('Wheat Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVW'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVW_M',
                'chartSessionId': 'HVWM',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVW_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Wheat Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVW'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVW_1M',
                'chartSessionId': 'HVW1M',
                'showChartDatatoExcelOption': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVM_M").length > 0) {

            model = new MarketMonitorDomainModel('Maize Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVM_M',
                'chartSessionId': 'HVMM',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVM_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Maize Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVM_1M',
                'chartSessionId': 'HVM1M',
                'showChartDatatoExcelOption': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVR_M").length > 0) {

            model = new MarketMonitorDomainModel('Rough Rice Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVR'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVR_M',
                'chartSessionId': 'HVRM',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVR_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Rough Rice Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVR'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVR_1M',
                'chartSessionId': 'HVR1M',
                'showChartDatatoExcelOption': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVS_M").length > 0) {

            model = new MarketMonitorDomainModel('Soybeans Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVS'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVS_M',
                'chartSessionId': 'HVSM',
                'showChartDatatoExcelOption': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_HVS_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Soybeans Historical Volatility', '7', 'AMIS_VOLATILITY_DAILY_INDICATORS', 'AMIS-STATISTICS', ['HVS'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_HVS_1M',
                'chartSessionId': 'HVS1M',
                'showChartDatatoExcelOption': false,
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
            });

            new BasicHighStocksController(model, view).getData();

        }


        // Indicators - Prices... - FPI - desktop
        if ($("#amis_mm_p_chart_FPI_M").length > 0) {
            model = new MarketMonitorDomainModel('FAO Food Price Index and Sub-Indices', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI', 'FPIC', 'FPIOF', 'FPIS', 'FPIM', 'FPID'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_M',
                'chartSessionId': 'FPIM',
                'chartHideSeries': ['FPIC', 'FPIOF', 'FPIS', 'FPIM', 'FPID'],
                'chartShowDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();

        }

        // Indicators - Prices... - FPI - mobile
        if ($("#amis_mm_p_chart_FPI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('FAO Food Price Index', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI', 'FPIC', 'FPIOF', 'FPIS', 'FPIM', 'FPID'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_1_M',
                'chartSessionId': 'FPI1M',
                'chartHideSeries': ['FPIC', 'FPIOF', 'FPIS', 'FPIM', 'FPID'],
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        //Compare By Year Charts
        if ($("#amis_mm_p_chart_FPI_FPI_M").length > 0) {

            model = new MarketMonitorDomainModel('(by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPI_M',
                'chartSessionId': 'FPI_FPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_FPI_FPI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPI_1M',
                'chartSessionId': 'FPI_FPI1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIC_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIC_M',
                'chartSessionId': 'FPI_FPICM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_FPI_FPIC_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIC'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIC_1M',
                'chartSessionId': 'FPI_FPIC1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIOF_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIOF'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIOF_M',
                'chartSessionId': 'FPI_FPIOFM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIOF_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIOF'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIOF_1M',
                'chartSessionId': 'FPI_FPIOF1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIS_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIS_M',
                'chartSessionId': 'FPI_FPISM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIS_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIS'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIS_1M',
                'chartSessionId': 'FPI_FPIS1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIM_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIM_M',
                'chartSessionId': 'FPI_FPIMM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPIM_1M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPIM'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPIM_1M',
                'chartSessionId': 'FPI_FPIM1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPID_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPID'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPID_M',
                'chartSessionId': 'FPI_FPIDM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        if ($("#amis_mm_p_chart_FPI_FPID_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Price Index (by year)', '4', 'AMIS_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['FPID'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_FPI_FPID_1M',
                'chartSessionId': 'FPI_FPID1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }







        // International Prices - Overview - desktop
        // and
        // Indicators - Prices... - GOI - desktop
        if ($("#amis_mm_p_chart_IGC_M").length > 0) {

            model = new MarketMonitorDomainModel('IGC Grains and Oilseeds Index and sub-Indices', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI', 'WPI', 'MPI', 'RPI', 'SPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_M',
                'chartSessionId': 'IGCM'
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Overview - mobile
        // and
        // Indicators - Prices... - GOI - mobile
        if ($("#amis_mm_p_chart_IGC_1_M").length > 0) {

            model = new MarketMonitorDomainModel('IGC Grains and Oilseeds Index', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI', 'WPI', 'MPI', 'RPI', 'SPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_1_M',
                'chartSessionId': 'IGC1M',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Wheat - desktop
        if ($("#amis_mm_p_chart_IGC_WPI_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['WPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_WPI_M',
                'chartSessionId': 'IGC_WPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Wheat - mobile
        if ($("#amis_mm_p_chart_IGC_WPI_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['WPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_WPI_1_M',
                'chartSessionId': 'IGC_WPI1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Maize - desktop
        if ($("#amis_mm_p_chart_IGC_MPI_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['MPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_MPI_M',
                'chartSessionId': 'IGC_MPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Maize - mobile
        if ($("#amis_mm_p_chart_IGC_MPI_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['MPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_MPI_1_M',
                'chartSessionId': 'IGC_MPI1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Rice - desktop
        if ($("#amis_mm_p_chart_IGC_RPI_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['RPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_RPI_M',
                'chartSessionId': 'IGC_RPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Rice - mobile
        if ($("#amis_mm_p_chart_IGC_RPI_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['RPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_RPI_1_M',
                'chartSessionId': 'IGC_RPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Soybean - desktop
        if ($("#amis_mm_p_chart_IGC_SPI_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['SPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_SPI_M',
                'chartSessionId': 'IGC_SPIM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // International Prices - Soybean - mobile
        if ($("#amis_mm_p_chart_IGC_SPI_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Sub-Index (by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['SPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_SPI_1_M',
                'chartSessionId': 'IGC_SPI1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // Future Markets - Wheat - desktop
        if ($("#amis_mm_p_chart_WFSMA_M").length > 0) {
            model = new MarketMonitorDomainModel('Wheat Futures Price and Simple Moving Average', '10', 'AMIS_WHEAT_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEW', 'CMEWSMA30', 'CMEWSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_WFSMA_M',
                'chartSessionId': 'WFSMA_M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Wheat - mobile
        if ($("#amis_mm_p_chart_WFSMA_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Wheat Futures Price', '10', 'AMIS_WHEAT_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEW', 'CMEWSMA30', 'CMEWSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_WFSMA_1_M',
                'chartSessionId': 'WFSMA_1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Maize - desktop
        // and
        // Indicators - Prices... - GIEWS Maize - desktop
        if ($("#amis_mm_p_chart_MFSMA_M").length > 0) {
            model = new MarketMonitorDomainModel('Maize Futures Price and Simple Moving Average', '10', 'AMIS_MAIZE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEM', 'CMEMSMA30', 'CMEMSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_MFSMA_M',
                'chartSessionId': 'MFSMAM',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Maize - mobile
        if ($("#amis_mm_p_chart_MFSMA_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Maize Futures Price', '10', 'AMIS_MAIZE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEM', 'CMEMSMA30', 'CMEMSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_MFSMA_1_M',
                'chartSessionId': 'MFSMA1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Rice - desktop
        if ($("#amis_mm_p_chart_RFSMA_M").length > 0) {
            model = new MarketMonitorDomainModel('Rice Futures Price and Simple Moving Average', '10', 'AMIS_RICE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMER', 'CMERSMA30', 'CMERSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_RFSMA_M',
                'chartSessionId': 'RFSMAM',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Rice - mobile
        if ($("#amis_mm_p_chart_RFSMA_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Rice Futures Price', '10', 'AMIS_RICE_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMER', 'CMERSMA30', 'CMERSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_RFSMA_1_M',
                'chartSessionId': 'RFSMA1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Soybean - desktop
        if ($("#amis_mm_p_chart_SFSMA_M").length > 0) {
            model = new MarketMonitorDomainModel('Soybean Futures Price and Simple Moving Average', '10', 'AMIS_SOYBEAN_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMES', 'CMESSMA30', 'CMESSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_SFSMA_M',
                'chartSessionId': 'SFSMAM',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false
            });
            new BasicHighStocksController(model, view).getData();
        }

        // Future Markets - Soybean - mobile
        if ($("#amis_mm_p_chart_SFSMA_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Soybean Futures Price', '10', 'AMIS_SOYBEAN_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMES', 'CMESSMA30', 'CMESSMA60'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_SFSMA_1_M',
                'chartSessionId': 'SFSMA1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });
            new BasicHighStocksController(model, view).getData();
        }


        // Indicators - Prices... - IGC Price - desktop
        if ($("#amis_mm_p_chart_IGC_GOI_M").length > 0) {

            model = new MarketMonitorDomainModel('(by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_GOI_M',
                'chartSessionId': 'IGC_IGCM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowDateInputBoxes': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // Indicators - Prices... - IGC Price - mobile
        if ($("#amis_mm_p_chart_IGC_GOI_1_M").length > 0) {

            model = new MarketMonitorDomainModel('(by year)', '5', 'AMIS_IGC_DAILY_INDICATORS', 'AMIS-STATISTICS', ['GOI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_IGC_GOI_1M',
                'chartSessionId': 'IGC_IGC1M',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();

        }

        // Indicators - Prices... - Wheat - desktop ????
        if ($("#amis_mm_p_chart_2301_M").length > 0) {

            model = new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2301', '2301'], ['16', '17'], 'amis-mm-est-prices');

            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_2301_M',
                'chartSessionId': '2301M'
            });

            new BasicHighStocksController(model, view).getData();
        }

        // Indicators - Prices... - Maize - desktop ????
        if ($("#amis_mm_p_chart_2302_M").length > 0) {

            model = new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2302', '2302'], ['3', '44'], 'amis-mm-est-prices');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_2302_M',
                'chartSessionId': '2302M'
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_2311_M").length > 0) {

            model = new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2311', '2311'], ['14', '25'], 'amis-mm-est-prices');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_2311_M',
                'chartSessionId': '2311M'
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_p_chart_2303_M").length > 0) {
            model = new MarketMonitorDomainModel('International Prices', '6', 'EST_DATABASE', 'FAO-EST', ['2303'], ['4'], 'amis-mm-est-prices');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_2303_M',
                'chartSessionId': '2303M'
            });

            new BasicHighStocksController(model, view).getData();
        }









        //Compare By Year Chart
        if ($("#amis_mm_p_chart_WFSMA_COMPARE_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '10', 'AMIS_WHEAT_FUTURES_SMA_DAILY_INDICATORS', 'AMIS-STATISTICS', ['CMEW'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_p_chart_WFSMA_COMPARE_M',
                'chartSessionId': 'WFSMA_COMPAREM',
                'chartTimeFrame': '4y',
                'chartType': 'COMPARE_YEARS',
                'showChartDatatoExcelOption': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });
            new BasicHighStocksController(model, view).getData();
        }






    }


    function loadStocksCharts() {

        var model = new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['1'], null, 'amis-mm-stocks-and-ratios');
        var view = new MarketMonitorChartView(model, {
            'chartDivId': 'amis_mm_stu_chart_1_M',
            'chartSessionId': '1M'

        });

        new StocksAndRatiosController(model, view).getData();

        var model2 = new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['5'], null, 'amis-mm-stocks-and-ratios');
        var view2 = new MarketMonitorChartView(model2, {
            'chartDivId': 'amis_mm_stu_chart_5_M',
            'chartSessionId': '5M'

        });

        new StocksAndRatiosController(model2, view2).getData();


        var model3 = new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['4'], null, 'amis-mm-stocks-and-ratios');
        var view3 = new MarketMonitorChartView(model3, {
            'chartDivId': 'amis_mm_stu_chart_4_M',
            'chartSessionId': '4M'

        });

        new StocksAndRatiosController(model3, view3).getData();

        var model4 = new MarketMonitorDomainModel('Stocks and Ratios', '6', 'AMIS', 'AMIS-STATISTICS', ['6'], null, 'amis-mm-stocks-and-ratios');
        var view4 = new MarketMonitorChartView(model4, {
            'chartDivId': 'amis_mm_stu_chart_6_M',
            'chartSessionId': '6M'

        });

        new StocksAndRatiosController(model4, view4).getData();


    }


    function loadOilFreightsEthanolCharts() {
        var model;
        var view;

        if ($("#amis_mm_ofe_chart_OFI_M").length > 0) {
            model = new MarketMonitorDomainModel('Ocean Freight Indices', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI', 'BHI', 'BPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_M',
                'chartSessionId': 'OFIM',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false

            });

            new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_ofe_chart_OFI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Ocean Freight Indices', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI', 'BHI', 'BPI'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_1_M',
                'chartSessionId': 'OFI1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_ofe_chart_EFP_M").length > 0) {

            model = new MarketMonitorDomainModel('Chicago Ethanol Futures Prices', '9', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['EFP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_EFP_M',
                'chartSessionId': 'EFPM',
                'showChartDatatoExcelOption': false,
                'chartShowDateInputBoxes': false,
                'chartEnableTooltip': true

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_EFP_1_M").length > 0) {

            model = new MarketMonitorDomainModel('Chicago Ethanol Futures Prices', '9', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['EFP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_EFP_1_M',
                'chartSessionId': 'EFP1M',
                'showChartDatatoExcelOption': false,
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        //Compare By Year Charts
        if ($("#amis_mm_ofe_chart_OFI_BDI_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BDI_M',
                'chartSessionId': 'OFI_BDIM',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_OFI_BDI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BDI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BDI_1M',
                'chartSessionId': 'OFI_BDI1M',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_OFI_BHI_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BHI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BHI_M',
                'chartSessionId': 'OFI_BHIM',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_OFI_BHI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BHI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BHI_1M',
                'chartSessionId': 'OFI_BHI1M',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false

            });

            new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_ofe_chart_OFI_BPI_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BPI_M',
                'chartSessionId': 'OFI_BPIM',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_ofe_chart_OFI_BPI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('(by year)', '8', 'AMIS_OCEAN_FREIGHTS_ETHANOL_DAILY_INDICATORS', 'AMIS-STATISTICS', ['BPI'], null, 'amis-mm-compare-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_ofe_chart_OFI_BPI_1M',
                'chartSessionId': 'OFI_BPI1M',
                'chartTimeFrame': '4y',
                'showChartDatatoExcelOption': false,
                'chartType': 'COMPARE_YEARS',
                'chartEnableTooltip': false,
                'chartShowNavigator': false,
                'chartShowExportButtons': false,
                'chartShowZoomandDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }
    }

    function loadFREDCharts() {
        var model;
        var view;

        if ($("#amis_mm_fred_chart_COP_M").length > 0) {
            model = new MarketMonitorDomainModel('Crude Oil Prices', '2', 'FRED', 'FRED', ['MCOILWTICO', 'MCOILBRENTEU'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_fred_chart_COP_M',
                'chartSessionId': 'COPM'

            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_fred_chart_COP_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Crude Oil Prices', '2', 'FRED', 'FRED', ['MCOILWTICO', 'MCOILBRENTEU'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_fred_chart_COP_1_M',
                'chartSessionId': 'COP1M',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_fred_chart_DI_M").length > 0) {
            model = new MarketMonitorDomainModel('Dollar Indices', '1', 'FRED', 'FRED', ['TWEXMPA', 'TWEXBPA'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_fred_chart_DI_M',
                'chartSessionId': 'DIM'
            });

            new BasicHighStocksController(model, view).getData();
        }


        if ($("#amis_mm_fred_chart_DI_1_M").length > 0) {
            model = new MarketMonitorDomainModel('Dollar Indices', '1', 'FRED', 'FRED', ['TWEXMPA', 'TWEXBPA'], null, null);
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_fred_chart_DI_1_M',
                'chartSessionId': 'DI1M',
                'chartShowNavigator': false,
                'chartShowZoomandDateInputBoxes': true,
                'chartShowExportButtons': false,
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }


    }

    function loadEthanolCharts() {
        var model;
        var view;

        if ($("#amis_mm_e_chart_EPM_M").length > 0) {
            model = new MarketMonitorDomainModel('US ethanol production margin', '11', 'AMIS_ETHANOL_MARGIN_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_e_chart_EPM_M',
                'chartSessionId': 'EPMM',
                'chartRangeSelectorButtons': ['YTD', '1y', '5y', 'all'],
                'addBaseLine': {
                    'value': 0,
                    'color': 'black'
                },
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_e_chart_EPM_1_M").length > 0) {
            model = new MarketMonitorDomainModel('US ethanol production margin', '11', 'AMIS_ETHANOL_MARGIN_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHM'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_e_chart_EPM_1_M',
                'chartSessionId': 'EPM1M',
                'chartRangeSelectorButtons': ['YTD', '1y', '5y', 'all'],
                'addBaseLine': {
                    'value': 0,
                    'color': 'black'
                },
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        if ($("#amis_mm_e_chart_EPCA_M").length > 0) {
            model = new MarketMonitorDomainModel('US ethanol production pace, capacity and annual mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP', 'ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_e_chart_EPCA_M',
                'chartSessionId': 'EPCAM',
                'chartRangeSelectorButtons': ['YTD', '1y', '5y', 'all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{
                    'ETHMAP': 'Dash'
                }],
                'chartShowDateInputBoxes': false
            });

            new BasicHighStocksController(model, view).getData();
        }

        /* chart - showing all components visible by default (Date To and From input boxes, export buttons (Print/Download) and Navigator bar) */
        if ($("#amis_mm_e_chart_EPCA_2_M").length > 0) {
            model = new MarketMonitorDomainModel('US Ethanol Production Pace, Capacity and Annual Mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP', 'ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_e_chart_EPCA_2_M',
                'chartSessionId': 'EPCA_2M',
                'chartRangeSelectorButtons': ['YTD', '1y', '5y', 'all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{
                    'ETHMAP': 'Dash'
                }]
            });

            new BasicHighStocksController(model, view).getData();
        }


        /* chart - with hidden Date To and From input boxes, hidden export buttons (Print/Download) and hidden Navigator bar */
        if ($("#amis_mm_e_chart_EPCA_1_M").length > 0) {
            model = new MarketMonitorDomainModel('US Ethanol Production Pace, Capacity and Annual Mandate', '11', 'AMIS_ETHANOL_CAPACITY_MONTHLY_INDICATORS', 'AMIS-STATISTICS', ['ETHP', 'ETHMA', 'ETHCAP', 'ETHMAP'], null, 'amis-mm-other-indicators');
            view = new MarketMonitorChartView(model, {
                'chartDivId': 'amis_mm_e_chart_EPCA_1_M',
                'chartSessionId': 'EPCA_1M',
                'chartRangeSelectorButtons': ['YTD', '1y', '5y', 'all'],
                'seriesColours': ['#2A5AA6', '#DD3218', '#07A02E', '#DD3218'], //blue, red, green, red,
                'seriesLineStyle': [{
                    'ETHMAP': 'Dash'
                }],
                'chartShowDateInputBoxes': false,
                'chartShowExportButtons': false,
                'chartShowNavigator': false
            });

            new BasicHighStocksController(model, view).getData();
        }


    }

    function loadDomainJSScript(url) {
        chart_script_tag = document.createElement('script');
        chart_script_tag.setAttribute("type", "text/javascript");
        chart_script_tag.setAttribute("src", url);

        // Try to find the head, otherwise default to the documentElement
        (document.getElementsByTagName("head")[0] || document.documentElement).appendChild(chart_script_tag);
    }


})(); // We call our anonymous function immediately