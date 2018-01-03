package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

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
    public float calculate(float value) {
        return (float) (1 / (1 + Math.pow(E, -value)));
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
