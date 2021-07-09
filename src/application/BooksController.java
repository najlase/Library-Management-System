package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.Book;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class BooksController implements Initializable {

	@FXML
	private TableView<Book> booksTable;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorsColumn;
	@FXML
	private TableColumn<Book, String> availableCopiesColumn;
	@FXML
	private TableColumn<Book, Pane> actionsColumn;
	@FXML
	private TextField filter;
	@FXML
	private Button searchButton;
	@FXML
	private Button backButton;
	@FXML
	private Button addBookButton;

	public static Book selectedBook = null;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		availableCopiesColumn.setCellValueFactory(bookCell -> new SimpleStringProperty(bookCell.getValue().getAvailableCopiesCount() + "/" + bookCell.getValue().getCopies().size()));
		authorsColumn.setCellValueFactory(bookCell -> new SimpleStringProperty(bookCell.getValue().getAuthorsNames()));
		actionsColumn.setCellValueFactory(bookCell -> setupActions(bookCell));
		
		
		booksTable.setItems(FXCollections.observableArrayList(Book.getAllBooks()));
	}
	
	public void search(ActionEvent event) {
		booksTable.setItems(FXCollections.observableArrayList(Book.filterByTitle(filter.getText())));
	}
	
	private ObservableValue<Pane> setupActions(CellDataFeatures<Book, Pane> bookCell) {
		Pane pane = new Pane();
		Button editButton = new Button("edit");
		Button deleteButton = new Button("delete");
		
		editButton.relocate(10, 0);
		deleteButton.relocate(60, 0);
		
		deleteButton.setOnMouseClicked(event -> {
			bookCell.getValue().deleteBook();
			booksTable.getItems().remove(bookCell.getValue());
		});
		editButton.setOnMouseClicked(event -> {
			BooksController.selectedBook = bookCell.getValue();
			Main m = new Main();
			try {
				m.changeScene("BookEditForm.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		pane.getChildren().addAll(editButton, deleteButton);
		return new ReadOnlyObjectWrapper<Pane>(pane);
	}
	
	public void navigateToAddBookForm() throws IOException {
		Main m = new Main();
		m.changeScene("BookCreateForm.fxml");
	}
	
	public void backToHome(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Home.fxml");
	}

}