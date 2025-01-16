package com.leo.particlesimulation;

import com.leo.particlesimulation.graphic.GraphicEngine;
import com.leo.particlesimulation.simulation.SimulationEngine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        SimulationEngine simulationEngine = new SimulationEngine();
        GraphicEngine graphicEngine = new GraphicEngine(stage);

        int numSteps = 10000;
        int delayMs = 1000 / 30;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(numSteps);
        timeline.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.millis(delayMs),
                                e -> {
                                    simulationEngine.step();
                                    graphicEngine.drawSimulationObjects(
                                            simulationEngine.getSimulationObjects());
                                }));
        timeline.play();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
