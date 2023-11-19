package lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Scanner;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Score extends Entity implements DrawableSimulable, Serializable {
    private int count;
    private String name;
    private Boolean lastToScore = false;
    private long lastModifiedTime = -1;

    Score(int sizex, int sizey, Point2D position) {
        super(sizex, sizey, position);
        this.count = 0;
        this.name = null;
    }

    public void simulate(double deltaT) {
        int newCount = count;
        newCount++;
        lastToScore = true;
        String buffer = null;

        try (Scanner scanner = new Scanner(new File(ScoreManager.SCORE_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String name = line.split(":")[0];
                if (!name.equals(this.name)) {
                    buffer = line;
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        

        try(FileWriter fw = new FileWriter(ScoreManager.SCORE_FILE_PATH)) {
            fw.write(name + ":" + newCount + "\n");
            if (buffer != null) {
                fw.append(buffer);
            }
            fw.close();
        } catch(IOException err) {
            err.printStackTrace();
        }

        System.out.println("Goal for " + name);
    }

    public void draw(GraphicsContext gc) {
        try {
            File scoreFile = new File(ScoreManager.SCORE_FILE_PATH);
            long currentModifiedTime = Files.getLastModifiedTime(scoreFile.toPath()).toMillis();

            if (lastModifiedTime != currentModifiedTime) {
                Scanner scanner = new Scanner(scoreFile);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    if (line.split(":")[0].equals(name)) {
                        int countFromFile = Integer.parseInt(line.split(":")[1]);
                        count = countFromFile;
                    }
                }
            }

            lastModifiedTime = currentModifiedTime;
        } catch(IOException e) {
            e.printStackTrace();
        }

        gc.setFont(new javafx.scene.text.Font("Arial", SIZEX));
        gc.fillText(String.valueOf(this.count), position.getX(), position.getY());
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return this.count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLastToScore() {
        return this.lastToScore;
    }

    public void setLastToScore(Boolean lastToScore) {
        this.lastToScore = lastToScore;
    }
}
