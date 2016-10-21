import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IceCreamController extends Application implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private Button customerBtn;

	@FXML
	private Button staffBtn;

	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(IceCreamController.class.getResource("IceCreamStartUp.fxml"));

		try {
			view = (AnchorPane) loader.load();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Scene scene = new Scene(view);
		primaryStage.setTitle("Ice Cream Shop");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	public void handle(ActionEvent event) {
		customerBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("Customer.fxml"));

			try {
				view = (AnchorPane) loader.load();
				closeWindow(customerBtn);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Customer Information");
			stage.setScene(scene);
			stage.show();

		});

		staffBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("Employee.fxml"));

			try {
				view = (AnchorPane) loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Employee Menu");
			stage.setScene(scene);
			stage.show();

		});

	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

}
