package com.leo.particlesimulation;

import com.leo.particlesimulation.graphic.GraphicEngine;
import com.leo.particlesimulation.simulation.SimulationEngine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public final class App extends Application {

    @Override
    public void start(Stage stage) {
        SimulationEngine simulationEngine = new SimulationEngine();
        GraphicEngine graphicEngine = new GraphicEngine(stage);

        AnimationTimer timer =
                new AnimationTimer() {
                    private long lastUpdate = 0;
                    private int stepCount = 0;
                    private final int numSteps = 10000;

                    @Override
                    public void handle(long now) {
                        if (lastUpdate == 0) {
                            lastUpdate = now;
                            return;
                        }

                        simulationEngine.step();
                        graphicEngine.drawSimulationObjects(
                                simulationEngine.getSimulationObjects());

                        stepCount++;
                        if (stepCount >= numSteps) {
                            this.stop();
                        }
                    }
                };
        timer.start();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
