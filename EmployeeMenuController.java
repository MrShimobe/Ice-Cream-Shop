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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeMenuController implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private Button manageBtn;

	@FXML
	private Button changeMenuBtn;

	@FXML
	private Button salesNumbersBtn;

	@FXML
	private Button exit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	public void handle(ActionEvent event) {

		manageBtn.setOnAction(e -> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("EmployeeManage.fxml"));

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
			closeWindow(manageBtn);

		});

		changeMenuBtn.setOnAction(e -> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("MenuConfigurations.fxml"));

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
			closeWindow(changeMenuBtn);

		});

		exit.setOnAction(e -> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(EmployeeMenuController.class.getResource("IceCreamStartUp.fxml"));

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
			closeWindow(exit);

		});

	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}
}
