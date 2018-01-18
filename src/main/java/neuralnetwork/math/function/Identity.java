package neuralnetwork.math.function;

import neuralnetwork.math.Matrix;

/**
 *
 */
public class Identity implements IFunction {


    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Identity() {

    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    /**
     * Returns the input x.
     */
    @Override
    public double calculate(double x) {
        return x;
    }

    @Override
    public double derivative(double x) {
        return 1;
    }

    @Override
    public double derivative(double x, double f) {
        return 1;
    }

    @Override
    public Matrix calculate(Matrix x) {
        return x;
    }

    @Override
    public Matrix derivative(Matrix x) {
        return new Matrix(x.getDimension(), 1);
    }

    @Override
    public Matrix derivative(Matrix x, Matrix f) { return new Matrix(x.getDimension(), 1); }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
