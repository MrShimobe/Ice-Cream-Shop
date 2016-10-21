import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
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

	protected LinkedList<Cone> list = new LinkedList<>();

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
	private ComboBox<Integer> scoopsCombo;

	@FXML
	private Button orderBtn;

	@FXML
	protected Button addOrder;

	@FXML
	protected Button removeOrder;

	@FXML
	private Label radioLbl;

	@FXML
	private Label flavorLbl;

	@FXML
	private Label coneLbl;

	@FXML
	private Label scoopsLbl;

	private String[] flavorList = { "Banana Nut Fudge", "Black Walnut", "Burgundy Cherry", "Butterscotch Ribbon",
			"Cherry Macaroon", "Chocolate", "Chocolate Almond", "Chocolate Chip", "Chocolate Fudge", "Chocolate Mint",
			"Chocolate Ribbon", "Coffee", "Coffee Candy", "Date Nut", "Egg Nog", "French Vanilla", "Green Mint Stick",
			"Lemon Crisp", "Lemon Custard", "Lemon Sherbet", "Maple Nut", "Orange Sherbet", "Peach",
			"Peppermint Fudge Ribbon", "Peppermint Stick", "Pineapple Sherbet", "Raspberry Sherbet", "Rocky Road",
			"Strawberry", "Vanilla", "Vanilla Burnt Almond" };

	ObservableList<String> flaveList = FXCollections.observableArrayList(flavorList);

	private String[] coneList = { "Cake Cone", "Waffle Cone", "Sugar Cone" };
	ObservableList<String> listOfCones = FXCollections.observableArrayList(coneList);

	private Integer[] scoopsList = { 1, 2, 3, 4, 5 };
	ObservableList<Integer> scoopList = FXCollections.observableArrayList(scoopsList);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		icecreamRadio.setToggleGroup(group);
		yogurtRadio.setToggleGroup(group);

		flavorCombo.setItems(flaveList);
		coneCombo.setItems(listOfCones);
		scoopsCombo.setItems(scoopList);

		radioLbl.setVisible(false);
		flavorLbl.setVisible(false);
		coneLbl.setVisible(false);
		scoopsLbl.setVisible(false);
	}

	@Override
	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MenuController.class.getResource("Order.fxml"));

		orderBtn.setOnAction(e -> {
			if (checkDataFields() || list.size() > 0) {
				try {
					if (list.size() == 0 || (checkDataFields() && list.size() < 2)) {
						cone = new Cone(coneCombo.getValue(), icecreamRadio.isSelected(), flavorCombo.getValue(),
								scoopsCombo.getValue());

						list.add(cone);
					}

					Cone[] coneArray = list.toArray(new Cone[list.size()]);
					order = new Order(customer, coneArray);

					root = (Parent) loader.load();
					OrderController orderController = (OrderController) loader.getController();
					orderController.initilize(order);

					view = (AnchorPane) root;
					closeCurrentWindow(orderBtn);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scene scene = new Scene(view);
				Stage stage = new Stage();
				stage.setTitle("Customer Information");
				stage.setScene(scene);
				stage.show();
			}
		});

		addOrder.setOnAction(e -> {
			if (checkDataFields()) {
				if (list.size() < 2) {
					list.add(new Cone(coneCombo.getValue(), icecreamRadio.isSelected(), flavorCombo.getValue(),
							scoopsCombo.getValue()));
					System.out.println("Something Added");
					clearNodes();
				}

				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Ice-Cream Shop");
					alert.setHeaderText("Order Processing");
					alert.setContentText("The maximum amount of orders is 10");
					alert.showAndWait();
				}
			}
		});

		removeOrder.setOnAction(e -> {
			if (list.size() > 0) {
				list.remove(list.size() - 1);
				System.out.println("Something Removed");
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ice-Cream Shop");
				alert.setHeaderText("Order Processing");
				alert.setContentText("You have no orders to remove");
				alert.showAndWait();
			}
		});

	}

	protected boolean checkDataFields() {

		if ((icecreamRadio.isSelected() || yogurtRadio.isSelected()) && (flavorCombo.getValue() != null)
				&& (coneCombo.getValue() != null) && (scoopsCombo.getValue() != null)) {
			return true;

		}

		else {

			if (!icecreamRadio.isSelected() && !yogurtRadio.isSelected())
				radioLbl.setVisible(true);
			else
				radioLbl.setVisible(false);
			if (flavorCombo.getValue() == null)
				flavorLbl.setVisible(true);
			else
				flavorLbl.setVisible(false);
			if (coneCombo.getValue() == null)
				coneLbl.setVisible(true);
			else
				coneLbl.setVisible(false);
			if (scoopsCombo.getValue() == null)
				scoopsLbl.setVisible(true);
			else
				scoopsLbl.setVisible(false);
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
}
