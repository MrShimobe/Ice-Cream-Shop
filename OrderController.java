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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

	private Statement stmt;

	@FXML
	private void fillTheMethods() {
	}

	@FXML
	public void initilize(Order order) {
		this.order = order;
		setFields(this.order);

	}

	private void setFields(Order order) {
		orderTxt.setText(order + "");
		IDTxt.setText(order.getCust().getCustomerID() + "");
		totalTxt.setText("$" + order.getTotal());

	}

	@Override
	public void handle(ActionEvent event) {
		initializeDB();

		btnExit.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(OrderController.class.getResource("IceCreamStartUp.fxml"));

			try {
				String queryString = "insert into icecream value ";
				cones = order.cones();

				for (int i = 0; i < cones.length; i++) {
					stmt.executeUpdate(
							queryString + "\n (\'" + cones[i].getConeType() + "\', " + cones[i].isIceCreamOrYogurt2()
									+ ", \'" + cones[i].getFlavor() + "\', " + cones[i].getNumberOfScoops() + ");");
				}

				view = (AnchorPane) loader.load();
				closeCurrentWindow(btnExit);
			} catch (IOException | SQLException ex) {

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