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

    public float[] range(int amount) {
        float[] result = new float[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = this.random.nextFloat();
        }
        return result;
    }

}
