package org.fao.fenix.web.modules.dataviewer.server.faostat.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DOM4JUtils {
	
	private final static String ROOT = "NOTES";
	
	private final String CLOSE_HTML = "</body></html>";
	
	private String ip;
	
	private String port;
	
	public Document parseFile(String filepath) throws DocumentException, FileNotFoundException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new FileReader(filepath));
		return document;
	}
	
	public Document parseXML(String xml) throws DocumentException, FileNotFoundException {
        SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(xml));
		return document;
    }
	
	public String readTag(String filepath, String tag, String language) throws DocumentException, FileNotFoundException, IOException {
		String xml = readURLContent(filepath).toString();
		Document document = parseXML(xml);
		String xPathExpression = "//" + ROOT + "/" + tag + "/" + language;
		Node node = document.selectSingleNode(xPathExpression);
		return node.getText();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> readCollection(String filepath, String tag, String language) throws DocumentException, FileNotFoundException, IOException {
		List<String> l = new ArrayList<String>();
		String file = readURLContent(filepath).toString();
		Document document = parseXML(file);
		String xPathExpression = "//" + ROOT + "/" + tag + "S/" + tag;
		List<Node> nodes = document.selectNodes(xPathExpression);
		for (Node n : nodes) {
			String xml = n.asXML();
			Document d = parseXML(xml);
			String xPathExpression2 = "//" + language;
			l.add(d.selectSingleNode(xPathExpression2).getText());
		}
		return l;
	}
	
	public List<String> readStructure(String filepath, String language) throws DocumentException, FileNotFoundException, IOException {
		List<String> l = new ArrayList<String>();
		l.add(readTag(filepath, "DESCRIPTION", language));
		List<String> customs = readCollection(filepath, "CUSTOM", language);
		if (!customs.isEmpty())
			for (String c : customs)
				l.add(c);
		l.add(readTag(filepath, "PLEASENOTE", language));
		l.add(readTag(filepath, "DISCLAIMER", language));
		return l;
	}
	
	public String readFile(String filepath, String language) throws DocumentException, FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(openHTML());
//		sb.append("<div class='title'>").append(readTag(filepath, "TITLE", language)).append("</div><br/><br/>");
		sb.append(readURLContent(readTag(filepath, "DESCRIPTION", language)));
		List<String> customs = readCollection(filepath, "CUSTOM", language);
		if (!customs.isEmpty())
			for (String s : customs)
				sb.append(readURLContent(s));
		try {
			sb.append(readURLContent(readTag(filepath, "PLEASENOTE", language)));
		} catch (NullPointerException e) {
			
		}
		try {
			sb.append(readURLContent(readTag(filepath, "DISCLAIMER", language)));
		} catch (Exception e) {
			
		}
		sb.append(CLOSE_HTML);
		return sb.toString();
	}
	
	public String openHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><link href=\"http://");
		sb.append(this.getIp());
		sb.append(":");
		sb.append(this.getPort());
		sb.append("/fenix-web/faostat/config/NOTES/BY_DOMAIN/COMMONS/StatisticalNotes.css\" rel=\"stylesheet\" type=\"text/css\"></head><body>");
		return sb.toString();
	}
	
	public StringBuilder readFileContent(String filepath) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(new FileInputStream(filepath));
	    try {
	    	while (scanner.hasNextLine()) {
	    		sb.append(scanner.nextLine());
	    	}
	    }
	    finally{
	    	scanner.close();
	    }
		return sb;
	}
	
	public StringBuilder readURLContent(String filepath) throws IOException {
		StringBuilder sb = new StringBuilder();
		URL oracle = new URL(base() + filepath);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();
		return sb;
	}
	
	@SuppressWarnings("unchecked")
	public List<String[]> readIndex(String group) throws IOException, DocumentException {
		List<String[]> l = new ArrayList<String[]>();
		String xml = readURLContent("/index.xml").toString();
		Document d = parseXML(xml);
		String xPath = "//NOTES/" + group + "/SECTION";
		List<Node> nodes = d.selectNodes(xPath);
		for (int i = 0 ; i < nodes.size() ; i++) {
			String[] s = {nodes.get(i).getText(), nodes.get(i).valueOf("@gwtLangCode")};
			l.add(s);
		}
		return l;
	}
	
	public String base() {
		return "http://" + this.getIp() + ":" + this.getPort() + "/fenix-web/faostat/config/NOTES/BY_DOMAIN";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}