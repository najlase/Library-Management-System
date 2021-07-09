package core;
import java.time.LocalDateTime;

public class BookRequest {
	
	private int id;
	private static int count;
	private Student student;
	private BookCopy bookCopy;
	private LocalDateTime date;
	private RequestStatus status;
	
	public BookRequest(Student student, BookCopy bookCopy) {
		this.id = ++count;
		this.student = student;
		this.bookCopy = bookCopy;
		this.date = LocalDateTime.now();
		this.status = RequestStatus.PENDING;
	}

	public int getId() {
		return id;
	}

	public static int getCount() {
		return count;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	public Acquisition confirm() {
		this.status = RequestStatus.ACCEPTED;
		Acquisition newAcquisition = new Acquisition(this.date.plusDays(15), this);
		
		DB.bookRequestNotifications.add(new BookRequestNotification(this));
		DB.deadlineNotifications.add(new DeadlineNotification(newAcquisition));
		
		return newAcquisition;
	}
	
	public void reject() {
		this.status = RequestStatus.REJECTED;
		DB.bookRequestNotifications.add(new BookRequestNotification(this));
	}
	
	public void cancel() {
		boolean found = false;
		int i = 0;
		while(!found && i < DB.bookRequests.size()) {
			found = (this == DB.bookRequests.get(i));
			i++;
		}
		DB.bookRequests.remove(i);
	}
	
	public Book getBook() {
		boolean found = false;
		int j = 0;
		while(!found && j < DB.books.size()) {
			int k = 0;
			while(!found && k < DB.books.get(j).getCopies().size()) {
				found = this.getBookCopy().getRef() == DB.books.get(j).getCopies().get(k).getRef();
				k++;
			}
			j++;
		}
		return DB.books.get(j);
	}

}
