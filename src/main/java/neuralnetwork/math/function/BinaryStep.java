package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

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
    public float calculate(float x) {
        if (x < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public float derivative(float x) {
        return 0;
    }

    @Override
    public float derivative(float x, float f) {
        return 0;
    }

    @Override
    public Vector calculate(Vector x) {
        float[] result = x.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = calculate(result[i]);
        }
        return new Vector(result);
    }

    @Override
    public Vector derivative(Vector x) {
        return new Vector(x.getDimension());
    }

    @Override
    public Vector derivative(Vector x, Vector f) {
        return new Vector(x.getDimension());
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
