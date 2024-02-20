package pt.rumos.window.menu.management;

import java.util.function.Function;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import pt.rumos.datahandler.CardHandler;
import pt.rumos.window.alertwindow.AlertWindow;
import pt.rumos.window.genericwindow.GenericSubWindow;

public class ManagementWindowController extends GenericSubWindow {
	
	@FXML private Button changePinButton;
	@FXML private Button goBackButton;
	@FXML private AnchorPane managementAnchorPane;

	@Override
	public AnchorPane getAnchorPane() {
		return managementAnchorPane;
	}

	@FXML
	protected void goBackMenu() {
		mainWindowController.goBackMainMenu(getAnchorPane());
	}
	
	@FXML
	private void changePin() {
		newPinInsertWindow();
	}
	
	
	private void newPinInsertWindow() {
		Function< Object, Boolean> function = new Function<Object, Boolean>() {
			@Override
			public Boolean apply(Object input) {
				return changePin((String) input);
			}
		};
		AlertWindow.singleInputWindow("Change PIN", "INSERT NEW PIN", function);
	}

	private boolean changePin(String input) {
		if(input.isBlank() || input.length()!=4) {
			AlertWindow.wrongInputAlert();
			return false;
		}
		try {
			CardHandler.setNewPin(input, cardClass);
			AlertWindow.confirmAlert("", "Pin was changed");
			return true;
		} catch (Exception e) {
			AlertWindow.errorAlert("Database" ,e.getMessage());
		}
		return false;
	}
	
}
