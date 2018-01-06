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

	public NeuralNetwork(Matrix... layers) {
		this.activation = new Logistic();
		this.layers = layers;
		this.outputs = new Vector[1 + this.layers.length];
		this.weightedInputs = new Vector[this.layers.length];
	}

	//--------------------------------------
	// Methods
	//--------------------------------------

	public Vector feedForward(Vector input) {
		this.outputs[0] = input; // Add input to outputs

		int i = 0;
		while (i < this.layers.length) {
			this.weightedInputs[i] = input = this.layers[i].multiply(input);
			this.outputs[++i] = input = this.activation.calculate(input);
		}
		return this.outputs[this.outputs.length - 1];
	}

	//TODO private - just for testing public
	public Matrix adjustWeights(Vector expected, Vector actual) {
		//TODO
		// Vector expected --> label[i] of fit(Vector[] inputs, Vector[] labels)
		// Vector actual --> return of feedForward(inputs[i])
		//Vector layerInput = this.outputs[i - 1]; // this.outputs must contain the forwarded input
		this.outputs[this.outputs.length - 1] = new Vector(0.655f, 0.55f);

		Matrix dWeightOutput = calculateOutputError(expected, actual, new Vector(true,0.761f,0.45f));
		return dWeightOutput;
	}

	private Matrix calculateOutputError(Vector expected, Vector actual, Vector layerInput) {
		return calculateOutputPhi(expected, actual).multiply(EPSILON).multiplyT(layerInput);
	}

	private Vector calculateOutputPhi(Vector expected, Vector actual) {
		//TODO
		Vector absError = expected.subtract(actual);
		Vector fStrichX = this.outputs[this.outputs.length - 1];
		Vector eins = new Vector(fStrichX.getDimension(), 1);
		Vector fStrich = eins.subtract(fStrichX).multiply(fStrichX);

		Vector phi = fStrich.multiply(absError);
		return phi;
	}

	private Vector calculateHiddenPhi() {
		//TODO
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
