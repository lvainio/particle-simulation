package com.leo.particlesimulation.simulation;

import javafx.scene.Node;

public interface SimulationObject {
    void update();

    Node toNode();
}
