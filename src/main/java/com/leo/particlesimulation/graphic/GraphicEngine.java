package com.leo.particlesimulation.graphic;

import com.leo.particlesimulation.simulation.Particle;
import com.leo.particlesimulation.simulation.SimulationObject;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicEngine {

    private Canvas canvas;
    private GraphicsContext gc;

    public GraphicEngine(Stage stage) {
        int width = 1000;
        int height = 800;

        Group root = new Group();

        this.canvas = new Canvas(width, height);
        root.getChildren().add(this.canvas);

        this.gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, width, height);

        stage.setTitle("Particle Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public void drawSimulationObjects(List<SimulationObject> simulationObjects) {
        this.gc.clearRect(0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
        for (SimulationObject so : simulationObjects) {
            Color color = so.getColor();
            this.gc.setFill(color);

            switch (so) {
                case Particle p -> {
                    double radius = p.getRadius();
                    double x_adjusted = p.getX() - radius;
                    double y_adjusted = p.getY() - radius;
                    this.gc.fillOval(x_adjusted, y_adjusted, radius, radius);
                }
                default -> System.out.println("INVALID SIMULATION OBJECT");
            }
        }
    }
}
