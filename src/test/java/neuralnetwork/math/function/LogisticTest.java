package neuralnetwork.math.function;

import neuralnetwork.math.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogisticTest {

    Logistic function = new Logistic();

    @Test
    void calculate() {
        double expected = 0.7613327;
        double result = function.calculate(1.16);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void calculate1() {
        Matrix expected = new Matrix(2,1,0.7613327084420322, 0.45016600419083186);
        Matrix result = function.calculate(new Matrix(2,1,1.16, -0.2));
        assertEquals(expected, result);
    }

    @Test
    void derivative() {
        double expected = 0.24751657;
        double result = function.derivative(0.2);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void derivative1() {
        double expected = 0.24751657;
        double result = function.derivative(0.2, 0.549834);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void derivative2() {
        Matrix expected = new Matrix(2,1,0.24751657286169182, 0.2261212919569842);
        Matrix result = function.derivative(new Matrix(2,1,0.2, 0.639));
        assertEquals(expected, result);
    }

    @Test
    void derivative3() {
        Matrix expected = new Matrix(2,1,0.24751657286169182, 0.2261212919569842);
        Matrix result = function.derivative(
                new Matrix(2,1,0.2, 0.639),
                new Matrix(2,1,0.5498339958091681, 0.6545273698831887));
        assertEquals(expected, result);
    }

}