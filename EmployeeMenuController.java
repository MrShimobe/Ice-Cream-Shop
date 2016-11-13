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
	private TextField textFieldId;

	@FXML
	private PasswordField textFieldPass;

	@FXML
	private TextArea textArea;

	ResultSet resultSet;

	Connection connection;
	Statement stmt;
	ResultSet set;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeDB();

		try {
			resultSet = stmt.executeQuery("select * from cscstudent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();

		try {
			while (resultSet.next()) {
				try {
					builder.append(resultSet.getString(1));
					builder.append(resultSet.getString(2));
					builder.append(resultSet.getString(3));
					builder.append(resultSet.getString(4));

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

	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "1224qwe.");
			System.out.print("Database Connectead");

			stmt = connection.createStatement();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

}
