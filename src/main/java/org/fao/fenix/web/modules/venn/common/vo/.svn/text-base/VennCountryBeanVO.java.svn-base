package org.fao.fenix.web.modules.venn.common.vo;



import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennCountryBeanVO implements IsSerializable {
	
	private VennProjectsBean a = new VennProjectsBean("a");
	
	private VennProjectsBean b = new VennProjectsBean("b");
	
	private VennProjectsBean c = new VennProjectsBean("c");
	
	private VennProjectsBean ab = new VennProjectsBean("ab");
	
	private VennProjectsBean bc = new VennProjectsBean("bc");
	
	private VennProjectsBean ac = new VennProjectsBean("ac");
	
	private VennProjectsBean abc = new VennProjectsBean("abc");
	
	private VennProjectsBean u = new VennProjectsBean("u");

	
	
	public VennProjectsBean getA() {
		return a;
	}

	public void setA(VennProjectsBean a) {
		this.a = a;
	}

	public VennProjectsBean getB() {
		return b;
	}

	public void setB(VennProjectsBean b) {
		this.b = b;
	}

	public VennProjectsBean getC() {
		return c;
	}

	public void setC(VennProjectsBean c) {
		this.c = c;
	}

	public VennProjectsBean getAb() {
		return ab;
	}

	public void setAb(VennProjectsBean ab) {
		this.ab = ab;
	}

	public VennProjectsBean getBc() {
		return bc;
	}

	public void setBc(VennProjectsBean bc) {
		this.bc = bc;
	}

	public VennProjectsBean getAc() {
		return ac;
	}

	public void setAc(VennProjectsBean ac) {
		this.ac = ac;
	}

	public VennProjectsBean getAbc() {
		return abc;
	}

	public void setAbc(VennProjectsBean abc) {
		this.abc = abc;
	}

	public VennProjectsBean getU() {
		return u;
	}

	public void setU(VennProjectsBean u) {
		this.u = u;
	}

	public List<VennProjectsBean> getAllIntersections(){
		List<VennProjectsBean> intersections = new ArrayList<VennProjectsBean>();
//		intersections.add(u);
//		intersections.add(abc);
//		intersections.add(ab);
//		intersections.add(ac);
//		intersections.add(bc);
//		intersections.add(c);
//		intersections.add(b);
//		intersections.add(a);
		
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
	
	public List<VennProjectsBean> getAllIntersectionsChart(){
		List<VennProjectsBean> intersections = new ArrayList<VennProjectsBean>();
		intersections.add(abc);
		intersections.add(ab);
		intersections.add(ac);
		intersections.add(bc);
		intersections.add(a);
		intersections.add(b);
		intersections.add(c);
		return intersections;
	}
	
	public VennProjectsBean getIntersection(Integer intersection){
		
		// 0 = universe
		// 1 = abc
		// 2 = ab
		// 3 = ac
		// 4 = bc
		// 5 = c
		// 6 = b
		// 7 = a
		
//		if (intersection == 0)
//			return this.getU();
//		else if (intersection == 1)
//			return this.getAbc();
//		else if (intersection == 2)
//			return this.getAb();
//		else if (intersection == 3)
//			return this.getAc();
//		else if (intersection == 4)
//			return this.getBc();
//		else if (intersection == 5)
//			return this.getC();
//		else if (intersection == 6)
//			return this.getB();
//		else
//			return this.getA();
		
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


	public VennProjectsBean getIntersection(String intersection){
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
