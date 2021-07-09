package core;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
	
	private String phone;

	public Admin(int id, String firstName, String lastName, String email, String password, String phone) {
		super(id, firstName, lastName, email, password);
		this.phone = phone;
	}
	
	
	public Admin(String firstName, String lastName, String email, String password, String phone) {
		super(firstName, lastName, email, password);
		this.phone = phone;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Student> getPenalizedStudents(){
		List<Student> penalizedStudents = new ArrayList<Student>();		
		for(int i = 0; i < DB.acquisitions.size(); i++) {
			if(DB.acquisitions.get(i).getDeadline().isBefore(LocalDateTime.now()))
				penalizedStudents.add(DB.acquisitions.get(i).getRequest().getStudent());
		}
		return penalizedStudents;
	}

}
