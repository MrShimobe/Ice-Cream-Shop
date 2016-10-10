import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	private AnchorPane view;

	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainClass.class.getResource("IceCreamStartUp.fxml"));

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
}