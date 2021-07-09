package core;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Book {

	private int id;
	private String title;
	private List<Author> authors = new ArrayList<Author>();
	private Specialty specialty;
	private List<BookCopy> copies = new ArrayList<BookCopy>();
	
	public Book(int id, String title, List<Author> authors, Specialty specialty, List<BookCopy> copies) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.specialty = specialty;
		this.copies = copies;
	}

	
	public Book(String title, List<Author> authors, Specialty specialty) {
		this.title = title;
		this.authors = authors;
		this.specialty = specialty;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<BookCopy> getCopies() {
		return copies;
	}

	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}
	
	

	public void addBook() {
		String query;
		Statement smt;
		try {
			smt = DbConnection.con.createStatement();
			ResultSet rs;
			query = "INSERT INTO book (title, specialty) VALUES ( '" + this.getTitle() + "' , '" + this.getSpecialty() + "'); ";
			smt.execute(query, Statement.RETURN_GENERATED_KEYS); 
			rs = smt.getGeneratedKeys();
			int idBook = -1;
			if(rs.next()) {
				idBook = rs.getInt(1);
				this.setId(idBook);
			}
			
			for(int i = 0; i < this.authors.size(); i++) {
				this.authors.get(i).addAuthor();

				query = "INSERT INTO bookauthor (idBook, idAuthor) VALUES (" + idBook + " , " + this.authors.get(i).getId() + ");";
				smt.execute(query, Statement.RETURN_GENERATED_KEYS); 
			}
			
			rs.close();
			smt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook() {
		String query;
		Statement smt;
		try {
			smt = DbConnection.con.createStatement();
			query = "UPDATE book SET title='" + this.title + "' , specialty='" + this.specialty + "' WHERE id = " + this.id;
			smt.execute(query);
			smt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteBook() {
		String query;
		Statement smt;
		try {
			smt = DbConnection.con.createStatement();
			
			for(int i = 0; i < this.authors.size(); i++) {
				query = "DELETE FROM bookauthor WHERE idBook = " + this.id;
				smt.execute(query); 
				
				this.authors.get(i).deleteAuthor();
			}
			
			query = "DELETE FROM bookCopy WHERE idBook = " + this.id;
			smt.execute(query); 
			
			query = "DELETE FROM book WHERE id = " + this.id;
			smt.execute(query); 
			
			smt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void addCopies(int total) {
		for (int i = 0; i < total; i++) {
			BookCopy copy = new BookCopy(this);
			copy.addBookCopy();
			this.copies.add(copy);
		}

	}
	
	
	@SuppressWarnings("finally")
	public static List<Book> filterByTitle(String title) {
		List<Book> booksList = new ArrayList<Book>();
		String query1, query2, query3;
		Statement smt1, smt2, smt3;
		try {
			smt1 = DbConnection.con.createStatement();
			smt2 = DbConnection.con.createStatement();
			smt3 = DbConnection.con.createStatement();
			ResultSet rs1, rs2, rs3;
			query1 = "SELECT * FROM book WHERE title LIKE '%" + title + "%' ORDER BY id DESC";
			rs1 = smt1.executeQuery(query1);
			while (rs1.next()) {
				List<Author> authorsList = new ArrayList<Author>();
				query2 = "SELECT author.id, author.fullName FROM author, bookauthor WHERE author.id = bookauthor.idAuthor AND bookauthor.idBook = " + rs1.getInt("id");
				rs2 = smt2.executeQuery(query2);
				while(rs2.next()) {
					authorsList.add(new Author(rs2.getInt("id"), rs2.getString("fullName")));
				}
				List<BookCopy> copiesList = new ArrayList<BookCopy>();
				query3 = "SELECT * FROM bookcopy WHERE idBook = " + rs1.getInt("id");
				rs3 = smt3.executeQuery(query3);
				while(rs3.next())
					copiesList.add(new BookCopy(rs3.getInt("ref") ,rs3.getBoolean("isAvailable")));
				
				booksList.add(new Book(rs1.getInt("id"), rs1.getString("title"), authorsList, Specialty.valueOf(rs1.getString("specialty")), copiesList));		
			}

			
			smt3.close();

			smt2.close();
			rs1.close();
			smt1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return booksList;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Book> getAllBooks() {
		List<Book> booksList = new ArrayList<Book>();
		String query1, query2, query3;
		Statement smt1, smt2, smt3;
		try {
			smt1 = DbConnection.con.createStatement();
			smt2 = DbConnection.con.createStatement();
			smt3 = DbConnection.con.createStatement();
			ResultSet rs1, rs2, rs3;
			query1 = "SELECT * FROM book ORDER BY id DESC";
			rs1 = smt1.executeQuery(query1);
			while (rs1.next()) {
				List<Author> authorsList = new ArrayList<Author>();
				query2 = "SELECT author.id, author.fullName FROM author, bookauthor WHERE author.id = bookauthor.idAuthor AND bookauthor.idBook = " + rs1.getInt("id");
				rs2 = smt2.executeQuery(query2);
				while(rs2.next()) {
					authorsList.add(new Author(rs2.getInt("id"), rs2.getString("fullName")));
				}
				List<BookCopy> copiesList = new ArrayList<BookCopy>();
				query3 = "SELECT * FROM bookcopy WHERE idBook = " + rs1.getInt("id");
				rs3 = smt3.executeQuery(query3);
				while(rs3.next())
					copiesList.add(new BookCopy(rs3.getInt("ref") ,rs3.getBoolean("isAvailable")));
				
				booksList.add(new Book(rs1.getInt("id"), rs1.getString("title"), authorsList, Specialty.valueOf(rs1.getString("specialty")), copiesList));		
			}

			
			smt3.close();

			smt2.close();
			rs1.close();
			smt1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return booksList;
		}
	}
	
	
	
	
	public BookCopy getAvailableCopy() {
		boolean isAvailable = false;
		int i =0;
		while(!isAvailable && i < this.copies.size()) {
			isAvailable = this.copies.get(i).isAvailable();
			i++;
		}
		if(isAvailable) {
			this.copies.get(i).setAvailable(false);
			return this.copies.get(i);
		}
		return null;
	}
	
	
	public int getAvailableCopiesCount() {		
		int count = 0;
		for(int i =0; i < this.copies.size(); i++) {
			if(this.copies.get(i).isAvailable())
				count++;
		}
		return count;
	}
	
	
	
	public String getAuthorsNames() {
		String names = "";
		for(int i = 0; i < this.authors.size(); i++) {
			names += this.authors.get(i).getFullName();
			if(i < this.authors.size() - 1)
				names += ", ";
		}
		if(names.length() == 0)
			return "N/A";
		
		return names;
	}
}
