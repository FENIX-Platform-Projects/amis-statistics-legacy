	    var featureList = ["pivot", "statistics"];
	    var pivot;
	
	     var header = ["Data Source","Country","Item","Element","Year","Unit","Flag","&nbsp;"];
  //var data = [["FAO-CBS<span class=cs>FAO-CBS</span>",
        var data = [["FAO-AMIS<span class=cs>FAO-AMIS</span>",
		"Australia<span class=cs>17</span>",
		"Wheat (Total)<span class=cs>1</span>",
		"Other Uses<span class=cs>15</span>",
		"2004","Million tonnes","B","1.28"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Australia<span class=cs>17</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","B","1.28"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Australia<span class=cs>17</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","B","1.53"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","B","0.03"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","B","0.04"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","B","0.03"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","F","0.3"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","F","0.25"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","F","0.3"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","F","0.5"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","F","1.1"],
        ["FAO-AMIS<span class=cs>FAO-AMIS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","F","0.8"]];
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Australia<span class=cs>17</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","B","1.28"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Australia<span class=cs>17</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","B","1.53"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","B","0.03"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","B","0.04"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Australia<span class=cs>17</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","B","0.03"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","F","0.3"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","F","0.25"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Wheat (Total)<span class=cs>1</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","F","0.3"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2004","Million tonnes","F","0.5"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2005","Million tonnes","F","1.1"],
		//["FAO-CBS<span class=cs>FAO-CBS</span>","Argentina<span class=cs>12</span>","Maize<span class=cs>5</span>","Other Uses<span class=cs>15</span>","2003","Million tonnes","F","0.8"]];
	    var pivot;
	    var row = [0,1];
	    var col = [2,3,4];
	    var agg = [];
	    var dataIndex=0;
	    var option = { showChart: 0, headingBefore: 0, headingAfter: 0, nbDec:2,headingOutside: 1, showUnit: 0,showFlag:1 ,nestedby:1,subtotals:0,totals:0,calcTotal:0};

	    function init() {
	      //init2(); 
	        
	    }
	    function init2() {

	       // alert("pivot_content" + "\n" + "pivot_chart" + "\n" + "pivot_page" + "\n" + header + "\n" + data + "\n" + row + "\n" + col + "\n" + agg + "\n" + dataIndex + "\n" + option);

		  
            if(option.nestedby==0)
			{document.getElementById("ODC_NESTED").style.display="none";
			document.getElementById("legendrow").style.display="none";
			}
			else{document.getElementById("ODC_NESTED").style.display="inline";
			document.getElementById("legendrow").style.display="inline";
			}
			dataIndex = data[0].length - 1;
           
		   
		   
		  
                pivot = new OAT.Pivot("pivot_content", "pivot_chart", "pivot_page", header, data, row, col, agg, dataIndex, option);
           
		  
		 
		   
	        var aggRef = function () {
	            pivot.options.agg = parseInt($v("pivot_agg"));
	            pivot.go();
	        }
            var nbDecRef = function () {
	            pivot.options.nbDec = parseInt($v("nbDec"));
	            pivot.go();
	        }
	        var aggRefTotals = function () {
	            pivot.options.aggTotals = parseInt($v("pivot_agg_totals"));
	            pivot.go();
	        }
			
			
			
			
		  
	        /* create agg function list */
	        OAT.Dom.clear("pivot_agg");
	        OAT.Dom.clear("pivot_agg_totals");
			
			
			
	       for (var i = 0; i < OAT.Statistics.list.length; i++) {
	            
				var item = OAT.Statistics.list[i];
				if (item.shortDesc == "SUM" || item.shortDesc == "MEAN") {
				OAT.Dom.option(item.shortDesc, i, "pivot_agg");
				OAT.Dom.option(item.shortDesc, i, "pivot_agg_totals");
				if (pivot.options.agg == i) { $("pivot_agg").selectedIndex = i; }
				if (pivot.options.aggTotals == i) { $("pivot_agg_totals").selectedIndex = i; }
				
                }
	        }
			
			
	        OAT.Dom.attach("pivot_agg", "change", aggRef);
	        OAT.Dom.attach("pivot_agg_totals", "change", aggRefTotals);
	        OAT.Dom.attach("nbDec", "change", nbDecRef);
	    }
		
		function setInnerHTML(inDOMNode, inHTML) {
     inDOMNode.innerHTML = "_" + inHTML;
     inDOMNode.removeChild(inDOMNode.firstChild);
}
		
	function getElementsByClassName2(className, tag, elm){
	
	var testClass = new RegExp("(^|\\s)" + className + "(\\s|$)");
	var tag = tag || "*";
	var elm = elm || document;
	var elements = (tag == "*" && elm.all)? elm.all : elm.getElementsByTagName(tag);
	var returnElements = [];
	var current;
	var length = elements.length;
	for(var i=0; i<length; i++){
		current = elements[i];
		if(testClass.test(current.className)){
			returnElements.push(current);
		}
	}
	return returnElements;
}
		
		
		
		 function showCode() {
		 
		 /*	alert("te2");
		var arr=getElementsByClassName2("sc","*",document);
		var arr2=getElementsByClassName2("sc2","*",document);
		alert(arr);
		alert(arr2);
		
		for(var i=0;i<arr.lenght;i++)
		{arr[i].className="sc2"}
		for(var i=0;i<arr2.lenght;i++)
		{arr2[i].className="sc"}
		
		alert("te");
		*/
	     if (document.getElementById('showCode').checked) {
			setInnerHTML(document.getElementById("CodeStyle"),"<style>.cs{display:inline}</style>");
			   //document.getElementById("CodeStyle").innerHTML = ".cs{display:inline}";
            }
            else {
			//document.getElementById("CodeStyle").innerHTML = ".cs{display:none}"; 
			setInnerHTML(document.getElementById("CodeStyle"),"<style>.cs{display:none}</style>");
			
			}

        }
        function showAggregate() {
            if (document.getElementById('showAgregate').checked) {
                option.totals = 1;
                document.getElementById('subTotalsDiv').style.display = "block";
              }
            else { option.totals = 0; document.getElementById('subTotalsDiv').style.display = "none"; }
          
            pivot.options = option;
            init2();
           // alert(document.getElementById('showAgregate').checked+"  " +option.aggTotals + "  $ " + option.showChart);
        }
		
		
        function showUnit() {
            if (document.getElementById('showUnit').checked) {
                option.showUnit = 1;
               
            }
            else { option.showUnit = 0; }

            pivot.options = option;
            init2();
        }
		
		
		
		
		
		
       
		
		function changeNestedBy()
		{
		
		if(option.nestedby==1){option.nestedby=0;}
		else{option.nestedby=1;}
		init2();
		}
		
		
		
        function changevisi(id) {
            if (document.getElementById(id).style.display == "none")
            {document.getElementById(id).style.display = "block"; }
            else {document.getElementById(id).style.display = "none";}
        }


