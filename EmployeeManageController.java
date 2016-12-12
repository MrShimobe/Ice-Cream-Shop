import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeManageController implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private TextField textFieldSearch;

	@FXML
	private Button addBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private Button exitBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private ListView listView;

	ObservableList list = FXCollections.observableArrayList();

	ConnectorClass connector = new ConnectorClass();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		String query = "select * from employee";
		ResultSet rset;

		try {
			rset = connector.stmt.executeQuery(query);

			while (rset.next()) {
				list.add(rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));
			}

			String[] array = new String[list.size()];
			array = (String[]) list.toArray(array);

			selectionSort(array);
			list = FXCollections.observableArrayList((Arrays.asList(array)));

			listView.setItems(list);

			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handle(ActionEvent event) {
		searchBtn.setOnAction(e -> {
			String query = "select * from employee";
			if (textFieldSearch.getText().isEmpty()) {
				query = "select * from employee;";

				try {
					ResultSet rset = connector.stmt.executeQuery(query);
					list.clear();

					while (rset.next())
						list.add(rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			else {
				try {
					ResultSet rset1 = connector.stmt2
							.executeQuery(query + " where firstName = '" + textFieldSearch.getText() + "';");

					ResultSet rset2 = connector.stmt3
							.executeQuery(query + " where lastName = '" + textFieldSearch.getText() + "';");
					ResultSet rset3 = connector.stmt4
							.executeQuery(query + " where userName = '" + textFieldSearch.getText() + "';");

					if (rset1.next()) {
						list.clear();
						loopThrough(rset1);
						listView.setItems(list);
					} else if (rset2.next()) {
						list.clear();
						loopThrough(rset2);
						listView.setItems(list);
					} else if (rset3.next()) {
						list.clear();
						loopThrough(rset3);
						listView.setItems(list);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Ice-Cream Shop");
						alert.setHeaderText("Information");
						alert.setContentText("There is no such employee");
						alert.showAndWait();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		addBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("addEmployee.fxml"));

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
			closeWindow(addBtn);

		});

		removeBtn.setOnAction(e -> {
			String option = (String) listView.getSelectionModel().getSelectedItem();
			if (option.equals(null)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("No Employee is selected for deletion");
				alert.showAndWait();
			} else {
				String[] person = option.split("[ ]");

				String query = "delete from employee where firstName = '" + person[0] + "' and lastName = '" + person[1]
						+ "';";

				try {
					connector.stmt.execute(query);

					ResultSet rset = connector.stmt.executeQuery("select * from employee");

					list.clear();

					while (rset.next())
						list.add(rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));

					listView.setItems(list);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

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

	public static void selectionSort(String[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				// "<" changed to use of compareTo()
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// int changed to String
			String temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	private void loopThrough(ResultSet rset) throws SQLException {

		list.add(rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));

		while (rset.next()) {
			list.add(rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));
		}
	}

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

}