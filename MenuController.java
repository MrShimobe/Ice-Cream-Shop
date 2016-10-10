import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class MenuController implements Initializable, EventHandler<ActionEvent> {

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
		flavorCombo.setItems(flaveList);
		coneCombo.setItems(listOfCones);
		scoopsCombo.setItems(scoopList);

	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}
}
