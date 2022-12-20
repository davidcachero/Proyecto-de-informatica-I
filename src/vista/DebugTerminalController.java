package vista;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DebugTerminalController {

	Controller controller;

    @FXML
    private TextField lblIdUser;
    
    @FXML
    private Button btnTwoHundred;

    @FXML
    private Button btnOneHundred;

    @FXML
    private Button btnFifty;

    @FXML
    private Button btnTwenty;

    @FXML
    private Button btnTen;

    @FXML
    private Button btnFive;

    @FXML
    private Button btnTwo;

    @FXML
    private Button BtnOne;

    @FXML
    private Button btnLogIn;

    public DebugTerminalController(Controller controller) {
    	super();
		this.controller = controller;
	}
    
    // Card action
    @FXML
    void LogIn(ActionEvent event) {
    	controller.logIn(lblIdUser);
    }
    
    
    // Currency actions
    @FXML
    void addFifty(ActionEvent event) {
    	controller.insertarMonedas(50);
    }

    @FXML
    void addFive(ActionEvent event) {
    	controller.insertarMonedas(5);
    }

    @FXML
    void addOne(ActionEvent event) {
    	controller.insertarMonedas(1);
    }

    @FXML
    void addOneHundred(ActionEvent event) {
    	controller.insertarMonedas(100);
    }

    @FXML
    void addTen(ActionEvent event) {
    	controller.insertarMonedas(10);
    }

    @FXML
    void addTwenty(ActionEvent event) {
    	controller.insertarMonedas(20);
    }

    @FXML
    void addTwo(ActionEvent event) {
    	controller.insertarMonedas(2);
    }

    @FXML
    void addTwoHundred(ActionEvent event) {
    	controller.insertarMonedas(200);
    }

}
