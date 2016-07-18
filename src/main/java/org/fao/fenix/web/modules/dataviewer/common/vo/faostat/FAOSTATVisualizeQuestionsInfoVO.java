package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;



import com.google.gwt.user.client.rpc.IsSerializable;

public class FAOSTATVisualizeQuestionsInfoVO implements IsSerializable {
	
	
	// title of the question
	String title;
	
	// subtitle of the question
	String subtitle;
	
	// description of the question
	String description;
	
	// filename of the questions (containing view settings VOs)
	String filename;
	
	// not used yet
	String id;
	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "FAOSTATVisualizeQuestionsInfoVO [filename=" + filename
				+ ", subtitle=" + subtitle + ", title=" + title + "]";
	}

	
	
}
