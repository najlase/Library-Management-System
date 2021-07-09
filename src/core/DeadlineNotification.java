package core;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DeadlineNotification implements Notification {

	private Acquisition acquisition;

	public DeadlineNotification(Acquisition acquisition) {
		this.acquisition = acquisition;
	}

	public void show() {

	}

	public void hide() {

	}

	@Override
	public String getMessageForStudent() {
		LocalDateTime d1 = this.acquisition.getDeadline();
		LocalDateTime d2 = LocalDateTime.now();
		long diff = ChronoUnit.DAYS.between(d2, d1);
			return "You have " + diff + " days until the deadline of your request of the book: " + this.acquisition.getRequest().getBook();
	}
	
	@Override
	public String getMessageForAdmin() {
			return "User with email " + this.acquisition.getRequest().getStudent().getEmail() + " has exceeded the deadline";
	}
	
	@Override
	public String getMessage(int role) {
		if(role == 0) {
			return getMessageForStudent();
		}
		else if(role == 1) {
			return getMessageForAdmin();
		}
		return null;
	}

}
