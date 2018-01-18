package neuralnetwork.math.function;

import neuralnetwork.math.Matrix;

public interface IFunction {

    double calculate(double x);
    double derivative(double x);
    double derivative(double x, double f);
    Matrix calculate(Matrix x);
    Matrix derivative(Matrix x);
    Matrix derivative(Matrix x, Matrix f);

}
