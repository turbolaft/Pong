package lab;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DrawingThread extends AnimationTimer {

	private final Canvas canvas;
	private final GraphicsContext gc;
	private final Game game;
	private long lastTime;
	private boolean isStarted;
	private long lastTimeStopped;

	public DrawingThread(Canvas canvas, Game game) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		this.game = game;
		this.isStarted = false;
	}

	/**
	  * Draws objects into the canvas. Put you code here. 
	 */
	@Override
	public void handle(long now) {
		if (this.game.getIsStopped()) {
			if (now - lastTimeStopped >= 1_000_000_000.0) {
				this.game.setIsStopped(false);
			}
		}

		if (isStarted) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			if (lastTime > 0) {
				game.simulate((now - lastTime) / 1e9);
			}
		}

		game.draw(gc);
		lastTime = now;
		if (!this.game.getIsStopped()) {
			lastTimeStopped = now;
		}
	}

	public void setStartness() {
		isStarted = !isStarted;
	}

	public boolean getStartness() {
		return isStarted;
	}
}
