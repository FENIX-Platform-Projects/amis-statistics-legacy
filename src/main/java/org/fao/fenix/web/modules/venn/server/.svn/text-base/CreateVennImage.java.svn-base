package org.fao.fenix.web.modules.venn.server;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;






class CreateVennImage extends Canvas {

	int width,height;
	
	Image offImage;
	Graphics2D offg;
	AreaPainter AP[];
	boolean firstFlag;
	
	// screen coordinates
	int xmin,ymin;
	
	// physical coordinates and scales
	double Xmin,Xmax,Ymin,Ymax,scaleX,scaleY;
	
	// centers of circles
	double XcA,YcA,XcB,YcB,XcC,YcC,R;
	
	double alpha;
	
	Color usualColor,selectedColor,lineColor,abcColor,fontColor;
	
	//
	boolean oldData[] = new boolean[8];
	//
		
	VennBeanVO vennBean;
	
	int fontSize; 
	
	int vennFontDistance; 
	
	int sizex;
	
	int sizey;
	
	int intersectionsFontSize; 
	
	public CreateVennImage(VennBeanVO bean, int width, int height, int fontSize, int vennFontDistance, int intersectionsFontSize)  {
		this.width = width;
		this.height = height;
		this.fontSize = fontSize;
		this.vennFontDistance = vennFontDistance;
		this.intersectionsFontSize = intersectionsFontSize;
		this.vennBean = bean;
		
		inizialize(bean);
	}
	
	public CreateVennImage(VennBeanVO bean, int width, int height, int fontSize, int vennFontDistance, int intersectionsFontSize, String selectedIntersectionLabel)  {
		this.width = width;
		this.height = height;
		this.fontSize = fontSize;
		this.vennFontDistance = vennFontDistance;
		this.intersectionsFontSize = intersectionsFontSize;
		this.vennBean = bean;
		
		int intersection = getIntersectionIndex(selectedIntersectionLabel);
		if(getIntersection(intersection)!=null) {
			//System.out.println("%%%%%%%%%%%%%%%% GET INTERSECTION "+intersection+" HIGHLIGHTED!!");
			getIntersection(intersection).setIsHighlighted(true);
		} else {
			System.out.println("CreateVennImage: GET INTERSECTION IS NULL !!");
		}
		inizialize(bean);
	}
	
	public String createImage() {
//		File imageOff = new File(Setting.getSystemPath() + File.separator + "venn" + File.separator + "temp" + File.separator + "imageoff.png" );
//		try {
//			Image offscreen = ImageIO.read(imageOff);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
		//RenderedImage image = new BufferedImage(width+2, height+2, BufferedImage.TYPE_INT_RGB);
		BufferedImage image = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);
		
		
		//Graphics2D g = ((BufferedImage)image).createGraphics();
		Graphics2D g = image.createGraphics();
		
		//g.setColor(Color.WHITE);
		g.setBackground(Color.WHITE);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.00f));
		
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g.setColor(Color.WHITE);
		
		// REMOVED RECT
		//g.fillRect(0, 0, width, height); 
		g.fill(rect); 
		
		paint(g);
		
		g.setColor(Color.GRAY);
		g.draw(rect);
		
		    
		g.dispose();
		
		String fileName = BirtUtil.randomFileExport() + ".png"; 

		//BufferedImage image2 = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.BITMASK);	
		
		
		File file = new File(Setting.getSystemPath() + File.separator + "venn" + File.separator + "temp" + File.separator + fileName );
		try {
			ImageIO.write((RenderedImage)image,"png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
		
	private void inizialize(VennBeanVO bean) {
		//this.vennBean = bean;

		usualColor = new Color(VennContants.usualColor);
		//lineColor = new Color(VennContants.lineColor);
		lineColor = new Color(VennContants.lineColor);
		
		abcColor = new Color(VennContants.abcColor);
		fontColor = new Color(VennContants.fontColor);
		
		R = VennContants.R;
		
		Xmin = Ymin = -0.5*R*VennContants.relSize;
	    Xmax = Ymax =  0.5*R*VennContants.relSize;
	    alpha = Math.acos(VennContants.relDistance);

	    XcA = -R*VennContants.relDistance;
	    YcA =  R*VennContants.relDistance/Math.sqrt(3.0) + 0.2*R;
	    XcB =  R*VennContants.relDistance;
	    YcB =  R*VennContants.relDistance/Math.sqrt(3.0) + 0.2*R;
	    XcC =  0;
	    YcC = -R*VennContants.relDistance/(0.5*Math.sqrt(3.0)) + 0.2*R;

	    computeScales(0.05);

	    AP = new AreaPainter[8];

	    for(int i=0; i< 8; i++) {
			AP[i] = new AreaPainter(this,i, bean);			
			
			AP[i].setSelected(getIntersection(i).getIsIntersected());
			AP[i].setHighlighted(getIntersection(i).getIsHighlighted());
			
			/*if(i==2) {
				System.out.println("INTERSECTION "+i+" getisHighlighted "+ getIntersection(i).getIsHighlighted());
				System.out.println("SET HIGHLIGHTED  "+i+" AP highlighted "+ AP[i].highlighted);	
			}*/
		}
	}
	
	private void computeScales(double insetRelative) {
		// In:  Xmin,Xmax,Ymin,Ymax,width,height,insetRelative
		// Out: xmin,ymin,scaleX,scaleY
		sizex = (int)(width*(1.0-2*insetRelative));
	    sizey = (int)(height*(1.0-2*insetRelative));
	
	    scaleX = sizex/(Xmax-Xmin);
	    scaleY = sizey/(Ymax-Ymin);
	
	    if(scaleX>scaleY) scaleX = scaleY; else scaleY = scaleX;
			    xmin = (width-(int)(scaleX*(Xmax-Xmin)))/2;
			    ymin = (height-(int)(scaleY*(Ymax-Ymin)))/2;
		}
		int insideArea(int ix0, int iy0) {
		boolean A,B,C;
		double X0,Y0;
		int na;
	
		A = false; B = false; C = false;
	
		X0 = xtoX(ix0);
		    Y0 = ytoY(iy0);
	
		if( (X0-XcA)*(X0-XcA)+(Y0-YcA)*(Y0-YcA) < R*R) A = true;
		if( (X0-XcB)*(X0-XcB)+(Y0-YcB)*(Y0-YcB) < R*R) B = true;
		if( (X0-XcC)*(X0-XcC)+(Y0-YcC)*(Y0-YcC) < R*R) C = true;
	
		na = -1;
	
		if(A&& B&& C) na = 1;
		if(A&& B&&!C) na = 2;
		if(A&& C&&!B) na = 3;
		if(B&& C&&!A) na = 4;
		if(C&&!A&&!B) na = 5;
		if(B&&!A&&!C) na = 6;
		if(A&&!B&&!C) na = 7;
	
		if(na==-1 && (X0>Xmin&&X0<Xmax) && (Y0>Ymin&&Y0<Ymax)) na = 0;
	
		return na;
	
	}
		
		public void update (Graphics g) {
			offg.setColor(Color.WHITE);
			offg.setBackground(Color.WHITE);
			offg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.00f));
			
			// REMOVED RECT
			offg.fillRect(0, 0, width, height); 
		    paint(offg);
		
		    g.drawImage(offImage, 0, 0, null);
	
		}
		
		public void paint(Graphics g) {
		//	System.out.println(" PAINT START ......................");
			
			// REMOVED RECT
			
			//
			FontMetrics fm;
			//
	
		    for(int i=1; i<8; i++) {
	
		        if(AP[i].selected) {
		        	if(AP[i].selected && AP[i].highlighted) {
		        		//System.out.println(" selected = highlighted ... PINK for "+i);
		        		selectedColor = new Color(VennContants.highlightedColor);
		        	} else {
		        		//System.out.println(" selected != highlighted ... GREEN for "+i);
		        		selectedColor = new Color(getIntersection(i).getColor());
		        	}
		        	g.setColor(selectedColor);
		        }
		        else {    
		        	//System.out.println(" NOT selected ... WHITE for "+i);
		        	g.setColor(usualColor);
		        }
		        g.fillPolygon(AP[i].ix,AP[i].iy,AP[i].nxy);
		    }
	
		    g.setColor(lineColor);
	
		   // g.drawPolygon(AP[0].ix,AP[0].iy,AP[0].nxy);
		    g.drawPolygon(AP[5].ix,AP[5].iy,AP[5].nxy);
		    g.drawPolygon(AP[6].ix,AP[6].iy,AP[6].nxy);
		    g.drawPolygon(AP[7].ix,AP[7].iy,AP[7].nxy);
		    g.drawPolygon(AP[1].ix,AP[1].iy,AP[1].nxy);

		

//			g.setColor(abcColor);
//			g.setColor(abcColor);
			
			
			fm = g.getFontMetrics();
			for(int i = 1; i<8; i++){
				g.setFont(new Font("Arial", Font.PLAIN, intersectionsFontSize));
				g.setColor(abcColor);
				g.drawString(AP[i].toString(), AP[i].xc()-fm.stringWidth(AP[i].toString())/2, AP[i].yc()+fm.getHeight()/2);
				
				if ( i == 7) {
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.setColor(fontColor);
					g.drawString(AP[i].getTitle(), AP[i].xc()-fm.stringWidth(AP[i].getTitle())/2, fm.getHeight() + vennFontDistance);
				}
				if ( i == 6) {
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.setColor(fontColor);
					g.drawString(AP[i].getTitle(), AP[i].xc()-fm.stringWidth(AP[i].getTitle())/2, fm.getHeight() + vennFontDistance);
				}
				if ( i == 5) {
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.setColor(fontColor);
					g.drawString(AP[i].getTitle(), AP[i].xc()-fm.stringWidth(AP[i].getTitle())/2, height - fm.getHeight() - vennFontDistance + 10);
				}
			}
			
			//g.setColor(Color.BLUE);
		}
		
		int Xtox(double Xi) {
	
		    return ( xmin + (int) (scaleX*(Xi-Xmin)+0.5) );
	
		}
	
		int Ytoy(double Yi) {
	
		    return ( ymin + (int) (scaleY*(Ymax-Yi)+0.5) );
		}
	
		double xtoX(int xi) {
	
		    return ( ((double)(xi-xmin)-0.5)/scaleX + Xmin );
		}
	
		double ytoY(int yi) {
	
		    return ( -(((double)(yi-ymin)-0.5)/scaleY - Ymax) );
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
	
private int getIntersectionIndex(String intersectionLabel){
		
	System.out.println("CreateVennImage: getIntersectionIndex intersectionLabel '"+intersectionLabel+"'");
		if (intersectionLabel.equalsIgnoreCase("U"))
			return 0;
		else if (intersectionLabel.equalsIgnoreCase("abc"))
			return 1;
		else if (intersectionLabel.equalsIgnoreCase("ab"))
			return 2;
		else if (intersectionLabel.equalsIgnoreCase("ac"))
			return 3;
		else if (intersectionLabel.equalsIgnoreCase("bc"))
			return 4;
		else if (intersectionLabel.equalsIgnoreCase("c"))
			return 5;
		else if (intersectionLabel.equalsIgnoreCase("b"))
			return 6;
		else
			return 7;
		
	}
		

}