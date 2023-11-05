package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Ball extends MovableEntity implements DrawableSimulable, Collisionable {
    private Point2D acceleration;
    private ScoreManager scoreManager;

    public Ball(Game game, int sizex, int sizey, Point2D position, Point2D acceleration, Point2D velocity, ScoreManager scoreManager) {
        super(game, sizex, sizey, position, velocity);
        this.acceleration = acceleration;
        this.scoreManager = scoreManager;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(position.getX(), position.getY(), SIZEX, SIZEY, 45, 45);
    }

    public void simulate(double deltaT) {
        velocity = velocity.add(acceleration.multiply(deltaT));
        position = position.add(velocity);

        // upper and lower Bounds
        if ((position.getY() + SIZEY) > game.getLowerBounds() || (position.getY() + SIZEY) < game.getUpperBounds()) {
            if (velocity.getX() > 0) {
                velocity = new Point2D(velocity.getX(), -velocity.getY());
            } else if (velocity.getX() < 0) {
                velocity = new Point2D(velocity.getX(), -velocity.getY());
            }
        
            acceleration = new Point2D(acceleration.getX(), -acceleration.getY());
            position = position.add(velocity.multiply(deltaT));
        }

        if (scoreManager.getRightGoalArea().collisionCheck(this)) {
            // velocity = new Point2D(-velocity.getX(), velocity.getY());
            // position = position.add(velocity.multiply(deltaT));
            scoreManager.getLeftGoalArea().hit(deltaT, this.getBoundingBox());
            game.initializeOriginal();
        } else if (scoreManager.getLeftGoalArea().collisionCheck(this)) {
            // velocity = new Point2D(-velocity.getX(), velocity.getY());
            // position = position.add(velocity.multiply(deltaT));
            scoreManager.getRightGoalArea().hit(deltaT, this.getBoundingBox());
            game.initializeOriginal();
        }
    }

    public void hit(double deltaT, Rectangle2D anotherObject) {

        if (this.getBoundingBox().intersects(anotherObject.getMinX(), anotherObject.getMaxY(), anotherObject.getMaxX() - this.SIZEX / 2, 0) || 
            this.getBoundingBox().intersects(anotherObject.getMinX(), anotherObject.getMinY(), anotherObject.getMaxX() - this.SIZEX / 2, 0)) {
            velocity = new Point2D(-velocity.getX(), -velocity.getY());
            acceleration = new Point2D(-acceleration.getX(), -acceleration.getY());
            System.out.println("Bat: Max point Y " + ((int) anotherObject.getMaxY()) + " Min point Y " + ((int) anotherObject.getMinY()));
            System.out.println("Ball: Max point Y " + ((int) this.getBoundingBox().getMaxY()) + " Min point Y " + ((int) this.getBoundingBox().getMinY()));
        } else {
            velocity = new Point2D(-velocity.getX(), velocity.getY());
            acceleration = new Point2D(-acceleration.getX(), acceleration.getY());
        }

        this.simulate(deltaT);
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY(), SIZEX, SIZEY);
    }
}
