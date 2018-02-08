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
    void fit() {
        Matrix[] inputs = new Matrix[]{new Matrix(2,1,0.1, 0.01)};
        Matrix[] labels = new Matrix[]{new Matrix(2,1,1)};
        NeuralNetwork brain = new NeuralNetwork(Matrix.I_2D);
        brain.fit(inputs, labels, 1);
    }


}