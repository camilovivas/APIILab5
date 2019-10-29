package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import modelo.Buscaminas;

public class ControllerWindow implements Initializable {
	
	private Buscaminas buscamina;
	@FXML
	private ChoiceBox<String> choiceBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addChoice();
		
	}
	public void jugar(ActionEvent av) {
		String a = choiceBox.getValue();
		int result = 0;
		if(a.compareTo("nivel experto")==0) {
			result = Buscaminas.EXPERTO;
		}
		if(a.compareTo("nivel intermedio")==0) {
			result = Buscaminas.INTERMEDIO;
		}
		if(a.compareTo("nivel principiante")==0) {
			result = Buscaminas.PRINCIPIANTE;
		}
		buscamina = new Buscaminas(result);
		
	}
	
	public void addChoice() {
		choiceBox.getItems().add("nivel experto");
		choiceBox.getItems().add("nivel intermedio");
		choiceBox.getItems().add("nivel principiante");
		
	}
	
	public void charge(ActionEvent  a) {
		GridPane gPane = new GridPane();
		if() {
			for(int i =0; i<= Buscaminas.FILAS_PRINCIPIANTE; i++) {
				for(int j = 0; j <= Buscaminas.COLUMNAS_PRINCIPIANTE; j++) {
					Button button = new Button(i+":"+j);
					gPane.add(button, i, j);
				}
			}
			
		}
		if() {
			
		}
		if() {
			
		}
	}

}
