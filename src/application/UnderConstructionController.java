package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UnderConstructionController{


	@FXML
	private Button backButton;
	
	public void backToHome(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Home.fxml");
	}

}