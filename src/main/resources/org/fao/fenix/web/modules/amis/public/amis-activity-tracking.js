if (!window.AMISActivityTracking) {
				
		window.AMISActivityTracking = {
	
		initActivityTrackingUI: function(language, domain) {

			$('#MAIN_CONTENT').load('amis-activity-monitoring-js/index.html', function() {
				AMISTrackingGUI.init();
			});
		}
	};
}
