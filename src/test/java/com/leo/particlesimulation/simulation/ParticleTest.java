package com.leo.particlesimulation.simulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ParticleTest {

    @Test
    void testAdd() {
        Particle p = new Particle.Builder(0).build();

        assertEquals(555, p.removeThisMethod(), "return 555.");
    }
}
