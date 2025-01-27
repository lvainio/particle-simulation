package com.leo.particlesimulation.simulation;

import javafx.scene.paint.Color;

/**
 * Represents a particle in the simulation with properties such as position, velocity, mass, radius,
 * restitution, and color. The particle can interact with other particles and is part of a broader
 * simulation environment.
 *
 * @author Leo Vainio
 * @since 2024-01-14
 */
public class Particle implements SimulationObject {
    private final long id;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double mass;
    private double radius;
    private double restitution;
    private Color color;

    private Particle(Builder builder) {
        this.id = builder.id;
        this.x = builder.x;
        this.y = builder.y;
        this.vx = builder.vx;
        this.vy = builder.vy;
        this.mass = builder.mass;
        this.radius = builder.radius;
        this.restitution = builder.restitution;
        this.color = builder.color;
    }

    /** Updates the state of this particle based on its current velocity. */
    @Override
    public void update(double dt, double gravity, double width, double height) {
        this.vy += gravity * dt;

        double dx = this.vx * dt;
        double dy = this.vy * dt;
        boolean collidesWithLeftWallDuringTimeStep = (this.x - this.radius) + dx < 0.0;
        boolean collidesWithRightWallDuringTimeStep = (this.x + this.radius) + dx > width;

        if (collidesWithLeftWallDuringTimeStep) {
            double dx1 = this.x - this.radius;
            double t1 = -dx1 / this.vx;
            double t2 = dt - t1;
            this.vx = -this.vx * this.restitution;
            this.vy = this.vy * this.restitution;
            double dx2 = t2 * this.vx;
            this.x = this.radius + dx2;

        } else if (collidesWithRightWallDuringTimeStep) {
            double dx1 = width - this.x - this.radius;
            double t1 = dx1 / this.vx;
            double t2 = dt - t1;
            this.vx = -this.vx * this.restitution;
            this.vy = this.vy * this.restitution;
            double dx2 = -t2 * this.vx;
            this.x = width - this.radius - dx2;

        } else {
            this.x += dx;
        }

        boolean collidesWithTopWallDuringTimeStep = (this.y - this.radius) + dy < 0.0;
        boolean collidesWithBottomWallDuringTimeStep = (this.y + this.radius) + dy > height;

        if (collidesWithTopWallDuringTimeStep) {
            double dy1 = this.y - this.radius;
            double t1 = -dy1 / this.vy;
            double t2 = dt - t1;
            this.vx = this.vx * this.restitution;
            this.vy = -this.vy * this.restitution;
            double dy2 = t2 * this.vy;
            this.y = this.radius + dy2;

        } else if (collidesWithBottomWallDuringTimeStep) {
            double dy1 = height - this.y - this.radius;
            double t1 = dy1 / this.vy;
            double t2 = dt - t1;
            this.vx = this.vx * this.restitution;
            this.vy = -this.vy * this.restitution;
            double dy2 = -t2 * this.vy;
            this.y = height - this.radius - dy2;

        } else {
            this.y += dy;
        }

        double threshold = 1e-3;
        if (Math.abs(this.vx) < threshold) {
            this.vx = 0.0;
        }
        if (Math.abs(this.vy) < threshold) {
            this.vy = 0.0;
        }
    }

    /**
     * Retrieves the unique identifier of this particle.
     *
     * @return the unique identifier of the particle
     */
    public double getId() {
        return this.id;
    }

    /**
     * Retrieves the x-coordinate of this particle.
     *
     * <p>The x-coordinate represents the horizontal position of the particle's center. In the
     * simulation, the x-coordinate increases from left to right, with x = 0 at the leftmost edge.
     *
     * @return the x-coordinate in the simulation space
     */
    public double getX() {
        return this.x;
    }

    /**
     * Retrieves the y-coordinate of this particle.
     *
     * <p>The y-coordinate represents the vertical position of the particle's center. In the
     * simulation, the y-coordinate increases downward, with y = 0 at the topmost edge.
     *
     * @return the y-coordinate in the simulation space
     */
    public double getY() {
        return this.y;
    }

    /**
     * Retrieves the horizontal velocity of this particle.
     *
     * <p>The velocity along the x-axis represents the rate of change of the particle's horizontal
     * position in the simulation, measured in meters per second (m/s). Positive values indicate
     * movement to the right, while negative values indicate movement to the left.
     *
     * @return the horizontal velocity of the particle in m/s
     */
    public double getVx() {
        return this.vx;
    }

    /**
     * Retrieves the vertical velocity of this particle.
     *
     * <p>The velocity along the y-axis represents the rate of change of the particle's vertical
     * position in the simulation, measured in meters per second (m/s). Positive values indicate
     * downward movement, while negative values indicate upward movement.
     *
     * @return the vertical velocity of the particle in m/s
     */
    public double getVy() {
        return this.vy;
    }

    /**
     * Retrieves the mass of this particle.
     *
     * <p>The mass is measured in kilograms (kg).
     *
     * @return the mass of the particle in kilograms (kg)
     */
    public double getMass() {
        return this.mass;
    }

    /**
     * Retrieves the radius of this particle.
     *
     * <p>The radius represents the distance from the center of the particle to its outer boundary,
     * measured in meters (m).
     *
     * @return the radius of the particle in meters (m)
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Retrieves the restitution coefficient of this particle.
     *
     * <p>The restitution coefficient represents the elasticity of the particle when it collides
     * with other objects. A value of 0 indicates a perfectly inelastic collision (no bounce), while
     * a value of 1 indicates a perfectly elastic collision (no energy loss). Values between 0 and 1
     * indicate varying degrees of elasticity.
     *
     * @return the restitution coefficient of the particle
     */
    public double getRestitution() {
        return this.restitution;
    }

    /**
     * Retrieves the color of this particle.
     *
     * @return the color of the particle
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Particle{"
                + "id="
                + id
                + ", x="
                + x
                + ", y="
                + y
                + ", vx="
                + vx
                + ", vy="
                + vy
                + ", mass="
                + mass
                + ", radius="
                + radius
                + ", restitution="
                + restitution
                + ", color="
                + color
                + '}';
    }

    /**
     * Builder class for creating instances of the Particle class.
     *
     * <p>This builder allows for a flexible and fluent way to construct a Particle object, setting
     * various properties like position, velocity, mass, radius, restitution, and color. The builder
     * ensures that the required fields are set, and it provides sensible defaults for optional
     * fields.
     */
    public static class Builder {
        private final long id;
        private double x = 0.0;
        private double y = 0.0;
        private double vx = 0.0;
        private double vy = 0.0;
        private double mass = 1.0;
        private double radius = 1.0;
        private double restitution = 1.0;
        private Color color = Color.BLACK;

        /**
         * Creates a new Builder instance for a Particle with a specified id.
         *
         * @param id the unique identifier for the Particle
         */
        public Builder(long id) {
            this.id = id;
        }

        /**
         * Sets the position of the Particle.
         *
         * @param x the x-coordinate of the Particle's position
         * @param y the y-coordinate of the Particle's position
         * @return the Builder instance
         */
        public Builder position(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }

        /**
         * Sets the velocity of the Particle.
         *
         * @param vx the x-component of the Particle's velocity
         * @param vy the y-component of the Particle's velocity
         * @return the Builder instance
         */
        public Builder velocity(double vx, double vy) {
            this.vx = vx;
            this.vy = vy;
            return this;
        }

        /**
         * Sets the mass of the Particle.
         *
         * @param mass the mass of the Particle
         * @return the Builder instance
         */
        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        /**
         * Sets the radius of the Particle.
         *
         * @param radius the radius of the Particle
         * @return the Builder instance
         */
        public Builder radius(double radius) {
            this.radius = radius;
            return this;
        }

        /**
         * Sets the restitution coefficient of the Particle.
         *
         * @param restitution the restitution coefficient of the Particle
         * @return the Builder instance
         */
        public Builder restitution(double restitution) {
            this.restitution = restitution;
            return this;
        }

        /**
         * Sets the color of the Particle.
         *
         * @param color the color of the Particle
         * @return the Builder instance
         */
        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        /**
         * Builds and returns a new Particle object with the properties set in the Builder.
         *
         * @return a new Particle instance
         */
        public Particle build() {
            return new Particle(this);
        }
    }
}
