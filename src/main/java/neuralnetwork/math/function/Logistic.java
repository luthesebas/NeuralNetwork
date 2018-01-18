package neuralnetwork.math.function;

import neuralnetwork.math.Matrix;

/**
 *
 */
public class Logistic implements IFunction {

    private final static float E = 2.7182818284590452354f;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Logistic() {

    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    @Override
    public double calculate(double x) {
        return 1 / (1 + Math.pow(E, -x));
    }

    @Override
    public double derivative(double x) {
        double f = calculate(x);
        return f * (1 - f);
    }

    @Override
    public double derivative(double x, double f) {
        return f * (1 - f);
    }

    @Override
    public Matrix calculate(Matrix x) {
        double[][] result = x.getElements();
        for (double[] iRow : result) {
            for (int j = 0; j < iRow.length; j++) {
                iRow[j] = calculate(iRow[j]);
            }
        }
        return new Matrix(result);
    }

    @Override
    public Matrix derivative(Matrix x) { // (1 - f) * f
        Matrix f = calculate(x);
        double[][] result = f.getElements();
        for (double[] iRow : result) {
            for (int j = 0; j < iRow.length; j++) {
                iRow[j] = (1 - iRow[j]) * iRow[j];
            }
        }
        return new Matrix(result);
    }

    @Override
    public Matrix derivative(Matrix x, Matrix f) {
        double[][] result = f.getElements();
        for (double[] iRow : result) {
            for (int j = 0; j < iRow.length; j++) {
                iRow[j] = (1 - iRow[j]) * iRow[j];
            }
        }
        return new Matrix(result);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
