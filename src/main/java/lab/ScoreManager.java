package lab;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ScoreManager implements Serializable {
    private GoalDetection leftGoalArea;
    private GoalDetection rightGoalArea;
    private Game game;
    public static final String SCORE_FILE_PATH = "scores.txt";

    private static final long serialVersionUID = 1L;

    public ScoreManager(Game game) {
        this.leftGoalArea = new GoalDetection(0, (int) game.getHeight(), new Point2D(-1, 0), new Score(60, 60, new Point2D(game.getWidth() / 2 - 60, 80)));
        this.rightGoalArea = new GoalDetection((int) game.getWidth() + 1, (int) game.getHeight(), new Point2D(game.getWidth(), 0), new Score(60, 60, new Point2D(game.getWidth() / 2 + 20, 80)));
    }

    public void draw(GraphicsContext gc) {
        leftGoalArea.draw(gc);
        rightGoalArea.draw(gc);
    }

    public void writeObject(ObjectOutputStream os) {
        leftGoalArea.writeObject(os);
        rightGoalArea.writeObject(os);
    }

    public void readObject(Point2D[] positions) {
        leftGoalArea.readObject(Arrays.copyOfRange(positions, 0, positions.length / 2));
        rightGoalArea.readObject(Arrays.copyOfRange(positions, positions.length / 2, positions.length));
    }

    public GoalDetection getLeftGoalArea() {
        return leftGoalArea;
    }

    public GoalDetection getRightGoalArea() {
        return rightGoalArea;
    }
}
