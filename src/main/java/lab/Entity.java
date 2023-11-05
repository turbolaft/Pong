package lab;

import javafx.geometry.Point2D;

abstract class Entity {
    protected final int SIZEX;
    protected final int SIZEY;
    protected Point2D position;

    Entity(int sizex, int sizey, Point2D position) {
        this.SIZEX = sizex;
        this.SIZEY = sizey;
        this.position = position;
    }
}
