/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ConnexionJDBC {
    
    
	public static final String URL = "jdbc:mysql://localhost:8889/lapepiteDB";
	public static final String LOGIN = "root";
	public static final String PASSWORD = "root";
        
	public static Connection getConnection() throws SQLException {
		
		Connection con = null;
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);
		
		return con;
		
	}

	
	
	
}
