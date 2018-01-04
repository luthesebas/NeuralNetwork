package neuralnetwork.math;

import neuralnetwork.exception.InvalidDimension;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public final class Vector {

    public static final Vector VECTOR_1D_UNIT = new Vector(1);
    public static final Vector VECTOR_2D_UNIT_1 = new Vector(1,0);
    public static final Vector VECTOR_2D_UNIT_2 = new Vector(0,1);
    public static final Vector VECTOR_3D_UNIT_1 = new Vector(1,0,0);
    public static final Vector VECTOR_3D_UNIT_2 = new Vector(0,1,0);
    public static final Vector VECTOR_3D_UNIT_3 = new Vector(0,0,1);

    private final float[] elements;
    private boolean transposed;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Vector(float... elements) {
        this.elements = Objects.requireNonNull(elements);
    }

    public Vector(boolean transposed, float... elements) {
        this(elements);
        this.transposed = transposed;
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public boolean isTransposed() {
        return this.transposed;
    }

    public void transpose() {
        this.transposed = !this.transposed;
    }

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
        if (!validDimension(other)) {
            throwInvalidDimension(this.getDimension(), other.getDimension());
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
        if (!validDimension(other)) {
            throwInvalidDimension(this.getDimension(), other.getDimension());
        }

        float[] result = this.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= other.elements[i];
        }
        return new Vector(result);
    }

    /**
     *
     * @param other
     * @return
     */
    public Matrix multiplyT(Vector other) {
        if (this.isTransposed() || !other.isTransposed()) {
            throwInvalidDimension(this.getDimension().swap(), other.getDimension());
        }

        Vector[] result = new Vector[this.elements.length]; // empty rows
        for (int i = 0; i < result.length; i++) {
            result[i] = other.multiply(this.elements[i]); // rows
        }
        return new Matrix(result);
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

    public boolean validDimension(Vector other) {
        if (this.getDimension().getM() != other.getDimension().getM()) {
            return false;
        }
        return true;
    }

    private void throwInvalidDimension(Dimension expected, Dimension actual) {
        throw new InvalidDimension(
                "Vectors must have the same dimensions." +
                        " Expected: " +expected +
                        " Actual: " + actual
        );
    }

    public float getElement(int index) {
        return this.elements[index];
    }

    public float[] toArray() {
        return this.elements.clone();
    }

    // dim([1,2,3,4,5]_T) = 5x1
    public Dimension getDimension() {
        if (this.transposed) {
            return new Dimension(1, this.elements.length);
        }
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
