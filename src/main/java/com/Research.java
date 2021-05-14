package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;

public class Research {
	
	
		
		//A common method to connect to the DB
		private Connection connect(){
			Connection con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_research", "root", "");
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return con;
		}
			
		
		
		//Insert Project Details
		public String insertpaper(String name, String description,String leader,String date,String campus)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " INSERT INTO paper (`id`, `name`, `description`, `leader`, `date`, `campus`) VALUES (?, ?, ?, ?, ?, ?);";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1,0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, description);
		 preparedStmt.setString(4, leader);
		 preparedStmt.setString(5, date);
		 preparedStmt.setString(6, campus);
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newProj = readpaper(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the project.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readpaper(){
			String output = "";
			try{
					Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Research ID</th><th>Research paper Title</th>" +
					 "<th>Research Paper Description</th>" +
					 "<th>Leaders Name</th>" +
					 "<th>Research Paper Published Date</th>" +
					 "<th>University</th>" +
					
					 "<th>Update</th><th>Remove</th></tr>";
	
				 String query = "select * from paper";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String id = Integer.toString(rs.getInt("id"));
					 String name= rs.getString("name");
					 String description = rs.getString("description");
					 String member = rs.getString("leader");
					 String pdate = rs.getString("date");
					 String campus = rs.getString("campus");
			
	
					 
					 // Add into the html table
					 
					 output += "<tr><td>" + id+ "</td>";
					 output += "<td>" + name + "</td>";
					 output += "<td>" + description + "</td>";
					 output += "<td>" + member + "</td>";
					 output += "<td>" + pdate + "</td>";
					 output += "<td>" + campus + "</td>";
					
					
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + id + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + id + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the Projects.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updatepaper(String rid, String rname, String rdescription, String rmember, String rdate, String rcampus){ 
			String output = ""; 
			try{
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				 String query = "UPDATE paper SET name=?,description=?,leader=?,date=?,campus=? WHERE id=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, rname);
				 preparedStmt.setString(2,rdescription);
				 preparedStmt.setString(3, rmember);
				 preparedStmt.setString(4, rdate);
				 preparedStmt.setString(5, rcampus);
			
				 preparedStmt.setInt(6,Integer.parseInt(rid));
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newProj = readpaper(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deletepaper(String ID) { 
			String output = ""; 
			try{ 
				Connection con = connect(); 
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
					String query = "delete from paper where id=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ID)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newUser = readpaper(); 
					output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}