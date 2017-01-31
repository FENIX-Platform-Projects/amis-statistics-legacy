package org.fao.fenix.web.modules.latex.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class BirtImageHound {

	private final static Logger LOGGER =  Logger.getLogger(BirtImageHound.class);
	
	public String findBirtImageDirectory(String dir) {
		
		List<String> candidates = new ArrayList<String>();
		
		String path = "";
		File f = new File(dir);
		LOGGER.info("We are @ " + f.getAbsolutePath());
		
		int counter = 0;
		int idx = 0;
		
		for (int i = f.getAbsolutePath().length() -1 ; i >= 0 ; i--) {
			if (f.getAbsolutePath().charAt(i) == File.separatorChar)
				counter++;
			if (counter == 2) {
				idx = i;
				break;
			}
		}
		LOGGER.info("IDX @ " + idx);
		
		path = f.getAbsolutePath().substring(0, idx);
		LOGGER.info(path);
		path += File.separator + "fenix-birt" + File.separator + "report" + File.separator + "images"; // + File.separator + "BIRTIMG" + JSESSIONID;
		LOGGER.info(path);
		
		List<String> datestrings = datestring();
		
		File tmpDir = new File(path);
		File[] first = tmpDir.listFiles();
		if (first != null) {
			for (File tmpf : first) {
				File[] second = tmpf.listFiles();
				if (second != null) {
					for (File stmpf : second) {
						if (stmpf.isDirectory()) {
							boolean startswith = false;
							for (String s : datestrings)
								startswith = startswith || stmpf.getName().startsWith(s);
							System.out.println(" >>>>> startswith: " + startswith);
							if (startswith) {
								System.out.println("\t\t" + stmpf.getName() + " contains:");
								File[] third = stmpf.listFiles();
								if (third != null) {
									for (File sstmpf : third) {
										System.out.println("\t\t\t" + sstmpf.getName() + ", Is a Directory? " + sstmpf.isDirectory());
										if (sstmpf.getAbsolutePath().endsWith(".png"))
											candidates.add(sstmpf.getAbsolutePath()); 
									}
								}
							}
						}
					}
				}
			}
		}
		
		for (String c : candidates)
			System.out.println("CANDIDATE: " + c);
		
		return candidates.get(candidates.size() - 1);
	}
	
	private String folderName(String path) {
		String f = "";
		int lastSeparatorIDX = path.lastIndexOf(File.separator);
		if (lastSeparatorIDX > -1)
			f = path.substring(1 + lastSeparatorIDX);
		return f;
	}
	
	private List<String> datestring() {
		List<String> ss = new ArrayList<String>();
		String s = "";
		Date today = new Date();
		for (int i = -2 ; i < 3 ; i++) {
			s += String.valueOf(1900 + today.getYear());
			if (today.getMonth() < 9)
				s += "0";
			s += Integer.valueOf(1 + today.getMonth());
			if (today.getDate() < 10)
				s += "0";
			s += Integer.valueOf(today.getDate());
			s += "_";
			if (today.getHours() < 10)
				s += "0";
			s += Integer.valueOf(today.getHours());
			if ((i + today.getMinutes()) < 10)
				s += "0";
			s += Integer.valueOf(i + today.getMinutes());
			LOGGER.info("datestring: " + s);
			ss.add(s);
			s = "";
		}
		return ss;
	}
	
}
