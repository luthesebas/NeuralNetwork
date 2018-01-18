package neuralnetwork.math;

/**
 *
 */
public final class Random {

    private final java.util.Random random;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Random() {
        this.random = new java.util.Random();
    }

    public Random(long seed) {
        this.random = new java.util.Random(seed);
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public double[] range(int amount) {
        double[] result = new double[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = this.random.nextDouble();
        }
        return result;
    }

}
