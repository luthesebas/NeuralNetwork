package neuralnetwork.math;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 *
 */
public class Vector {

    private final float[] elements;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    /**
     *
     * @param elements
     */
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
        checkVectorSize(other);

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
        checkVectorSize(other);

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

    private void checkVectorSize(Vector other) {
        if (this.numberOfElements() != other.numberOfElements()) {
            throw new InvalidParameterException("Vectors must have the same size");
        }
    }

    public float[] toArray() {
        return this.elements.clone();
    }

    public int numberOfElements() {
        return this.elements.length;
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
