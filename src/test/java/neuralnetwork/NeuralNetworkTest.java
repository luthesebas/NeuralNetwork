package neuralnetwork;

import neuralnetwork.math.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NeuralNetworkTest {

    @Test
    void feedForward() {
        Matrix expected = new Matrix(2, 1, 0.6061277720053839, 0.5421337758147264);
        Matrix input = new Matrix(2, 1, 0.7, 0.6);
        Matrix layer0 = new Matrix(2, 2, 0.8, 0.5, -0.6, 0.7);
        Matrix layer1 = new Matrix(2, 2, 0.4, 0.3, -0.4, 0.9);
        NeuralNetwork brain = new NeuralNetwork(layer0, layer1);
        Matrix result = brain.feedForward(input, new Matrix[2], new Matrix[3]);
        assertEquals(expected, result);
    }

    @Test
    void calculateLayerWeightShift() {
        Matrix expected = new Matrix(2, 2, 4.213190763422101E-4, 2.4913743090011173E-4, -6.592162305861712E-4, -3.898124896734953E-4);
        NeuralNetwork brain = new NeuralNetwork(3, 2, 1);
        Matrix[] result = brain.calculateLayerWeightShift(
                new Matrix(2, 1, 0.9, 0.2),
                new Matrix(2, 1, 0.655, 0.55),
                NeuralNetwork.DEFAULT_EPSILON);
        assertEquals(expected, result[0]);
    }

}