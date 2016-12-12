import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private TextField textFieldId;

	@FXML
	private PasswordField textFieldPass;

	@FXML
	private Button continueBtn;

	@FXML
	private Button exitBtn;

	ConnectorClass connector = new ConnectorClass();
	HashMap<String, String> users;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		users = new HashMap<String, String>();

		String query = "select * from employee";
		ResultSet rset;

		try {
			rset = connector.stmt.executeQuery(query);

			while (rset.next()) {
				users.put(rset.getString(1), rset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handle(ActionEvent event) {
		continueBtn.setOnAction(e -> {

			if (textFieldId.getText().isEmpty() || textFieldPass.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("Please enter your employee credentials");
				alert.showAndWait();
			}

			else if (checkEmployee()) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(EmployeeController.class.getResource("EmployeeMenu.fxml"));

				try {
					view = (AnchorPane) loader.load();
					closeWindow(continueBtn);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scene scene = new Scene(view);
				Stage stage = new Stage();
				stage.setTitle("Employee Menu");
				stage.setScene(scene);
				stage.show();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("The information you have entered is wrong, please try again");
				alert.showAndWait();
			}
		});

		exitBtn.setOnAction(e -> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("IceCreamStartUp.fxml"));

			try {
				view = (AnchorPane) loader.load();
			} catch (IOException ex) {

				ex.printStackTrace();
			}

			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Ice Cream Shop");
			stage.setScene(scene);
			stage.show();
			closeWindow(exitBtn);

		});
	}

	private boolean checkEmployee() {

		Iterator<String> iter = users.keySet().iterator();

		System.out.println();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + " " + users.get(key));
			if (textFieldId.getText().equals(key) && textFieldPass.getText().equals(users.get(key)))
				return true;
		}
		return false;
	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

}
