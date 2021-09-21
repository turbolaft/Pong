package lab;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	
	private Canvas canvas;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Construct a main window with a canvas.  
			Group root = new Group();
			canvas = new Canvas(800, 400);
			root.getChildren().add(canvas);
			Scene scene = new Scene(root, 800, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Java 1 - 1th laboratory");
			primaryStage.show();
			
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
			
			//Draw scene on a separate thread to avoid blocking UI.
			new Thread(this::drawScene).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws objects into the canvas. Put you code here. 
	 *
	 *@return      nothing
	 */
	private void drawScene() {
		//graphic context is used for a painting
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// put your code here
		// gc.setFill(Color.AQUA);
		// gc.setStroke(Color.BLACK);
	}
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}