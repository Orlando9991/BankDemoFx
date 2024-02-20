module Rumos_Project_ATM_JavaFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires Service;
	requires Model;
	requires javafx.base;
	
	opens pt.rumos.app to javafx.graphics, javafx.fxml;
	opens pt.rumos.window.main;
	opens pt.rumos.window.account;
	opens pt.rumos.window.pin;
	opens pt.rumos.window.menu;
	opens pt.rumos.window.menu.management;
}