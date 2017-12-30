package neuralnetwork.math;

/**
 *
 */
public class Dimension {

    private final int m;
    private final int n;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Dimension(int m, int n) {
        this.m = m;
        this.n = n;
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public String toString() {
        return "Dimension{" +
                "m=" + m +
                ", n=" + n +
                '}';
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

}
