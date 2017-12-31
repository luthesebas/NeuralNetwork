package neuralnetwork;

import neuralnetwork.math.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeuralNetworkTest {

    @Test
    public void feedForward() {
        Vector expected = new Vector(17,39);
        Vector input = new Vector(5,6);
        NeuralNetwork brain = new NeuralNetwork(1,2,3,100);
        brain.feedForward(input);
        Vector result = brain.getResult();
        assertEquals(expected, result);
    }

}