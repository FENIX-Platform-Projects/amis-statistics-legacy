/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.sldeditor.client.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.sldeditor.common.vo.Interval;

public class SLDEditorColorRampMaker {
	
	public static Map<String, String> ramp(String min, String max, String minColor, String maxColor, int intervals) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		List<String> colors = ramp(minColor, maxColor, intervals);
		double step = (Double.valueOf(max) - Double.valueOf(min)) / intervals;
		double value = step;
		for (int i = 0 ; i < intervals - 1 ; i++) {
			m.put(String.valueOf(value), colors.get(i));
			value += step;
		}
		m.put(String.valueOf(max), colors.get(colors.size() - 1));
		return m;
	}
	
	public static Map<String, String> ramp(String min, String max, String minColor, String maxColor, int intervals, int algorithm) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		List<String> colors = ramp(minColor, maxColor, intervals);
		if(algorithm == 0)
		{
			m = rampEqualInterval(min, max, minColor, maxColor, intervals, colors, m);
		}
		else if(algorithm == 1)
		{
			m = rampEqualArea(min, max, minColor, maxColor, intervals, colors, m);
		}
		return m;
	}
	

	public static List<Interval> rampCreateIntervals(String min, String max, String minColor, String maxColor, int intervals, 
			final int algorithm, List<Interval> intervalList) {
		List<Interval> m;
		if(intervalList == null)
		{
			m = new ArrayList<Interval>();
		}
		else
		{
			m = intervalList;
		}
		List<String> colors = ramp(minColor, maxColor, intervals);
		if(algorithm == 0)
		{
			m = rampEqualIntervalList(min, max, minColor, maxColor, intervals, colors, m);
		}
		else if(algorithm == 1)
		{
			m = rampEqualAreaList(min, max, minColor, maxColor, intervals, colors, m);
		}
		return m;
	}

	private static Map<String, String> rampEqualArea(String min, String max,
			String minColor, String maxColor, int intervals, List<String> colors, Map<String, String> m) {
		
		double step = (Double.valueOf(max) - Double.valueOf(min)) / intervals;
		m.put(String.valueOf(min), colors.get(0));
		double value = step + Double.valueOf(min);
		for (int i = 0 ; i < intervals - 1 ; i++) {
			m.put(String.valueOf(value), colors.get(i));
			value += step;
		}
		m.put(String.valueOf(max), colors.get(colors.size() - 1));
		return m;
	}

	private static Map<String, String> rampEqualInterval(String min,
			String max, String minColor, String maxColor, int intervals,
			List<String> colors, Map<String, String> m) {
		
		double step = (Double.valueOf(max) - Double.valueOf(min)) / intervals;
		m.put(String.valueOf(min), colors.get(0));
		double value = step + Double.valueOf(min);
		for (int i = 0 ; i < intervals - 1 ; i++) {
			m.put(String.valueOf(value), colors.get(i));
			value += step;
		}
		m.put(String.valueOf(max), colors.get(colors.size() - 1));
		return m;
	}

	private static List<Interval> rampEqualAreaList(String min, String max,
			String minColor, String maxColor, int intervals, List<String> colors, List<Interval> m) {
		double minDouble = Double.valueOf(min);
		double step = (Double.valueOf(max) - minDouble) / intervals;
		double minInterval = minDouble; 
		double maxInterval = minInterval + step;
	
		for (int i = 0 ; i < intervals ; i++) {
			Interval interval = new Interval(minInterval,maxInterval,colors.get(i));
			m.add(interval);
			minInterval += step;
			maxInterval = minInterval+ step;
		}
		return m;
	}

	private static List<Interval> rampEqualIntervalList(String min,
			String max, String minColor, String maxColor, int intervals,
			List<String> colors, List<Interval> m) {
		
		double minDouble = Double.valueOf(min);
		double step = (Double.valueOf(max) - minDouble) / intervals;
		double minInterval = minDouble; 
		double maxInterval = minInterval + step;
	
		for (int i = 0 ; i < intervals ; i++) {
			Interval interval = new Interval(minInterval,maxInterval,colors.get(i));
			m.add(interval);
			minInterval += step;
			maxInterval = minInterval+ step;
		}
		return m;
	}

	
	private static List<String> ramp(String minColor, String maxColor, int intervals) {
		
		List<String> colors = new ArrayList<String>();
		
		int min_red = Integer.parseInt(getRed(minColor), 16);
		int max_red = Integer.parseInt(getRed(maxColor), 16);
		int delta_red = max_red - min_red;
		
		int min_green = Integer.parseInt(getGreen(minColor), 16);
		int max_green = Integer.parseInt(getGreen(maxColor), 16);
		int delta_green = max_green - min_green;
		
		int min_blue = Integer.parseInt(getBlue(minColor), 16);
		int max_blue = Integer.parseInt(getBlue(maxColor), 16);
		int delta_blue = max_blue - min_blue;
		
		for (int i = 0 ; i < intervals - 1 ; i++) {
			int red = min_red + delta_red * i / intervals;
			int green = min_green + delta_green * i / intervals;
			int blue = min_blue + delta_blue * i / intervals;
			String color = Integer.toHexString(red).toUpperCase() + Integer.toHexString(green).toUpperCase() + Integer.toHexString(blue).toUpperCase();
			colors.add(color);
		}
		if(maxColor.startsWith("#"))
			maxColor = maxColor.substring(1);
		
		colors.add(maxColor);
		
		return colors;
	}
	
	private static String getRed(String color) {
		if (color.startsWith("#"))
			return color.substring(1, 3);
		else 
			return color.substring(0, 2);
	}
	
	private static String getGreen(String color) {
		if (color.startsWith("#"))
			return color.substring(3, 5);
		else 
			return color.substring(2, 4);
	}
	
	private static String getBlue(String color) {
		if (color.startsWith("#"))
			return color.substring(5);
		else 
			return color.substring(4);
	}
	
}
