package com.leo.particlesimulation.simulation;

import javafx.scene.paint.Color;

public class Particle {
    private final long ID;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double mass;
    private double radius;
    private double restitution;
    private Color color;

    private Particle(Builder builder) {
        this.ID = builder.ID;
        this.x = builder.x;
        this.y = builder.y;
        this.vx = builder.vx;
        this.vy = builder.vy;
        this.mass = builder.mass;
        this.radius = builder.radius;
        this.restitution = builder.restitution;
        this.color = builder.color;
    }

    public double getID() {
        return this.ID;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getVx() {
        return this.vx;
    }

    public double getVy() {
        return this.vy;
    }

    public double getMass() {
        return this.mass;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getRestitution() {
        return this.restitution;
    }

    public Color getColor() {
        return this.color;
    }

    public static class Builder {
        private final long ID;
        private double x = 0.0;
        private double y = 0.0;
        private double vx = 0.0;
        private double vy = 0.0;
        private double mass = 1.0;
        private double radius = 1.0; 
        private double restitution = 1.0;
        private Color color = Color.BLACK; 

        public Builder(long ID) {
            this.ID = ID;
        }

        public Builder position(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder velocity(double vx, double vy) {
            this.vx = vx;
            this.vy = vy;
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder radius(double radius) {
            this.radius = radius;
            return this;
        }

        public Builder restitution(double restitution) {
            this.restitution = restitution;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Particle build() {
            return new Particle(this);
        }
    }
}
