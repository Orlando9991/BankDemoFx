package pt.rumos.window.account;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pt.rumos.datahandler.CardHandler;
import pt.rumos.window.alertwindow.AlertWindow;
import pt.rumos.window.genericwindow.GenericWindow;

public class AccountWindowController extends GenericWindow {
	
    @FXML private AnchorPane accountAnchorPane;
	@FXML private TextField accountNumberField;
	@FXML private Button buttonAccountConfirm;
	@FXML private Label cardTypeLabel;
	
	@FXML
	public void confirmButtonPressed() {
		if (accountNumberField.getText().isBlank()) {
			AlertWindow.wrongInputAlert();
		} else {
			try {
				Long cardId = Long.parseLong(accountNumberField.getText());
				CardHandler.cardCheckValidation(cardId, cardClass);
				accountConfirmation();
			} catch (NumberFormatException e) {
				AlertWindow.wrongInputAlert();
			} catch (Exception e) {
				AlertWindow.warningDatabaseAlert("Database" ,e.getMessage());
			}
		}
	}

	@Override
	public void setCardByLabel(String cardLabel) {
		super.setCardByLabel(cardLabel);
		cardTypeLabel.setText(cardLabel);
	}
	
	private void accountConfirmation() {
		mainWindowController.accountAccepted();
	}

	public AnchorPane getAnchorPane() {
		return accountAnchorPane;
	}

	public void clearInputs() {
		accountNumberField.setText("");
	}
}
