package org.fao.fenix.web.modules.ipc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlUtils
{
	public static String toString(Element xml)
	{
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		return outputter.outputString(xml);
	}
	
	public static void write(Element xml, File file) throws Exception
	{
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		OutputStream out = new FileOutputStream(file);
		try
		{
			outputter.output(new Document((Element)xml.clone()), out);
			xml.detach();
		}
      catch (IOException e)
      {
      	try { out.close(); }
      	catch (IOException ioe) { ioe.printStackTrace(); }
      	throw e;
      }
	}
	
	public static void write(Element xml, OutputStream out) throws IOException
	{
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(new Document((Element)xml.clone()), out);
	}

	public static Element read(File file) throws Exception
	{
		SAXBuilder builder = new SAXBuilder();
		Document   doc     = builder.build(file);
		return (Element)doc.getRootElement().detach();
	}

}
