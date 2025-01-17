package com.leo.particlesimulation.simulation;

public class SimulationConfig {
    private static final double DEFAULT_DT = 0.01;
    private static final double DEFAULT_GRAVITY = 9.82;
    private static final double DEFAULT_WIDTH = 1000.0;
    private static final double DEFAULT_HEIGHT = 800.0;

    private double dt; // seconds
    private double gravity; // m/s^2
    private double width; // m
    private double height; // m

    private SimulationConfig(Builder builder) {
        this.dt = builder.dt;
        this.gravity = builder.gravity;
        this.width = builder.width;
        this.height = builder.height;
    }

    public static class Builder {
        private double dt = DEFAULT_DT;
        private double gravity = DEFAULT_GRAVITY;
        private double width = DEFAULT_WIDTH;
        private double height = DEFAULT_HEIGHT;

        public Builder setDt(double dt) {
            if (dt <= 0.0) {
                throw new IllegalArgumentException("Time step (dt) must be positive.");
            }
            this.dt = dt;
            return this;
        }

        public Builder setGravity(double gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setWidth(double width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }

        public SimulationConfig build() {
            return new SimulationConfig(this);
        }
    }
}
