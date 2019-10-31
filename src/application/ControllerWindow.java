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
import javafx.scene.layout.HBox;
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
//		addChoice();
		
	}
	
	
	public ChoiceBox<String> getChoiceBox() {
		return choiceBox;
	}

	public void setChoiceBox(ChoiceBox<String> choiceBox) {
		this.choiceBox = choiceBox;
	}


	//cuando se unda el boton juagr
	public void jugar(Stage s, Scene s1) {
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
		charge(s, s1);

	}
	
	public void addChoice() {
		choiceBox.getItems().add("nivel experto");
		choiceBox.getItems().add("nivel intermedio");
		choiceBox.getItems().add("nivel principiante");
		
	}
	
	public void charge(Stage stage, Scene s1) {
		Pane root = new Pane();
		GridPane gPane = new GridPane();
		root.getChildren().add(gPane);
		if(buscamina.darNivel() == Buscaminas.PRINCIPIANTE) {
			for(int i =0; i<= Buscaminas.FILAS_PRINCIPIANTE; i++) {
				for(int j = 0; j <= Buscaminas.COLUMNAS_PRINCIPIANTE; j++) {
					Button button = new Button();
					button.setOnAction(e->{
						if( buscamina.abrirCasilla(0, 0)== false) {
							lost(s1, stage);
						}
					});
					button.setId(i+":"+j);
					gPane.add(button, i, j);
				}
			}
		}
		Scene sc = new Scene(root);
		stage.setScene(sc);
		stage.show();
	}
	
	public void lost(Scene s1, Stage s2) {
		Stage st = new Stage();
		
		HBox root = new HBox();
		
		
		Button btt = new Button();
		btt.setText("jugar otra vez");
		btt.setOnAction(e->{
			s2.setScene(s1);
			s2.show();

		});
		
		Button btt2 = new Button();
		btt2.setText("salir");
		btt2.setOnAction(e->{
			
		});
		
		root.getChildren().addAll(btt, btt2);
		Scene s = new Scene(root); 
		st.setScene(s);
		st.show();
	}

}
