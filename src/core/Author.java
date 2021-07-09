package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Author {
	private int id;
	private String fullName;

	public Author(int id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public Author(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void addAuthor() {
		String query;
		Statement smt;
		ResultSet rs;
		try {
			smt = DbConnection.con.createStatement();

			query = "INSERT INTO author (fullname) VALUES ('" + this.fullName + "'); ";
			smt.execute(query, Statement.RETURN_GENERATED_KEYS);
			rs = smt.getGeneratedKeys();

			if (rs.next()) {
				this.id = rs.getInt(1);
			}

			rs.close();
			smt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAuthor() {
		String query;
		Statement smt;
		ResultSet rs;
		try {
			smt = DbConnection.con.createStatement();

			query = "DELETE FROM author WHERE id = " + this.id;
			smt.execute(query, Statement.RETURN_GENERATED_KEYS);
			rs = smt.getGeneratedKeys();

			rs.close();
			smt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
