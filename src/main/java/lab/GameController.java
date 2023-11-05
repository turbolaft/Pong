package lab;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class GameController {
    private Game game;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private ToggleButton start;
    @FXML
    private Button user1_top;
    @FXML
    private Button user2_top;
    @FXML
    private Button user1_down;
    @FXML
    private Button user2_down;
    
    private AnimationTimer animationTimer;

    public GameController() {}

    public void start() {
        this.game = new Game(canvas.getWidth(), canvas.getHeight());
        this.game.getScoreManager().getLeftGoalArea().getScore().setName(text1.getText());
        this.game.getScoreManager().getRightGoalArea().getScore().setName(text2.getText());
        String fileName = "scores.txt";

        try (RandomAccessFile file = new RandomAccessFile(new File(fileName), "rw")) {
            // Set the file length to 0 to truncate the file
            file.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        input1.textProperty().addListener((observe, oldValue, newValue) -> {
            text1.setText(newValue);
            this.game.getScoreManager().getLeftGoalArea().getScore().setName(newValue);
        });
        input2.textProperty().addListener((observe, oldValue, newValue) -> {
            text2.setText(newValue);
            this.game.getScoreManager().getRightGoalArea().getScore().setName(newValue);
        });

        user1_top.setOnAction((event) -> game.controlBats(new Point2D(0, -100), 2));
        user2_top.setOnAction((event) -> game.controlBats(new Point2D(0, -100), 1));
        user1_down.setOnAction((event) -> game.controlBats(new Point2D(0, 100), 2));
        user2_down.setOnAction((event) -> game.controlBats(new Point2D(0, 100), 1));


        animationTimer = new DrawingThread(canvas, game);
        start.setOnAction((event) -> ((DrawingThread) animationTimer).setStartness());
        animationTimer.start();
    }

    public void stop() {
		animationTimer.stop();
	}

}
