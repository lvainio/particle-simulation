package com.leo.particlesimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

   @Override
    public void start(Stage stage) {
        int width = 800;
        int height = 600;
        
        // Create a WritableImage (image buffer)
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        // Fill the image with white color (background)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelWriter.setColor(x, y, Color.WHITE);
            }
        }

        // Draw 1 million circles onto the buffer
        for (int i = 0; i < 100; i++) {
            // Random position for each circle
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int radius = 4;

            // Draw a circle onto the writable image using pixel manipulation
            drawCircle(pixelWriter, x, y, radius, Color.GREEN, width, height);
        }

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(writableImage);

        // Add ImageView to the scene
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, width, height);

        stage.setTitle("Large Number of Circles with WritableImage");
        stage.setScene(scene);
        stage.show();
    }

    // Draw a circle on the WritableImage buffer (pixel by pixel)
    private void drawCircle(PixelWriter pixelWriter, int centerX, int centerY, int radius, Color color, int width, int height) {
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