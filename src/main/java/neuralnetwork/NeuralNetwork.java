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

	private Matrix[] layers;
	private Vector result;
	
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
		this.result = input;
		//TODO Activation
		for (Matrix layer : this.layers) {
			this.result = layer.multiply(this.result);
		}
		return this.result;
	}

	//--------------------------------------
	// Generated
	//--------------------------------------



	//--------------------------------------
	// Getters and Setters
	//--------------------------------------

	public Vector getResult() {
		return this.result;
	}

}
