package Mathematics;

import java.io.Serial;
import java.io.Serializable;

public class EquationsSystems implements Serializable {

    @Serial
    private static final long serialVersionUID = 2326978052669142776L;

    /**
     * The method is calculating the answers of an equation system by CramerMethod.
     * @param order is the order of the main matrix. The more value of "order", the more answers.
     * @param isIndependently means whether the algorithm below will fill the matrices or not (or a user will do it).
     * @return a matrix which number of strings matches the value of "order".
     * @throws IncompatibleSizeException is thrown when the size of editable objects of both of mainMatrin
     * freeCoefficientMatrix and are not the same.
     * @throws ArithmeticException is thrown when a determinant of main matrix equals 0, because division by zero
     * is impossible.
     */
    public static Matrix CramerMethod(int order, boolean isIndependently) throws IncompatibleSizeException, ArithmeticException {

        Matrix mainMatrix = new Matrix(order, order);
        if (isIndependently) {
            mainMatrix.fillIndependently();
        } else {
            mainMatrix.fillRandomly(10);
        }
        mainMatrix.show();

        Matrix freeCoefficientMatrix = new Matrix(order, 1);
        if (isIndependently) {
            freeCoefficientMatrix.fillIndependently();
        } else {
            freeCoefficientMatrix.fillRandomly(10);
        }
        freeCoefficientMatrix.show();

        Matrix resultMatrix = new Matrix(order, 1);

        double determinant = mainMatrix.toDeterminant().getDeterminantAsNumber();
        if (determinant == 0) {
            throw new ArithmeticException("Division by zero!");
        }

        for (int i = 0; i < order; i++) {
            Matrix edited = (Matrix) mainMatrix.paste("columns", i+1, freeCoefficientMatrix, 1);
            double currentDeterminant = edited.toDeterminant().getDeterminantAsNumber();
            resultMatrix.getAsArray()[i][0] = currentDeterminant / determinant;
        }

        return resultMatrix;

    }

}