package org.fao.fenix.web.modules.console.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.core.persistence.utils.DatasetUtils;
import org.fao.fenix.web.modules.console.common.service.ConsoleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConsoleServiceImpl extends RemoteServiceServlet implements ConsoleService {

	String envVarName = "CATALINA_HOME";
	
	DatasetUtils datasetUtils;

	public List<String> getLogsList() {
		List<String> logsList = new ArrayList<String>();
		Map<String, String> envMap = System.getenv();
		File logsDir = new File(envMap.get(envVarName) + "/logs");
		for (File file : logsDir.listFiles())
			if (!file.isDirectory())
				logsList.add(file.getName());
		return logsList;
	}

	public String getLogContent(String fileName) {
		String content = "";
		try {
			Map<String, String> envMap = System.getenv();
			File logFile = new File(envMap.get(envVarName) + "/logs/" + fileName);
			if (!logFile.isDirectory()) {
				BufferedReader reader = new BufferedReader(new FileReader(logFile));
				String line = null;
				while ((line = reader.readLine()) != null) {
					content += line + "\n";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public List<String> getMetadataTemplateNames() {
		return datasetUtils.getMetadataTemplateNames();
	}
	
	public String getMetadataTemplate(String metadataTemplateName) {
		return datasetUtils.getMetadataTemplate(metadataTemplateName);
	}

	public void setDatasetUtils(DatasetUtils datasetUtils) {
		this.datasetUtils = datasetUtils;
	}

}
