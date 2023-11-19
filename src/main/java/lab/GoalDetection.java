package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GoalDetection extends Entity implements Collisionable, Serializable {
    private Score score;

    GoalDetection(int sizex, int sizey, Point2D position, Score score) {
        super(sizex, sizey, position);
        this.score = score;
    }

    public void writeObject(ObjectOutputStream os) {
        writePoint2D(os);
        score.writePoint2D(os);
    }

    public void readObject(Point2D[] positions) {
        setPosition(new Point2D(positions[0].getX(), positions[0].getY()));
        score.setPosition(new Point2D(positions[1].getX(), positions[1].getY()));
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY(), SIZEX, SIZEY);
    }

    public void hit(double deltaT, Rectangle2D anotherObject) {
        if (position.getX() == -1 && score.getCount() == 9) {
            score.setPosition(new Point2D(score.getPosition().getX() - 30, score.getPosition().getY()));
        }
        score.simulate(deltaT);
    }

    public void draw(GraphicsContext gc) {
        score.draw(gc);
    }

    public Score getScore() {
        return score;
    }
}
