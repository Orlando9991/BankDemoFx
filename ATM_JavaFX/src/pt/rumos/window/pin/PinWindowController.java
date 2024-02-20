package pt.rumos.window.pin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import pt.rumos.datahandler.CardHandler;
import pt.rumos.generic.Card;
import pt.rumos.window.alertwindow.AlertWindow;
import pt.rumos.window.genericwindow.GenericWindow;

public class PinWindowController extends GenericWindow {
		
	@FXML private AnchorPane pinAnchorPane;
	@FXML private PasswordField passwordField;
	@FXML private Button buttonDigit_1;
	@FXML private Button buttonDigit_2;
	@FXML private Button buttonDigit_3;
	@FXML private Button buttonDigit_4;
	@FXML private Button buttonDigit_5;
	@FXML private Button buttonDigit_6;
	@FXML private Button buttonDigit_7;
	@FXML private Button buttonDigit_8;
	@FXML private Button buttonDigit_9;
	@FXML private Button buttonDigit_0;
	@FXML private Button buttonClearScreen;
	@FXML private Button buttonPinConfirm;
	
	private String password = "";

	@FXML
	private void buttonOnePressed() {
		addNumber("1");
	}

	@FXML
	private void buttonTwoPressed() {
		addNumber("2");
	}

	@FXML
	private void buttonThreePressed() {
		addNumber("3");
	}

	@FXML
	private void buttonFourPressed() {
		addNumber("4");
	}

	@FXML
	private void buttonFivePressed() {
		addNumber("5");
	}

	@FXML
	private void buttonSixPressed() {
		addNumber("6");
	}

	@FXML
	private void buttonSevenPressed() {
		addNumber("7");
	}

	@FXML
	private void buttonEightPressed() {
		addNumber("8");
	}

	@FXML
	private void buttonNinePressed() {
		addNumber("9");
	}

	@FXML
	private void buttonZeroPressed() {
		addNumber("0");
	}

	@FXML
	public void clearInputs() {
		passwordField.setText("");
		password = "";
	}

	@FXML
	private void confirmButton() {
		if (password.length() != 4) {
			AlertWindow.wrongInputAlert();
		} else {
			try {
				Card card = CardHandler.getCard();
				if (card.getPin().equals(password)) {
					pinConfirmation();
				} else {
					AlertWindow.warningDatabaseAlert("Input", "Wrong Pin");
					;
				}

			} catch (NumberFormatException e) {
				AlertWindow.wrongInputAlert();
			} catch (Exception e) {
				AlertWindow.warningDatabaseAlert("Database Error", e.getMessage());
			}
		}

	}

	private void addNumber(String number) {
		if (password.length() < 4)
			password = new StringBuilder().append(password).append(number).toString();

		passwordField.setText(password);
	}

	private void pinConfirmation() {
		mainWindowController.pinAccepted();
	}

	@Override
	public AnchorPane getAnchorPane() {
		return pinAnchorPane;
	}
}
