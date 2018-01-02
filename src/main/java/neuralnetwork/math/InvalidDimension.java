package neuralnetwork.math;

/**
 *
 */
public class InvalidDimension extends RuntimeException{

    //--------------------------------------
    // Custom exception
    //--------------------------------------

    public InvalidDimension() {
        super();
    }

    public InvalidDimension(String message) {
        super(message);
    }

}
