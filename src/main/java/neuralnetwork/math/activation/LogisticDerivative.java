package neuralnetwork.math.activation;

import neuralnetwork.math.Vector;

/**
 *
 */
public class LogisticDerivative implements IActivationFunction {

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
    public float activate(float value) {
        float a = logistic.activate(value);
        return a * (1 - a);
    }

    @Override
    public Vector activate(Vector values) {
        float[] result = values.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = activate(result[i]);
        }
        return new Vector(result);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
