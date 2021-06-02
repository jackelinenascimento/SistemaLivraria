package br.com.sistemalivraria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDFunctions {

	public static Connection conexaoBD() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
		
	}
}
