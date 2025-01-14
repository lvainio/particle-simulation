package com.leo.particlesimulation.graphic;

import com.leo.particlesimulation.simulation.Particle;
import com.leo.particlesimulation.simulation.RectangleObstacle;
import com.leo.particlesimulation.simulation.SimulationObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Graphics1 {

    public Graphics1(Stage stage) {
        int width = 1000;
        int height = 800;

        List<SimulationObject> simulationObjects = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 40000; i++) {
            double x = random.nextDouble() * width;
            double y = random.nextDouble() * height;
            double vx = random.nextDouble() * 5 - 2.5;
            double vy = random.nextDouble() * 5 - 2.5;
            double radius = random.nextDouble() * 20 + 5.0;

            int r = random.nextInt(256);  // Red component
            int g = random.nextInt(256);  // Green component
            int b = random.nextInt(256);  // Blue component

            simulationObjects.add(
                    new Particle.Builder((long) i)
                            .position(x, y)
                            .velocity(vx, vy)
                            .color(Color.rgb(r, g, b))
                            .radius(radius)
                            .build());
        }

        SimulationObject leftWall = new RectangleObstacle.Builder(-1).position(0, 0).dimensions(2, height).build();
        SimulationObject rightWall = new RectangleObstacle.Builder(-2).position(width-2, 0).dimensions(2, height).build();
        SimulationObject topWall = new RectangleObstacle.Builder(-3).position(0, 0).dimensions(width, 2).build();
        SimulationObject bottomWall = new RectangleObstacle.Builder(-4).position(0, height-2).dimensions(width, 2).build();

        simulationObjects.add(leftWall);
        simulationObjects.add(rightWall);
        simulationObjects.add(topWall);
        simulationObjects.add(bottomWall);

        Timeline timeline = new Timeline();
        AtomicInteger iterations = new AtomicInteger(1000);

        KeyFrame keyFrame =
                new KeyFrame(
                        Duration.millis(10),
                        e -> {
                            if (iterations.get() > 0) {
                                for (SimulationObject so : simulationObjects) {
                                    so.update();
                                }
                                iterations.decrementAndGet();
                            }
                        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();
        for (SimulationObject so : simulationObjects) {
            root.getChildren().add(so.toNode());
        }

        Scene scene = new Scene(root, width, height);

        stage.setTitle("Moving Circle");
        stage.setScene(scene);
        stage.show();

        timeline.play();
    }
}
