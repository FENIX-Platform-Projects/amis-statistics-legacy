/**
 * Point SLD generation
 */
function generatePointSLD()
{
	propertyName = document.getElementById('propertyName').value;
	labelColor = document.getElementById('labelColor').value;
	fillColor = document.getElementById('fillColor').value;
	fillOpacity = document.getElementById('fillOpacity').value;
	lineColor = document.getElementById('lineColor').value;
	lineOpacity = document.getElementById('lineOpacity').value;
	pointSize = document.getElementById('pointSize').value;
	pointShape = document.getElementById('pointShape').value;

	if (labelColor.length == 6)
		labelColor = '#'+labelColor;
	if (fillColor.length == 6)
		fillColor = '#'+fillColor;

	var font;
	var halo;

	// check values to make sure they are in range and valid
	if ((propertyName != null && propertyName != "" && propertyName != "none") && (labelColor == null || labelColor == "") )
		return "ERROR: label name specified, but no text color specified.";
	if ((labelColor != null && labelColor != "") && labelColor.length != 7)
		return "ERROR: Label color must be 7 characters long in hexadecimal (#00ff23).";
	if (fillColor == null || fillColor == "")
		return "ERROR: Point color cannot be empty";
	if (fillColor.length != 7)
		return "ERROR: Point color must be 7 characters long in hexadecimal (#00ff23).";
	if (fillOpacity == null || fillOpacity == "")
		return "ERROR: Point opacity cannot be empty";
	if (fillOpacity < 0.0 || fillOpacity > 1.0)
		return "ERROR: Point opacity must be between 0.0 and 1.0";
	if (lineColor != null && lineColor != "" && lineColor.length != 7)
		return "ERROR: Line color must be 7 characters long in hexadecimal (#00ff23).";
	if (lineOpacity != null && lineOpacity != "" && (lineOpacity < 0.0 || lineOpacity > 1.0))
		return "ERROR: Line opacity must be between 0.0 and 1.0";
	if (pointSize == null || pointSize == "")
		return "ERROR: Point size cannot be empty";


	// create stroke
	var stroke;
	if (lineColor != null && lineColor != "" && lineOpacity != null && lineOpacity != "")
		stroke = createStroke(lineColor, lineOpacity);

	pointFill = createFill(fillColor, fillOpacity);
	graphicMark = createGraphic(pointShape, pointFill, stroke, pointSize, 1.0);

	SLDr  = '<Rule>';
	SLDr += createSLDPointSymbolizer(graphicMark);

	textFont = createFont("Times New Roman", "Normal", 12);

	textFill = createFill(labelColor, 1.0);

	if (propertyName != null && propertyName != "none")
		SLDr += createTextSymbolizer(propertyName, textFont, halo, textFill);

	SLDr += '</Rule>';

	return SLDr;
}

/**
 * Line SLD generation
 */
function generateLineSLD()
{
	propertyName = document.getElementById('propertyName').value;
	labelColor = document.getElementById('labelColor').value;
	lineColor = document.getElementById('lineColor').value;
	lineOpacity = document.getElementById('lineOpacity').value;
	lineWidth = document.getElementById('lineWidth').value;

	if (labelColor.length == 6)
		labelColor = '#'+labelColor;
	if (lineColor.length == 6)
		lineColor = '#'+lineColor;

	var halo;

	// check values to make sure they are in range and valid
	if ((propertyName != null && propertyName != "" && propertyName != "none") && (labelColor == null || labelColor == "") )
		return "ERROR: label name specified, but no text color specified.";
	if ((labelColor != null && labelColor != "") && labelColor.length != 7)
		return "ERROR: Label color must be 7 characters long in hexadecimal (#00ff23).";
	if (lineColor == null || lineColor == "")
		return "ERROR: Line color cannot be empty";
	if (lineColor.length != 7)
		return "ERROR: Line color must be 7 characters long in hexadecimal (#00ff23).";
	if (lineOpacity == null || lineOpacity == "")
		return "ERROR: Line opacity cannot be empty";
	if (lineOpacity < 0.0 || lineOpacity > 1.0)
		return "ERROR: Line opacity must be between 0.0 and 1.0";
	if (lineWidth == null || lineWidth == "")
		return "ERROR: Line width cannot be empty";
	if (lineWidth < 0)
		return "ERROR: Line width must be a positive number";


	// create stroke
	stroke = createStroke(lineColor, lineOpacity, lineWidth);
	
	SLDr  = '<Rule>';
	SLDr += createSLDLineSymbolizer(stroke);

	textFont = createFont("Times New Roman", "Normal", 12);

	textFill = createFill(labelColor, 1.0);

	if (propertyName != null && propertyName != "none")
		SLDr += createTextSymbolizer(propertyName, textFont, halo, textFill);

	SLDr += '</Rule>';

	return SLDr;
}

function generatePolygonSLD()
{
	//alert(quantizationRules[0].Filter.PropertyIsLessThanOrEqualTo.Literal);
	
	propertyName = document.getElementById('propertyName').value;
	labelColor = document.getElementById('labelColor').value;
	fillColor = document.getElementById('fillColor').value;
	fillOpacity = document.getElementById('fillOpacity').value;
	lineColor = document.getElementById('lineColor').value;
	lineOpacity = document.getElementById('lineOpacity').value;
	
	if (labelColor.length == 6)
		labelColor = '#'+labelColor;
	if (fillColor.length == 6)
		fillColor = '#'+fillColor;
	if (lineColor.length == 6)
		lineColor = '#'+lineColor;
	
	var halo;

	// check values to make sure they are in range and valid
	if ((propertyName != null && propertyName != "" && propertyName != "none") && (labelColor == null || labelColor == "") )
		return "ERROR: label name specified, but no text color specified.";
	if ((labelColor != null && labelColor != "") && labelColor.length != 7)
		return "ERROR: Label color must be 7 characters long in hexadecimal (#00ff23).";
		
	if (typeof quantizationRules != "undefined" && quantizationRules != null) {
		for(i=0;i<quantizationRules.length;i++) {
			fillColor = eval("document.getElementById('rule_color_"+i+"').value");
			if (fillColor == null || fillColor == "")
				return "ERROR: Polygon fill color on Rule-"+i+" cannot be empty";
			if (fillColor.length != 7)
				return "ERROR: Polygon fill color on Rule-"+i+" must be 7 characters long in hexadecimal (#00ff23).";
		}
	} else {
		if (fillColor == null || fillColor == "")
			return "ERROR: Polygon fill color cannot be empty";
		if (fillColor.length != 7)
			return "ERROR: Polygon fill color must be 7 characters long in hexadecimal (#00ff23).";
		if (fillOpacity == null || fillOpacity == "")
			return "ERROR: Polygon color opacity cannot be empty";
		if (fillOpacity < 0.0 || fillOpacity > 1.0)
			return "ERROR: Polygon fill opacity must be between 0.0 and 1.0";
	}
	if (lineColor == null || lineColor == "")
		return "ERROR: Polygon outline color cannot be empty";
	if (lineColor.length != 7)
		return "ERROR: Polygon outline color must be 7 characters long in hexadecimal (#00ff23).";
	if (lineOpacity == null || lineOpacity == "")
		return "ERROR: Polygon outline opacity cannot be empty";
	if (lineOpacity < 0.0 || lineOpacity > 1.0)
		return "ERROR: Polygon outline opacity must be between 0.0 and 1.0";

  SLDr  = "";
  
	if (typeof quantizationRules != "undefined" && quantizationRules != null) {
		for(i=0;i<quantizationRules.length;i++) {
			SLDr += '<Rule>';
			SLDr += createFilter(quantizationRules[i]);

			// create fill
			fillColor = document.getElementById('rule_color_'+i).value;
			if(typeof fillOpacity == "undefined")
				fillOpacity = 1.0;
			polygonFill = createFill(fillColor, fillOpacity);

			SLDr += createSLDPolygonSymbolizer(polygonFill, null);

			SLDr += '</Rule>';
		}
	}
  
	SLDr += '<Rule>';

  if (typeof quantizationRules == "undefined" || quantizationRules == null) {
		// create fill
		polygonFill = createFill(fillColor, fillOpacity);
		
		// create stroke
		stroke = createStroke(lineColor, lineOpacity);

		SLDr += createSLDPolygonSymbolizer(polygonFill, stroke);
  } else {
  	// create stroke
		stroke = createStroke(lineColor, lineOpacity);
  	SLDr += createSLDLineSymbolizer(stroke);
  }

	textFill = createFill(labelColor, 1.0);

	textFont = createFont("Times New Roman", "Normal", 12);

	if (propertyName != null && propertyName != "none")
		SLDr += createTextSymbolizer(propertyName, textFont, halo, textFill);

	SLDr += '</Rule>';
	
	//prompt("", SLDr);
	
	return SLDr;
}

function createSLDHeader(featureType)
{
	//log("Making sld for: "+featureType);
	XML  = '<?xml version="1.0" encoding="UTF-8"?>';
	XML += '<StyledLayerDescriptor version="1.0.0"';
	XML += ' xmlns:gml="http://www.opengis.net/gml"';
	XML += ' xmlns:ogc="http://www.opengis.net/ogc"';
	XML += ' xmlns="http://www.opengis.net/sld">';
	XML += '<NamedLayer>';
	XML += '<Name>'+featureType+'</Name>';
	XML += '<UserStyle>';
	XML += '<Name>'+featureType+'_style</Name>';
	XML += '<Title>geoserver style</Title>';
	XML += '<Abstract>Generated by GeoServer</Abstract>';
	XML += '<FeatureTypeStyle>';
	//log(XML);
	
	return XML;
}


function createSLDFooter()
{
	XML   = '</FeatureTypeStyle>';
	XML  += '</UserStyle>';
	XML  += '</NamedLayer>';
	XML  += '</StyledLayerDescriptor>';

	return XML;
}

function createSLDPointSymbolizer(graphic)
{
	XML  = '<PointSymbolizer>';
	XML += ''+graphic;
	XML += '</PointSymbolizer>';

	return XML;

}

function createSLDLineSymbolizer(stroke)
{
	XML  = '<LineSymbolizer>';
	XML += stroke;
	XML += '</LineSymbolizer>';

	return XML;
}

function createSLDPolygonSymbolizer(fill, stroke)
{
	XML  = '<PolygonSymbolizer>';
	XML += fill;
	
	if (stroke != null) {
		XML += stroke;
	}
	
	XML += '</PolygonSymbolizer>';

	return XML;
}

function createFill(color, opacity)
{
	// add # to front of 'color'
	if(color.charAt(0) != "#")
		color = "#"+color;

	XML  = '<Fill>';
	XML += '<CssParameter name="fill">'+color+'</CssParameter>';
	XML += '<CssParameter name="fill-opacity">'+opacity+'</CssParameter>';
	XML += '</Fill>';

	return XML;
}

function createStroke(color, opacity, width, linecap, linejoin, dasharray)
{
	// add # to front of 'color'
	if(color.charAt(0) != "#")
		color = "#"+color;

	XML  = '<Stroke>';
	if(color)	XML += '<CssParameter name="stroke">'+color+'</CssParameter>';
	if(opacity)	XML += '<CssParameter name="stroke-opacity">'+opacity+'</CssParameter>';
	if(width)	XML += '<CssParameter name="stroke-width">'+width+'</CssParameter>';
	if(linecap)	XML += '<CssParameter name="stroke-linecap">'+linecap+'</CssParameter>';
	if(linejoin)XML += '<CssParameter name="stroke-linejoin">'+linejoin+'</CssParameter>';
	if(dasharray)XML += '<CssParameter name="stroke-dasharray">'+dasharray+'</CssParameter>';
	XML += '</Stroke>';

	return XML;
}

// a note to figure out all possible line caps
function getLineCaps()
{

}

// a note to figure out all possible line joins
function getLineJoins()
{

}

function createGraphic(shape, fill, stroke, size, opacity)
{
	XML = '<Graphic>';
	XML += '<Mark>';
	XML += '<WellKnownName>'+shape+'</WellKnownName>';
	XML += fill;
	if(stroke) XML += stroke;
	XML += '</Mark>';
	XML += '<Opacity>'+opacity+'</Opacity>';
	XML += '<Size>'+size+'</Size>';
	XML += '</Graphic>';

	return XML;
}


function createTextSymbolizer(columnName, font, halo, fill)
{
	XML  = '<TextSymbolizer>';
	XML += '<Label>';
	XML += '<ogc:PropertyName>'+columnName+'</ogc:PropertyName>';
	XML += '</Label>';
	if(font) XML += font;
	if(halo) XML += halo;
	if(fill) XML += fill;
	XML += '</TextSymbolizer>';


	return XML;
}

function createFont(name, style, size, weight)
{
	XML  = '<Font>';
	XML += '<CssParameter name="font-family">'+name+'</CssParameter>';
	XML += '<CssParameter name="font-style">'+style+'</CssParameter>';
	XML += '<CssParameter name="font-size">'+size+'</CssParameter>';
	if (weight)XML += '<CssParameter name="font-weight">'+weight+'</CssParameter>';
	XML += '</Font>';

	return XML;
}

function createHalo(radius, fill)
{
	XML  = '<Halo>';
	XML += '<Radius>';
	XML += '<ogc:Literal>'+radius+'</ogc:Literal>';
	XML += '</Radius>';
	XML += fill;
	XML += '</Halo>';

	return XML;
}


function createMinScaleDenominator(scale)
{
	XML = '<MinScaleDenominator>'+scale+'</MinScaleDenominator>';

	return XML;
}

function createMaxScaleDenominator(scale)
{
	XML = '<MaxScaleDenominator>'+scale+'</MaxScaleDenominator>';

	return XML;
}

function createFilter(rule) 
{
	XML = '<ogc:Filter>';
	
	if (rule.Filter.And != undefined && rule.Filter.And != null) {
		XML += '<ogc:And>';
			if (rule.Filter.And.PropertyIsLessThan != undefined && rule.Filter.And.PropertyIsLessThan != null) {
				XML += '<ogc:PropertyIsLessThan>';
		    XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsLessThan.PropertyName+'</ogc:PropertyName>';
		    XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsLessThan.Literal+'</ogc:Literal>';
		    XML += '</ogc:PropertyIsLessThan>';
			}
		
			if (typeof rule.Filter.And.PropertyIsLessThanOrEqualTo != undefined && rule.Filter.And.PropertyIsLessThanOrEqualTo != null) {
				XML += '<ogc:PropertyIsLessThanOrEqualTo>';
		    XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsLessThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
		    XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsLessThanOrEqualTo.Literal+'</ogc:Literal>';
		    XML += '</ogc:PropertyIsLessThanOrEqualTo>';
			}
		
			if (typeof rule.Filter.And.PropertyIsGreaterThan != undefined && rule.Filter.And.PropertyIsGreaterThan != null) {
				XML += '<ogc:PropertyIsGreaterThan>';
		    XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsGreaterThan.PropertyName+'</ogc:PropertyName>';
		    XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsGreaterThan.Literal+'</ogc:Literal>';
		    XML += '</ogc:PropertyIsGreaterThan>';
			}
		
			if (typeof rule.Filter.And.PropertyIsGreaterThanOrEqualTo != undefined && rule.Filter.And.PropertyIsGreaterThanOrEqualTo != null) {
				XML += '<ogc:PropertyIsGreaterThanOrEqualTo>';
		    XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsGreaterThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
		    XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsGreaterThanOrEqualTo.Literal+'</ogc:Literal>';
		    XML += '</ogc:PropertyIsGreaterThanOrEqualTo>';
			}
		XML += '</ogc:And>';		
  }
  
	if (rule.Filter.PropertyIsLessThan != undefined && rule.Filter.PropertyIsLessThan != null) {
		XML += '<ogc:PropertyIsLessThan>';
    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsLessThan.PropertyName+'</ogc:PropertyName>';
    XML += '<ogc:Literal>'+rule.Filter.PropertyIsLessThan.Literal+'</ogc:Literal>';
    XML += '</ogc:PropertyIsLessThan>';
	}

	if (typeof rule.Filter.PropertyIsLessThanOrEqualTo != undefined && rule.Filter.PropertyIsLessThanOrEqualTo != null) {
		XML += '<ogc:PropertyIsLessThanOrEqualTo>';
    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsLessThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
    XML += '<ogc:Literal>'+rule.Filter.PropertyIsLessThanOrEqualTo.Literal+'</ogc:Literal>';
    XML += '</ogc:PropertyIsLessThanOrEqualTo>';
	}

	if (typeof rule.Filter.PropertyIsGreaterThan != undefined && rule.Filter.PropertyIsGreaterThan != null) {
		XML += '<ogc:PropertyIsGreaterThan>';
    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsGreaterThan.PropertyName+'</ogc:PropertyName>';
    XML += '<ogc:Literal>'+rule.Filter.PropertyIsGreaterThan.Literal+'</ogc:Literal>';
    XML += '</ogc:PropertyIsGreaterThan>';
	}

	if (typeof rule.Filter.PropertyIsGreaterThanOrEqualTo != undefined && rule.Filter.PropertyIsGreaterThanOrEqualTo != null) {
		XML += '<ogc:PropertyIsGreaterThanOrEqualTo>';
    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsGreaterThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
    XML += '<ogc:Literal>'+rule.Filter.PropertyIsGreaterThanOrEqualTo.Literal+'</ogc:Literal>';
    XML += '</ogc:PropertyIsGreaterThanOrEqualTo>';
	}

	XML += '</ogc:Filter>';

  return XML;
}