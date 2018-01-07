package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

public interface IFunction {

    float calculate(float value);
    Vector calculate(Vector values);

}
