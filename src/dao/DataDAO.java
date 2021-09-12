package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import mysqlconnection.connection;

public class DataDAO {
	
	public Connection conn; 
	public Statement stmt;
	public ResultSet rs;
	public PreparedStatement ps;
	Random rand = new Random();
	
	public DataDAO() {
		
		try {
			initConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initConn() throws SQLException {
		
		conn = connection.connection(); 
		if (conn == null) {
			throw new SQLException("conn");
		}
		
	}
	
	public void insertNewData (String name, Integer price, Integer stock) {
		
		try {
			stmt = conn.createStatement();
			String mysql = "INSERT INTO `databoba`(`Code`, `Name`, `Price`, `Stock`) VALUES ('" + randId() + "','" + name + "','" + price + "','" + stock + "')";
			stmt.executeUpdate(mysql);
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
	}
	
	public void updateData (String code, String name, Integer price, Integer stock) {
		
		try {
			stmt = conn.createStatement();
			String mysql = "UPDATE `databoba` SET `Name`='" + name + "',`Price`='" + price + "',`Stock`='" + stock + "' WHERE Code = '" + code + "'";
			stmt.executeUpdate(mysql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteData (String code) {
		
		try {
			stmt = conn.createStatement();
			String mysql = "DELETE FROM `databoba` WHERE Code = '" + code + "'";
			stmt.executeUpdate(mysql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Vector<Vector<Object>> getData(){
		
		Vector<Vector<Object>> data = new Vector<>();
		
		try {
			stmt = conn.createStatement();
			String mysql = "SELECT * FROM `databoba` WHERE 1";
			rs = stmt.executeQuery(mysql);
			
			while (rs.next()) {
				
				Vector<Object> row = new Vector<>();
				
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getInt(3));
				row.add(rs.getInt(4));
				
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public ResultSet search(String searchForString) {
		
		try {
			ps = conn.prepareStatement("SELECT * FROM `databoba` WHERE Code = ? or Name = ?");
			ps.setString(1, searchForString);
			ps.setString(2, searchForString);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String randId() {
		String newId = "";
		
		try {
			Statement stmt = conn.createStatement();
			int random = rand.nextInt(1000);
			newId = String.format("BC-%03d", random);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newId;
	}

}

