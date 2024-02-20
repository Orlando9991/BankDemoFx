package pt.rumos.window.alertwindow;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class AlertWindow {
	
	public static boolean exitAlert () {
		Alert alert =new Alert(AlertType.NONE);
    	alert.setTitle("Exit");
    	alert.setContentText("Do you want to exit?");
    	alert.getButtonTypes().add(ButtonType.YES);
    	alert.getButtonTypes().add(ButtonType.NO);
    	
    	//Get clicked button
    	Optional<ButtonType> resultClick = alert.showAndWait();
    	ButtonType button = resultClick.orElse(ButtonType.NO);
    	
    	if(button.equals(ButtonType.YES)) {
    		return true;
    	}
    	return false;
	}
	
	public static void wrongInputAlert () {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Wrong Input");
    	alert.setHeaderText("Enter a valid number");
    	alert.show();
	}
	
	public static void warningDatabaseAlert (String title ,String message) {
		Alert alert =new Alert(AlertType.WARNING);
    	alert.setTitle(title);
    	alert.setHeaderText(message);
    	alert.show();
	}
	
	public static void errorAlert (String title ,String message) {
		Alert alert =new Alert(AlertType.ERROR);
    	alert.setTitle(title);
    	alert.setHeaderText(message);
    	alert.show();
	}
	
	public static void confirmAlert (String title ,String message) {
		Alert alert = new Alert(AlertType.INFORMATION, title, ButtonType.OK);
		alert.setTitle(title);
		alert.setHeaderText(message);
    	alert.show();
	}
	
	public static void singleInputWindow(String title, String message, Function<Object, Boolean> function) {
		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
         
        Label label = new Label(message);
        label.setFont(Font.font("System Bold"));;
        
        TextField textField = new TextField();
             
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.setTextFill( Paint.valueOf("FFFFFF"));
		confirmButton.setStyle("-fx-background-color: #00FF00;");
         
        VBox vBoxlayout = new VBox(label,textField,confirmButton);
        vBoxlayout.setAlignment(Pos.CENTER);
		VBox.setMargin(textField, new Insets(30, 70, 30, 70));
         
        Scene scene = new Scene(vBoxlayout, 250, 150);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
  
        
        confirmButton.setOnAction(event -> {
			if(function.apply(textField.getText())) {
				stage.close();	
			}
		});
	}
	
	
	public static void twoInputWindow(String title, String messageOne, String messageTwo,
			BiFunction<Object, Object, Boolean> bifunction) {
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);

		Label labelOne = new Label(messageOne);
		labelOne.setFont(Font.font("System Bold"));
		Label labelTwo = new Label(messageTwo);
		labelTwo.setFont(Font.font("System Bold"));

		TextField textFieldOne = new TextField();
		TextField textFieldTwo = new TextField();

		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.setTextFill(Paint.valueOf("FFFFFF"));
		confirmButton.setStyle("-fx-background-color: #00FF00;");

		VBox vBoxlayout = new VBox(
				labelOne,
				textFieldOne,
				labelTwo,
				textFieldTwo,
				confirmButton);
		
		vBoxlayout.setAlignment(Pos.CENTER);
		VBox.setMargin(textFieldOne, new Insets(30, 70, 30, 70));
		VBox.setMargin(textFieldTwo, new Insets(30, 70, 30, 70));

		Scene scene = new Scene(vBoxlayout, 250, 300);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		confirmButton.setOnAction(event -> {
			if (bifunction.apply(textFieldOne.getText(), textFieldTwo.getText())) {
				stage.close();	
			}
		});
	}
	
}
