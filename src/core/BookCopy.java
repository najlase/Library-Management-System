package core;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookCopy {
	
	private long ref;
	private boolean isAvailable;
	private Book book;
	
	
	
	public BookCopy(long ref, boolean isAvailable) {
		this.ref = ref;
		this.isAvailable = isAvailable;
	}

	public BookCopy(long ref, Book book) {
		this.ref = ref;
		this.book = book;
	}

	public BookCopy(Book book) {
		this.isAvailable = true;
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getRef() {
		return ref;
	}
	public void setRef(long ref) {
		this.ref = ref;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	
public void addBookCopy() {
		String query;
		Statement smt;
		try {
			smt = DbConnection.con.createStatement();
			ResultSet rs;
			query = "INSERT INTO bookcopy (idBook, isAvailable) VALUES (" + this.getBook().getId() + " , " + this.isAvailable() + ")";
			smt.execute(query, Statement.RETURN_GENERATED_KEYS);
			rs = smt.getGeneratedKeys();
			int ref = -1;
			if(rs.next()) {
				ref = rs.getInt(1);
				this.setRef(ref);
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
