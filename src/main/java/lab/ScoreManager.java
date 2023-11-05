package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ScoreManager {
    private GoalDetection leftGoalArea;
    private GoalDetection rightGoalArea;
    private Game game;
    public static final String SCORE_FILE_PATH = "scores.txt";

    public ScoreManager(Game game) {
        this.leftGoalArea = new GoalDetection(0, (int) game.getHeight(), new Point2D(-1, 0), new Score(80, 80, new Point2D(game.getWidth() / 2 - 80, 80)));
        this.rightGoalArea = new GoalDetection((int) game.getWidth() + 1, (int) game.getHeight(), new Point2D(game.getWidth(), 0), new Score(80, 80, new Point2D(game.getWidth() / 2 + 30, 80)));
    }

    public void draw(GraphicsContext gc) {
        leftGoalArea.draw(gc);
        rightGoalArea.draw(gc);
    }

    public GoalDetection getLeftGoalArea() {
        return leftGoalArea;
    }

    public GoalDetection getRightGoalArea() {
        return rightGoalArea;
    }
}
