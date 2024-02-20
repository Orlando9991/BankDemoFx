package pt.rumos.window.main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import pt.rumos.types.EnumCardTypes;
import pt.rumos.window.account.AccountWindowController;
import pt.rumos.window.alertwindow.AlertWindow;
import pt.rumos.window.menu.GeneralMenuWindowController;
import pt.rumos.window.menu.management.ManagementWindowController;
import pt.rumos.window.pin.PinWindowController;

public class MainWindowController {

	@FXML private Button changeCardButton;
    @FXML private Button exitButton;
    @FXML private ComboBox<String> comboBox;
   
    @FXML private AccountWindowController accountWindowController;
    @FXML private PinWindowController pinWindowController;
    @FXML private GeneralMenuWindowController generalMenuWindowController;
    @FXML private ManagementWindowController managementWindowController;
    
    @FXML
    public void exitAplication(){
    	if(AlertWindow.exitAlert()) {
    		 System.exit(0);
    	};
    }
    
    @FXML
    public void changeCard() {
    	resetView();
    }
    
	@FXML
	public void initialize() {
		initializeComboBoxItems();
		setMainWindowController();
		changeCardTypeLabel();
	}
	
	@FXML
    public void changeCardTypeLabel() {
    	String cardLabel = comboBox.getValue();
		accountWindowController.setCardByLabel(cardLabel);
		managementWindowController.setCardByLabel(cardLabel);
		pinWindowController.setCardByLabel(cardLabel);
		generalMenuWindowController.setCardByLabel(cardLabel);
    }
		
	private void initializeComboBoxItems() {
		comboBox.getItems().add(EnumCardTypes.DEBITCARD.getString());
		comboBox.getItems().add(EnumCardTypes.CREDITCARD.getString());
		//set default value
		comboBox.getSelectionModel().selectFirst();
	}
	
	public void accountAccepted() {
		ObjectVisibility(accountWindowController.getAnchorPane(), false);
		ObjectVisibility(pinWindowController.getAnchorPane(), true);
		ObjectVisibility(comboBox,false);
		ObjectVisibility(changeCardButton, true);
	}
	
	public void pinAccepted() {
		ObjectVisibility(pinWindowController.getAnchorPane(), false);
		ObjectVisibility(generalMenuWindowController.getAnchorPane(), true);
	}
	
	
	private void ObjectVisibility(Node obj, Boolean visible) {
		obj.setDisable(!visible);
		obj.setVisible(visible);
	}
	
	private void resetView() {
		ObjectVisibility(accountWindowController.getAnchorPane(), true);
		ObjectVisibility(pinWindowController.getAnchorPane(), false);
		ObjectVisibility(generalMenuWindowController.getAnchorPane(), false);
		ObjectVisibility(comboBox,true);
		ObjectVisibility(changeCardButton, false);
		
		accountWindowController.clearInputs();
		pinWindowController.clearInputs();
	}
	
	private void setMainWindowController() {
		accountWindowController.setMainWindowController(this);
		pinWindowController.setMainWindowController(this);
		generalMenuWindowController.setMainWindowController(this);
		managementWindowController.setMainWindowController(this);
	}

	public void managementMenuStart() {
		ObjectVisibility(managementWindowController.getAnchorPane(), true);
		ObjectVisibility(generalMenuWindowController.getAnchorPane(), false);
	}

	public void goBackMainMenu(AnchorPane anchorPane) {
		ObjectVisibility(anchorPane, false);
		ObjectVisibility(generalMenuWindowController.getAnchorPane(), true);
	}
	

}
