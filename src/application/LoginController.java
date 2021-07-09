package application;

import java.io.IOException;

import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	
	public void login(ActionEvent event) throws IOException {
		checkLogin();
	}
	
	private void checkLogin() throws IOException {
		Main m = new Main();
		
		if(email.getText().isEmpty() || password.getText().isEmpty()) {
			wrongLogin.setText("Please enter your credentials.");
		}
		else {
			User user = User.login(email.getText().toString(), password.getText().toString());
			if(user == null)
				wrongLogin.setText("Wrong email or password!");
			else {
				wrongLogin.setText("Success!");
				
				m.changeScene("Home.fxml");
			}
		}

	}
	

}
