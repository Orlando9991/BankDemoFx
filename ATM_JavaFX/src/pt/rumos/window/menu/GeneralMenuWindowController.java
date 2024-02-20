package pt.rumos.window.menu;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import pt.rumos.datahandler.BankAccountHandler;
import pt.rumos.datahandler.CardHandler;
import pt.rumos.model.BankAccount;
import pt.rumos.window.alertwindow.AlertWindow;
import pt.rumos.window.genericwindow.GenericWindow;

public class GeneralMenuWindowController extends GenericWindow {

	@FXML private AnchorPane generalMenuAnchorPane;
	@FXML private Button managementButton;
	@FXML private Button withdrawButton;
	@FXML private Button cButton;
	
	
	@Override
	public AnchorPane getAnchorPane() {
		return generalMenuAnchorPane;
	}
	
	@FXML
	private void managementButtonClick() {
		mainWindowController.managementMenuStart();
	}
	
	@FXML 
	private void withdrawWindow() {
		Function< Object, Boolean> function = new Function<Object, Boolean>() {
			@Override
			public Boolean apply(Object input) {
				return confirmWithdraw((String)input);
			}
		};
		AlertWindow.singleInputWindow("WITHDRAW", "AMOUNT TO WITHDRAW", function);
	}

	private boolean confirmWithdraw(String input) {
		if (input.isBlank()) {
			AlertWindow.wrongInputAlert();
			return false;
		}
		try {
			double amount = Double.valueOf(input);
			CardHandler.withdrawAmount(amount, cardClass);
			AlertWindow.confirmAlert("Sucess", "Withdraw Complete");
			return true;
		} catch (NumberFormatException e) {
			AlertWindow.wrongInputAlert();
		} catch (Exception e) {
			AlertWindow.errorAlert("Error", e.getMessage());
		}

		return false;
	}
	
	@FXML
	private void transferWindow() {
		BiFunction<Object, Object, Boolean> biFunction = new BiFunction<Object, Object, Boolean>() {

			@Override
			public Boolean apply(Object t, Object u) {
				return confirmTransfer((String) t, (String) u);
			}
		};
		AlertWindow.twoInputWindow("Tranfer","Bank Account Destination", "Amount", biFunction);
	}
	

	private boolean confirmTransfer(String bankAccountInput, String amountInput) {
		if (bankAccountInput.isBlank() || amountInput.isBlank()) {
			AlertWindow.wrongInputAlert();
			return false;
		}
		try {
			long bankAccountId = Long.parseLong(bankAccountInput);
			BankAccount ba = BankAccountHandler.getBankAccount(bankAccountId);
			double amount = Double.valueOf(amountInput);
			CardHandler.transferAmount(amount, ba, cardClass);
			AlertWindow.confirmAlert("TRANSACTION", "TRANSACTION COMPLETE");
			return true;
		} catch (NumberFormatException e) {
			AlertWindow.wrongInputAlert();
		} catch (Exception e) {
			AlertWindow.errorAlert("Error", e.getMessage());
		}
		return false;
	}

}
