package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	ControllerWindow relation = new  ControllerWindow();
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox p = new VBox(3);
			Scene scn = new Scene(p, 300,300);
			Text tx = new Text("ingrese el nivel de dificultad");
			tx.setFont(new Font(20));
		
			ChoiceBox<String> cb = new ChoiceBox<>();
			relation.setChoiceBox(cb);
			relation.addChoice();
		
			Button bt = new Button();
			bt.setText("��A JUGAR!!");
			bt.setOnAction(e->{relation.jugar(primaryStage, scn);});
			
			p.getChildren().addAll(tx, cb, bt);
			primaryStage.setTitle("Buscaminas Game");
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
