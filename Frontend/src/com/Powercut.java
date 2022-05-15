package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Powercut {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/powercuts",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

		
	public String insertPowercut(String lineNo, String areaNo, String areaName, String startTime, String endTime, String date, String reason) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into powercutdetails(`ID`,`lineNo`,`areaNo`,`areaName`, `startTime`,`endTime`,`date`,`reason`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, lineNo);
			preparedStmt.setString(3, areaNo);
			preparedStmt.setString(4, areaName);
			preparedStmt.setString(5, startTime);
			preparedStmt.setString(6, endTime);
			preparedStmt.setString(7, date);
			preparedStmt.setString(8, reason);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPowercut = readPowercut();
			output = "{\"status\":\"success\", \"data\": \"" + newPowercut + "\"}"; 
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPowercut() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			
			output = "<table border=\"1\"><tr><th>ID</th><th>Line No</th><th>Area No</th><th>Area Name</th><th>Start Time</th><th>End Time No</th><th>date</th><th>Reason</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from powercutdetails";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("ID"));
				String lineNo = rs.getString("lineNo");
				String areaNo = rs.getString("areaNo");
				String areaName = rs.getString("areaName");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String date = rs.getString("date");
				String reason = rs.getString("reason");

				// Add into the html table
				
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate'type='hidden' value='" + ID + "'>"+ lineNo + "</td>"; 
				output += "<td>" + areaNo + "</td>";
				output += "<td>" + areaName + "</td>";
				output += "<td>" + startTime + "</td>";
				output += "<td>" + endTime + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + reason + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + ID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + ID + "'></td></tr>"; 
			}
			con.close();
			
			// Complete the html table
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the powercut details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePowercut(String ID, String lineNo, String areaNo, String areaName, String startTime, String endTime, String date, String reason) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			
			String query = "UPDATE powercutdetails SET lineNo=?,areaNo=?,areaName=?,startTime=?,endTime=?,date=?,reason=?" + "WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, lineNo);
			preparedStmt.setString(2, areaNo);
			preparedStmt.setString(3, areaName);
			preparedStmt.setString(4, startTime);
			preparedStmt.setString(5, endTime);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, reason);
			preparedStmt.setInt(8, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPowercut = readPowercut();
			output = "{\"status\":\"success\", \"data\": \"" + newPowercut + "\"}";
			
		} catch (Exception e) {
			output = "Error while updating the powercut details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePowercut(String ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			
			String query = "delete from powercutdetails where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPowercut = readPowercut();
			output = "{\"status\":\"success\", \"data\": \"" + newPowercut + "\"}"; 

			
		
		} catch (Exception e) {
			output = "Error while deleting the powercut details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	

}
