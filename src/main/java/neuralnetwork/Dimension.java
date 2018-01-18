package neuralnetwork;

import neuralnetwork.exception.IndexOutOfDimension;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable Value Object
 */
public class Dimension {

    private final int m;
    private final int n;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Dimension(int m, int n) {
        if (m <= 0)
            throw new IndexOutOfDimension("Invalid value m: It must be a number greater than zero.");
        if (n <= 0)
            throw new IndexOutOfDimension("Invalid value n: It must be a number greater than zero.");
        this.m = m;
        this.n = n;
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public double[][] constructArray() {
        return new double[this.m][this.n];
    }

    public double[][] constructArray(double value) {
        double[][] result = constructArray();
        for (double[] row : result) {
            Arrays.fill(row, value);
        }
        return result;
    }

    public int elements() {
        return this.m * this.n;
    }

    public Dimension swap() {
        return new Dimension(this.n, this.m);
    }

    public Dimension duplicate() {
        return new Dimension(this.m, this.n);
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public String toString() {
        return "Dimension{" + "m=" + m + ", n=" + n + '}';
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
