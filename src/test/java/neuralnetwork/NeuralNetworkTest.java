package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NeuralNetworkTest {

    @Test
    public void feedForward() {
        Vector expected = new Vector(new float[]{0.74674326f, 0.7892308f});
        Vector input = new Vector(new float[]{5,6,7});
        NeuralNetwork brain = new NeuralNetwork(3,2, 1);
        Vector result = brain.feedForward(input, new Vector[2], new Vector[3]);
        assertEquals(expected, result);
    }

    @Test
    public void feedForward1() {
        Vector expected = new Vector(new float[]{0.6061278f, 0.54213375f});
        Vector input = new Vector(new float[]{0.7f,0.6f});
        Matrix layer0 = new Matrix(2, 0.8f, 0.5f, -0.6f, 0.7f);
        Matrix layer1 = new Matrix(2, 0.4f, 0.3f, -0.4f, 0.9f);
        NeuralNetwork brain = new NeuralNetwork(layer0, layer1);
        Vector result = brain.feedForward(input, new Vector[2], new Vector[3]);
        assertEquals(expected, result);
    }

    @Test
    public void feedForward2() {
        Vector expected = new Vector(new float[]{0.74674326f});
        Vector input = new Vector(new float[]{1,2,3,4});
        NeuralNetwork brain = new NeuralNetwork(4,1, 2);
        Vector result = brain.feedForward(input, new Vector[3], new Vector[4]);
        assertEquals(expected, result);
    }

    @Test
    public void calculateLayerWeightShift() {
        Matrix expected = new Matrix(2, 4.213191E-4f, 2.4913746E-4f, -6.592163E-4f, -3.898125E-4f);
        NeuralNetwork brain = new NeuralNetwork(3,2, 1);
        Matrix[] result = brain.calculateLayerWeightShift(
                new Vector(new float[]{0.9f,0.2f}),
                new Vector(new float[]{0.655f,0.55f}),
                NeuralNetwork.DEFAULT_EPSILON);
        assertEquals(expected, result[0]);
    }

}