package main;

import java.sql.Connection;

import mysqlconnection.connection;

public class Main {
	
	public Main() {
		
		Connection conn = connection.connection();
		new Menu();
	
	}

	public static void main(String[] args) {
		
		new Main();

	}

}
