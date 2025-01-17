package com.leo.particlesimulation.graphic;

import com.leo.particlesimulation.simulation.Particle;
import com.leo.particlesimulation.simulation.SimulationObject;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GraphicEngine {

    private Canvas canvas;
    private GraphicsContext gc;

    public GraphicEngine(Stage stage) {
        int width = 1000;
        int height = 800;

        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();

        StackPane canvasWrapper = new StackPane();
        canvasWrapper.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        canvasWrapper.getChildren().add(canvas);
        canvasWrapper.setPrefSize(width, height);

        VBox controlPanel = new VBox(10);
        controlPanel.setStyle(
                "-fx-background-color: #f0f0f0; -fx-border-color: black; -fx-border-width: 1px;");
        controlPanel.setPrefWidth(300);

        controlPanel.getChildren().add(new Text("Control Panel"));
        controlPanel.getChildren().add(new Text("Setting 1"));
        controlPanel.getChildren().add(new Text("Setting 2"));

        BorderPane root = new BorderPane();
        root.setLeft(canvasWrapper);
        root.setRight(controlPanel);

        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 1400, 840);
        stage.setTitle("Particle Simulation");
        stage.setResizable(false);
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
                    this.gc.fillOval(x_adjusted, y_adjusted, radius * 2.0, radius * 2.0);
                }
                default -> System.out.println("INVALID SIMULATION OBJECT");
            }
        }
    }
}
