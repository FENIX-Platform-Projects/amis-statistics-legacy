W.registerWidgetFactory ("org.fao.wHighstocks.1.0", function(aw) {
	
		    aw.chart = null;
			aw.startConfig = null;
		
			aw.config = function(config) {
				aw.startConfig = config;
			};
		
		    console.log(config);
			console.log(container);
			
			aw.content = function(container) {
				if (container == null)
					throw "Container is null";
		
				// The starting configuration is null -> assign a default, empty one
				if (aw.startConfig == null) {
					aw.startConfig = _wChart_createDefaultHighstocksPrefs(container);
				}
				// change the container in the starting config
				_wChart_changeContainer(aw.startConfig, container);
				// create the new highcharts object
				aw.chart = new Highcharts.Chart(aw.startConfig);
			};
		
			aw.setHighstockChartObject = function(hStocksOptions) {
				_wChart_changeContainer(hStocksOptions, aw.container);
				aw.chart = new Highcharts.Chart(hStocksOptions);
			};
		
			aw.setType = function(type) {
				_wChart_changeType(aw.chart, type);
			};
		
			aw.setTitle = function(title) {
				aw.chart.setTitle({
					text : title
				});
			};
		
			aw.setXAxisCategories = function(xAxisCategories) {
				aw.chart.xAxis[0].setCategories(xAxisCategories);
			};
		
			aw.setYAxisTitle = function(title) {
				aw.chart.yAxis[0].axisTitle.attr({
					text : title
				});
			};
		
			aw.setSeries = function(series) {
				var i = 0;
		
				if (aw.chart.series != null) {
					var sLen = aw.chart.series.length;
					for (i = 0; i < sLen; i++) {
						aw.chart.series[0].remove(false);
					}
				}
				if (series == null)
					return;
		
				for (i = 0; i < series.length; i++) {
					aw.chart.addSeries({
						name : series[i].name,
						data : series[i].values
					}, false);
				}
				aw.chart.redraw();
			};
		
			aw.setChartObject = function(obj) {
				// change the container in the starting config
				_wChart_changeContainer(obj, aw.container);
				// create the new highcharts object
				aw.chart = new Highcharts.Chart(obj);
			};
		
			return aw;
	});
	
	
	function _wChart_changeContainer(hStocksOptions, container) {
		if (hStocksOptions.chart == null) {
			// the chart object in the starting configuration is null -> create a
			// new one
			hStocksOptions.chart = {
				renderTo : container
			};
		} else {
			// the chart object in the starting configuration is not null -> just
			// change the contaner
			hStocksOptions.chart.renderTo = container;
		}
	}
	
	function _wChart_createDefaultHighstocksPrefs(container) {
		var hStocksOptions = {
			chart : {
			// renderTo : container
			},
			title : {
				text : ''
			},
			xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : ''
				}
			},
			series : []
		};
		return hStocksOptions;
	}
	
	function _wChart_changeType(chObj, newType) {
		if (chObj == null)
			return;
		if (chObj.series == null)
			return;
		var serie = null;
		for ( var i = 0; i < chObj.series.length; i++) {
			serie = chObj.series[0];
			serie.chart.addSeries({
				type : newType,
				name : serie.name,
				color : serie.color,
				data : serie.options.data
			}, false);
			serie.remove(false);
		}
		chObj.redraw();
	}