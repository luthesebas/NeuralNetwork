package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

/**
 *
 */
public class LogisticDerivative implements IFunction {

    private  Logistic logistic;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public LogisticDerivative() {
        this.logistic = new Logistic();
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    @Override
    public float calculate(float value) {
        float a = logistic.calculate(value);
        return a * (1 - a);
    }

    @Override
    public Vector calculate(Vector values) {
        float[] result = values.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = calculate(result[i]);
        }
        return new Vector(result);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
