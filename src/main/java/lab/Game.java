package lab;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Game implements DrawableSimulable {
    private final double width;
    private final double height;
    private final double upperBounds;
    private final double lowerBounds;
    private Boolean isStopped = false;
    private DrawableSimulable[] objects;
    private CenterGrid[] centerGrids;
    private ScoreManager scoreManager;

    public Game(double width, double height) {
        this.width = width;
        this.height = height;
        objects = new DrawableSimulable[3];
        upperBounds = 20;
        lowerBounds = this.height - this.upperBounds;

        scoreManager = new ScoreManager(this);
        initializeOriginal();

        // CENTER BLOCKS
		int currentY = 20;
		int blockSize = 20;
        int i = 0;
        centerGrids = new CenterGrid[(int) (height - 20 * 2) / (20 + blockSize)];
		for (int iterator = currentY; iterator <= height - (20 * 2); iterator += 20 + blockSize) {
            centerGrids[i] = new CenterGrid(blockSize, blockSize, new Point2D((width / 2) - (blockSize / 2), iterator));
            i++;
		}
    }

    public void draw(GraphicsContext gc) {

        // BACKGROUND
		gc.setFill(Color.web("#0a0a0a"));
		gc.fillRect(0, 0, this.width, this.height);
        
        gc.setFill(Color.web("#ffffff"));
		//gc.setStroke(Color.BLACK);

		// UP AND DOWN LINES
		gc.fillRect(0, 0, this.width, upperBounds);
		//gc.strokeRect(0, 0, this.width, upperBounds);
		gc.fillRect(0, lowerBounds, this.width, upperBounds);
		//gc.strokeRect(0, lowerBounds, this.width, upperBounds);

        for (CenterGrid cg : centerGrids) {
            cg.draw(gc);
        }

        for (DrawableSimulable d : objects) {
            d.draw(gc);
        }
        
        scoreManager.draw(gc);
    }

    public void simulate(double deltaT) {
        for (DrawableSimulable d : objects) {
            if (d instanceof Ball b) {
                if (isStopped) continue;
            }
            d.simulate(deltaT);
        }

        for (DrawableSimulable d : objects) {
            if (d instanceof Collisionable obj1) {
                for (DrawableSimulable d1 : objects) {
                    if (d1 instanceof Collisionable obj2) {
                        if (obj1 != obj2) if (obj1.collisionCheck(obj2)) obj1.hit(deltaT, obj2.getBoundingBox());
                    }
                }
            }
        }

    }

    public void initializeOriginal() {
        Point2D newBallPos = null, newVelocity = null, newAcceleration = null;

        if (scoreManager.getLeftGoalArea().getScore().getLastToScore()) {
            newBallPos = new Point2D(width / 2 + 30, 150);
            newVelocity = new Point2D(-1, 1);
            newAcceleration = new Point2D(-1, 1);
            scoreManager.getLeftGoalArea().getScore().setLastToScore(false);
        } else {
            newVelocity = new Point2D(1, 1);
            newBallPos = new Point2D(width / 2 - 30, 150);
            newAcceleration = new Point2D(1, 1);
            scoreManager.getRightGoalArea().getScore().setLastToScore(false);
        }

        isStopped = true;

        objects[0] = new Ball(this, 20, 20, newBallPos, newAcceleration, newVelocity, scoreManager);
        objects[1] = new Bat(this, 20, 100, new Point2D(width - 20, 20), new Point2D(1, 40));
        objects[2] = new Bat(this, 20, 100, new Point2D(10, height - 120), new Point2D(1, 40));
    }

    public void controlBats(Point2D newVelocity, int id) {
        ((Bat) objects[id]).changeVelocity(newVelocity);
    }

    public double getUpperBounds() {
        return this.upperBounds;
    }

    public double getLowerBounds() {
        return this.lowerBounds;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public ScoreManager getScoreManager() {
        return this.scoreManager;
    }

    public Boolean getIsStopped() {
        return isStopped;
    }

    public void setIsStopped(Boolean isStopped) {
        this.isStopped = isStopped;
    }
}
