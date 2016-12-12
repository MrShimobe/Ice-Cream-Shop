import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderController implements EventHandler<ActionEvent> {

	protected AnchorPane view;

	protected Order order;

	protected Customer customer;

	protected Cone cone;

	protected Cone[] cones;

	@FXML
	private TextField IDTxt;

	@FXML
	private TextField totalTxt;

	@FXML
	private Button btnExit;

	@FXML
	private TextArea orderTxt;

	ConnectorClass connector = new ConnectorClass();

	@FXML
	private void fillTheMethods() {
	}

	@FXML
	public void initilize(Order order, int num) {
		this.order = order;
		setFields(this.order, num);

	}

	private void setFields(Order order, int num) {

		orderTxt.setText(order.toString() + "");
		IDTxt.setText(num + "");
		totalTxt.setText("$" + order.getTotal());

		if (num % 10 == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ice Cream Shop");
			alert.setHeaderText("Order");
			alert.setContentText("Thank-You for selecting IceCream Shop. You Get 1 Free Small Cone (: ");
			alert.showAndWait();
		}

	}

	@Override
	public void handle(ActionEvent event) {

		btnExit.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OrderController.class.getResource("IceCreamStartUp.fxml"));

			try {
				view = (AnchorPane) loader.load();
				closeCurrentWindow(btnExit);
			} catch (IOException ex) {

				ex.printStackTrace();
			}
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Ice Cream Shop");
			stage.setScene(scene);
			stage.show();
		});
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();

	}
}