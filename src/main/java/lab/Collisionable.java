package lab;

import javafx.geometry.Rectangle2D;

interface Collisionable {
    Rectangle2D getBoundingBox();
    void hit(double deltaT, Rectangle2D anotherObject);

    default Boolean collisionCheck(Collisionable anotherObject) {
        if (anotherObject.getBoundingBox().intersects(this.getBoundingBox())) {
            return true;
        }
        return false;
    }
}
