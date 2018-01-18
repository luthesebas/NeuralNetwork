package neuralnetwork.math.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogisticTest {

    Logistic function = new Logistic();

    @Test
    void calculate() {
        float expected = 0.7613327f;
        float result = function.calculate(1.16f);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void calculate1() {
        Vector expected = new Vector(new float[]{0.7613327f, 0.45016602f});
        Vector result = function.calculate(new Vector(new float[]{1.16f, -0.2f}));
        assertEquals(expected, result);
    }

    @Test
    void derivative() {
        float expected = 0.24751657f;
        float result = function.derivative(0.2f);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void derivative1() {
        float expected = 0.24751657f;
        float result = function.derivative(0.2f, 0.549834f);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    void derivative2() {
        Vector expected = new Vector(new float[]{0.24751657f, 0.22612129f});
        Vector result = function.derivative(new Vector(new float[]{0.2f, 0.639f}));
        assertEquals(expected, result);
    }

    @Test
    void derivative3() {
        Vector expected = new Vector(new float[]{0.24751657f, 0.22612129f});
        Vector result = function.derivative(
                new Vector(new float[]{0.2f, 0.639f}),
                new Vector(new float[]{0.549834f, 0.65452737f}));
        assertEquals(expected, result);
    }

}