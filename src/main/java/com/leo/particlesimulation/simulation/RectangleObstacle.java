package com.leo.particlesimulation.simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleObstacle implements SimulationObject {
    private final long id;
    private double x;
    private double y;
    private double height;
    private double width;
    private Color color;
    private Rectangle rectangle;

    private RectangleObstacle(Builder builder) {
        this.id = builder.id;
        this.x = builder.x;
        this.y = builder.y;
        this.width = builder.width;
        this.height = builder.height;
        this.color = builder.color;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        this.rectangle.setFill(this.color);
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public static class Builder {
        private final long id;
        private double x = 0.0;
        private double y = 0.0;
        private double width = 0.0;
        private double height = 0.0;
        private Color color = Color.BLACK;

        public Builder(long id) {
            this.id = id;
        }

        public Builder position(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder dimensions(double width, double height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public RectangleObstacle build() {
            return new RectangleObstacle(this);
        }
    }
}
