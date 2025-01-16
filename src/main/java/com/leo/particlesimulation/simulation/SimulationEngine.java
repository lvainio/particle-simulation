package com.leo.particlesimulation.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class SimulationEngine {

    private List<SimulationObject> simulationObjects;

    public SimulationEngine() {
        double width = 1000;
        double height = 800;
        Random rng = new Random();
        this.simulationObjects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = rng.nextDouble() * width;
            double y = rng.nextDouble() * height;
            double vx = rng.nextDouble() * 5 + 1.0;
            double vy = rng.nextDouble() * 5 + 1.0;

            int r = rng.nextInt(256);
            int g = rng.nextInt(256);
            int b = rng.nextInt(256);

            Particle particle =
                    new Particle.Builder(i)
                            .position(x, y)
                            .velocity(vx, vy)
                            .radius(3.0)
                            .mass(1.0)
                            .restitution(0.9)
                            .color(Color.rgb(r, g, b))
                            .build();

            this.simulationObjects.add(particle);
        }
    }

    public void step() {
        for (SimulationObject so : this.simulationObjects) {
            so.update();
        }
    }

    public List<SimulationObject> getSimulationObjects() {
        return this.simulationObjects;
    }
}
