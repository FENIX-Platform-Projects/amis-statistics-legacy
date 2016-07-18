if (!window.BulkDownload) {
	
	window.BulkDownload = {
		
		// initiate interface	
		
		baseurl : "",
		
		datasource : "",
		
		language : "",
			
		initUI : function() {
			
			if ($.url().param('language') != null) {
				BulkDownload.language = $.url().param('language');
			} else { 
				BulkDownload.language = 'en';
			}
			
			$('#jqxTree').jqxTree({
				height : '600px',
				width : '300px'
			});
			
			$('#jqxTree').bind('expand', function (event) { 
				BulkDownload.expand(event);
			});
			
			$('#jqxTree').bind('select', function (event) { 
				var args = event.args;
		        var item = $('#jqxTree').jqxTree('getItem', args.element);
				$('#jqxTree').jqxTree('expandItem', item.element);
				if (item.parentElement != null && item.hasItems == false) {
					BulkDownload.showBulkDownloads(item);
				}
			});
			
			$.getJSON('config/bulk-download-configuration.json', function(data) {
				BulkDownload.populateTree(data);
			});
			
		},
		
		populateTree : function(json) {
			
			BulkDownload.baseurl = json.baseurl;
			BulkDownload.datasource = json.datasource;
			
			$.ajax({
				
				type: 'GET',
				url: 'http://' + BulkDownload.baseurl + '/wds/rest/groups/' + BulkDownload.datasource + '/' + BulkDownload.language,
				dataType: 'json',
				
				success : function(response) {
					
					for (var i = 1 ; i < response.length ; i++) {
						
						// add element
						$('#jqxTree').jqxTree('addTo', { label: response[i][1], icon : 'css/images/folder.png', value: response[i][0], id: response[i][0]});
						
						// add "Loading" child
						var elementByID = $('#jqxTree').find("#" + response[i][0])[0];
						$('#jqxTree').jqxTree('addTo', { label: 'Loading. Please wait...', icon : 'css/images/loading.gif', id: 'placeholder_' + response[i][0]}, elementByID);
					}
					
				},
				
				error : function(err,b,c) {
					alert(err.status + ", " + b + ", " + c);
				}
				
			});
			
		},
		
		showBulkDownloads : function(item) {
			$.ajax({
				type: 'GET',
				url: 'http://' + BulkDownload.baseurl + '/wds/rest/bulkdownloads/' + BulkDownload.datasource + '/' + item.value + '/' + BulkDownload.language,
				dataType: 'json',
				success : function(response) {
					$("#domainNameTitle").remove();
					$("#bulkDownloadsList").remove();
					$("#break").remove();
					$("#listArea").append("<div id='domainNameTitle' class='download_title'>" + response[1][1] + " (" + response[1][4].substring(0, 10) + ")</div>");
					$("#listArea").append("<br id='break'/>");
					var s = BulkDownload.createList(response);
					$("#listArea").append(s);
				},
				error : function(err,b,c) {
					alert(err.status + ", " + b + ", " + c);
				}
			});
		},
		
		createList : function(response) {
			var root = 'http://faostat.fao.org/Portals/_Faostat/Downloads/zip_files/';
			var s = "";
			s += "<ul id='bulkDownloadsList' class='bullet-list'>";
			for (var i = 1 ; i < response.length ; i++) {
				var zip = response[i][2].replace(".csv", ".zip");
				s += "<li><a href='" + root + zip + "'>" + response[i][3] + "</li></a>";
			}
			s += "</ul>";
			return s;
		},
		
		// expand controller
		expand : function(event) {
			var args = event.args;
	        var item = $('#jqxTree').jqxTree('getItem', args.element);
			var domainCode = item.value;
			var placeholderElement = $('#jqxTree').find("#placeholder_" + domainCode)[0];
			if (placeholderElement != null) {
				$.ajax({
					type: 'GET',
					url: 'http://' + BulkDownload.baseurl + '/wds/rest/domains/' + BulkDownload.datasource + '/' + domainCode + '/' + BulkDownload.language,
					dataType: 'json',
					success : function(response) {
						for (var i = 1 ; i < response.length ; i++) {
							$('#jqxTree').jqxTree('removeItem', placeholderElement);
							$('#jqxTree').jqxTree('addTo', { label: response[i][1], icon : 'css/images/folder.png', value: response[i][0] }, item.element);
							$('#jqxTree').jqxTree('expandItem', item.element);
						}
					},
					error : function(err,b,c) {
						alert(err.status + ", " + b + ", " + c);
					}
				});
			}
		}
		
	};
	
}