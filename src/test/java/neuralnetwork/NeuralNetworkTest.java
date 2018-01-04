package neuralnetwork;

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

}