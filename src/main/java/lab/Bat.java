package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Bat extends MovableEntity implements DrawableSimulable, Collisionable {

    public Bat(Game game, int sizex, int sizey, Point2D position, Point2D velocity) {
        super(game, sizex, sizey, position, velocity);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(position.getX(), position.getY(), SIZEX, SIZEY);
    }

    public void simulate(double deltaT) {
        double newPos = position.getY() + (velocity.getY() * deltaT * 2);

        if ((newPos + SIZEY) > game.getLowerBounds() || (newPos) < game.getUpperBounds()) {
            return;
        }

        position = new Point2D(position.getX(), newPos);
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY(), SIZEX, SIZEY);
    }

    public void hit(double deltaT, Rectangle2D anotherObject) {
        
    }

    public void changeVelocity(Point2D newVelocity) {
        this.velocity = new Point2D(newVelocity.getX(), newVelocity.getY());
    }
}
