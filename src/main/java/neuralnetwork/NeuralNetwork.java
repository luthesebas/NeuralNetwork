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

	private final float EPSILON = 0.01f;
	private IFunction activation;

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
		this.activation = new Logistic();
		int hiddenRange = outputNeurons * outputNeurons;

		this.layers = new Matrix[1 + hiddenLayers];
		this.outputs = new Vector[1 + this.layers.length];
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
		this.outputs[0] = input; // Add input to outputs

		int i = 0;
		while (i < this.layers.length) {
			weightedInput = this.layers[i].multiply(weightedInput);
			this.weightedInputs[i] = weightedInput;
			this.outputs[++i] = this.activation.calculate(weightedInput);
		}
		return this.outputs[this.outputs.length - 1];
	}

	public Vector calculateOutputError(Vector expected, Vector actual, Vector net) {
		return expected.subtract(actual).multiply(net).multiply(EPSILON);
	}

	private Vector calculateHiddenError() {
		return null;
	}

	public Vector classify(Vector input) {
		Vector output = input;
		for (Matrix layer : this.layers) {
			output = layer.multiply(output);
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

	public void setActivation(IFunction activation) {
		this.activation = Objects.requireNonNull(activation);
	}

	public IFunction getActivation() {
		return this.activation;
	}
}
