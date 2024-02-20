package pt.rumos.window.genericwindow;

import javafx.scene.layout.AnchorPane;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.types.EnumCardTypes;
import pt.rumos.window.main.MainWindowController;

public abstract class GenericWindow {

	protected MainWindowController mainWindowController;
	protected Class<?> cardClass;

	public void setMainWindowController(MainWindowController mainController) {
		this.mainWindowController = mainController;
	}

	public void setCardClass(Class<?> cardClass) {
		this.cardClass = cardClass;
	}

	public void setCardByLabel(String cardLabel) {
		if (cardLabel.equals(EnumCardTypes.DEBITCARD.getString())) {
			setCardClass(DebitCard.class);
		} else {
			setCardClass(CreditCard.class);
		}
	}

	public abstract AnchorPane getAnchorPane();

}
