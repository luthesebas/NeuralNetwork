/**
 * 
 */
package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Random;
import neuralnetwork.math.Vector;
import neuralnetwork.math.function.IFunction;
import neuralnetwork.math.function.Logistic;

import java.util.Objects;

/**
 * 
 */
public class NeuralNetwork {

	public static final float DEFAULT_EPSILON = 0.01f;

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
		Matrix inputLayer = new Matrix(hiddenNeurons, random.range(hiddenNeurons * inputNeurons));
		Matrix outputLayer = new Matrix(outputNeurons, random.range(outputNeurons * hiddenNeurons));

		for (int i = 0; i <= hiddenLayers - 2; i++) {
			Matrix hl = new Matrix(hiddenNeurons, random.range(hiddenNeurons * hiddenNeurons));
			this.layers[i + 1] = hl;
		}
		this.layers[0] = inputLayer;
		this.layers[this.layers.length - 1] = outputLayer;
	}

	//--------------------------------------
	// Public Methods
	//--------------------------------------

	public Vector classify(final Vector input) {
		Vector output = input;
		for (Matrix layer : this.layers) {
			output = layer.multiply(output);
			output = this.activation.calculate(output);
		}
		return output;
	}

	public void fit(final Vector[] inputs, final Vector[] labels) {
		fit(inputs, labels, DEFAULT_EPSILON);
	}

	public void fit(final Vector[] inputs, final Vector[] labels, float learningRate) {
		//TODO
		if (inputs.length != labels.length)
			throw new RuntimeException("Number of inputs must be equal to the number of labels");

		Vector[] layerNets = new Vector[this.layers.length];
		Vector[] layerOuts = new Vector[this.layers.length + 1];

		for (int i = 0; i < inputs.length; i++) {
			Vector result = feedForward(inputs[i], layerNets, layerOuts);
			propagateBack(labels[i], result, learningRate);
		}
	}

	//--------------------------------------
	// Internal Methods
	//--------------------------------------

	//TODO private - just for testing public
	public Vector feedForward(Vector input, Vector[] layerNets, Vector[] layerOuts) {
		Vector output = input;
		layerOuts[0] = output; // Add input to outputs
		for (int i = 0; i < this.layers.length;) {
			layerNets[i] = output = this.layers[i].multiply(output);
			layerOuts[++i] = output = this.activation.calculate(output);
		}
		return layerOuts[layerOuts.length - 1];
	}

	private void propagateBack(Vector expected, Vector actual, float learningRate) {
		//TODO for all layers add delta weights
		Matrix[] deltaWeights = calculateLayerWeightShift(expected, actual, learningRate);
	}

	//TODO private - just for testing public
	public Matrix[] calculateLayerWeightShift(Vector expected, Vector actual, float learningRate) {

		Vector[] weightedInputs = new Vector[this.layers.length];
		Vector[] outputs = new Vector[this.layers.length + 1];

		//TODO
		// Vector expected --> label[i] of fit(Vector[] inputs, Vector[] labels)
		// Vector actual --> return of feedForward(inputs[i])
		// Vector layerInput = outputs[i - 1]; // outputs must contain the forwarded input
		// Vector layerOutput = outputs[i];

		Vector net = new Vector(new float[]{0.639f, 0.2f}); // weightedInputs[i]
		Vector layerInput = new Vector(new float[]{0.761f,0.45f}, true);

		Matrix[] deltaLayerWeights = new Matrix[this.layers.length];
		deltaLayerWeights[0] = calculateOutputError(expected, actual, net, layerInput, learningRate);



		return deltaLayerWeights;
	}

	private Matrix calculateOutputError(Vector expected, Vector actual, Vector net, Vector layerInput, float epsilon) {
		if (this.activation instanceof Logistic) { net = null; }
		Vector absError = expected.subtract(actual);
		Vector derivation = this.activation.derivative(net, actual);
		Vector phi = derivation.multiply(absError);
		return phi.multiply(epsilon).multiplyT(layerInput);
	}

	private Vector calculateHiddenPhi() {
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
