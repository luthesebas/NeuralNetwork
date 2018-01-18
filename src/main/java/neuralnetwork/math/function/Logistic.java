package neuralnetwork.math.function;

/**
 *
 */
public class Logistic implements IFunction {

    private final static float E = 2.7182818284590452354f;

    //--------------------------------------
    // Constructors
    //--------------------------------------

    public Logistic() {

    }

    //--------------------------------------
    // Methods
    //--------------------------------------

    @Override
    public float calculate(float x) {
        return (float) (1 / (1 + Math.pow(E, -x)));
    }

    @Override
    public float derivative(float x) {
        float f = calculate(x);
        return f * (1 - f);
    }

    @Override
    public float derivative(float x, float f) {
        return f * (1 - f);
    }

    @Override
    public Vector calculate(Vector x) {
        float[] result = x.toArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = calculate(result[i]);
        }
        return new Vector(result);
    }

    @Override
    public Vector derivative(Vector x) {
        Vector f = calculate(x);
        Vector one = new Vector(f.getNumberOfElements(), 1);
        return one.subtract(f).multiply(f);
    }

    @Override
    public Vector derivative(Vector x, Vector f) {
        Vector one = new Vector(f.getNumberOfElements(), 1);
        return one.subtract(f).multiply(f);
    }

    //--------------------------------------
    // Getters and Setters
    //--------------------------------------


}
