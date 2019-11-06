package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.relation.Relation;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
		VBox root = new VBox(3);
		Text title = new Text("Presione click derecho para marcar como mina");
		GridPane gPane = new GridPane();
		
		HBox hb = new HBox();
		
		Button bt2 = new Button();
		bt2.setText("Dar pista");

		Button bt3 = new Button();
		bt3.setText("Resolver");
		
		hb.getChildren().addAll(bt2, bt3);
		
		root.getChildren().addAll(title, gPane, hb);
		int fila = limitFila();
		int columna = limitColum();
		for(int i =0; i< fila; i++) {
			for(int j = 0; j < columna; j++) {
				Button button = new Button();
				button.setId(i+":"+j);
				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						MouseButton mb = event.getButton();
						if(mb == MouseButton.PRIMARY) {
							destapar(button, s1, stage);
						}
						if(mb == MouseButton.SECONDARY) {
							button.setText("?");
						}
					}
				});
				gPane.add(button, j, i);
			}
		}
		Scene sc = new Scene(root, 500,450);
		stage.setScene(sc);
		stage.show();
	}
	
	public void destapar(Button button, Scene s1, Stage stage) {
		String a = button.getId();
		String [] sp = a.split(":");
		int n1 = Integer.parseInt(sp[0]);
		int n2 = Integer.parseInt(sp[1]); 
		if( buscamina.abrirCasilla(n1, n2)== false) {
			lost(s1, stage);
			button.setText("*");
		}
		else {
			button.setText(buscamina.darCasillas()[n1][n2].mostrarValorCasilla());	
			button.setDisable(true);
		}
	}
	
	
	public int limitFila() {
		int retorno = 0;
		if(buscamina.darNivel() == Buscaminas.PRINCIPIANTE) {
			retorno = Buscaminas.FILAS_PRINCIPIANTE;
		}
		else if(buscamina.darNivel() == Buscaminas.INTERMEDIO) {
			retorno = Buscaminas.FILAS_INTERMEDIO;
		}
		else if(buscamina.darNivel() == Buscaminas.EXPERTO) {
			retorno = Buscaminas.FILAS_EXPERTO;
		}
		return retorno;
	}
	
	public int limitColum() {
		int retorno = 0;
		if(buscamina.darNivel() == Buscaminas.PRINCIPIANTE) {
			retorno = Buscaminas.COLUMNAS_PRINCIPIANTE;
		}
		else if(buscamina.darNivel() == Buscaminas.INTERMEDIO) {
			retorno = Buscaminas.COLUMNAS_INTERMEDIO;
		}
		else if(buscamina.darNivel() == Buscaminas.EXPERTO) {
			retorno = Buscaminas.COLUMNAS_EXPERTO;
		}
		return retorno;
	}
	
	public void lost(Scene s1, Stage s2) {
		Stage st = new Stage();
		VBox vb = new VBox(4); 
		Text tx = new Text("¡¡PINCHASTE UNA MINA!!");
		HBox root = new HBox();
		Scene s = new Scene(vb, 180,100); 
		
		s2.close();
		Button btt = new Button();
		btt.setText("jugar otra vez");
		btt.setOnAction(e->{
			s2.setScene(s1);
			st.close();
			s2.show();

		});
		
		Button btt2 = new Button();
		btt2.setText("salir");
		btt2.setOnAction(e->{
			st.close();
		});
		Text ts = new Text("SOLUCION");
		GridPane g = solucion();
		root.getChildren().addAll(btt, btt2);
		vb.getChildren().addAll(tx, ts, g, root);
		st.setScene(s);
		st.show();
	}
	
	public GridPane solucion() {
		buscamina.resolver();
		GridPane g = new GridPane();
		int fila = limitFila();
		int columna = limitColum();
		for(int i =0; i< fila; i++) {
			for(int j = 0; j < columna; j++) {
				Button bs = new Button();
				bs.setText(buscamina.darCasillas()[i][j].mostrarValorCasilla());
				bs.setDisable(true);
				g.add(bs, j, i);
			}
		}
		return g;
	}

}
