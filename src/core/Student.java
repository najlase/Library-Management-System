package core;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {

	private Specialty specialty;
	private String level;

	public Student(int id, String firstName, String lastName, String email, String password, Specialty specialty,
			String level) {
		super(id, firstName, lastName, email, password);
		this.specialty = specialty;
		this.level = level;
	}
	
	public Student(String firstName, String lastName, String email, String password, Specialty specialty,
			String level) {
		super(firstName, lastName, email, password);
		this.specialty = specialty;
		this.level = level;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public BookRequest borrow(Book book) {
		BookRequest request = new BookRequest(this, book.getAvailableCopy());
		DB.bookRequests.add(request);
		return request;
	}

	public List<Book> getBookedBooks() {
		List<Book> myBookedBooks = new ArrayList<Book>();
		for (int i = 0; i < DB.bookRequests.size(); i++) {
			BookRequest request = DB.bookRequests.get(i);
			if (request.getStudent().getId() == this.id && request.getStatus() == RequestStatus.ACCEPTED)
				myBookedBooks.add(request.getBook());
		}
		return myBookedBooks;
	}

	public List<Book> getRequestedBooks() {
		List<Book> myRequestedBooks = new ArrayList<Book>();
		for (int i = 0; i < DB.bookRequests.size(); i++) {
			BookRequest request = DB.bookRequests.get(i);
			if (request.getStudent().getId() == this.id && request.getStatus() == RequestStatus.PENDING)
				myRequestedBooks.add(request.getBook());
		}
		return myRequestedBooks;
	}

}
