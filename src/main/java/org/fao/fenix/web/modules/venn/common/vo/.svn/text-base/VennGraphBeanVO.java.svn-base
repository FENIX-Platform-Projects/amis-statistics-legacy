package org.fao.fenix.web.modules.venn.common.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennGraphBeanVO implements IsSerializable {
	
	/** this is used for the report to retrieve the venn images**/
	// venn type, venn image path
	private HashMap<String, String> vennImages = new HashMap<String, String>();
	
	
	private VennIntersectionsBean a = new VennIntersectionsBean("a");
	
	private VennIntersectionsBean b = new VennIntersectionsBean("b");
	
	private VennIntersectionsBean c = new VennIntersectionsBean("c");
	
	private VennIntersectionsBean ab = new VennIntersectionsBean("ab");
	
	private VennIntersectionsBean bc = new VennIntersectionsBean("bc");
	
	private VennIntersectionsBean ac = new VennIntersectionsBean("ac");
	
	private VennIntersectionsBean abc = new VennIntersectionsBean("abc");
	
	private VennIntersectionsBean u = new VennIntersectionsBean("u");

	
	private HashMap<String, String> dacCodes = new HashMap<String, String>();
	
	private HashMap<String, HashMap<String, String>> so_dacCodes = new HashMap<String, HashMap<String, String>>();
	
	private HashMap<String, HashMap<String, String>> dac_FAO_SOCodes = new HashMap<String, HashMap<String, String>>();
	
	private Integer aggregationLevel;
	
	private VennResultsVO vennResults;
	
	


	public HashMap<String, String> getVennImages() {
		return vennImages;
	}

	public void setVennImages(HashMap<String, String> vennImages) {
		this.vennImages = vennImages;
	}

	public VennIntersectionsBean getA() {
		return a;
	}

	public void setA(VennIntersectionsBean a) {
		this.a = a;
	}

	public VennIntersectionsBean getB() {
		return b;
	}

	public void setB(VennIntersectionsBean b) {
		this.b = b;
	}

	public VennIntersectionsBean getC() {
		return c;
	}

	public void setC(VennIntersectionsBean c) {
		this.c = c;
	}

	public VennIntersectionsBean getAb() {
		return ab;
	}

	public void setAb(VennIntersectionsBean ab) {
		this.ab = ab;
	}

	public VennIntersectionsBean getBc() {
		return bc;
	}

	public void setBc(VennIntersectionsBean bc) {
		this.bc = bc;
	}

	public VennIntersectionsBean getAc() {
		return ac;
	}

	public void setAc(VennIntersectionsBean ac) {
		this.ac = ac;
	}

	public VennIntersectionsBean getAbc() {
		return abc;
	}

	public void setAbc(VennIntersectionsBean abc) {
		this.abc = abc;
	}

	public VennIntersectionsBean getU() {
		return u;
	}

	public void setU(VennIntersectionsBean u) {
		this.u = u;
	}

	public HashMap<String, String> getDacCodes() {
		return dacCodes;
	}

	public void setDacCodes(HashMap<String, String> dacCodes) {
		this.dacCodes = dacCodes;
	}



	public HashMap<String, HashMap<String, String>> getSo_dacCodes() {
		return so_dacCodes;
	}

	public void setSo_dacCodes(HashMap<String, HashMap<String, String>> soDacCodes) {
		so_dacCodes = soDacCodes;
	}

	public VennResultsVO getVennResults() {
		return vennResults;
	}

	public void setVennResults(VennResultsVO vennResults) {
		this.vennResults = vennResults;
	}
	
	

	public void setAggregationLevel(Integer aggregationLevel) {
		this.aggregationLevel = aggregationLevel;
	}
	
	

	public Integer getAggregationLevel() {
		return aggregationLevel;
	}

	public HashMap<String, HashMap<String, String>> getDac_FAO_SOCodes() {
		return dac_FAO_SOCodes;
	}

	public void setDac_FAO_SOCodes(HashMap<String, HashMap<String, String>> dacFAOSOCodes) {
		dac_FAO_SOCodes = dacFAOSOCodes;
	}



	public List<VennIntersectionsBean> getAllIntersections(){
		List<VennIntersectionsBean> intersections = new ArrayList<VennIntersectionsBean>();
	
		intersections.add(a);
		intersections.add(b);
		intersections.add(c);
		intersections.add(ab);
		intersections.add(bc);
		intersections.add(ac);
		intersections.add(abc);
		intersections.add(u);
		return intersections;
	}
	
	public List<VennIntersectionsBean> getAllIntersectionsChart(){
		List<VennIntersectionsBean> intersections = new ArrayList<VennIntersectionsBean>();
		intersections.add(abc);
		intersections.add(ab);
		intersections.add(ac);
		intersections.add(bc);
		intersections.add(a);
		intersections.add(b);
		intersections.add(c);
		return intersections;
	}
	
	public VennIntersectionsBean getIntersection(Integer intersection){
		
		// 0 = universe
		// 1 = abc
		// 2 = ab
		// 3 = ac
		// 4 = bc
		// 5 = c
		// 6 = b
		// 7 = a

		if (intersection == 0)
			return this.getA();
		else if (intersection == 1)
			return this.getB();
		else if (intersection == 2)
			return this.getC();
		else if (intersection == 3)
			return this.getAb();
		else if (intersection == 4)
			return this.getAc();
		else if (intersection == 5)
			return this.getBc();
		else if (intersection == 6)
			return this.getAbc();
		else
			return this.getU();
		
	}

	
	public VennIntersectionsBean getIntersection(String intersection){
		// 0 = universe
		// 1 = abc
		// 2 = ab
		// 3 = ac
		// 4 = bc
		// 5 = c
		// 6 = b
		// 7 = a
		if (intersection.equals("a"))
			return this.getA();
		else if (intersection.equals("b"))
			return this.getB();
		else if (intersection.equals("c"))
			return this.getC();
		else if (intersection.equals("ab"))
			return this.getAb();
		else if (intersection.equals("ac"))
			return this.getAc();
		else if (intersection.equals("bc"))
			return this.getBc();
		else if (intersection.equals("abc"))
			return this.getAbc();
		else
			return this.getU();
		
	}



}
