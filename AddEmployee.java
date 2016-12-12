import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddEmployee implements EventHandler<ActionEvent> {

	protected AnchorPane view;

	protected Customer customer;

	protected Parent root;

	@FXML
	TextField firstName;

	@FXML
	TextField lastName;

	@FXML
	TextField userName;

	@FXML
	TextField password;

	@FXML
	TextField position;

	@FXML
	Button addBtn;

	ConnectorClass connector = new ConnectorClass();

	public void handle(ActionEvent event) {

		addBtn.setOnAction(e -> {

			if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty()
					|| password.getText().isEmpty() ||  position.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("Please fill out all of the fields for the new employee");
				alert.showAndWait();
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(CustomerController.class.getResource("EmployeeManage.fxml"));

				String query = "insert into employee value \n ('" + userName.getText() + "', '" + password.getText()
						+ "', '" + firstName.getText() + "', '" + lastName.getText() + "', '" + position.getText()
						+ "');";
				try {
					connector.stmt.execute(query);
					view = (AnchorPane) loader.load();
					closeCurrentWindow(addBtn);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}

			}
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Employee Menu");
			stage.setScene(scene);
			stage.show();
		});
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}