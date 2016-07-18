package org.fao.fenix.web.modules.venn.server;

import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;

class AreaPainter {

//		SetsPainter SP;
		CreateVennImage SP;
		int noOfArea;
		boolean selected;
		boolean highlighted;
		double x[],y[];
		int ix[],iy[];
		int nxy;
		int ixmin,ixmax,iymin,iymax;
	
		//
		String name;
		
		String title;
		//
	
		VennBeanVO vennBean;
	
		public AreaPainter(CreateVennImage SP,int iArea, VennBeanVO bean) {
	
			this.vennBean = bean;
			//
			name = getIntersection(iArea).getCenterLabel();
			
			title = getIntersection(iArea).getLabel();
			//
	
			int ix0,iy0;
	
			this.SP = SP;
			noOfArea = iArea;
			selected = false;
			highlighted = false;
			ixmin=10000; ixmax=0; iymin=10000; iymax=0; //min 0 -> 1000
	
			switch(noOfArea) {
	
			case 0: area0();
				break;
			case 1: area1();
				break;
			case 2: area2();
	   			break;
			case 3: area3();
	   			break;
			case 4: area4();
	   			break;
			case 5: area5();
	   			break;
			case 6: area6();
	   			break;
			case 7: area7();
			}
	
			ix = new int[nxy];
			iy = new int[nxy];
	
			for(int i=0; i<nxy; i++) {
	
	    		ix0 = SP.Xtox(x[i]);
	    		iy0 = SP.Ytoy(y[i]);
	    		ix[i] = ix0;
	    		iy[i] = iy0;
	
	    		if(ix0<ixmin) ixmin = ix0;
	    		if(ix0>ixmax) ixmax = ix0;
	    		if(iy0<iymin) iymin = iy0;
	    		if(iy0>iymax) iymax = iy0;
	
			}
	
		}
		
		void togleSelected() {
	
	    	if(selected) selected = false;
	    	else         selected = true;
	
		}
		
		void togleHighlighted() {
			
	    	if(highlighted) highlighted = false;
	    	else         highlighted = true;
	
		}
	
	
		void area0() {
	
		nxy = 4;
		x = new double[nxy];
		y = new double[nxy];
	
		x[0] = SP.Xmin;
		y[0] = SP.Ymin;
		x[1] = SP.Xmax;
		y[1] = SP.Ymin;
		x[2] = SP.Xmax;
		y[2] = SP.Ymax;
		x[3] = SP.Xmin;
		y[3] = SP.Ymax;
	
		}
	
		void area1() {
	
	    	double fi;
	
	    	nxy = 3*(int)((2*SP.alpha-Math.PI/3)/VennContants.deltaAngle+1)+1;
	    	x = new double[nxy];
	    	y = new double[nxy];
	
	    	nxy = 0;
	
	    	for(fi=-SP.alpha; fi<SP.alpha-Math.PI/3; fi+=VennContants.deltaAngle) {
	        	x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
		    for(fi=2*Math.PI/3-SP.alpha; fi<SP.alpha+Math.PI/3; fi+=VennContants.deltaAngle) {
	        	x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
		    for(fi=4*Math.PI/3-SP.alpha; fi<SP.alpha+Math.PI; fi+=VennContants.deltaAngle) {
	        	x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
		}
	
		void area2() {
	
	    	double fi;
	
	    	nxy = (int)((2*SP.alpha-Math.PI/3)/VennContants.deltaAngle+1)
		        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
		    x = new double[nxy];
		    y = new double[nxy];
	
	    	nxy = 0;
	
	    	for(fi=-Math.PI/3+SP.alpha; fi<SP.alpha; fi+=VennContants.deltaAngle) {
		        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
	    	for(fi=Math.PI-SP.alpha; fi<4*Math.PI/3-SP.alpha; fi+=VennContants.deltaAngle) {
		        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
	    	for(fi=SP.alpha+Math.PI/3; fi>2*Math.PI/3-SP.alpha; fi-=VennContants.deltaAngle) {
		        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        	y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        	nxy++;
	    	}
	
		}
	
	void area3() {
	
	    double fi;
	
	    nxy = (int)((2*SP.alpha-Math.PI/3)/VennContants.deltaAngle+1)
	        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
	    x = new double[nxy];
	    y = new double[nxy];
	
	    nxy = 0;
	
	    for(fi=Math.PI/3+SP.alpha; fi<2*Math.PI/3+SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=-Math.PI/3-SP.alpha; fi<-SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=Math.PI+SP.alpha; fi>4*Math.PI/3-SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	}
	
	void area4() {
	
	    double fi;
	
	    nxy = (int)((2*SP.alpha-Math.PI/3)/VennContants.deltaAngle+1)
	        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
	    x = new double[nxy];
	    y = new double[nxy];
	
	    nxy = 0;
	
	    for(fi=Math.PI+SP.alpha; fi<4*Math.PI/3+SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=Math.PI/3-SP.alpha; fi<2*Math.PI/3-SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=-Math.PI/3+SP.alpha; fi>-SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	}
	
	void area5() {
	
	    double fi;
	
	    nxy = (int)((5*Math.PI/3-2*SP.alpha)/VennContants.deltaAngle+1)
	        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
	    x = new double[nxy];
	    y = new double[nxy];
	
	    nxy = 0;
	
	    for(fi=-SP.alpha; fi>-Math.PI/3-SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=2*Math.PI/3+SP.alpha; fi<7*Math.PI/3-SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=4*Math.PI/3+SP.alpha; fi>Math.PI+SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	}
	
	void area6() {
	
	    double fi;
	
	    nxy = (int)((5*Math.PI/3-2*SP.alpha)/VennContants.deltaAngle+1)
	        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
	    x = new double[nxy];
	    y = new double[nxy];
	
	    nxy = 0;
	
	    for(fi=2*Math.PI/3-SP.alpha; fi>Math.PI/3-SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=-2*Math.PI/3+SP.alpha; fi<Math.PI-SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=SP.alpha; fi>-Math.PI/3+SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	}
	
	void area7() {
	
	    double fi;
	
	    nxy = (int)((5*Math.PI/3-2*SP.alpha)/VennContants.deltaAngle+1)
	        + 2*((int)(Math.PI/3/VennContants.deltaAngle+1))+1;
	
	    x = new double[nxy];
	    y = new double[nxy];
	
	    nxy = 0;
	
	    for(fi=4*Math.PI/3-SP.alpha; fi>Math.PI-SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcB + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcB + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=SP.alpha; fi<5*Math.PI/3-SP.alpha; fi+=VennContants.deltaAngle) {
	        x[nxy] = SP.XcA + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcA + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	    for(fi=2*Math.PI/3+SP.alpha; fi>Math.PI/3+SP.alpha; fi-=VennContants.deltaAngle) {
	        x[nxy] = SP.XcC + SP.R*Math.cos(fi);
	        y[nxy] = SP.YcC + SP.R*Math.sin(fi);
	        nxy++;
	    }
	
	}
	
	
	
		public String toString(){
			return name;
		}
	
		public void setName(String string){
			name = string;
		}
		public boolean isSelected(){
			return selected;
		}
	
		public void setSelected(boolean selected){
			this.selected = selected;
		}
		
		public boolean isHighlighted(){
			return highlighted;
		}
	
		public void setHighlighted(boolean highlighted){
			this.highlighted = highlighted;
		}
	
		public int xc(){
			return (ixmax+ixmin)/2;
		}
	
		public int yc(){
			return (iymax+iymin)/2;
		}
		
		
		
		public String getTitle() {
			return title;
		}

		private VennIntersectionsBean getIntersection(Integer intersection){
			
			// 0 = universe
			// 1 = abc
			// 2 = ab
			// 3 = ac
			// 4 = bc
			// 5 = c
			// 6 = b
			// 7 = a
			
			if (intersection == 0)
				return vennBean.getVennGraphBeanVO().getU();
			else if (intersection == 1)
				return vennBean.getVennGraphBeanVO().getAbc();
			else if (intersection == 2)
				return vennBean.getVennGraphBeanVO().getAb();
			else if (intersection == 3)
				return vennBean.getVennGraphBeanVO().getAc();
			else if (intersection == 4)
				return vennBean.getVennGraphBeanVO().getBc();
			else if (intersection == 5)
				return vennBean.getVennGraphBeanVO().getC();
			else if (intersection == 6)
				return vennBean.getVennGraphBeanVO().getB();
			else
				return vennBean.getVennGraphBeanVO().getA();
			
		}

}