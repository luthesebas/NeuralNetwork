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
		this(inputNeurons, outputNeurons, hiddenLayers, new Random());
	}

	public NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers, long seed) {
		this(inputNeurons, outputNeurons, hiddenLayers, new Random(seed));
	}

	private NeuralNetwork(int inputNeurons, int outputNeurons, int hiddenLayers, Random random) {
		this.activation = new Logistic();
		int hiddenRange = outputNeurons * outputNeurons;

		this.layers = new Matrix[1 + hiddenLayers];
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
		Vector output = input;
		Vector[] weightedInputs = new Vector[this.layers.length];
		Vector[] outputs = new Vector[this.layers.length + 1];

		outputs[0] = output; // Add input to outputs
		for (int i = 0; i < this.layers.length;) {
			weightedInputs[i] = output = this.layers[i].multiply(output);
			outputs[++i] = output = this.activation.calculate(output);
		}
		return outputs[outputs.length - 1];
	}

	public void fit(Vector[] inputs, Vector[] labels) {
		//TODO
	}

	//TODO private - just for testing public
	public Matrix deltaWeights(Vector expected, Vector actual) {

		Vector[] weightedInputs = new Vector[this.layers.length];
		Vector[] outputs = new Vector[this.layers.length + 1];

		//TODO
		// Vector expected --> label[i] of fit(Vector[] inputs, Vector[] labels)
		// Vector actual --> return of feedForward(inputs[i])
		// Vector layerInput = outputs[i - 1]; // outputs must contain the forwarded input
		// Vector layerOutput = outputs[i];

		Vector net = new Vector(0.639f, 0.2f); // weightedInputs[i]

		Matrix dWeightOutput = calculateOutputError(
				expected, actual, net,
				new Vector(true,0.761f,0.45f), DEFAULT_EPSILON);
		return dWeightOutput;
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

	public Vector classify(final Vector input) {
		Vector output = input;
		for (Matrix layer : this.layers) {
			output = layer.multiply(output);
			output = this.activation.calculate(output);
		}
		return output;
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
