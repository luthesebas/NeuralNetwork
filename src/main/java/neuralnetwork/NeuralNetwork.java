/**
 * 
 */
package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Random;
import neuralnetwork.math.Vector;

/**
 * 
 */
public class NeuralNetwork {

	private final float EPSILON = 0.01f;

	private Matrix[] layers;
	private Vector[] outputs;
	private Vector[] weightedInputs;
	
	//--------------------------------------
	// Constructors
	//--------------------------------------

	public NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers) {
		this(inputNeurons, outputNeurons, hiddenLayers, new Random());
	}

	public NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers, long seed) {
		this(inputNeurons, outputNeurons, hiddenLayers, new Random(seed));
	}

	private NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers, Random random) {
		int hiddenRange = outputNeurons * outputNeurons;

		this.layers = new Matrix[2 + hiddenLayers];
		this.outputs = new Vector[this.layers.length];
		this.weightedInputs = new Vector[this.layers.length];

		Matrix inputLayer = new Matrix(outputNeurons, random.range(outputNeurons * inputNeurons));
		Matrix outputLayer = new Matrix(outputNeurons, random.range(hiddenRange));

		for (int i = 0; i < hiddenLayers; i++) {
			this.layers[i + 1] = new Matrix(outputNeurons, random.range(hiddenRange));
		}
		this.layers[0] = inputLayer;
		this.layers[this.layers.length - 1] = outputLayer;
	}

	//--------------------------------------
	// Methods
	//--------------------------------------

	public Vector feedForward(Vector input) {
		Vector weightedInput = input;
		for (int i = 0; i < this.layers.length; i++) {
			weightedInput = this.layers[i].multiply(weightedInput);
			this.weightedInputs[i] = weightedInput;
			this.outputs[i] = weightedInput; //TODO Activation
		}
		return getResult();
	}

	public Vector calculateOutputError(Vector expected, Vector actual, Vector net) {
		return expected.subtract(actual).multiply(net).multiply(EPSILON);
	}

	private Vector calculateHiddenError() {
		return null;
	}

	public Vector classify(Vector input) {
		Vector output = input;
		for (int i = 0; i < this.layers.length; i++) {
			output = this.layers[i].multiply(output);
			output = output; //TODO Activation
		}
		return output;
	}

	//--------------------------------------
	// Generated
	//--------------------------------------



	//--------------------------------------
	// Getters and Setters
	//--------------------------------------

	public Vector getResult() {
		return this.outputs[this.outputs.length - 1];
	}

}
