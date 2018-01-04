package neuralnetwork.math;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Matrix {

    private final Vector[] vectors;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Matrix(final Vector[] rows) {
        this.vectors = Objects.requireNonNull(rows);
    }

    public Matrix(final int rows, final float... elements) {
        Objects.requireNonNull(elements);
        if (elements.length % rows != 0) { // check for valid dimensions
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }

        int columns = elements.length / rows;
        this.vectors = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            int indexFrom = i * columns;
            this.vectors[i] = new Vector(
                    Arrays.copyOfRange(elements, indexFrom, indexFrom + columns)
            );
        }//for
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public Vector multiply(Vector other) {
        float[] result = new float[this.vectors.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.vectors[i].dot(other);
        }

        return new Vector(result);
    }

    public Vector getColumn(int index) {
        float[] result = new float[this.vectors.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.vectors[i].getElement(index);
        }
        return new Vector(result);
    }

    public Dimension getDimension() {
        return new Dimension(
                this.vectors.length,
                this.vectors[0].getDimension().getM()
        );
    }

    public float[][] toArray() {
        Dimension dim = getDimension();
        float[][] result = new float[dim.getM()][dim.getN()];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.vectors[i].toArray();
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "Matrix{" + getDimension() + "columns=";
        Vector[] temps = this.vectors.clone();
        Arrays.stream(temps).forEach(Vector::transpose);
        return s + Arrays.toString(temps) + "}";
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.equals(vectors, matrix.vectors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectors);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

}
