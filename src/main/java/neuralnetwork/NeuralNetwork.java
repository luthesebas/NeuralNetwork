/**
 * 
 */
package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Random;
import neuralnetwork.math.Vector;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 */
public class NeuralNetwork {

	private Matrix[] layers;
	private Vector[] results;
	
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
		this.results = new Vector[this.layers.length];

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
		Vector result = input;
		//TODO Activation
		for (int i = 0; i < this.layers.length; i++) {
			result = this.layers[i].multiply(result);
			this.results[i] = result;
		}
		return getResult();
	}

	//--------------------------------------
	// Generated
	//--------------------------------------



	//--------------------------------------
	// Getters and Setters
	//--------------------------------------

	public Vector getResult() {
		return this.results[this.results.length - 1];
	}

}
