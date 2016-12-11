import java.io.IOException;
import java.sql.ResultSet;
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

public class AddScoopController implements EventHandler<ActionEvent> {

	protected AnchorPane view;

	protected Customer customer;

	protected Parent root;

	@FXML
	TextField name;

	@FXML
	TextField id;

	@FXML
	Button addBtn;

	@FXML
	Button exitBtn;

	ConnectorClass connector = new ConnectorClass();

	public void handle(ActionEvent event) {

		addBtn.setOnAction(e -> {

			if (name.getText().isEmpty() || id.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("Please fill out all of the fields for the new scoop number");
				alert.showAndWait();
			} else
				try {
					if (id.getText().length() > 4 || id.getText().length() == 4 && checkdatabase()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Ice-Cream Shop");
						alert.setHeaderText("Information");
						alert.setContentText("The ID Number should be 4 characters long and not already in use");
						alert.showAndWait();
					} else {

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(CustomerController.class.getResource("MenuConfigurations.fxml"));

						String query = "insert into scoops value \n ('" + id.getText() + "', '" + name.getText()
								+ "', '" + 0 + "');";

						try {
							connector.stmt.execute(query);
							view = (AnchorPane) loader.load();
							closeCurrentWindow(addBtn);
						} catch (SQLException | IOException e1) {
							e1.printStackTrace();
						}

						Scene scene = new Scene(view);
						Stage stage = new Stage();
						stage.setTitle("Employee Menu");
						stage.setScene(scene);
						stage.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
		exitBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CustomerController.class.getResource("MenuConfigurations.fxml"));

			try {
				view = (AnchorPane) loader.load();
				closeCurrentWindow(addBtn);
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

	private boolean checkdatabase() throws SQLException {
		String query = "select * from scoops";
		ResultSet rset = connector.stmt.executeQuery(query);

		while (rset.next()) {
			if (id.getText().equals(rset.getString(1)))
				return true;

		}
		return false;
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}