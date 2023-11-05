package lab;

import javafx.geometry.Point2D;

abstract class MovableEntity extends Entity {
    protected Point2D velocity;
    protected final Game game;

    MovableEntity(Game game, int sizex, int sizey, Point2D position, Point2D velocity) {
        super(sizex, sizey, position);
        this.velocity = velocity;
        this.game = game;
    }
}
