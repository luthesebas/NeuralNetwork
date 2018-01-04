package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NeuralNetworkTest {

    @Test
    public void feedForward() {
        Vector expected = new Vector(0.9999362f, 0.9999871f);
        Vector input = new Vector(5,6,7);
        NeuralNetwork brain = new NeuralNetwork(3,2, 1, 100);
        Vector result = brain.feedForward(input);
        assertEquals(expected, result);
    }

    @Test
    public void adjustWeights() {
        Matrix expected = new Matrix(2, 4.213191E-4f, 2.4913746E-4f, -6.592163E-4f, -3.898125E-4f);
        NeuralNetwork brain = new NeuralNetwork(3,2, 1, 100);
        Matrix result = brain.adjustWeights(new Vector(0.9f,0.2f), new Vector(0.655f,0.55f));
        assertEquals(expected, result);
    }

}