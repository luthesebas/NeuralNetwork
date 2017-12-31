package neuralnetwork.math;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return m == dimension.m &&
                n == dimension.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m, n);
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
