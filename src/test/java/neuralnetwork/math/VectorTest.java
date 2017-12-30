package neuralnetwork.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    private Vector a = new Vector(1,2,3);
    private Vector b = new Vector(3,2,1);

    @Test
    void add() {
        Vector expected = new Vector(4,4,4);
        Vector result = a.add(b);
        assertEquals(expected, result);
    }

    @Test
    void add1() {
        Vector expected = new Vector(8,9,10);
        Vector result = a.add(7);
        assertEquals(expected, result);
    }

    @Test
    void subtract() {
        Vector expected = new Vector(-2,0,2);
        Vector result = a.subtract(b);
        assertEquals(expected, result);
    }

    @Test
    void subtract1() {
        Vector expected = new Vector(-2,-1,0);
        Vector result = a.subtract(3);
        assertEquals(expected, result);
    }

    @Test
    void multiply() {
        Vector expected = new Vector(3,4,3);
        Vector result = a.multiply(b);
        assertEquals(expected, result);
    }

    @Test
    void multiply1() {
        Vector expected = new Vector(7,14,21);
        Vector result = a.multiply(7);
        assertEquals(expected, result);
    }

    @Test
    void dot() {
        float expected = 10;
        float result = a.dot(b);
        assertEquals(expected, result);
    }

    @Test
    void sumUp() {
        float expected = 6f;
        float result = a.sumUp();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    void negate() {
        Vector expected = new Vector(-1,-2,-3);
        Vector result = a.negate();
        assertEquals(expected, result);
    }

    @Test
    void scale() {
        Vector expected = new Vector(3, 6, 9);
        Vector result = a.scale(3);
        assertEquals(expected, result);
    }

    @Test
    void toArray() {
        float[] expected = new float[]{1f, 2f, 3f};
        float[] result = a.toArray();
        assertArrayEquals(expected, result);
    }
}