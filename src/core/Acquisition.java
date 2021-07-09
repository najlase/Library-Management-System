package core;
import java.time.LocalDateTime;

public class Acquisition {

	private int id;
	private static int count;
	private LocalDateTime deadline;
	private boolean isReturned;
	private BookRequest request;
	
	public Acquisition(LocalDateTime localDateTime, BookRequest request) {
		this.id = ++count;
		this.deadline = localDateTime;
		this.isReturned = false;
		this.request = request;
	}

	public int getId() {
		return id;
	}

	public static int getCount() {
		return count;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public BookRequest getRequest() {
		return request;
	}

	public void setRequest(BookRequest request) {
		this.request = request;
	}
	
	public void cancel() {
		boolean found = false;
		int i = 0;
		while(!found && i < DB.acquisitions.size()) {
			found = (this == DB.acquisitions.get(i));
			i++;
		}
		DB.acquisitions.remove(i);
	}
}
