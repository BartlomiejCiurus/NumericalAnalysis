package JNumericalAnalysis;


import java.util.ArrayList;
import java.util.List;


class LagrangeInterpolation implements FunctionNeeded{

    private List<Double> y = new ArrayList<Double>();
    private List<Double> x = new ArrayList<Double>();

    /**
     *
     * @param nodes random points in our exercise
     */
    LagrangeInterpolation(List<Double> nodes){
        this.x = nodes;
        for (int i = 0; i < x.size(); i++) {
            y.add(returnFunctionValue(x.get(i)));
        }
    }

    @Override
    public String toString(){
        String s = "";
        for (double i = -1; i < 1; i+=0.01) {
            s += "[" + i + "," + laGrange(x, y, i) + "]" + "\n";
        }

        return s;
    }

    /**
     * Lagrange algorithm, we can also use it without creating instance of our class because of "static"
     * but only with function defined in this class
     * @param nodes
     * @param valuesInNodes
     * @param point
     * @return value of our function in point
     */
    public static double laGrange(List<Double> nodes, List<Double> valuesInNodes, double point){
        double valueOfLagrangePolynominalInPoint = 0.;
        for(int i = 0; i < nodes.size(); i++){
            double t = 1.0;
            for(int j = 0; j < nodes.size(); j++){
                if(i != j)
                    t *= (point - nodes.get(j)) / (nodes.get(i) - nodes.get(j));
            }
            valueOfLagrangePolynominalInPoint += t*valuesInNodes.get(i);
        }
        return valueOfLagrangePolynominalInPoint;
    }

    /**
     * For polynomials Horner Algorithm is better, because it's "super effective" (^^) - it requiers minimum number of multiplications
     * @param point
     * @return value in point
     */
    @Override
    public double returnFunctionValue(double point){
        return 1. /( 1 + 5 * Math.pow(point,2) );
    }

}
