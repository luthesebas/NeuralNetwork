package neuralnetwork.math.activation;

import neuralnetwork.math.Vector;

/**
 *
 */
public class Logistic implements IActivationFunction {

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
    public float activate(float value) {
        return (float) (1 / (1 + Math.pow(E, -value)));
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
