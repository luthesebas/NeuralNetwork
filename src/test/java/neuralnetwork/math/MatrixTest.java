package neuralnetwork.math;

import neuralnetwork.Dimension;
import neuralnetwork.exception.IndexOutOfDimension;
import neuralnetwork.exception.InvalidDimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private final Matrix A = new Matrix(3, 3, 2);
    private final Matrix B = new Matrix(3, 2, 3);
    private final Matrix C = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private final Matrix D = new Matrix(3, 3, 9, 8, 7, 6, 5, 4, 3, 2, 1);

    private final Matrix a = new Matrix(3, 1, 1, 2, 3);
    private final Matrix b = new Matrix(3, 1, 3, 2, 1);

    @Test
    void operations() {
        Matrix input = new Matrix(4, 1, 1);
        Matrix layer0 = new Matrix(3, 4, 1);
        Matrix layer1 = new Matrix(3, 3, 1);
        Matrix layer2 = new Matrix(1, 3, 1);

        Matrix result = layer0.multiply(input);
        result = layer1.multiply(result);
        result = layer1.multiply(result);
        result = layer2.multiply(result);

        assertEquals(new Matrix(1, 1, 108), result);
    }

    @Test
    void add() {
        Matrix expected = new Matrix(3, 3, 10);
        Matrix actual = C.add(D);
        assertEquals(expected, actual);
    }

    @Test
    void add1() {
        Matrix expected = new Matrix(3, 1, 4);
        Matrix actual = a.add(b);
        assertEquals(expected, actual);
    }

    @Test
    void add2() {
        Matrix c = new Matrix(4, 1);
        Executable addException = () -> a.add(c);
        assertThrows(InvalidDimension.class, addException);
    }

    @Test
    void add3() {
        Executable addException = () -> a.add(A);
        assertThrows(InvalidDimension.class, addException);
    }

    @Test
    void subtract() {
        Matrix expected = new Matrix(3, 3);
        Matrix actual = C.subtract(C);
        assertEquals(expected, actual);
    }

    @Test
    void subtract1() {
        Matrix expected = new Matrix(new Dimension(3, 1), -2, 0, 2);
        Matrix actual = a.subtract(b);
        assertEquals(expected, actual);
    }

    @Test
    void subtract2() {
        Matrix c = new Matrix(new Dimension(4, 1));
        Executable subtractException = () -> a.subtract(c);
        assertThrows(InvalidDimension.class, subtractException);
    }

    @Test
    void subtract3() {
        Executable subtractException = () -> a.subtract(A);
        assertThrows(InvalidDimension.class, subtractException);
    }

    @Test
    void multiply() {
        Matrix expected = new Matrix(new Dimension(3, 2), 18);
        Matrix actual = A.multiply(B);
        assertEquals(expected, actual);
    }

    @Test
    void multiply1() {
        Matrix expected = new Matrix(new Dimension(3, 1), 12);
        Matrix actual = A.multiply(a);
        assertEquals(expected, actual);
    }

    @Test
    void multiply2() {
        Matrix expected = new Matrix(new Dimension(3, 3), 3, 2, 1, 6, 4, 2, 9, 6, 3);
        Matrix actual = a.multiply(b.transpose());
        assertEquals(expected, actual);
    }

    @Test
    void multiply3() {
        Matrix expected = new Matrix(new Dimension(3, 3), 4);
        Matrix actual = A.multiply(new Matrix(new Dimension(1, 1), 2));
        assertEquals(expected, actual);
    }

    @Test
    void multiply4() {
        Matrix expected = new Matrix(new Dimension(3, 1), 2, 4, 6);
        Matrix actual = a.multiply(new Matrix(new Dimension(1, 1), 2));
        assertEquals(expected, actual);
    }

    @Test
    void multiply5() {
        Executable multiplyException = () -> B.multiply(A);
        assertThrows(InvalidDimension.class, multiplyException);
    }

    @Test
    void multiply6() {
        Executable multiplyException = () -> a.multiply(A);
        assertThrows(InvalidDimension.class, multiplyException);
    }

    @Test
    void multiply7() {
        Matrix expected = new Matrix(new Dimension(3, 3), 4);
        Matrix actual = A.multiply(new Matrix(new Dimension(1, 1), 2));
        assertEquals(expected, actual);
    }

    @Test
    void negate() {
        Matrix expected = new Matrix(new Dimension(3, 3), -1, -2, -3, -4, -5, -6, -7, -8, -9);
        Matrix actual = C.negate();
        assertEquals(expected, actual);
    }

    @Test
    void transpose() {
        Matrix expected = new Matrix(new Dimension(3, 3), 1, 4, 7, 2, 5, 8, 3, 6, 9);
        Matrix actual = C.transpose();
        assertEquals(expected, actual);
    }

    @Test
    void transpose1() {
        Matrix expected = new Matrix(new Dimension(1, 3), 1, 2, 3);
        Matrix actual = a.transpose();
        assertEquals(expected, actual);
    }

    @Test
    void transpose2() {
        Matrix actual = C.transpose().transpose();
        assertEquals(C, actual);
    }

    @Test
    void transpose3() {
        Matrix actual = a.transpose().transpose();
        assertEquals(a, actual);
    }

    @Test
    void getDimension() {
        Dimension actual = A.getDimension();
        assertEquals(3, actual.getM(), 1);
        assertEquals(3, actual.getN(), 1);
    }

    @Test
    void getDimension1() {
        Dimension actual = B.getDimension();
        assertEquals(3, actual.getM(), 1);
        assertEquals(2, actual.getN(), 1);
    }

    @Test
    void getColumn() {
        double[] actual = C.getColumn(0);
        assertArrayEquals(new double[]{1, 4, 7}, actual);
    }

    @Test
    void getColumn1() {
        Executable getColumnException = () -> C.getColumn(3);
        assertThrows(IndexOutOfDimension.class, getColumnException);
    }

    @Test
    void getColumn2() {
        Executable getColumnException = () -> C.getColumn(-1);
        assertThrows(IndexOutOfDimension.class, getColumnException);
    }

    @Test
    void getRow() {
        double[] actual = C.getRow(0);
        assertArrayEquals(new double[]{1, 2, 3}, actual);
    }

    @Test
    void getRow1() {
        Executable geRowException = () -> C.getRow(3);
        assertThrows(IndexOutOfDimension.class, geRowException);
    }

    @Test
    void getRow2() {
        Executable geRowException = () -> C.getRow(-1);
        assertThrows(IndexOutOfDimension.class, geRowException);
    }

    @Test
    void getElement() {
        double actual = C.getElement(0, 0);
        assertEquals(1, actual, 1);
    }

    @Test
    void getElement1() {
        Executable getElementException = () -> C.getElement(0, 3);
        assertThrows(IndexOutOfDimension.class, getElementException);
    }

    @Test
    void getElement2() {
        Executable getElementException = () -> C.getElement(3, 0);
        assertThrows(IndexOutOfDimension.class, getElementException);
    }

    @Test
    void getElement3() {
        Executable getElementException = () -> C.getElement(-1, 0);
        assertThrows(IndexOutOfDimension.class, getElementException);
    }

    @Test
    void getElement4() {
        Executable getElementException = () -> C.getElement(0, -1);
        assertThrows(IndexOutOfDimension.class, getElementException);
    }

    @Test
    void getElements() {
        double[][] actual = C.getElements();
        assertArrayEquals(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, actual);
    }

    @Test
    void duplicate() {
        assertEquals(A, A.duplicate());
    }

    @Test
    void isScalar() {
        assertTrue((new Matrix(1, 1)).isScalar());
        assertTrue(a.transpose().multiply(b).isScalar());
    }

    @Test
    void isScalar1() {
        assertFalse(a.isScalar());
        assertFalse(b.isScalar());
        assertFalse(A.isScalar());
        assertFalse(B.isScalar());
        assertFalse(a.transpose().isScalar());
        assertFalse(b.transpose().isScalar());
    }

    @Test
    void isVector() {
        assertTrue(a.isVector());
        assertTrue(b.isVector());
        assertFalse(A.isVector());
        assertFalse(B.isVector());
        assertTrue(a.transpose().isVector());
        assertTrue(b.transpose().isVector());
    }

}