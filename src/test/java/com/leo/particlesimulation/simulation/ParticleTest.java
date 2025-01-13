package com.leo.particlesimulation.simulation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParticleTest {

    @Test
    void testAdd() {
        Particle p = new Particle.Builder(0).build();

        assertEquals(555, p.removeThisMethod(), "return 555.");
    }
}
