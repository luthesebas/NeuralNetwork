package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

public interface IFunction {

    public float calculate(float value);
    public Vector calculate(Vector values);

}
