package lab;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingThread extends AnimationTimer {

	private final GraphicsContext gc;

	public DrawingThread(GraphicsContext gc) {
		this.gc = gc;
	}

	/**
	  * Draws objects into the canvas. Put you code here. 
	 */
	@Override
	public void handle(long now) {
		// put your code here
		gc.setFill(Color.AQUA);
		gc.setStroke(Color.BLACK);
		gc.fillOval(10, 10, 20, 20);

	}

}
