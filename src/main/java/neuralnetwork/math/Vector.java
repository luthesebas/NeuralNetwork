package neuralnetwork.math;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Vector {

    private final float[] elements;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Vector(float... elements) {
        this.elements = Objects.requireNonNull(elements);
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public Vector negate() {
        return this.mult(-1);
    }

    public Vector add(float scalar) {
        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] += scalar;
        }

        return new Vector(result);
    }

    public Vector add(Vector other) {
        checkVectorDimension(other);

        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] += other.elements[i];
        }

        return new Vector(result);
    }

    public Vector substract(float scalar) {
        return this.add(-scalar);
    }

    public Vector substract(Vector other) {
        return this.add(other.negate());
    }

    public Vector mult(float scalar) {
        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= scalar;
        }

        return new Vector(result);
    }

    public Vector mult(Vector other) {
        checkVectorDimension(other);

        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= other.elements[i];
        }

        return new Vector(result);
    }

    public Vector divide(float scalar) {
        return this.mult(1f / scalar);
    }

    public Vector divide(Vector other) {
        return this.mult(other.inverse());
    }

    public Vector inverse() {
        float[] result = toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = 1f / result[i];
        }

        return new Vector(result);
    }

    public Vector scale(float scalar) {
        return this.mult(scalar);
    }

    public float sumUp() {
        float result = 0;
        for (int i = 0; i < this.elements.length; i++) {
            result += this.elements[i];
        }

        return result;
    }

    private void checkVectorDimension(Vector other) {
        if (this.getDimension() != other.getDimension()) {
            throw new InvalidParameterException("Vectors must have the same size");
        }
    }

    public boolean equalsDimension(Vector other) {
        if (this.getDimension() != other.getDimension())
            return false;
        return true;
    }

    public float[] toArray() {
        return this.elements.clone();
    }

    // dim([1,2,3,4,5]_T) = 5x1
    public Dimension getDimension() {
        return new Dimension(this.elements.length, 1);
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public String toString() {
        return "Vector{" + getDimension() +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
