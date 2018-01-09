package neuralnetwork.math;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Matrix {

    private final Vector[] rows;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Matrix(final Vector[] rows) {
        this.rows = Objects.requireNonNull(rows).clone();
        for (Vector vector : this.rows) {
            vector.transpose();
        }
    }

    public Matrix(final int rows, final float... elements) {
        Objects.requireNonNull(elements);
        if (elements.length % rows != 0) { // check for valid dimensions
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }

        int columns = elements.length / rows;
        this.rows = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            int indexFrom = i * columns;
            this.rows[i] = new Vector(
                    Arrays.copyOfRange(elements, indexFrom, indexFrom + columns)
            ).transpose();
        }//for
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public Vector multiply(Vector other) {
        float[] result = new float[this.rows.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.rows[i].dot(other);
        }

        return new Vector(result);
    }

    public Vector getColumn(int index) {
        float[] result = new float[this.rows.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.rows[i].getElement(index);
        }
        return new Vector(result);
    }

    public Dimension getDimension() {
        return new Dimension(
                this.rows.length,
                this.rows[0].getDimension().getN()
        );
    }

    public float[][] toArray() {
        Dimension dim = getDimension();
        float[][] result = new float[dim.getM()][dim.getN()];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.rows[i].toArray();
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "Matrix{" + getDimension() + "columns=";
        Vector[] temps = this.rows.clone();
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
        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

}
