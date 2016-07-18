package org.fao.fenix.web.modules.birt.server.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.birt.report.model.api.ReportDesignHandle;

public class RptdesignToString {

	private ReportDesignHandle designHandle = null;
	private String nameFile;

	public RptdesignToString(ReportDesignHandle designHandle) {
		super();
		this.designHandle = designHandle;
	}
	
	public RptdesignToString(String nameFile) {
		this.setNameFile(nameFile);
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	

	private void saveReport() throws IOException {
		setNameFile(BirtUtil.randomNameFile());
		designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + getNameFile());
	}

	private boolean deleteTmpFile(File f){
		return f.delete();
	}
	
	public String getRptdesign() {
		String reportAsString = null;
		try {

			saveReport();
			
			File f = new File(System.getProperty("java.io.tmpdir") + File.separator + getNameFile());
			reportAsString = FileUtils.readFileToString(f, null);

			/*
			if (deleteTmpFile(f)){
				System.out.println("Deleted rptdesign file");
			}else{
				System.out.println("Error to delete rptdesign file");
			}
			*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return reportAsString;
	}
	
	public String getRptdesignLikeString(){
		String reportAsString = null;
		try {

			File f = new File(System.getProperty("java.io.tmpdir") + File.separator + getNameFile());
			reportAsString = FileUtils.readFileToString(f, null);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return reportAsString;
	}

}
