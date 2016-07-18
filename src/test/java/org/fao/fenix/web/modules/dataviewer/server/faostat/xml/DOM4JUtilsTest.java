package org.fao.fenix.web.modules.dataviewer.server.faostat.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class DOM4JUtilsTest extends TestCase {
	
	private final static String Q = "src/main/webapp/faostat/config/NOTES/BY_DOMAIN/Q.xml";
	
	private final static String B = "src/main/webapp/faostat/config/NOTES/BY_DOMAIN/B.xml";
	
	private final static String XML = "<CUSTOM><E>/Q/ValueOfAgriculturalProduction_E.html</E><F>/Q/ValueOfAgriculturalProduction_F.html</F><S>/Q/ValueOfAgriculturalProduction_S.html</S></CUSTOM>";
	
	private DOM4JUtils u;
	
	public DOM4JUtilsTest() {
		u = new DOM4JUtils();
		u.setIp("127.0.0.1");
		u.setPort("8080");
	}
	
	public void testParseFile() {
		try {
			Document d = u.parseFile(Q);
			assertNotNull(d);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void testParseXML() {
		try {
			Document d = u.parseXML(XML);
			assertNotNull(d);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void testReadTag() {
		try {
			String t = u.readTag(Q, "TITLE", "E");
			assertEquals("Production", t);
			t = u.readTag(Q, "TITLE", "F");
			assertEquals("Production", t);
			t = u.readTag(Q, "TITLE", "S");
			assertEquals("Producción", t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testReadCollection() {
		try {
			List<String> nodes = u.readCollection(Q, "CUSTOM", "E");
			assertEquals(2, nodes.size());
			nodes = u.readCollection(B, "CUSTOM", "E");
			assertEquals(0, nodes.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testReadStructure() {
		try {
			List<String> l = u.readStructure(Q, "E");
			assertEquals(5, l.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// http://localhost:8080/fenix-web/faostat/config/NOTES/BY_DOMAIN/COMMONS/StatisticalNotes.css
	public void testReadFile() {
		try {
			String c = u.readFile(Q, "E");
			System.out.println(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testReadURLContent() {
		try {
			String c = u.readURLContent("/COMMONS/StatisticalNotes.css").toString();
			System.out.println(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testReadIndex() {
		try {
			u.readIndex("Q");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
}