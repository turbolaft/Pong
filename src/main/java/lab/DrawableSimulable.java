package lab;

import javafx.scene.canvas.GraphicsContext;

interface DrawableSimulable {
    void draw(GraphicsContext gc);
    void simulate(double deltaT);
}
