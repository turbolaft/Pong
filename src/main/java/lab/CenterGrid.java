package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class CenterGrid extends Entity {
    CenterGrid(int sizex, int sizey, Point2D position) {
        super(sizex, sizey, position);
    }

    public void draw(GraphicsContext gc) {
        gc.fillRect(position.getX(), position.getY(), SIZEX, SIZEY);
    }
}
