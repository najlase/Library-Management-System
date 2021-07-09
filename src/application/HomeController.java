package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

	@FXML
	private Button booksButton;
	@FXML
	private Button requestsButton;
	@FXML
	private Button studentsButton;
	@FXML
	private Button notificationsButton;
	@FXML
	private Button logoutButton;

	public void navigateToBooksPage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Books.fxml");
	}
	
	public void navigateToRequestsPage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("UnderConstruction.fxml");
	}
	
	public void navigateToStudentsPage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("UnderConstruction.fxml");
	}
	
	public void navigateToNotificationsPage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("UnderConstruction.fxml");
	}
	
	public void logout(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Login.fxml");
	}

}