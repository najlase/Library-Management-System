package core;

public class BookRequestNotification implements Notification {

	private BookRequest request;

	public BookRequestNotification(BookRequest request) {
		this.request = request;
	}
	
	public void show() {
		
	}
	
	public void hide() {
		
	}
	
	@Override
	public String getMessageForStudent() {
		if(request.getStatus() == RequestStatus.ACCEPTED)
			return "your request for book: " + request.getBook().getTitle() + " was accepted.";
		else if(request.getStatus() == RequestStatus.REJECTED)
			return "your request for book: " + request.getBook().getTitle() + " was rejected.";
		else
			return null;
	}
	
	@Override
	public String getMessageForAdmin() {
		return "Student with" + this.request.getStudent().getEmail() + " has issued a new request";
	}
	
	@Override
	public String getMessage(int role) {
		if(role == 0) {
			return this.getMessageForStudent();
		}
		else if(role == 1) {
			return this.getMessageForAdmin();
		}
		return null;
	}
}
