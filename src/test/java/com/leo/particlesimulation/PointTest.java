package com.leo.particlesimulation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    void testAdd() {
        int a = 5;
        int b = 3;

        int result = Point.add(a, b);

        assertEquals(8, result, "The add method should correctly sum the two numbers.");
    }
}