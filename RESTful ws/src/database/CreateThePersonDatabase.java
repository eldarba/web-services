package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateThePersonDatabase {

	public static void main(String[] args) {
		System.out.println("creating the person database");
		String url = "jdbc:derby://localhost:1527/person_db;create=true";
		String sql = "CREATE TABLE person(id INT PRIMARY KEY, name VARCHAR(25), age INT)";
		try(Connection con = DriverManager.getConnection(url)){
			System.out.println("connected to: " + url);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("executed successfuly");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
