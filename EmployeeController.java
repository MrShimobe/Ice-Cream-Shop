import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	Connection connection;
	Statement statement;
	ResultSet set;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "1224qwe.");
			statement = connection.createStatement();
			statement.execute("use employee");
			set = statement.executeQuery("select * from employee");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Not found");
		}

		System.out.println("Driver loaded");
	        try {
				while (set.next()){
				    try {
						System.out.println(set.getString(1) +  set.getString(2) +  set.getString(3));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

			else if (textFieldId.getText().equals("olopez") && (textFieldPass.getText().equals("999999"))) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(IceCreamController.class.getResource("EmployeeMenu.fxml"));

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
	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

}
