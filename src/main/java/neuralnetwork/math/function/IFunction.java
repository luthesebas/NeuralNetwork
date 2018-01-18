package neuralnetwork.math.function;

public interface IFunction {

    float calculate(float x);
    float derivative(float x);
    float derivative(float x, float f);
    Vector calculate(Vector x);
    Vector derivative(Vector x);
    Vector derivative(Vector x, Vector f);

}
