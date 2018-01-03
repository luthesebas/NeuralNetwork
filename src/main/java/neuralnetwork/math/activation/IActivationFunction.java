package neuralnetwork.math.activation;

import neuralnetwork.math.Vector;

public interface IActivationFunction {

    public float activate(float value);
    public Vector activate(Vector values);

}
