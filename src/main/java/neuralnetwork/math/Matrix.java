package neuralnetwork.math;

import neuralnetwork.exception.IndexOutOfDimension;
import neuralnetwork.exception.InvalidDimension;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class Matrix {

    public final static Matrix I_1D = new Matrix(new Dimension(1,1), 1);
    public final static Matrix I_2D = new Matrix(new Dimension(2,2), 1,0,0,1);
    public final static Matrix I_3D = new Matrix(new Dimension(3,3), 1,0,0,0,1,0,0,0,1);

    protected final double[][] elements;
    protected Dimension dim;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Matrix(int m, int n) {
        this.dim = new Dimension(m,n);
        this.elements = dim.constructArray();
    }

    public Matrix(int m, int n, double value) {
        this.dim = new Dimension(m,n);
        this.elements = dim.constructArray(value);
    }

    public Matrix(int m, int n, double... rowedElements) {
        this(new Dimension(m,n), rowedElements);
    }

    public Matrix(Dimension dim) {
        this.dim = Objects.requireNonNull(dim).duplicate();
        this.elements = dim.constructArray();
    }

    public Matrix(Dimension dim, double value) {
        this.dim = Objects.requireNonNull(dim).duplicate();
        this.elements = dim.constructArray(value);
    }

    public Matrix(Dimension dim, double... rowedElements) {
        Objects.requireNonNull(dim);
        Objects.requireNonNull(rowedElements);
        if (isValidDimension(dim, rowedElements.length)) {
            this.dim = dim.duplicate();
            this.elements = dim.constructArray();
            fillArray(this.elements, rowedElements, dim.getN());
        } else {
            throw new InvalidDimension(
                    "Dimension and number of elements do not fit. Actual dimension: " + dim
                    + ". Actual number of elements: " + rowedElements.length);
        }
    }

    public Matrix(double[][] elements) {
        this.elements = Objects.requireNonNull(elements);
        this.dim = new Dimension(elements.length, elements[0].length);
    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    public boolean isScalar() {
        return (this.dim.getM() == 1) && (this.dim.getN() == 1);
    }

    public boolean isVector() {
        return (this.dim.getM() == 1) || (this.dim.getN() == 1);
    }

    public Matrix add(Matrix that) {
        if (!this.dim.equals(that.dim)) {
            throw new InvalidDimension("Invalid dimensions. Expected: " + this.dim + ". Actual: " + that.dim);
        }
        double[][] result = this.elements.clone();
        for (int m = 0; m < result.length; m++) {
            double[] mRowResult = result[m];
            double[] mRowThat = that.elements[m];
            for (int n = 0; n < mRowResult.length; n++) {
                mRowResult[n] += mRowThat[n];
            }
        }
        return new Matrix(result);
    }

    public Matrix subtract(Matrix that) {
        if (!this.dim.equals(that.dim)) {
            throw new InvalidDimension("Invalid dimensions. Expected: " + this.dim + ". Actual: " + that.dim);
        }
        double[][] result = this.elements.clone();
        for (int m = 0; m < result.length; m++) {
            double[] mRowResult = result[m];
            double[] mRowThat = that.elements[m];
            for (int n = 0; n < mRowResult.length; n++) {
                mRowResult[n] -= mRowThat[n];
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix that) {
        if (that.isScalar()) {
            return multiply(that.elements[0][0]);
        }
        if (this.dim.getN() == that.dim.getM()) {
            return multiplyMatrix(that);
        }
        throw new InvalidDimension(
                "Invalid dimension. Expected scalar or Mx" + this.dim.getN()
                        + ". Actual: " + that.dim);
    }

    public Matrix multiply(double scalar) {
        double[][] result = this.elements.clone();
        for (int i = 0; i < this.dim.getM(); i++) {
            double[] iRowResult = result[i];
            for (int k = 0; k < iRowResult.length; k++) {
                iRowResult[k] *= scalar;
            }
        }
        return new Matrix(result);
    }

    private Matrix multiplyMatrix(Matrix that) {
        double[][] result = new double[this.dim.getM()][that.dim.getN()];
        for (int i = 0; i < result.length; i++) {
            double[] iRowThis = this.elements[i];
            double[] iRowResult = result[i];
            for (int k = 0; k < iRowThis.length; k++) {
                double[] kRowThat = that.elements[k];
                double ikThis = iRowThis[k];
                for (int j = 0; j < kRowThat.length; j++) {
                    iRowResult[j] += ikThis * kRowThat[j];
                }
            }
        }
        return new Matrix(result);
    }

    public Matrix negate() {
        double[][] result = this.elements.clone();
        for (double[] iRowResult : result) {
            for (int j = 0; j < iRowResult.length; j++) {
                iRowResult[j] = -iRowResult[j];
            }
        }
        return new Matrix(result);
    }

    public Matrix transpose() {
        double[][] elements = calculateColumns();
        return new Matrix(elements);
    }

    private double[][] calculateColumns() {
        int M = this.dim.getM();
        int N = this.dim.getN();
        double[][] result = new double[N][M];
        for(int i = 0; i < N; i++) {
            double[] iColumnResult = result[i];
            for(int j = 0; j < M; j++) {
                iColumnResult[j] = this.elements[j][i];
            }
        }
        return result;
    }

    private double[] calculateColumn(int j, int elements) {
        double[] column = new double[elements];
        for (int i = 0; i < column.length; i++) {
            column[i] = this.elements[i][j];
        }
        return column;
    }

    public Matrix duplicate() {
        return new Matrix(this.elements.clone());
    }

    private boolean isValidDimension(Dimension dimension, int elements) {
        return (dimension.area() == elements);
    }

    private void fillArray(double[][] array, double[] rowedElements, int columns) {
        int indexFrom = 0;
        for (int i = 0; i < array.length; i++) {
            indexFrom = i * columns;
            array[i] = Arrays.copyOfRange(rowedElements, indexFrom, indexFrom + columns);
        }
    }

    //--------------------------------------
    // Generated
    //--------------------------------------

    @Override
    public String toString() {
        return "Matrix{" + "dim=" + dim + ", elements=" + Arrays.deepToString(elements) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(elements, matrix.elements) &&
                Objects.equals(dim, matrix.dim);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dim);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------

    public Dimension getDimension() {
        return this.dim;
    }

    public double getElement(int i, int j) {
        if (i < 0 || i >= this.elements.length)
            throw new IndexOutOfDimension("Index out of dimension dim: " + this.dim + ". Actual index: " + i);
        if (j < 0 || j >= this.elements[0].length)
            throw new IndexOutOfDimension("Index out of dimension dim: " + this.dim + ". Actual index: " + j);
        return this.elements[i][j];
    }

    public double[] getColumn(int j) {
        if (j < 0 || j >= this.elements[0].length)
            throw new IndexOutOfDimension("Index out of dimension dim: " + this.dim + ". Actual index: " + j);
        return calculateColumn(j, this.dim.getM());
    }

    public double[] getRow(int i) {
        if (i < 0 || i >= this.elements.length)
            throw new IndexOutOfDimension("Index out of dimension dim: " + this.dim + ". Actual index: " + i);
        return this.elements[i].clone();
    }

    public double[][] getElements() {
        return this.elements.clone();
    }

    public int getNumberOfElements() { return this.dim.area(); }


}
