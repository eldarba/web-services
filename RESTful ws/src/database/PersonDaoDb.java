package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoDb {

	static {
		String driverName = "org.apache.derby.jdbc.ClientDriver";
		try {
			Class.forName(driverName);
			System.out.println("database driver loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	String url = "jdbc:derby://localhost:1527/person_db";

	public void addPerson(Person person) throws SQLException {
		String sql = "insert into person(id, name, age) values(?,?,?)";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, person.getId());
			pstmt.setString(2, person.getName());
			pstmt.setInt(3, person.getAge());
			pstmt.executeUpdate();
			System.out.println("added: " + person + " to the database");
		}
	}

	public Person getPerson(int personId) throws SQLException {
		String sql = "select * from person where id = ?";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, personId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				return person;
			} else {
				return null;
			}
		}
	}

	public List<Person> getAllPeople() throws SQLException {
		String sql = "select * from person";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Person> list = new ArrayList<>();
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				list.add(person);
			}
			return list;
		}
	}

	public String updatePerson(Person person) throws SQLException {
		String sql = "update person set name=?, age=? where id=?";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setInt(3, person.getId());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return "updated: " + person + " in the database";
			} else {
				return "not found: : " + person;
			}
		}
	}

	public String deletePerson(int personId) throws SQLException {
		String sql = "delete from person where id = ?";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, personId);
			int rows = pstmt.executeUpdate();
			return rows + " people deleted";
		}
	}

	public String deleteAll() throws SQLException {
		String sql = "delete from person";
		try (Connection con = DriverManager.getConnection(url);) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			int rows = pstmt.executeUpdate();
			return rows + " people deleted";
		}
	}

}
