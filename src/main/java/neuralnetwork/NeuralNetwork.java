package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.function.IFunction;
import neuralnetwork.math.function.Logistic;

import java.util.Objects;
import java.util.Random;

/**
 *
 */
public class NeuralNetwork {

    public static final double DEFAULT_EPSILON = 0.01;

    private final IFunction activation;
    private final Matrix[] layers;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public NeuralNetwork(Matrix... layers) {
        this(new Logistic(), layers);
    }

    public NeuralNetwork(IFunction activation, Matrix... layers) {
        this.activation = Objects.requireNonNull(activation);
        this.layers = Objects.requireNonNull(layers);
    }

    public NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers) {
        this(inputNeurons, outputNeurons, hiddenLayers, outputNeurons);
    }

    private NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers, int hiddenNeurons) {
        Random random = new Random(100);
        this.activation = new Logistic();

        this.layers = new Matrix[1 + hiddenLayers];
        Dimension dim = new Dimension(hiddenNeurons, inputNeurons);
        Matrix inputLayer = new Matrix(dim, random.doubles(dim.elements()).toArray());
        dim = new Dimension(outputNeurons, hiddenNeurons);
        Matrix outputLayer = new Matrix(dim, random.doubles(dim.elements()).toArray());

        dim = new Dimension(hiddenNeurons, hiddenNeurons);
        int numberOfWeights = dim.elements();
        for (int i = 0; i <= hiddenLayers - 2; i++) {
            Matrix hl = new Matrix(dim, random.doubles(numberOfWeights).toArray());
            this.layers[i + 1] = hl;
        }
        this.layers[0] = inputLayer;
        this.layers[this.layers.length - 1] = outputLayer;
    }

    //--------------------------------------
    // Public Methods
    //--------------------------------------

    public Matrix classify(final Matrix input) {
        Matrix output = input;
        for (Matrix layer : this.layers) {
            output = layer.multiply(output);
            output = this.activation.calculate(output);
        }
        return output;
    }

    public void fit(final Matrix[] inputs, final Matrix[] labels) {
        fit(inputs, labels, DEFAULT_EPSILON);
    }

    public void fit(final Matrix[] inputs, final Matrix[] labels, double learningRate) {
        if (inputs.length != labels.length)
            throw new RuntimeException("Number of inputs is not equal to the number of labels");

        Matrix[] layerNets = new Matrix[this.layers.length];
        Matrix[] layerOuts = new Matrix[this.layers.length + 1];
        for (int i = 0; i < inputs.length; i++) {
            feedForward(inputs[i], layerNets, layerOuts);
            propagateBack(labels[i], learningRate, layerNets, layerOuts);
        }
    }

    //--------------------------------------
    // Internal Methods
    //--------------------------------------

    //TODO private - just for testing public
    public Matrix feedForward(Matrix input, Matrix[] layerNets, Matrix[] layerOuts) {
        Matrix output = input;
        layerOuts[0] = output; // Add input to outputs
        for (int i = 0; i < this.layers.length; ) {
            layerNets[i] = output = this.layers[i].multiply(output);
            layerOuts[++i] = output = this.activation.calculate(output);
        }
        return layerOuts[layerOuts.length - 1];
    }

    private void propagateBack(Matrix expected, double learningRate, Matrix[] layerNets, Matrix[] layerOuts) {
        // Calculate delta weights for each layer
        Matrix[] deltaWeights = new Matrix[this.layers.length];

        Matrix actual = layerOuts[layerOuts.length - 1];
        for (int i = 0; i < deltaWeights.length; i++) {
            deltaWeights[i] = calculateOutputError(expected, actual, layerNets[i], layerOuts[i], learningRate);
        }

        // Adjust weights
        for (int i = 0; i < deltaWeights.length; i++) {
            Matrix deltaWeight = deltaWeights[i];
            System.out.println("Delta: " + deltaWeight);
            this.layers[i] = this.layers[i].add(deltaWeight);
        }
    }

    //TODO private - just for testing public
    public Matrix[] calculateDeltaWeights(Matrix expected, Matrix actual, double learningRate) {

        // Matrix[] layerNets, Matrix[] layerOuts
        // Vector expected --> label[i] of fit(Vector[] inputs, Vector[] labels)
        // Vector actual --> return of feedForward(..)
        // Vector layerInput = layerOuts[i - 1]; // outputs must contain the forwarded input
        // Vector layerOutput = layerOuts[i];

        Matrix net = new Matrix(2, 1, 0.639, 0.2); // layerNets[i]
        Matrix layerInput = new Matrix(1, 2, 0.761f, 0.45f);

        Matrix[] deltaLayerWeights = new Matrix[this.layers.length];
        deltaLayerWeights[0] = calculateOutputError(expected, actual, net, layerInput, learningRate);

        return deltaLayerWeights;
    }

    private Matrix calculateOutputError(Matrix expected, Matrix actual, Matrix net, Matrix layerInput, double epsilon) {
        Matrix absError = expected.subtract(actual);
        System.out.println("Abs. error: " + absError);
        Matrix derivation = this.activation.derivative(net, actual);
        System.out.println("Derivation: " + derivation);
        Matrix phi = derivation.multiplyElementWise(absError);
        System.out.println("Phi: " + phi);
        return phi.multiply(epsilon).multiply(layerInput.transpose());
    }

    private Matrix calculateHiddenPhi() {
        //TODO
        return null;
    }

    //--------------------------------------
    // Generated
    //--------------------------------------


    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

    public Matrix[] getLayers() {
        return layers;
    }

    public IFunction getActivation() {
        return this.activation;
    }

}
