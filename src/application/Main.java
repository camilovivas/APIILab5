package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Buscaminas;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application {
	private ControllerWindow relation = new  ControllerWindow();
	@Override
	public void start(Stage primaryStage) {
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/application/window.fxml"));
			VBox p = new VBox(3);
			Text tx = new Text("ingrese el nivel de dificultad");
		
			ChoiceBox<String> cb = new ChoiceBox<>();
			cb.setId("choiceBox");
			relation.setChoiceBox(cb);
			relation.addChoice();
			
			
			Button bt = new Button();
			bt.setText("¡¡A JUGAR!!");
			bt.setOnAction(e->{relation.jugar(primaryStage);});
			
			p.getChildren().addAll(tx, cb, bt);
			Scene scn = new Scene(p);
			primaryStage.setScene(scn);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
