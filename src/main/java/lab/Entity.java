package lab;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.geometry.Point2D;

abstract class Entity implements Serializable {
    protected final int SIZEX;
    protected final int SIZEY;
    protected transient Point2D position;

    Entity(int sizex, int sizey, Point2D position) {
        this.SIZEX = sizex;
        this.SIZEY = sizey;
        this.position = position;
    }

    public void writePoint2D(ObjectOutputStream os) {
        try {
            os.writeDouble(position.getX());
            os.writeDouble(position.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
