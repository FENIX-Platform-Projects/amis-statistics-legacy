package org.fao.fenix.web.modules.venn.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;

import ch.ethz.ssh2.log.Logger;

public class VennUtils {

	
	public static void calculateIntersections(List<String> a_dacCodes, List<String> b_dacCodes, List<String> c_dacCodes, VennBeanVO bean,  Boolean setDACCodes, Boolean setValues) {
		calculateNumeberOfIntersections(a_dacCodes, b_dacCodes, c_dacCodes, bean.getVennGraphBeanVO(), setDACCodes, setValues);
//		fillIntersectionsColors(bean);
	}

	public static void calculateNumeberOfIntersections(List<String> a_dacCodes, List<String> b_dacCodes, List<String> c_dacCodes, VennGraphBeanVO bean,  Boolean setDACCodes, Boolean setValues) {		
		Integer a = 0;
		Integer b = 0;
		Integer c = 0;
		Integer ab = 0;
		Integer ac = 0;
		Integer bc = 0;
		Integer abc = 0;
		List<String> ab_dacCodes = new ArrayList<String>();
		List<String> bc_dacCodes = new ArrayList<String>();
		List<String> ac_dacCodes = new ArrayList<String>();
		List<String> abc_dacCodes = new ArrayList<String>();
		
		
		List<String> remove = new ArrayList<String>();
		for(String a_code : a_dacCodes) {
			//System.out.println("a_code = "+a_code);
			if (b_dacCodes.contains(a_code) && c_dacCodes.contains(a_code)){
				abc++;
				abc_dacCodes.add(a_code);
				//System.out.println("ABC add = "+a_code);
				b_dacCodes.remove(a_code);
				c_dacCodes.remove(a_code);
				
				remove.add(a_code);
				
			}
			else if ( b_dacCodes.contains(a_code) ) {		
				ab++;
				ab_dacCodes.add(a_code);
				//System.out.println("AB add = "+a_code);
				b_dacCodes.remove(a_code);
				remove.add(a_code);		
							
			}
			else if ( c_dacCodes.contains(a_code) ) {
				ac++;
				ac_dacCodes.add(a_code);
				//System.out.println("AC add = "+a_code);
				c_dacCodes.remove(a_code);
				remove.add(a_code);
			}		
		}
		
		
		a_dacCodes.removeAll(remove);
		remove= new ArrayList<String>();
		
		for(String b_code : b_dacCodes) {
			if (c_dacCodes.contains(b_code)){
				bc++;
				bc_dacCodes.add(b_code);
				
				c_dacCodes.remove(b_code);
				remove.add(b_code);
			}
		}
		b_dacCodes.removeAll(remove);
		
		
		a = a_dacCodes.size();
		b = b_dacCodes.size();
		c = c_dacCodes.size();
		
		System.out.println("List:");
		System.out.println("a:" + a_dacCodes);
		System.out.println("b:" + b_dacCodes);
		System.out.println("c:" + c_dacCodes);
		System.out.println("ab:" + ab_dacCodes);
		System.out.println("bc:" + bc_dacCodes);
		System.out.println("ac:" + ac_dacCodes);
		System.out.println("abc:" + abc_dacCodes);
		System.out.println("\nvalues:");
		System.out.println("a:" + a);
		System.out.println("b:" + b);
		System.out.println("c:" + c);
		System.out.println("ab:" + ab);
		System.out.println("bc:" + bc);
		System.out.println("ac:" + ac);
		System.out.println("abc:" + abc);
		
		if ( setDACCodes) 
			setDacCodes(bean, a_dacCodes, b_dacCodes, c_dacCodes, ab_dacCodes, bc_dacCodes, ac_dacCodes, abc_dacCodes);
		else 
			setDacAggregatedCodes(bean, a_dacCodes, b_dacCodes, c_dacCodes, ab_dacCodes, bc_dacCodes, ac_dacCodes, abc_dacCodes);
		
		if( setValues )
			setValues(bean, a, b, c, ab, bc, ac, abc);

		
	}

	public static void calculateDACAggregations(List<String> a_dacCodes, List<String> b_dacCodes, List<String> c_dacCodes, VennBeanVO bean,  Boolean setDACCodes, Boolean setValues, Integer aggregationLevel) {
//		System.out.println("***************aggregationLevel!!: "+ aggregationLevel );
		
		
//		if ( aggregationLevel != 2) {
//			// filterDACCodes with the aggregation level
//			a_dacCodes = filterDACCodes(a_dacCodes, aggregationLevel);
//			b_dacCodes = filterDACCodes(b_dacCodes, aggregationLevel);
//			c_dacCodes = filterDACCodes(c_dacCodes, aggregationLevel);
//		}
		calculateNumeberOfIntersections(a_dacCodes, b_dacCodes, c_dacCodes, bean.getVennGraphBeanVO(), setDACCodes, setValues);
		
		
//		fillIntersectionsColors(bean);		
	}
	
//	private static List<String> filterDACCodes(List<String> dacCodes, Integer level) {
//		System.out.println("***************DAC CODES!!: "+ dacCodes );
//		System.out.println("LVL: "+ level);
//		HashMap<String, String> filteredCodes = new HashMap<String, String>();
//		List<String> result = new ArrayList<String>();
//		if ( !dacCodes.isEmpty()) {
//			for( String dacCode : dacCodes ) {
//				if ( level == 0)
//					filteredCodes.put(dacCode.substring(0, 2) + "000", "");
//				else if ( level == 1 )
//					filteredCodes.put(dacCode.substring(0, 3) + "00", "");
//				else
//					return dacCodes;
//			}
//	
//			for(String dacCode : filteredCodes.keySet())
//				result.add(dacCode);
//		}
//		
//		return result;
//	}
	
	private static void setDacCodes(VennGraphBeanVO bean, List<String> a_dacCodes, List<String> b_dacCodes, List<String> c_dacCodes, List<String> ab_dacCodes, List<String> bc_dacCodes, List<String> ac_dacCodes , List<String> abc_dacCodes) { 
		bean.getA().setDacCodes(a_dacCodes);
		
		bean.getB().setDacCodes(b_dacCodes);
		
		bean.getC().setDacCodes(c_dacCodes);
		
		bean.getAb().setDacCodes(ab_dacCodes);
		
		bean.getBc().setDacCodes(bc_dacCodes);
		
		bean.getAc().setDacCodes(ac_dacCodes);
		
		bean.getAbc().setDacCodes(abc_dacCodes);
	}
	
	private static void setDacAggregatedCodes(VennGraphBeanVO bean, List<String> a_dacCodes, List<String> b_dacCodes, List<String> c_dacCodes, List<String> ab_dacCodes, List<String> bc_dacCodes, List<String> ac_dacCodes , List<String> abc_dacCodes) { 
		bean.getA().setAggregatedDacCodes(a_dacCodes);
		
		bean.getB().setAggregatedDacCodes(b_dacCodes);
		
		bean.getC().setAggregatedDacCodes(c_dacCodes);
		
		bean.getAb().setAggregatedDacCodes(ab_dacCodes);
		
		bean.getBc().setAggregatedDacCodes(bc_dacCodes);
		
		bean.getAc().setAggregatedDacCodes(ac_dacCodes);
		
		bean.getAbc().setAggregatedDacCodes(abc_dacCodes);
	}
	
	private static void setValues(VennGraphBeanVO bean, Integer a,  Integer b, Integer c, Integer ab, Integer bc,  Integer ac,  Integer abc) {
		bean.getA().setValue(new Double(a));
		
		bean.getB().setValue(new Double(b));
		
		bean.getC().setValue(new Double(c));
		
		bean.getAb().setValue(new Double(ab));
		
		bean.getBc().setValue(new Double(bc));
		
		bean.getAc().setValue(new Double(ac));
		
		bean.getAbc().setValue(new Double(abc));
	}


	public static void fillIntersectionsColors(VennGraphBeanVO bean) {
		bean.getU().setColor(VennContants.selectedColor);
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		
		if ( bean.getA().getValue() != 0) {
//			bean.getA().setColor(VennContants.selectedColor);
			bean.getA().setCenterLabel(Integer.toString(bean.getA().getValue().intValue()));
			bean.getA().setIsIntersected(true);
			values.put("a", bean.getA().getValue().intValue());
		}
		else
			values.put("a", 0);
			
		
		if ( bean.getB().getValue() != 0) {
//			bean.getB().setColor(VennContants.selectedColor);
			bean.getB().setCenterLabel(Integer.toString(bean.getB().getValue().intValue()));
			bean.getB().setIsIntersected(true);
			values.put("b",bean.getB().getValue().intValue());
		}
		else
			values.put("b", 0);
		
		if ( bean.getC().getValue() != 0) {
//			bean.getC().setColor(VennContants.selectedColor);
			bean.getC().setCenterLabel(Integer.toString(bean.getC().getValue().intValue()));
			bean.getC().setIsIntersected(true);
			values.put("c", bean.getC().getValue().intValue());
		}
		else
			values.put("c", 0);
	
		if ( bean.getAb().getValue() != 0) {
//			bean.getAb().setColor(VennContants.selectedColor);
			bean.getAb().setCenterLabel(Integer.toString(bean.getAb().getValue().intValue()));
			bean.getAb().setIsIntersected(true);
			values.put("ab", bean.getAb().getValue().intValue());
		}
		else
			values.put("ab", 0);
		
		if ( bean.getBc().getValue() != 0) {
//			bean.getBc().setColor(VennContants.selectedColor);
			bean.getBc().setCenterLabel(Integer.toString(bean.getBc().getValue().intValue()));
			bean.getBc().setIsIntersected(true);
			values.put("bc", bean.getBc().getValue().intValue());
		}
		else
			values.put("bc", 0);
		
		if ( bean.getAc().getValue() != 0) {
//			bean.getAc().setColor(VennContants.selectedColor);
			bean.getAc().setCenterLabel(Integer.toString(bean.getAc().getValue().intValue()));
			bean.getAc().setIsIntersected(true);
			values.put("ac", bean.getAc().getValue().intValue());
		}
		else
			values.put("ac", 0);
		
		if ( bean.getAbc().getValue() != 0) {
//			bean.getAbc().setColor(VennContants.selectedColor);
			bean.getAbc().setCenterLabel(Integer.toString(bean.getAbc().getValue().intValue()));
			bean.getAbc().setIsIntersected(true);
			values.put("abc", bean.getAbc().getValue().intValue());
		}
		else
			values.put("abc", 0);
		
		
		List<List<String>> levels = sortByValues(values);
		setRampColors(bean, levels);
	}
	
	private static void setRampColors(VennGraphBeanVO vennBean, List<List<String>> levels) {
		System.out.println("setting colors: ");
		
		int surplus = 0;
		for(int i=0; i < levels.size(); i++) {
			for(String value : levels.get(i)) {
				System.out.println("i: " + i );
				if ( value.equals("a"))
//					vennBean.getA().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getA().setColor(VennContants.selectedColorRamp[7-i]);
				else if (value.equals("b")) {
//					vennBean.getB().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getB().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("b")) {
//					vennBean.getB().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getB().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("c")) {
//					vennBean.getC().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getC().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("ab")) {
//					vennBean.getAb().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getAb().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("ac")) {
//					vennBean.getAc().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getAc().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("bc")) {
//					vennBean.getBc().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getBc().setColor(VennContants.selectedColorRamp[7-i]);
				}
				else if (value.equals("abc")) {
//					vennBean.getAbc().setColor(VennContants.selectedColorRamp[i+surplus]);
					vennBean.getAbc().setColor(VennContants.selectedColorRamp[7-i]);
				}
			}
		}

		
	}
	
	private static List<List<String>> sortByValues(Map<String, Integer> in) {
		System.out.println("in: " + in);
		List<List<String>> levels = new ArrayList<List<String>>();
		LinkedHashMap<String, Integer> out = new LinkedHashMap<String, Integer>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());	
		List<Integer> mapValues = new ArrayList<Integer>(in.values());
		TreeSet<Integer> sortedSet = new TreeSet<Integer>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		
		System.out.println("mapKeys: " + mapKeys);
		System.out.println("mapValues: " + mapValues);
		System.out.println("sortedSet: " + sortedSet.toArray());
		int size = sortedArray.length;
		int lvl = 0;
		for (int i=size -1; i>=0; i--) {
			List<String> level = new ArrayList<String>();
			
			System.out.println("sortedArray: " + sortedArray[i]);
			Boolean check = false;
			if ( Integer.valueOf(sortedArray[i].toString()) != 0) {
				for(String value : in.keySet()){
					if ( sortedArray[i] == in.get(value)) {
						System.out.println("samevalue: " + sortedArray[i] + " | " + in.get(value) + " | " + value + " | " + lvl);
						check = true;
						level.add(value);
						// adding the lvl to vo...useful??
					}
				}
				if ( check ) {
					levels.add(level);
					lvl++;
				}
			}
			
		}
		System.out.println("levels: " + levels);
		System.out.println("out: " + out);
		return levels;
	}
	
	// this calculate for the venn digram the number of projects
	public static void calulateNumberOfProjects(VennCountryBeanVO vennBean) {
		for(VennProjectsBean intersaction : vennBean.getAllIntersections()) {
			if ( !intersaction.getProjectsRows().isEmpty() ) 
				intersaction.setValue(Double.valueOf(intersaction.getProjectsRows().size()));	
			else 
				intersaction.setValue(new Double(0));	
		}
		
//		fillIntersectionsColors(vennBean);
		
	}
	
	// method this calculate the number of DAC codes, with high level definition
	// calculate total and for each intersection
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calculateDonorsTotalAndintersections(VennCountryBeanVO vennBean, VennGraphBeanVO vennGraphBean) {
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> donors = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		 
		LinkedHashMap<String, Double> total = new LinkedHashMap<String, Double>();
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> intersectionsHM = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					LinkedHashMap<String, Double> intersectionHM = calcultateDonorIntersactionAndTotal(total, intersaction);
					intersectionsHM.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
//					donors.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
				}
			}
		}
	
		total = setXaxisEntries(total, 10);
		donors.put("Total", total);
		donors.putAll(intersectionsHM);
		
//		System.out.println("end total: " + total);		
//		System.out.println("end donors: " + donors);
		return donors;
	}
	
	// calculate for total
	public static HashMap<String, Double> calculateDonorsTotal(VennBeanVO vennBean) {		
		HashMap<String, Double> donors = new HashMap<String, Double>();
		
//		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
//		for (VennProjectsBean intersaction : intersactions) {
////			System.out.println("intersavction: " + intersaction.getLabel());
//			if (intersaction.getProjectsRows() != null) {
//				if (!intersaction.getProjectsRows().isEmpty())
//					calcultateDonorIntersaction(donors, intersaction);
//			}
//		}
		
//		System.out.println("end donors: " + donors);
		return donors;
	}
	
	private static LinkedHashMap<String, Double> calcultateDonorIntersactionAndTotal(LinkedHashMap<String, Double> donors,  VennProjectsBean intersaction) {
		LinkedHashMap<String, Double> intersectionHM = new LinkedHashMap<String, Double>();
		
		Integer quantityIdx = 0;
		Integer donorIdx = 0;
		
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity")) {
				quantityIdx = i;
			}
			else if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals("donor partner")) {
				donorIdx = i;
			}
		}		
//		LOGGER.info("donorIDX|quantityIDX->: " + donorIdx + " | " + quantityIdx);


		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
			try {
				Double valueT = Double.parseDouble(projectRow.get(quantityIdx));
				Double valueI = Double.parseDouble(projectRow.get(quantityIdx));
				
				String donor = projectRow.get(donorIdx);
				
				if( !donors.containsKey(donor)) {
					donors.put(donor, valueT);
				}
				else {
					Double v = donors.get(donor);
					valueT = v + valueT;
					donors.put(donor, valueT);
				}
				
				if( !intersectionHM.containsKey(donor)) {
					intersectionHM.put(donor, valueI);
				}
				else {
					Double v = intersectionHM.get(donor);
					valueI = v + valueI;
					intersectionHM.put(donor, valueI);
				}
				
				} catch (NumberFormatException nfe) {
				}

		}
		
		intersectionHM = setXaxisEntries(intersectionHM, 10);
		
		return intersectionHM;
	}
	
	
	
	
	private static void calcultateDonorIntersaction(HashMap<String, Double> donors,  VennIntersectionsBean intersaction) {
//		Integer quantityIdx = 0;
//		
//		// hardcoded for now
//		Integer donorIdx = 1;
//		
//		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
//			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
//				// the +1 is because the fist header column is the id of the row
//				quantityIdx = i + 1;
//		}
//
//		Double sum = new Double(0);
//		// content
//		for (List<String> projectRow : intersaction.getProjectsRows()) {
//			// there is the id as first cell value
//			String donor = new String();
//			for (int i = 1; i < projectRow.size(); i++) {
//				Double value = new Double(0);
//				
//				if (i == quantityIdx) {
//					try {
////						System.out.println("projectRow.get(i): " + projectRow.get(i));
//						value = Double.parseDouble(projectRow.get(i));
//						
//						if( donors.get(donor) == null) {
//							donors.put(donor, value);
//						}
//						else {
//							Double v = donors.get(donor);
//							value = v + value;
//							donors.put(donor, value);
//						}
//					} catch (NumberFormatException nfe) {
//					}
//				}
//				if ( i == donorIdx) {
////					System.out.println("DONOR: " + projectRow.get(i));
//					donor = projectRow.get(i);
//				}
//			}
////			System.out.println("donors: " + donors);
//		
//		}
	}
	
	
	
	public static HashMap<String, Double> calculateCategories(VennBeanVO vennBean) {
		HashMap<String, Double> categories = new HashMap<String, Double>();
//		
//		List<VennIntersectionsBean> intersactions = vennBean.getAllIntersections();
//		for (VennIntersectionsBean intersaction : intersactions) {
////			System.out.println("intersavction: " + intersaction.getLabel());
//			if (intersaction.getProjectsRawRows() != null) {
//				if (!intersaction.getProjectsRawRows().isEmpty()) {
//					calcultateCategoryIntersaction(categories, intersaction);
//				} 
//			}
//		}
////		System.out.println("end categories: " + categories);
		return categories;
	}
	
	
	private static void calcultateCategoryIntersaction(HashMap<String, Double> hashMap,  VennIntersectionsBean intersaction) {
//		Integer quantityIdx = 0;
//		
//		// hardcoded for now
//		Integer dacIdx = 3;
//		
//		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
//			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
//				// the +1 is because the fist header column is the id of the row
//				quantityIdx = i + 1;
//		}
//
//		Double sum = new Double(0);
//		// content
//		for (List<String> projectRawRow : intersaction.getProjectsRawRows()) {
//			// there is the id as first cell value
//			String category = new String();
//			for (int i = 1; i < projectRawRow.size(); i++) {
//				Double value = new Double(0);
//				
//				if (i == quantityIdx) {
//					try {
////						System.out.println("projectRow.get(i): " + projectRawRow.get(i));
//						value = Double.parseDouble(projectRawRow.get(i));
//						
//						if( hashMap.get(category) == null) {
//							hashMap.put(category, value);
//						}
//						else {
//							Double v = hashMap.get(category);
//							value = v + value;
//							hashMap.put(category, value);
//						}
//					} catch (NumberFormatException nfe) {
//					}
//				}
//				if ( i == dacIdx) {
////					System.out.println("DAC: " + projectRawRow.get(i));
//					category = projectRawRow.get(i).substring(0, 2);
//				}
//			}
////			System.out.println("category: " + category);
//		
//		}
	}
	
	// the key of the first hashmap is the donor
	// the second hashmap contains the different funding modality based on the donor
	/*
	public static HashMap<String, HashMap<String, Double>> calculateDonorsAndFunding(VennBeanVO vennBean) {
		HashMap<String, HashMap<String, Double>> donors = new HashMap<String, HashMap<String,Double>>();
		
		List<VennIntersectionsBean> intersactions = vennBean.getAllIntersections();
		for (VennIntersectionsBean intersaction : intersactions) {
//			System.out.println("intersavction: " + intersaction.getLabel());
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty())
					calcultateDonorAndFundingIntersaction(donors, intersaction);
			}
		}
		
//		System.out.println("end donors: " + donors);
		return donors;
	}
	
	
	private static void calcultateDonorAndFundingIntersaction(HashMap<String, HashMap<String, Double>> donors,  VennIntersectionsBean intersaction) {
		Integer quantityIdx = 0;
		
		// hardcoded for now
		Integer donorIdx = 1;
		Integer fundingModalityIdx = 7;
		
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
				// the +1 is because the fist header column is the id of the row
				quantityIdx = i + 1;
		}

		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
			// there is the id as first cell value
			try {
				String donor = projectRow.get(donorIdx);
				
				Double value = Double.valueOf(projectRow.get(quantityIdx));
				
				String fundingModality = projectRow.get(fundingModalityIdx);
				
				// create hashMap with donors and fundings
				if( donors.get(donor) == null) {
					HashMap<String, Double> fundingModalityHM = new HashMap<String, Double>();
					fundingModalityHM.put(fundingModality, value);
					donors.put(donor, fundingModalityHM);
				}
				else {
					if( donors.get(donor).get(fundingModality) == null) {
						donors.get(donor).put(fundingModality, value);
					}
					else {
						Double v =  donors.get(donor).get(fundingModality);
						value = v + value;						
						donors.get(donor).put(fundingModality, value);
					}
				}
			} catch (NumberFormatException nfe) {
			}
			
//			System.out.println("donors: " + donors);
		}
	}
	*/
	
	
	// the second hashmap contains the different funding modality based on the donor
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calculateDonorsAndFunding(VennCountryBeanVO vennBean) {
		LinkedHashMap<String, LinkedHashMap<String, Double>> donors = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		LinkedHashMap<String, Double> totalHM = new LinkedHashMap<String, Double>();
		 
		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
//			System.out.println("intersavction: " + intersaction.getLabel());
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty())
					calcultateDonorAndFundingIntersaction(donors, totalHM, intersaction);
			}
		}
		
		totalHM = setXaxisEntries(totalHM, 10);
		
		donors.put("Total", totalHM);
//		System.out.println("end donors: " + donors);
		return donors;
	}
	
	
	private static void calcultateDonorAndFundingIntersaction(LinkedHashMap<String, LinkedHashMap<String, Double>> donors, LinkedHashMap<String, Double> totalHM, VennProjectsBean intersaction) {
//		
//		
		Integer quantityIdx = 0;
		
		// hardcoded for now
		Integer donorIdx = 1;
		Integer fundingModalityIdx = 7;
		
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
				// the +1 is because the fist header column is the id of the row
				quantityIdx = i + 1;
		}

		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
			// there is the id as first cell value
			try {
				String donor = projectRow.get(donorIdx);
				
				Double value = Double.valueOf(projectRow.get(quantityIdx));
				Double valueT = Double.valueOf(projectRow.get(quantityIdx));
				
				String fundingModality = projectRow.get(fundingModalityIdx);
				
				// create hashMap with donors and fundings
				if( donors.get(donor) == null) {
					LinkedHashMap<String, Double> fundingModalityHM = new LinkedHashMap<String, Double>();
					fundingModalityHM.put(fundingModality, value);
					donors.put(donor, fundingModalityHM);
				}
				else {
					if( donors.get(donor).get(fundingModality) == null) {
						donors.get(donor).put(fundingModality, value);
					}
					else {
						Double v =  donors.get(donor).get(fundingModality);
						value = v + value;						
						donors.get(donor).put(fundingModality, value);
					}
				}
				
				if ( !totalHM.containsKey(fundingModality) ) {
					totalHM.put(fundingModality, valueT);
				}
				else {
					Double v =  totalHM.get(fundingModality);
					valueT = v + valueT;						
					totalHM.put(fundingModality, valueT);
				}
				
			} catch (NumberFormatException nfe) {
			}
			
//			System.out.println("donors: " + donors);
		}
	}
	
	
	
	// calculate total and for each intersection
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calculateReciepientTotalAndintersections(VennCountryBeanVO vennBean) {
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> donors = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		 
		LinkedHashMap<String, Double> total = new LinkedHashMap<String, Double>();

		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					LinkedHashMap<String, Double> intersectionHM = calcultateReciepientTotalAndintersections(total, intersaction);
					donors.put(intersaction.getIntersectionName(), intersectionHM);
//					intersections.add(intersectionHM);
				}
			}
		}
	
		donors.put("Total", total);
		System.out.println("RECIEPIENT end total: " + total);
		
		System.out.println("RECIEPIENT end all: " + donors);
		return donors;
	}
	
	private static LinkedHashMap<String, Double> calcultateReciepientTotalAndintersections(LinkedHashMap<String, Double> totalHM,  VennProjectsBean intersaction) {
		LinkedHashMap<String, Double> intersectionHM = new LinkedHashMap<String, Double>();
//		
		Integer quantityIdx = 0;
		
		Integer reciepientIdx = 8;
		
		
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {
//			System.out.println(intersaction.getHeaders().get(i).getHeader() + " | " + intersaction.getHeaders().get(i).getDataType());
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
				// the +1 is because the fist header column is the id of the row
				quantityIdx = i + 1;
		}

//		System.out.println("REPIENT IDX " + reciepientIdx);
//		System.out.println("quantityIdx IDX " + quantityIdx);

		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
			// there is the id as first cell value
//			System.out.println("quantityIdx v  " + projectRow.get(quantityIdx));
//			System.out.println("recipe v  " + projectRow.get(reciepientIdx));
			try {
				Double valueT = Double.parseDouble(projectRow.get(quantityIdx));
				Double valueI = Double.parseDouble(projectRow.get(quantityIdx));
				String indicator = projectRow.get(reciepientIdx);
				
				
				
				if( !totalHM.containsKey(indicator)) {
					totalHM.put(indicator, valueT);
				}
				else {
					Double v = totalHM.get(indicator);
					valueT = v + valueT;
					totalHM.put(indicator, valueT);
				}
				
				if( !intersectionHM.containsKey(indicator)) {
					intersectionHM.put(indicator, valueI);
				}
				else {
					Double v = intersectionHM.get(indicator);
					valueI = v + valueI;
					intersectionHM.put(indicator, valueI);
				}
			} catch (NumberFormatException nfe) {
				}
			
		
		}
//		
		return intersectionHM;
	}
	
	
	// calculate total and for each intersection
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calcultateCategoriesTotalAndintersections(VennCountryBeanVO vennBean, VennGraphBeanVO vennGraphBean, CodecDao codecDao) {

		LinkedHashMap<String, LinkedHashMap<String, Double>> result = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		 
		LinkedHashMap<String, Double> total = new LinkedHashMap<String, Double>();
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> intersectionsHM = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
//					System.out.println("-> " + intersaction.getProjectsRows().size());
					LinkedHashMap<String, Double> intersectionHM = calcultateCategoriesTotalAndintersections(total, intersaction, codecDao);
					intersectionsHM.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
//					donors.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
				}
			}
		}
	
		total = VennUtils.setXaxisEntries(total, 10);
		result.put("Total", total);
		result.putAll(intersectionsHM);
//		
//		System.out.println("end total: " + total);		
//		System.out.println("end donors: " + donors);
		return result;
	}
	
	
	
	
	private static LinkedHashMap<String, Double> calcultateCategoriesTotalAndintersections(LinkedHashMap<String, Double> totalHM,  VennProjectsBean intersaction, CodecDao codecDao) {

		
		LinkedHashMap<String, Double> intersectionHM = new LinkedHashMap<String, Double>();
		
		Integer quantityIdx = 0;
		
		Integer dacIdx = 0;
		

		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity")) {
				quantityIdx = i;
			}
			else if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals("sector code")) {
				dacIdx = i;
			}
		}		
	

		Double sum = new Double(0);
		
		
		// content
		for (List<String> projectRawRow : intersaction.getProjectsRawRows()) {
			// there is the id as first cell value
			String category = new String();
			for (int i = 1; i < projectRawRow.size(); i++) {
				Double valueT = new Double(0);
				Double valueI = new Double(0);
				
				if (i == quantityIdx) {
					try {
//						System.out.println("projectRow.get(i): " + projectRawRow.get(i));
						valueT = Double.parseDouble(projectRawRow.get(i));
						valueI = Double.parseDouble(projectRawRow.get(i));
						
						if( totalHM.get(category) == null) {
							totalHM.put(category, valueT);
						}
						else {
							Double v = totalHM.get(category);
							valueT = v + valueT;
							totalHM.put(category, valueT);
						}
						
						if( !intersectionHM.containsKey(category)) {
							intersectionHM.put(category, valueI);
						}
						else {
							Double v = intersectionHM.get(category);
							valueI = v + valueI;
							intersectionHM.put(category, valueI);
						}
						
					} catch (NumberFormatException nfe) {
					}
				}
				if ( i == dacIdx) {
					category = codecDao.getLabelFromCodeCodingSystem(projectRawRow.get(i).substring(0, 2) + "000", "DAC", "0", "EN");
					if ( category.length() > 21 )
						category = category.substring(0, 20);
				}
			}
		}
		
		intersectionHM = setXaxisEntries(intersectionHM, 10);
		
		return intersectionHM;
	}
	
	
	// calculate total and for each intersection
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calculateNumberOfProjectsByCategory(VennCountryBeanVO vennBean, VennGraphBeanVO vennGraphBean, CodecDao codecDao) {

		LinkedHashMap<String, LinkedHashMap<String, Double>> result = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		 
		LinkedHashMap<String, Double> total = new LinkedHashMap<String, Double>();
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> intersectionsHM = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					LinkedHashMap<String, Double> intersectionHM = calculateNumberOfProjectsByCategory(total, intersaction, codecDao);
					intersectionsHM.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
//					donors.put(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel(), intersectionHM);
				}
			}
		}
	
//		total = VennUtils.setXaxisEntries(total, 10);
		result.put("Total", total);
		result.putAll(intersectionsHM);
		
		System.out.println("result: " + result);
		
		return result;
	}
	
	
	private static LinkedHashMap<String, Double> calculateNumberOfProjectsByCategory(LinkedHashMap<String, Double> totalHM,  VennProjectsBean intersaction, CodecDao codecDao) {

		
		LinkedHashMap<String, Double> intersectionHM = new LinkedHashMap<String, Double>();
	
		Integer dacIdx = 0;
		

		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals("sector code")) {
				dacIdx = i;
			}
		}		
	

		Double count = new Double(1);
		
		
		// content
		for (List<String> projectRawRow : intersaction.getProjectsRawRows()) {
			// there is the id as first cell value
			String category = codecDao.getLabelFromCodeCodingSystem(projectRawRow.get(dacIdx).substring(0, 2) + "000", "DAC", "0", "EN");
			if ( category.length() > 21 )
				category = category.substring(0, 20);
					
			try {
	
				if( !totalHM.containsKey(category)) {
					totalHM.put(category, count);
				}
				else {
					Double v = totalHM.get(category);
					v = v + 1;
					totalHM.put(category, v);
				}
				
				if( !intersectionHM.containsKey(category)) {
					intersectionHM.put(category, count);
				}
				else {
					Double v = intersectionHM.get(category);
					v = v + 1;
					intersectionHM.put(category, v);
				}
		
			} catch (NumberFormatException nfe) {
			}
		}
		
		
		// content
//		for (List<String> projectRawRow : intersaction.getProjectsRawRows()) {
//			// there is the id as first cell value
//			String category = new String();
//			for (int i = 1; i < projectRawRow.size(); i++) {
////				Double valueT = new Double(0);
////				Double valueI = new Double(0);
//				
//				if (i == quantityIdx) {
//					try {
////						System.out.println("projectRow.get(i): " + projectRawRow.get(i));
////						valueT = Double.parseDouble(projectRawRow.get(i));
////						valueI = Double.parseDouble(projectRawRow.get(i));
//						
//						if( totalHM.get(category) == null) {
//							totalHM.put(category, count);
//						}
//						else {
//							Double v = totalHM.get(category);
//							v = v + 1;
//							totalHM.put(category, v);
//						}
//						
//						if( !intersectionHM.containsKey(category)) {
//							intersectionHM.put(category, count);
//						}
//						else {
//							Double v = intersectionHM.get(category);
//							v = v + 1;
//							intersectionHM.put(category, v);
//						}
//						
//					} catch (NumberFormatException nfe) {
//					}
//				}
//				if ( i == dacIdx) {
//					category = codecDao.getLabelFromCodeCodingSystem(projectRawRow.get(i).substring(0, 2) + "000", "DAC", "0", "EN");
//					if ( category.length() > 21 )
//						category = category.substring(0, 20);
//				}
//			}
//		}
		
		
		System.out.println("");
		return intersectionHM;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> calculateDonorsByCategory(VennCountryBeanVO vennBean, VennGraphBeanVO vennGraphBean, CodecDao codecDao) {

		LinkedHashMap<String, LinkedHashMap<String, Double>> result = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		List<VennProjectsBean> intersactions = vennBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					calculateDonorsByCategory(result, intersaction, codecDao);
				}
			}
		}
		
		// for each one call setXaxis entries?
		for (String key : result.keySet()) {
			LinkedHashMap<String, Double> hashMap = setXaxisEntries(result.get(key), 10);
			result.put(key, hashMap);
		}
		return result;
	}
	
	private static void calculateDonorsByCategory(LinkedHashMap<String, LinkedHashMap<String, Double>> result,  VennProjectsBean intersaction, CodecDao codecDao) {

		
		
		
		Integer quantityIdx = 0;
		
		Integer dacIdx = 0;
		
		Integer donorIdx = 0;
		

		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity")) {
				quantityIdx = i;
			}
			else if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals("sector code")) {
				dacIdx = i;
			}
			else if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals("donor partner")) {
				donorIdx = i;
			}
		}		
		
		
		
		// content
		for (List<String> projectRawRow : intersaction.getProjectsRawRows()) {
			// there is the id as first cell value
			String category = codecDao.getLabelFromCodeCodingSystem(projectRawRow.get(dacIdx).substring(0, 2) + "000", "DAC", "0", "EN");
			if ( category.length() > 21 )
				category = category.substring(0, 20);
			
			try {
			
				Double value = Double.parseDouble(projectRawRow.get(quantityIdx));
				String donor = projectRawRow.get(donorIdx);

				// if the category is not contained 
				if( !result.containsKey(category)) {
					LinkedHashMap<String, Double> donorHM = new LinkedHashMap<String, Double>();
					donorHM.put(donor, value);
					result.put(category, donorHM);
				}
				
				// if the category exist
				else {
					// check if the donor already exist
					if( result.get(category).containsKey(donor)) {
						// update existing donor (value)
						Double v = result.get(category).get(donor);
						value = v + value;
					}
					result.get(category).put(donor, value);		
				}
		
			} catch (NumberFormatException nfe) {
			}
		}

		
		
		
	}
	
	
	public static LinkedHashMap<String, Double> setXaxisEntries(LinkedHashMap<String, Double> hashMap, int maxXentry) {
		// if the entries in the hashmap are less than maxXentry 
		
		hashMap = sortByValues(hashMap);
		
//		System.out.println("maxXentry: " + maxXentry);
//		System.out.println("hashMap.size(): " + hashMap.size());
		
		if ( maxXentry > hashMap.size()) {
			return hashMap;
		} 
		
		// if the entries in the hashmap are more than maxXentry 
		else {
			LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
			Double total = new Double(0);
			
			int i=0;
			for(String key : hashMap.keySet()) {
				if ( i < maxXentry) 
					result.put(key, hashMap.get(key));
				else {
					total += hashMap.get(key);
				}
				i++;
			}
			
			/** TODO: set multilanguage **/
			result.put("Others", total);

			return result;
		}
	}
	
	private static LinkedHashMap<String, Double> sortByValues(Map<String, Double> in) {
		LinkedHashMap<String, Double> out = new LinkedHashMap<String, Double>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<Double> mapValues = new ArrayList<Double>(in.values());
		TreeSet<Double> sortedSet = new TreeSet<Double>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
//		for (int i=0; i<size; i++) 
//			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), Double.valueOf(sortedArray[i].toString()));
		for (int i=size-1; i>0; i--) 
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), Double.valueOf(sortedArray[i].toString()));
		return out;
	}
	
	
	
	
	

}
