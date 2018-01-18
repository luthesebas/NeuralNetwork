/**
 * 
 */
package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Random;
import neuralnetwork.math.function.IFunction;
import neuralnetwork.math.function.Logistic;

import java.util.Objects;

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
		Matrix inputLayer = new Matrix(dim, random.range(dim.elements()));
		dim = new Dimension(outputNeurons, hiddenNeurons);
		Matrix outputLayer = new Matrix(dim, random.range(dim.elements()));

		dim = new Dimension(hiddenNeurons, hiddenNeurons);
		int numberOfWeights = dim.elements();
		for (int i = 0; i <= hiddenLayers - 2; i++) {
			Matrix hl = new Matrix(dim, random.range(numberOfWeights));
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
		//TODO
		if (inputs.length != labels.length)
			throw new RuntimeException("Number of inputs must be equal to the number of labels");

		Matrix[] layerNets = new Matrix[this.layers.length];
		Matrix[] layerOuts = new Matrix[this.layers.length + 1];

		for (int i = 0; i < inputs.length; i++) {
			Matrix result = feedForward(inputs[i], layerNets, layerOuts);
			propagateBack(labels[i], result, learningRate);
		}
	}

	//--------------------------------------
	// Internal Methods
	//--------------------------------------

	//TODO private - just for testing public
	public Matrix feedForward(Matrix input, Matrix[] layerNets, Matrix[] layerOuts) {
		Matrix output = input;
		layerOuts[0] = output; // Add input to outputs
		for (int i = 0; i < this.layers.length;) {
			layerNets[i] = output = this.layers[i].multiply(output);
			layerOuts[++i] = output = this.activation.calculate(output);
		}
		return layerOuts[layerOuts.length - 1];
	}

	private void propagateBack(Matrix expected, Matrix actual, double learningRate) {
		//TODO for all layers add delta weights
		Matrix[] deltaWeights = calculateLayerWeightShift(expected, actual, learningRate);
	}

	//TODO private - just for testing public
	public Matrix[] calculateLayerWeightShift(Matrix expected, Matrix actual, double learningRate) {

		Matrix[] weightedInputs = new Matrix[this.layers.length];
		Matrix[] outputs = new Matrix[this.layers.length + 1];

		//TODO
		// Vector expected --> label[i] of fit(Vector[] inputs, Vector[] labels)
		// Vector actual --> return of feedForward(inputs[i])
		// Vector layerInput = outputs[i - 1]; // outputs must contain the forwarded input
		// Vector layerOutput = outputs[i];

		Matrix net = new Matrix(2,1,0.639, 0.2); // weightedInputs[i]
		Matrix layerInput = new Matrix(1,2,0.761f,0.45f);

		Matrix[] deltaLayerWeights = new Matrix[this.layers.length];
		deltaLayerWeights[0] = calculateOutputError(expected, actual, net, layerInput, learningRate);



		return deltaLayerWeights;
	}

	private Matrix calculateOutputError(Matrix expected, Matrix actual, Matrix net, Matrix layerInput, double epsilon) {
		Matrix absError = expected.subtract(actual);
		Matrix derivation = this.activation.derivative(net, actual);
		Matrix phi = derivation.multiplyElementWise(absError);
		return phi.multiply(epsilon).multiply(layerInput);
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
