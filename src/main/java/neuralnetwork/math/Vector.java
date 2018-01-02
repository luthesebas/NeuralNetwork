package neuralnetwork.math;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public final class Vector {

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
        return this.multiply(-1);
    }

    public Vector add(float scalar) {
        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] += scalar;
        }
        return new Vector(result);
    }

    public Vector add(Vector other) {
        if (!sameDimension(other)) {
            throwInvalidDimension(other.getDimension());
        }
        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] += other.elements[i];
        }
        return new Vector(result);
    }

    public Vector subtract(float scalar) {
        return this.add(-scalar);
    }

    public Vector subtract(Vector other) {
        return this.add(other.negate());
    }

    public Vector multiply(float scalar) {
        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= scalar;
        }
        return new Vector(result);
    }

    /**
     * Not summed up dot product.
     * @param other the vector with which is multiplied
     * @return multiplied vector result
     */
    public Vector multiply(Vector other) {
        if (!sameDimension(other)) {
            throwInvalidDimension(other.getDimension());
        }

        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= other.elements[i];
        }
        return new Vector(result);
    }

    public float dot(Vector other) {
        return this.multiply(other).sumUp();
    }

    public Vector scale(float scalar) {
        return this.multiply(scalar);
    }

    public float sumUp() {
        float result = 0;
        for (float element : this.elements) {
            result += element;
        }
        return result;
    }

    public boolean sameDimension(Vector other) {
        if (this.getDimension().getM() != other.getDimension().getM()) {
            return false;
        }
        return true;
    }

    private void throwInvalidDimension(Dimension actual) {
        throw new InvalidDimension(
                "Vectors must have the same dimensions." +
                        " Expected: " + this.getDimension() +
                        " Actual: " + actual
        );
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

}
