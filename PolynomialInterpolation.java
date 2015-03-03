package JNumericalAnalysis;

import Jama.Matrix;

import java.util.Formatter;
import java.util.Locale;

public class PolynomialInterpolation {


    private double[] x;
    private double[][] y;
    private Matrix coefficients;

    /**
     * @param x vector of nodes
     * @param y function values in nodes
     */
    public PolynomialInterpolation(double[] x, double[][] y) {
        this.x = x;
        this.y = y;
        coefficients = this.coefficients(x, y);
    }

    @Override
    public String toString() {
        String s = "f(x) = ";
        for (int i = 0; i < x.length; i++){
            String formatted = new Formatter().format(Locale.US, "%.4f", coefficients.get(i, 0)).toString();
            if(coefficients.get(i, 0) < 0)
                s +=  formatted + "x^" + (x.length-(i+1)) + " ";
            else
                s += "+" + formatted + "x^" + (x.length-(i+1)) + " ";
        }
        return s;
    }
    /**
     * Function finds coefficients by polynominal iterpolation algorithm
     * @param x this.x
     * @param y this.y
     * @return coefficients of interpolation polynominal
     */
    public Matrix coefficients(double[] x, double[][] y) {
        Matrix A = new Matrix(x.length, y.length);
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y.length; j++)
                A.set(i, j, Math.pow(x[i], j));
        Matrix coefficients = A.solve(new Matrix(y));
        return coefficients;
    }

}
