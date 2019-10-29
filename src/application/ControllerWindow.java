package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class ControllerWindow implements Initializable {
	
	@FXML
	private ChoiceBox<String> choiceBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addChoice();
		
	}
	
	public void addChoice() {
		choiceBox.getItems().add("nivel experto");
		
	}
	
	public void charge(ActionEvent  a) {
		
	}

}
