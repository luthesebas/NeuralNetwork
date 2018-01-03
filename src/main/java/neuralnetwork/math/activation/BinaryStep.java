package neuralnetwork.math.activation;

import neuralnetwork.math.Vector;

/**
 *
 */
public class BinaryStep implements IActivationFunction {


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
    public float activate(float value) {
        if (value < 0) {
            return -1;
        } else {
            return 1;
        }
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
