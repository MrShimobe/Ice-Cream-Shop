import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageConfigurationsController implements Initializable, EventHandler<ActionEvent> {

	private AnchorPane view;

	@FXML
	private ComboBox choice;

	@FXML
	private Button applyBtn;

	@FXML
	private Button addBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private Button exitBtn;

	@FXML
	private ListView listView;

	ObservableList list = FXCollections.observableArrayList();

	ObservableList list2 = FXCollections.observableArrayList();

	ConnectorClass connector = new ConnectorClass();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list2.addAll("Flavor", "Scoops", "Cones");
		choice.setItems(list2);
	}

	@Override
	public void handle(ActionEvent event) {
		applyBtn.setOnAction(e -> {
			if (choice.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("Please select an option from the combo box");
				alert.showAndWait();
			} else {
				String query;
				if (choice.getValue() == "Flavor")
					query = "select * from flavor;";
				else if (choice.getValue() == "Scoops")
					query = "select * from scoops;";
				else
					query = "select * from cones;";

				try {
					ResultSet rset = connector.stmt.executeQuery(query);
					list.clear();

					while (rset.next())
						list.add(rset.getString(1) + " " + rset.getString(2));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				listView.setItems(list);
			}

		});
		addBtn.setOnAction(e -> {
			if (choice.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("Please select an option from the combo box");
				alert.showAndWait();
			} else {
				String stuff2;
				if (choice.getValue() == "Flavor")
					stuff2 = "AddFlavor.fxml";
				else if (choice.getValue() == "Scoops")
					stuff2 = "AddScoop.fxml";
				else
					stuff2 = "AddCone.fxml";

				System.out.println(stuff2);
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(ManageConfigurationsController.class.getResource(stuff2));

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
			}

		});

		removeBtn.setOnAction(e -> {
			String stuff2;
			if (choice.getValue() == "Flavor")
				stuff2 = "flavor";
			else if (choice.getValue() == "Scoops")
				stuff2 = "scoops";
			else
				stuff2 = "cones";

			String stuff3;
			if (choice.getValue() == "Flavor")
				stuff3 = "select * from flavor";
			else if (choice.getValue() == "Scoops")
				stuff3 = "select * from scoops";
			else
				stuff3 = "select * from cones";

			String option = (String) listView.getSelectionModel().getSelectedItem();
			if (option.equals(null)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Information");
				alert.setContentText("No Employee is selected for deletion");
				alert.showAndWait();
			} else {
				String[] stuff = option.split("[ ]");

				for (int i = 0; i < stuff.length; i++)
					System.out.println(stuff[i]);

				String query = "delete from " + stuff2 + " where id = '" + stuff[0] + "' and name = '" + stuff[1]
						+ "';";

				try {
					connector.stmt.execute(query);

					ResultSet rset = connector.stmt.executeQuery(stuff3);

					list.clear();

					while (rset.next())
						list.add(rset.getString(1) + " " + rset.getString(2));

					listView.setItems(list);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		exitBtn.setOnAction(e -> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(IceCreamController.class.getResource("EmployeeMenu.fxml"));

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

	private void closeWindow(Button customerBtn2) {
		Stage stage = (Stage) customerBtn2.getScene().getWindow();
		stage.close();
	}

}