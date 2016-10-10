import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldPass;

	@FXML
	private Button continueBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(ActionEvent event) {
		continueBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("EmployeeMenu.fxml"));

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
		
		textFieldId.setOnAction(e -> {
		});
		
		textFieldPass.setOnAction(e -> {
		});
	}
}
