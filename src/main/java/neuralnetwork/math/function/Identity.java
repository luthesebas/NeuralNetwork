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
     * Returns the input value.
     */
    @Override
    public float calculate(float value) {
        return value;
    }

    @Override
    public Vector calculate(Vector values) {
        return values;
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
