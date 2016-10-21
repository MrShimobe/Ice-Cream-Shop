import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerController implements EventHandler<ActionEvent> {

	protected AnchorPane view;

	protected Customer customer;

	protected Parent root;

	@FXML
	protected CustomerController customerController;

	@FXML
	protected MenuController menuController;

	@FXML
	protected OrderController orderController;

	@FXML
	protected TextField firstNameTextField;

	@FXML
	protected TextField lastNameTextField;

	@FXML
	protected Button continueBtn;

	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CustomerController.class.getResource("Menu.fxml"));

		if(checkDataFields()){continueBtn.setOnAction(e -> {

			try {
				customer = new Customer(firstNameTextField.getText(), lastNameTextField.getText());
				root = (Parent) loader.load();
				MenuController menuController = (MenuController) loader.getController();
				menuController.setCustomer(customer);

				closeCurrentWindow(continueBtn);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			view = (AnchorPane) root;
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Customer Information");
			stage.setScene(scene);
			stage.show();
		}); }
	}

	private boolean checkDataFields() {
		if(firstNameTextField.getText().isEmpty() && lastNameTextField.getText().isEmpty()){
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Ice-Cream Shop");
	        alert.setHeaderText("Information");
	        alert.setContentText("Please enter your first and last name");
	        alert.showAndWait();
	        return false;
		}
		return true;
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}