package neuralnetwork.math;

import neuralnetwork.exception.InvalidDimension;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public final class Vector {

    public static final Vector VECTOR_1D_UNIT = new Vector(new float[]{1});
    public static final Vector VECTOR_2D_UNIT_1 = new Vector(new float[]{1,0});
    public static final Vector VECTOR_2D_UNIT_2 = new Vector(new float[]{0,1});
    public static final Vector VECTOR_3D_UNIT_1 = new Vector(new float[]{1,0,0});
    public static final Vector VECTOR_3D_UNIT_2 = new Vector(new float[]{0,1,0});
    public static final Vector VECTOR_3D_UNIT_3 = new Vector(new float[]{0,0,1});


    private final float[] elements; // column --> M x 1
    private boolean transposed;
    private Dimension dimension;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Vector(final int elements) {
        this.elements = new float[elements];
        updateDimension();
    }

    public Vector(final int elements, final float value) {
        this(elements, value, false);
    }

    public Vector(final int elements, final float value, final boolean transposed) {
        this.elements = new float[elements];
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i] = value;
        }
        this.transposed = transposed;
        updateDimension();
    }

    public Vector(final float[] elements) {
        this(elements, false);
    }

    public Vector(final float[] elements, final boolean transposed) {
        this.elements = Objects.requireNonNull(elements).clone();
        this.transposed = transposed;
        updateDimension();
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
        if (!(this.getDimension().getM() == other.getDimension().getM())) {
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
        float[] result = this.toArray().clone();
        for (int i = 0; i < result.length; i++) {
            result[i] *= scalar;
        }
        return new Vector(result);
    }

    /**
     * Not summed up dot product.
     * @param other the vector with which is multiplied
     * @return the vector result of the multiplication
     */
    public Vector multiply(Vector other) {
        if (!(this.elements.length == other.elements.length)) {
            throwInvalidDimension(this.getDimension(), other.getDimension());
        }

        float[] result = this.toArray().clone();
        for (int i = 0; i < result.length; i++) {
            result[i] *= other.elements[i];
        }
        return new Vector(result);
    }

    /**
     * Multiplies the vector with an transposed vector.
     * @param other the vector with which is multiplied
     * @return the matrix result of the multiplication
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

    public boolean isTransposed() {
        return this.transposed;
    }

    public Vector transpose() {
        this.transposed = !this.transposed;
        updateDimension();
        return this;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public boolean validDimension(Vector other) {
        return validDimension(other, false);
    }

    public boolean validDimension(Vector other, boolean transposed) {
        if (transposed) {
            return this.dimension.getM() == other.getDimension().getN();
        } else {
            return this.dimension.getM() == other.getDimension().getM();
        }
    }

    public float getElement(int index) {
        return this.elements[index];
    }

    public float[] toArray() {
        return this.elements;
    }

    public int getNumberOfElements() {
        return  this.elements.length;
    }

    //--------------------------------------
    // Internal Methods
    //--------------------------------------

    private void updateDimension() {
        if (this.transposed) {
            this.dimension = new Dimension(1, this.elements.length);
        } else {
            this.dimension = new Dimension(this.elements.length, 1);
        }
    }

    private void throwInvalidDimension(Dimension expected, Dimension actual) {
        throw new InvalidDimension(
                "Vectors must have the same dimensions." +
                        " Expected: " +expected +
                        " Actual: " + actual
        );
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
