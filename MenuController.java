import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController implements EventHandler<ActionEvent>, Initializable {

	protected AnchorPane view;

	protected Customer customer;

	protected Order order;

	protected Cone cone;

	protected Parent root;

	protected LinkedList<Cone> list = new LinkedList<Cone>();

	protected ToggleGroup group = new ToggleGroup();

	@FXML
	private RadioButton icecreamRadio;

	@FXML
	private RadioButton yogurtRadio;

	@FXML
	private ComboBox<String> flavorCombo;

	@FXML
	private ComboBox<String> coneCombo;

	@FXML
	private ComboBox<String> scoopsCombo;

	@FXML
	private Button orderBtn;

	@FXML
	protected Button addOrder;

	@FXML
	protected Button removeOrder;

	@FXML
	private ListView listView;

	// private ArrayList<String> flavorList;

	ObservableList<String> flaveList = FXCollections.observableArrayList();

	ObservableList<String> scoopsList = FXCollections.observableArrayList();

	ObservableList<String> conesList = FXCollections.observableArrayList();

	ObservableList<Cone> cones = FXCollections.observableList(list);

	ObservableList<Cone> viewList;

	ConnectorClass connector = new ConnectorClass();

	int orderNum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		String queryString = "select name from flavor";
		String queryString2 = "select name from scoops";
		String queryString3 = "select name from cones";

		try {
			ResultSet rset = connector.stmt.executeQuery(queryString);
			while (rset.next()) {
				flaveList.add(rset.getString(1));
			}
			flavorCombo.setItems(flaveList);
			rset.close();

			rset = connector.stmt.executeQuery(queryString2);
			while (rset.next()) {
				scoopsList.add(rset.getString(1));
			}
			scoopsCombo.setItems(scoopsList);
			rset.close();

			rset = connector.stmt.executeQuery(queryString3);
			while (rset.next()) {
				conesList.add(rset.getString(1));
			}
			coneCombo.setItems(conesList);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		icecreamRadio.setToggleGroup(group);
		yogurtRadio.setToggleGroup(group);

	}

	@Override
	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MenuController.class.getResource("Order.fxml"));

		orderBtn.setOnAction(e -> {
			if (checkDataFieldsSpecial() && list.size() == 0 || list.size() > 0) {
				try {
					if (checkDataFieldsSpecial() && list.size() < 10) {
						cone = new Cone(coneCombo.getValue(), icecreamRadio.isSelected(), flavorCombo.getValue(),
								scoopsCombo.getValue());

						list.add(cone);
					}

					Cone[] coneArray = list.toArray(new Cone[list.size()]);
					order = new Order(customer, coneArray);

					updateOrderNum();

					for (int i = 0; i < list.size(); i++) {

						String query = "insert into Orders VALUE \n ('" + customer.customerID + "', '" + orderNum
								+ "', '" + list.get(i).getConeType() + "', '" + list.get(i).isIceCreamOrYogurt2()
								+ "', '" + list.get(i).getFlavor() + "', '" + list.get(i).getNumberOfScoops() + "');";

						connector.stmt.execute(query);

					}

					root = (Parent) loader.load();
					OrderController orderController = (OrderController) loader.getController();
					orderController.initilize(order, orderNum);

					view = (AnchorPane) root;
					closeCurrentWindow(orderBtn);
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
				Scene scene = new Scene(view);
				Stage stage = new Stage();
				stage.setTitle("Customer Information");
				stage.setScene(scene);
				stage.show();
			} else
				warnCustomer("Please Compelete Order Information");
		});

		addOrder.setOnAction(e -> {
			if (checkDataFields()) {
				if (list.size() < 10) {
					System.out.println("Something Added");
					list.add(new Cone(coneCombo.getValue(), icecreamRadio.isSelected(), flavorCombo.getValue(),
							scoopsCombo.getValue()));

					clearNodes();
				}

				else {
					warnCustomer("Too many Orders");
				}
			}
			castIntoList();
		});

		removeOrder.setOnAction(e -> {
			if (list.size() > 0) {
				list.remove(list.size() - 1);
				System.out.println("Something Removed");
			} else {
				warnCustomer("You have nothing to delete");
			}
			castIntoList();
		});
	}

	private void updateOrderNum() throws SQLException {

		String query = "SELECT * from Orders where orderNum = '" + orderNum + "' and id = '" + customer.customerID
				+ "';";

		ResultSet rset = connector.stmt.executeQuery(query);

		boolean first = true;

		if (!rset.next()) {
			System.out.println("First Order :)");
		} else {
			System.out.println("Order Already Exists");
			do {
				query = "SELECT * from Orders where orderNum = '" + orderNum + "' and id = '" + customer.customerID
						+ "';";
				rset = connector.stmt.executeQuery(query);
				if (rset.next()) {
					orderNum++;
				} else
					first = false;

			} while (first);
			System.out.println(orderNum);

		}

	}

	private void castIntoList() {

		ArrayList temp = new ArrayList(list);
		ObservableList tempObserve = FXCollections.observableArrayList(temp);
		listView.setItems(tempObserve);

	}

	protected boolean checkDataFields() {

		if ((icecreamRadio.isSelected() || yogurtRadio.isSelected()) && (flavorCombo.getValue() != null)
				&& (coneCombo.getValue() != null) && (scoopsCombo.getValue() != null)) {
			return true;

		}

		else
			warnCustomer("Not all of the Information Was Selected");
		return false;

	}

	protected boolean checkDataFieldsSpecial() {
		if ((icecreamRadio.isSelected() || yogurtRadio.isSelected()) && (flavorCombo.getValue() != null)
				&& (coneCombo.getValue() != null) && (scoopsCombo.getValue() != null)) {
			return true;
		}
		return false;
	}

	protected void clearNodes() {
		icecreamRadio.setSelected(false);
		yogurtRadio.setSelected(false);
		flavorCombo.setValue(null);
		coneCombo.setValue(null);
		scoopsCombo.setValue(null);

	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}

	public void warnCustomer(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ice Cream Shop");
		alert.setHeaderText("Order");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
