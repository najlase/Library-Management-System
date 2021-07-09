package core;

public interface Notification {

	void show();
	void hide();
	String getMessage(int role);
	String getMessageForStudent();
	String getMessageForAdmin();
}
