package core;
import java.util.*;

public class DB {
	public static List<Book> books = new ArrayList<Book>() {
		{
			/*add(new Book("Advanced programming", new String[] { "K.James" }, Specialty.SWE, new ArrayList<BookCopy>() {
				{
					add(new BookCopy());
					add(new BookCopy());
				}
			}));
			add(new Book("Marketing strategies", new String[] { "J.Northen", "I.Litt" }, Specialty.MARKETING,
					new ArrayList<BookCopy>() {
						{
							add(new BookCopy());
						}
					}));
			add(new Book("Administration and management", new String[] { "L.Martin" }, Specialty.MANAGEMENT,
					new ArrayList<BookCopy>() {
						{
							add(new BookCopy());
							add(new BookCopy());
							add(new BookCopy());
						}
					}));*/

		}
	};
	
	public static List<BookRequest> bookRequests = new ArrayList<BookRequest>() {
		{
		}
	};

	public static List<Acquisition> acquisitions = new ArrayList<Acquisition>() {
		{
		}
	};
	
	public static List<DeadlineNotification> deadlineNotifications = new ArrayList<DeadlineNotification>() {
		{
		}
	};
	
	public static List<BookRequestNotification> bookRequestNotifications = new ArrayList<BookRequestNotification>() {
		{
		}
	};
}
