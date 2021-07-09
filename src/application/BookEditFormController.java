package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.BookCopy;
import core.Specialty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookEditFormController implements Initializable{

	@FXML
	private Button backButton;
	@FXML
	private Button saveButton;
	@FXML
	private TextField title;
	@FXML
	private ChoiceBox<String> specialty;
	@FXML
	private TextField copies;
	@FXML
	private TableView<BookCopy> copiesTable;
	@FXML
	private TableColumn<BookCopy, String> referenceColumn;
	@FXML
	private TableColumn<BookCopy, String> statusColumn;
	@FXML
	private Label successMessage;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> specialtyNames = new ArrayList<String>();
		for(Specialty specialtyValue: Specialty.values()) {
			specialtyNames.add(specialtyValue.name());
		}
		specialty.setItems(FXCollections.observableArrayList(specialtyNames));
		
		
		title.setText(BooksController.selectedBook.getTitle());
		specialty.setValue(BooksController.selectedBook.getSpecialty().name());
		copies.setText("0");
		
		referenceColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("ref"));
		statusColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().isAvailable() ? "Available" : "Not Available"));
		
		copiesTable.setItems(FXCollections.observableArrayList(BooksController.selectedBook.getCopies()));
	}
	
	//On action of save button
	public void editBook() throws IOException, InterruptedException {
		BooksController.selectedBook.setTitle(title.getText());
		BooksController.selectedBook.setSpecialty(Specialty.valueOf((String) specialty.getValue()));
		BooksController.selectedBook.updateBook();
		BooksController.selectedBook.addCopies(Integer.valueOf(copies.getText())); //Many to one relationship so changes made in BookCopy table only
		
		copiesTable.setItems(FXCollections.observableArrayList(BooksController.selectedBook.getCopies()));
		successMessage.setVisible(true);
	}
	
	public void backToHome(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Books.fxml");
	}
}