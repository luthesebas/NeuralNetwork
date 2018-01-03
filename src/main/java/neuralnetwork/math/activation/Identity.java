package neuralnetwork.math.activation;

import neuralnetwork.math.Vector;

/**
 *
 */
public class Identity implements IActivationFunction {


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
    public float activate(float value) {
        return value;
    }

    @Override
    public Vector activate(Vector values) {
        return values;
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
