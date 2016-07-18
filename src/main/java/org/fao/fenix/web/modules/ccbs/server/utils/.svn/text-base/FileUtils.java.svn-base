package org.fao.fenix.web.modules.ccbs.server.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileUtils
{
	public static ArrayList<String> readLines(String path) throws IOException
	{
		return readLines(new File(path));
	}
	
	public static ArrayList<String> readLines(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try
		{
			return readLines(reader);
		}
		finally
		{
			reader.close();
		}
	}
	
	public static ArrayList<String> readLines(BufferedReader reader) throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		int i = 0;
		for (String line; (line = reader.readLine()) != null; )
		{
			if (line.trim().length() > 0 && !line.startsWith("#"))
				lines.add(line);
		}
		return lines;
	}

	public static void writeLines(String path, ArrayList<String> lines) throws IOException
	{
		writeLines(new File(path), lines);
	}
	
	public static void writeLines(File file, ArrayList<String> lines) throws IOException
	{
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		try     { writeLines(writer, lines); }
		finally { writer.close();            }
	}
	
	public static void writeLines(PrintWriter writer, ArrayList<String> lines)
	{
		for (int i = 0; i < lines.size(); i++)
			writer.println(lines.get(i));
	}
}
