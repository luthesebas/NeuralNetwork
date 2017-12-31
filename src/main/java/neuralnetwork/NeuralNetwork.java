/**
 * 
 */
package neuralnetwork;

import neuralnetwork.math.Matrix;
import neuralnetwork.math.Vector;

import java.util.Random;

/**
 * 
 */
public class NeuralNetwork {

	private Matrix weightsInput;
	private Matrix[] weightsHidden;
	private Matrix weightsOutput;

	private Vector result;
	
	//--------------------------------------
	// Constructors
	//--------------------------------------

	public NeuralNetwork(int inputNeurons, int hiddenLayers, int outputNeurons) {
		this(inputNeurons, hiddenLayers, outputNeurons, new Random());
	}

	public NeuralNetwork(int inputNeurons, int hiddenLayers, int outputNeurons, long seed) {
		this(inputNeurons, hiddenLayers, outputNeurons, new Random(seed));
	}

	private NeuralNetwork(int inputNeurons, int hiddenLayers, int outputNeurons, final Random rand) {
		this.weightsInput = new Matrix(2, 1,2,3,4);
		this.weightsOutput = new Matrix(2, 1,0,0,1);

		this.weightsHidden = new Matrix[hiddenLayers];
		for (int i = 0; i < hiddenLayers; i++) {
			this.weightsHidden[i] = new Matrix(2, 1,0,0,1);
		}
	}

	//--------------------------------------
	// Methods
	//--------------------------------------

	public void feedForward(Vector input) {
		this.result = this.weightsInput.multiply(input);
		//TODO Activation
		for (Matrix hiddenLayer : this.weightsHidden) {
			this.result = hiddenLayer.multiply(this.result);
		}
		this.result = this.weightsOutput.multiply(this.result);
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
