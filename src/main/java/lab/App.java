package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private GameController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/lab/layout.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);

			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Ping Pong");
			primaryStage.show();

			controller = loader.getController();
			controller.start();
			
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}