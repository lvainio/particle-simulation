package com.leo.particlesimulation.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class SimulationEngine {

    private SimulationConfig simulationConfig;
    private List<SimulationObject> simulationObjects;

    public SimulationEngine(SimulationConfig simulationConfig) {
        double width = simulationConfig.getWidth();
        double height = simulationConfig.getHeight();
        Random rng = new Random();
        this.simulationObjects = new ArrayList<>();
        this.simulationConfig = simulationConfig;

        for (int i = 0; i < 5; i++) {
            double x = rng.nextDouble() * width;
            double y = rng.nextDouble() * height;
            double vx = rng.nextDouble() * 500.0 - 250.0;
            double vy = rng.nextDouble() * 500.0 - 250.0;

            int r = rng.nextInt(256);
            int g = rng.nextInt(256);
            int b = rng.nextInt(256);

            Particle particle =
                    new Particle.Builder(i)
                            .position(x, y)
                            .velocity(vx, vy)
                            .radius(rng.nextDouble() * 10 + 2)
                            .mass(1.0)
                            .restitution(0.8)
                            .color(Color.rgb(r, g, b))
                            .build();

            this.simulationObjects.add(particle);
        }
    }

    public void step() {
        double dt = simulationConfig.getDt();
        double gravity = simulationConfig.getGravity();
        double width = simulationConfig.getWidth();
        double height = simulationConfig.getHeight();

        for (SimulationObject so : this.simulationObjects) {
            so.update(dt, gravity, width, height);
        }
    }

    public List<SimulationObject> getSimulationObjects() {
        return this.simulationObjects;
    }
}
