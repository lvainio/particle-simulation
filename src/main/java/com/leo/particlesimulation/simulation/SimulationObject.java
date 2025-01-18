package com.leo.particlesimulation.simulation;

import javafx.scene.paint.Color;

public interface SimulationObject {
    void update(double dt, double gravity, double width, double height);

    Color getColor();
}
