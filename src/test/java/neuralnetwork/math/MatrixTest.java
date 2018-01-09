package neuralnetwork.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

    private Matrix a = new Matrix(2, 1,2,3,4);
    private Matrix b = new Matrix(3, 1,2,3,4,5,6);
    private Matrix c = new Matrix(2, 1,2,3,4,5,6);

    @Test
    void getDimension() {
        Dimension expected = new Dimension(2, 2);
        Dimension result = a.getDimension();
        assertEquals(expected, result);
    }

    @Test
    void getDimension1() {
        Dimension expected = new Dimension(3, 2);
        Dimension result = b.getDimension();
        assertEquals(expected, result);
    }

    @Test
    void getDimension2() {
        Dimension expected = new Dimension(2, 3);
        Dimension result = c.getDimension();
        assertEquals(expected, result);
    }

    @Test
    void toArray() {
        float[][] expected = new float[][]{{1,2}, {3,4}};
        float[][] result = a.toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    void toArray1() {
        float[][] expected = new float[][]{{1,2}, {3,4}, {5,6}};
        float[][] result = b.toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    void toArray2() {
        float[][] expected = new float[][]{{1,2,3}, {4,5,6}};
        float[][] result = c.toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    void multiply() {
        Vector expected = new Vector(new float[]{17,39});
        Vector other = new Vector(new float[]{5,6});
        Vector result = a.multiply(other);
        assertEquals(expected, result);
    }

    @Test
    void getColumn() {
        Vector expected = new Vector(new float[]{1,3,5});
        Vector result = b.getColumn(0);
        assertEquals(expected, result);
    }
}