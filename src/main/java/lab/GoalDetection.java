package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class GoalDetection extends Entity implements Collisionable {
    private Score score;

    GoalDetection(int sizex, int sizey, Point2D position, Score score) {
        super(sizex, sizey, position);
        this.score = score;
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY(), SIZEX, SIZEY);
    }

    public void hit(double deltaT, Rectangle2D anotherObject) {
        score.simulate(deltaT);
    }

    public void draw(GraphicsContext gc) {
        score.draw(gc);
    }

    public Score getScore() {
        return score;
    }
}
