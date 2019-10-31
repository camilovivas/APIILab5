package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Buscaminas;

public class ControllerWindow implements Initializable {
	
	private Buscaminas buscamina;
	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private Button bt;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addChoice();
		
	}
	
	//cuando se unda el boton juagr
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
		FXMLLoader root1;
		try {
			root1 = FXMLLoader.load(getClass().getResource("/application/window.fxml"));
			Main d = root1.getController();
			charge(av, d);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void addChoice() {
		choiceBox.getItems().add("nivel experto");
		choiceBox.getItems().add("nivel intermedio");
		choiceBox.getItems().add("nivel principiante");
		
	}
	
	public void charge(ActionEvent av, Stage stage) {
		Pane root = new Pane();
		GridPane gPane = new GridPane();
		root.getChildren().add(gPane);
		if(buscamina.darNivel() == Buscaminas.PRINCIPIANTE) {
			for(int i =0; i<= Buscaminas.FILAS_PRINCIPIANTE; i++) {
				for(int j = 0; j <= Buscaminas.COLUMNAS_PRINCIPIANTE; j++) {
					Button button = new Button();
					button.setId(i+":"+j);
					gPane.add(button, i, j);
				}
			}
		}
		Scene sc = new Scene(root);
		stage.setScene(sc);
		stage.show();
		
		
		
	}

}
