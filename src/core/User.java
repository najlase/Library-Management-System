package core;
import java.sql.*;

public abstract class User {

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;

	public User(int id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public static User login(String email, String password) {
		String query;
		Statement smt;
		try {
			smt = DbConnection.con.createStatement();
			ResultSet rs = null;
			query = " SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
			rs = smt.executeQuery(query);	
			if(!rs.next())
				return null;
			else if(rs.getString("role").compareTo("STUDENT") == 0) {
				smt =  DbConnection.con.createStatement();
				ResultSet rs2 = null;
				query = "SELECT * FROM student WHERE id = '" + rs.getInt("id") + "'";
				rs2 = smt.executeQuery(query);
				rs2.next();
				Student student =  new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), Specialty.valueOf(rs2.getString("specialty")), rs2.getString("level") );
				rs2.close();
				smt.close();
				return student;
			}
				smt =  DbConnection.con.createStatement();
				ResultSet rs3 = null;
				query = "SELECT * FROM admin WHERE id = '" + rs.getInt("id") + "'";
				rs3 = smt.executeQuery(query);
				rs3.next();
				Admin admin = new Admin(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs3.getString("phone") );
				rs.close();
				rs3.close();
				smt.close();
				return admin;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
