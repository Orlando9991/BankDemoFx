package pt.rumos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
	//****************************************************************	
	//
	//	Connection Url = "jdbc:(dynamic 1):(dynamic 2)
	//	dynamic 1 - host (ex: mysql)
	//	dynamic 2 - location (ex: //localhost:3306/rumos_digital_bank)
	//_______________________________________________________________
	//
	//	(Execute Query) Query - (ex: select) 
	//	(Execute Update) Command - (ex: update, insert)
	//_______________________________________________________________
	//
	//	Start connection
	//	Statement creator
	//	Statement execution
	//	Process data
	//	Close connection	
	//
	//****************************************************************
	
	private String url;
	private String username; 
	private String password; 

	private static Database database;

	private Database() {
		Credentials credentials = Credentials.getInstance();
		setPassword(credentials.getPassword());
		setUrl(credentials.getUrl());
		setUsername(credentials.getUsername());
	}
	
	//Only one Database open
	public static Database getInstance() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}
	
	public boolean executeCommand(String sqlCommand) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(sqlCommand);
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
		
	public List <HashMap<String,Object>> executeQueryMultiRow(String sqlQuery) {
		
		List <HashMap<String,Object>> resultsList = new ArrayList<>();
		ResultSet resultSet = null;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			
			int columnSize = resultSet.getMetaData().getColumnCount();
			
			while(resultSet.next()) {
				HashMap<String, Object> row = new HashMap<>();
				for (int i = 1; i <= columnSize; i++) {
					row.put(resultSet.getMetaData().getColumnName(i),resultSet.getObject(i));
				}
				resultsList.add(row);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultsList;
	}
	
	public HashMap<String,Object> executeQuerySingleRow(String sqlQuery) {
		
		HashMap<String,Object> row = new HashMap<String, Object>();
		ResultSet resultSet = null;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			
			int columnSize = resultSet.getMetaData().getColumnCount();
			
			if(resultSet.next()) {
				for (int i = 1; i <= columnSize; i++) {
					row.put(resultSet.getMetaData().getColumnName(i),resultSet.getObject(i));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return row;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
}
