package neuralnetwork.math;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Matrix {

    private final Vector[] columns;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Matrix(final Vector[] columns) {
        this.columns = Objects.requireNonNull(columns);
    }

    // m x n
    // rows = m
    // columns = n
    public Matrix(final int rows, final float... elements) {
        Objects.requireNonNull(elements);
        if (elements.length % rows != 0) { // check for valid dimensions
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }

        int columns = elements.length / rows;
        this.columns = new Vector[columns];

        for (int i = 0; i < columns; i++) {
            float[] tupel = new float[rows];
            for (int j = 0; j < rows; j++) {
                int index = i + j * columns;
                tupel[j] = elements[index];
            }
            this.columns[i] = new Vector(tupel);
        }
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public Dimension getDimension() {
        return new Dimension(this.columns[0].getDimension().getM(), this.columns.length);
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public String toString() {
        return "Matrix{" + getDimension() +
                "columns=" + Arrays.toString(columns) +
                '}';
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

}
