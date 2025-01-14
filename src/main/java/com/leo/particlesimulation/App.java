package com.leo.particlesimulation;

import com.leo.particlesimulation.simulation.Particle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        int width = 800;
        int height = 600;

        Particle p1 =
                new Particle.Builder(1)
                        .position(100, 100)
                        .velocity(5, 5)
                        .radius(60.0)
                        .color(Color.GRAY)
                        .build();

        Particle p2 =
                new Particle.Builder(2)
                        .position(200, 400)
                        .radius(30.0)
                        .color(Color.MAGENTA)
                        .build();

        Particle p3 =
                new Particle.Builder(2)
                        .position(400, 300)
                        .radius(100.0)
                        .color(Color.LIGHTBLUE)
                        .build();

        Particle p4 =
                new Particle.Builder(2)
                        .position(500, 150)
                        .radius(40.0)
                        .color(Color.CHOCOLATE)
                        .build();

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        drawCircle(
                pixelWriter,
                (int) p1.getX(),
                (int) p1.getY(),
                (int) p1.getRadius(),
                p1.getColor(),
                width,
                height);
        drawCircle(
                pixelWriter,
                (int) p2.getX(),
                (int) p2.getY(),
                (int) p2.getRadius(),
                p2.getColor(),
                width,
                height);
        drawCircle(
                pixelWriter,
                (int) p3.getX(),
                (int) p3.getY(),
                (int) p3.getRadius(),
                p3.getColor(),
                width,
                height);
        drawCircle(
                pixelWriter,
                (int) p4.getX(),
                (int) p4.getY(),
                (int) p4.getRadius(),
                p4.getColor(),
                width,
                height);

        ImageView imageView = new ImageView(writableImage);

        // Add ImageView to the scene
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, width, height);

        stage.setTitle("Large Number of Circles with WritableImage");
        stage.setScene(scene);
        stage.show();
    }

    // Draw a circle on the WritableImage buffer (pixel by pixel)
    private void drawCircle(
            PixelWriter pixelWriter,
            int centerX,
            int centerY,
            int radius,
            Color color,
            int width,
            int height) {
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x * x + y * y <= radius * radius) {
                    int drawX = centerX + x;
                    int drawY = centerY + y;

                    // Only draw within the bounds of the image
                    if (drawX >= 0 && drawX < width && drawY >= 0 && drawY < height) {
                        pixelWriter.setColor(drawX, drawY, color);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
