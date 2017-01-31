package org.fao.fenix.web.modules.ipc.client.view.viewer;

public class IPCViewHeaderBuilder {

	public static String buildTabOneHeaders() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td colspan='5'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>Area of Analysis </b>(Region, District or Livelihood Zone):");
		sb.append("</td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Reference Outcomes</b><br>(As defined by IPC Reference Table)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Direct and Indirect Evidence For Phase In Given Time Period</b><br>(As defined by IPC Reference Table)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Projected Phase for Time Period</b><br>(As defined by IPC Reference Table)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Evidence of Risk for Worsening Phase or Magnitude</b><br>(As defined by IPC Reference Table)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Risk Level</b><br>(As defined by IPC Reference Table)</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	public static String buildTabTwoHeaders() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td colspan='8'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>Area of Analysis </b>(Region, District or Livelihood Zone):");
		sb.append("</td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td colspan='6' align='center'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>ANALYSIS</b>");
		sb.append("</td>");
		sb.append("<td colspan='2' align='center'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>ACTION</b>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Current or Imminent Phase</b><br>(Circle or Bold Phase from Part 1)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Immediate Hazards</b><br>(Driving Forces)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Direct Food Security Problem</b><br>(Access, Availability, and/or Utilization)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Effect on Livelihood Strategies</b><br>(Summary Statement)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Population Affected</b><br>(Characteristics, Percent, and Total Estimate)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Projected Trend</b><br>(Improving, No Change, Worsening, Mixed Signals)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Risk Factors to Monitor</b><br></div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Opportunities for Response</b><br>(To Immediately Improve Food Access)</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	public static String buildTabThreeHeaders() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td colspan='5'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>Area of Analysis </b>(Region, District or Livelihood Zone):");
		sb.append("</td>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td colspan='4' align='center'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>ANALYSIS</b>");
		sb.append("</td>");
		sb.append("<td colspan='1' align='center'>");
		sb.append("<div style='background-color: #1D4589; color: white; font-family: sans-serif; font-size: 10pt;'>");
		sb.append("<b>ACTION</b>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Current or Imminent Phase</b><br>(Circle or Bold Phase From Part 1)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Underlying Causes</b><br>(Environmental, Degradation, Social, Poor Governance, Marginalization, etc.)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Effect on Livelihood Assets</b><br>(Summary Statements)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Projected Trend</b><br>(Improving, No Change, Worsening, Mixed Signalss)</div></td>");
		sb.append("<td align='center'><div style='font-family: sans-serif; font-size: 10pt;'><b>Opportunities to Support Livelihoods and Address Underlying Causes</b><br>(Policy, Programmes and/or Advocacy)</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

}