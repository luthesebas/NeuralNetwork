package neuralnetwork.math.function;

import neuralnetwork.math.Vector;

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
    public float calculate(float x) {
        return x;
    }

    @Override
    public float derivative(float x) {
        return 1;
    }

    @Override
    public float derivative(float x, float f) {
        return 1;
    }

    @Override
    public Vector calculate(Vector x) {
        return x;
    }

    @Override
    public Vector derivative(Vector x) {
        return new Vector(x.getDimension(), 1);
    }

    @Override
    public Vector derivative(Vector x, Vector f) {
        return new Vector(x.getDimension(), 1);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
