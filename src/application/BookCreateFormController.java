package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.Author;
import core.Book;
import core.Specialty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class BookCreateFormController implements Initializable{

	@FXML
	private Button backButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button clearButton;
	@FXML
	private TextField title;
	@FXML
	private TextField authors;
	@FXML
	private ChoiceBox<String> specialty;
	@FXML
	private TextField copies;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> specialtyNames = new ArrayList<String>();
		for(Specialty specialtyValue: Specialty.values()) {
			specialtyNames.add(specialtyValue.name());
		}
		specialty.setItems(FXCollections.observableArrayList(specialtyNames));
	}
	
	//On action of save button
	public void createBook() throws IOException {
		List<Author> authorsList = new ArrayList<Author>();
		for(String author: authors.getText().split(",")) {
			authorsList.add(new Author(author));
		}
		Book newBook = new Book(title.getText(), authorsList, Specialty.valueOf((String) specialty.getValue()));
		newBook.addBook();
		newBook.addCopies(Integer.parseInt(copies.getText()));
		
		Main m = new Main();
		m.changeScene("Books.fxml");
	}
	
	public void clear() {
		title.setText("");
		authors.setText("");
		specialty.setValue(null);
		copies.setText("");
	}
	
	public void backToHome(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Books.fxml");
	}
}