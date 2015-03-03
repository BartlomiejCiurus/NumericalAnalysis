package JNumericalAnalysis;


class GaussianEliminationWithPivoting {
    private double[][] matrix;
    private double[] results;
    private double[][] upperTriangularMatrix;
    private double[] backsubstitutionResults;

    /**
     *
     * @param matrix matrix of coefficients of the unknown
     * @param results results (vector) of our linear equations
     */
    GaussianEliminationWithPivoting(double[][] matrix, double[] results){
        this.matrix = matrix;
        this.results = results;
        try {
            this.upperTriangularMatrix = gaussianEliminationAlgorithm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.backsubstitutionResults = backsubstitution();
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getResults() {
        return results;
    }

    public double[][] getUpperTriangularMatrix() {
        return upperTriangularMatrix;
    }

    public double[] getBacksubstitutionResults() {
        return backsubstitutionResults;
    }

    /**
     *
     * @return upper tringular matrix
     * @throws Exception if matrix is singular
     */
    public double[][] gaussianEliminationAlgorithm() throws Exception {
        double[][] tempMatrix = matrixCopy();
        for (int p = 0; p < results.length; p++) {
            pivoting(tempMatrix,results,p,p);
            if (Math.abs(tempMatrix[p][p]) <= 1E-10) {
                throw new Exception("Singular matrix");
            }
            for (int i = p + 1; i < results.length; i++) {
                double temp = tempMatrix[i][p] / tempMatrix[p][p];
                results[i] -= temp * results[p];
                for (int j = p; j < results.length; j++) {
                    tempMatrix[i][j] -= temp * tempMatrix[p][j];
                }
            }
        }
        return tempMatrix;
    }

    /**
     * Algorithm will find pivot if it's different than 0, it's static so you can use it
     * without creating instance of this class
     * @param matrix
     * @param results
     * @param column
     * @param line
     * @return true if success, false if pivot is 0
     */
    public static boolean pivoting(double[][] matrix, double[] results, int column, int line){
        int maxAbsLine = -1;
        double maxAbs = 0;
        for (int i = line; i < matrix.length; i++) {
            if(Math.abs(matrix[i][column]) > maxAbs){
                maxAbsLine = i;
                maxAbs = matrix[i][column];
            }
        }
        if(maxAbsLine != -1){
            swapLines(matrix,results,maxAbsLine,line);
            return true;
        }
        return false;
    }

    /**
     * Works only for upper tringular matrix
     * @return calculated unknowns (vector)
     */
    public double[] backsubstitution(){
        double[] temp = new double[results.length];
        for (int i = results.length - 1; i > -1; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < results.length; j++) {
                sum += upperTriangularMatrix[i][j] * temp[j];
            }
            temp[i] = (results[i] - sum) / upperTriangularMatrix[i][i];
        }
        return temp;
    }

    public static String printVector(double[] vector){
        String s = "";
        for (int i = 0; i < vector.length ; i++) {
            s += vector[i] + "\n";
        }
        return s;
    }

    public static String printMatrix(double[][] matrix){
        String s = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * it's static so you can use it
     * without creating instance of this class
     * @param matrix
     * @param results
     * @param firstLine
     * @param secondLine
     */
    public static void swapLines(double[][] matrix, double[] results, int firstLine, int secondLine){
        double[] tempLine = matrix[firstLine].clone();
        matrix[firstLine] = matrix[secondLine].clone();
        matrix[secondLine] = tempLine;
        double tempValue = results[firstLine];
        results[firstLine] = results[secondLine];
        results[secondLine] = tempValue;
    }

    public double[][] matrixCopy(){
        double [][] temp = new double[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            temp[i] = matrix[i].clone();
        return temp;
    }
}
