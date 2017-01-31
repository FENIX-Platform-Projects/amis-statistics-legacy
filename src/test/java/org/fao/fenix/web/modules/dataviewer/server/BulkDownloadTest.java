package org.fao.fenix.web.modules.dataviewer.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import junit.framework.TestCase;

public class BulkDownloadTest extends TestCase {

	public void testBulkDownload() {
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection("jdbc:sqlserver://FAOSTAT-PROD\\Production;databaseName=Warehouse;", 
													   "Warehouse", 
													   "w@reh0use");
			Statement stmt = c.createStatement();
			stmt.executeQuery("SELECT Source, FileName, FolderPath, FileContent, LanguageID FROM vBulkDownloads WHERE LanguageID = 'E' ");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				System.out.print(rs.getString(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getString(5));
				System.out.println();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}