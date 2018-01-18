package neuralnetwork.math.function;

import neuralnetwork.math.Matrix;

/**
 *
 */
public class BinaryStep implements IFunction {


    //--------------------------------------
    // Constructors
    //--------------------------------------

    public BinaryStep() {

    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    /**
     * Returns -1 for x < 0 and
     * returns 1 for x >= 0.
     */
    @Override
    public double calculate(double x) {
        if (x < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public double derivative(double x) {
        return 0;
    }

    @Override
    public double derivative(double x, double f) {
        return 0;
    }

    @Override
    public Matrix calculate(Matrix x) {
        double[][] result = x.getElements();
        for (double[] iRow : result) {
            for (int j = 0; j < result.length; j++) {
                iRow[j] = calculate(iRow[j]);
            }
        }
        return new Matrix(result);
    }

    @Override
    public Matrix derivative(Matrix x) {
        return new Matrix(x.getDimension());
    }

    @Override
    public Matrix derivative(Matrix x, Matrix f) {
        return new Matrix(x.getDimension());
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
