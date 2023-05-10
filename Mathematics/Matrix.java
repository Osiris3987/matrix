package Mathematics;

import java.io.Serial;
import java.io.Serializable;
import java.lang.Math;
import java.util.*;

public class Matrix implements Serializable, Table {

    @Serial
    private static final long serialVersionUID = 413824565218358308L;

    /**
     * The standard input stream.
     */
    private final Scanner STANDARD_INPUT = new Scanner(System.in);

    /**
     * How many strings a matrix has.
     */
    private final int STRINGS;

    /**
     * How many columns a matrix has.
     */
    private final int COLUMNS;

    /**
     * Two-dimensional array -- the matrix itself.
     */
    private final Double[][] MATRIX;

    /**
     * The standard constructor used while making a matrix.
     */
    public Matrix(int strings, int columns) {
        STRINGS = strings;
        COLUMNS = columns;
        MATRIX = new Double[STRINGS][COLUMNS];
    }

    /**
     * Returns a null matrix. In this kind of matrix each element equals 0.
     *
     * @param string is required to set the matrix size.
     * @param column as the previous parameter, is required to set the matrix size.
     * @return The method returns the matrix as object of Matrix class.
     */
    public static Matrix getNullMatrix(int string, int column) {
        return new Matrix(string, column);
    }

    /**
     * Returns an identity matrix. In this kind of matrix a main diagonal contains only ones.
     * Let our identity matrix be square temporarily as no algorithms for non-square identity matrix created.
     *
     * @param size is required to set the matrix size.
     * @return The method returns the matrix as object of Matrix class.
     */
    public static Matrix getIdentityMatrix(int size) {
        Matrix identityMatrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            identityMatrix.getAsArray()[i][i] = 1d;
        }
        return identityMatrix;
    }

    /**
     * The method created for realization of other methods of this class.
     * @return Two-dimensional array itself.
     */
    @Override
    public Double[][] getAsArray() {
        return MATRIX;
    }

    /**
     * Returns the matrix element specified. Note that the first array index in Java begins with '0',
     * though a matrix element begins with '1'. To make method work correct, '-1' has been added to return
     * statement.
     *
     * @param string is required to find the element definitely.
     * @param column is required to find the element definitely.
     * @return Matrix element specified.
     */

    @Override
    public Double getElement(int string, int column) {
        return MATRIX[string-1][column-1];
    }

    /**
     * Returns a string length of a matrix.
     *
     * @return A string length of a matrix as integer.
     */
    @Override
    public int getStrings() {
        return STRINGS;
    }

    /**
     * Returns how many strings a matrix has.
     *
     * @return A column amount of a matrix as integer.
     */
    @Override
    public int getColumns() {
        return COLUMNS;
    }

    /**
     * Returns how many columns a matrix has.
     *
     * @return A size of a matrix as integer.
     */
    @Override
    public int getSize() {
        return (STRINGS * COLUMNS);
    }

    /**
     * This method was created to fill all the matrix elements by random numbers. In fact, there are not
     * absolutely random. Static method random() taken from Math class creates only "conditional randomness"
     * described with specified algorithms and mathematical transformations. It a creates regularity. But
     * the regularity is an attribute of order, not randomness.
     *
     * @param increment or "coefficient of increment" is needed to increase each matrix element.
     * For instance, if increment = 5, an element's takes a value from 0 to 5, because Math.random()
     * returns values from 0 to 1. In general, it means than each element of this matrix is
     * multiplied by the specified coefficient.
     */
    @Override
    public void fillRandomly(int increment) {
        int index = 1;
        for (int i = 0; i < STRINGS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                index *= Math.pow(-1, (int) (Math.random() * increment));
                MATRIX[i][j] = (Math.random() * increment * index);
            }
        }
    }

    /**
     * Shows a matrix as a table in the Console.
     */
    @Override
    public void show() {
        for (int i = 0; i < STRINGS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("(" + MATRIX[i][j] + ") ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    /**
     * The method which compares two matrices. In fact, the method has been overridden in Object class to
     * compare matrices correctly. Note that we compare SIZES of matrices, not values of their elements.
     *
     * @param someMatrix is the matrix we compare with.
     * @return true if matrices have the same size, false - otherwise.
     */
    public boolean equals(Matrix someMatrix) {
        return (this.STRINGS == someMatrix.STRINGS &&
                this.COLUMNS == someMatrix.COLUMNS);
    }

    /**
     * The method adds two matrices. Addition is impossible if matrices don't have the same size. Any
     * MxN matrix can be added only to MxN matrix and cannot be added to matrix with other size.
     *
     * @param someMatrix is the matrix we use for addition.
     * @return new matrix which is the result of the addition of two previous matrices.
     */
    public Matrix addition(Matrix someMatrix) {
        Matrix newMatrix = new Matrix(STRINGS, COLUMNS);
        if (this.equals(someMatrix)) {
            for (int i = 0; i < STRINGS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    newMatrix.getAsArray()[i][j] = MATRIX[i][j] + someMatrix.getAsArray()[i][j];
                }
            }
            return newMatrix;
        } else {
            System.out.println("Invalid operation. Incompatible matrices sizes.");
            return null;
        }
    }

    /**
     *  The method shows whether two matrices can be multiplied or not.
     *
     * @param someMatrix is the matrix we use for a potential multiplication.
     * @return true if multiplication is possible, false - otherwise.
     * @exception NullPointerException is thrown when parameter(s) or object itself is(are) null.
     */
    public boolean ableToMultiply(Matrix someMatrix) throws NullPointerException {
        return (this.COLUMNS == someMatrix.STRINGS);
    }

    /**
     * The method creates a new matrix where string and column numbers from original matrix are swapped.
     *
     * @return A new matrix as an object of Matrix which implements Table.
     */
    @Override
    public Table transposition() {
        Table matrix = new Matrix(this.COLUMNS, this.STRINGS);
        for (int i = 0; i < this.COLUMNS; i++) {
            for (int j = 0; j < this.STRINGS; j++) {
                 matrix.getAsArray()[i][j] = this.getAsArray()[j][i];
            }
        }
        return matrix;
    }

    /**
     * The method is used for matrix multiplication. It's obvious that this process is not always
     * possible. In this case, column numbers of the first matrix must equal string numbers of the
     * second matrix. Surely, the algorithm is written so that multiplication is simply impossible without
     * necessary conditions. Moreover, currently the algorithm is ineffective if huge value of variables
     * are used.
     *
     * @param someMatrix is required in multiplication with other matrix.
     * @return new matrix which is the result of multiplication of two matrices.
     */
    public Matrix multiplication(Matrix someMatrix) throws IncompatibleSizeException {
        if (this.ableToMultiply(someMatrix)) {
            Matrix MMatrix = new Matrix(this.STRINGS, someMatrix.transposition().getStrings());
            for (int i = 0; i < this.STRINGS; i++) {
                for (int j = 0; j < someMatrix.transposition().getStrings(); j++) {
                    for (int n = 0; n < this.COLUMNS; n++) {
                        MMatrix.getAsArray()[i][j] += this.getAsArray()[i][n] * someMatrix.getAsArray()[n][j];
                    }
                }
            }
            return MMatrix;
        } else {
            throw new IncompatibleSizeException();
        }
    }

    /**
     * The method allows to user to enter a value of each matrix element, using keyboard.
     * The user enters the values sequentially one by one until the matrix is full.
     * Also, the user can see which element (being more precise, string and column of the element) he
     * fills.
     */
    @Override
    public void fillIndependently() {
        System.out.println("Enter the matrix number: ");
        for (int i = 0; i < this.STRINGS; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
                System.out.print("["+(i+1)+"]["+(j+1)+"] = ");
                this.getAsArray()[i][j] = STANDARD_INPUT.nextDouble();
            }
        }
        System.out.println("The matrix has been successfully filled.");
    }

    /**
     * The method transforms a matrix into a determinant. It's possible only for square matrices.
     * The method is used if it needs to find a value of matrix's determinant as an integer using
     * another method of Determinant class.
     *
     * @return Determinant as object of Determinant class.
     */
    public Determinant toDeterminant() {
        if (this.getStrings() == this.getColumns()) {
            Determinant determinant = new Determinant(this.getStrings());
            for (int i = 0; i < this.getStrings(); i++) {
                for (int j = 0; j < this.getStrings(); j++) {
                    determinant.getAsArray()[i][j] = this.getAsArray()[i][j];
                }
            }
            return determinant;
        } else {
            System.out.println("Invalid operation. Matrix is not square!");
            return null;
        }
    }

    /**
     * NEEDS TO BE REDO.
     * The method finds a string which consist of only the element specified.
     *
     * @param target is the only element a string should contain.
     * @return true if a table contains at least one string with only specified element,
     * false - otherwise.
     */
    @Override
    public boolean containsStringWithOnly(Double target) {
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < this.STRINGS; i++) {
            Set<Double> betweenResults = new HashSet<>();
            for (int j = 0; j < this.COLUMNS; j++) {
                betweenResults.add(this.MATRIX[i][j]);
            }
            results.add(betweenResults.size() == 1 && betweenResults.contains(target));
        }
        return results.contains(true);
    }

    /**
     * NEEDS TO BE REDO.
     * The method finds a column which consist of only the element specified.
     *
     * @param target is the only element a column should contain.
     * @return true if a table contains at least one column with only specified element,
     * false - otherwise.
     */
    @Override
    public boolean containsColumnWithOnly(Double target) {
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < this.COLUMNS; i++) {
            Set<Double> betweenResults = new HashSet<>();
            for (int j = 0; j < this.STRINGS; j++) {
                betweenResults.add(this.MATRIX[j][i]);
            }
            results.add(betweenResults.size() == 1 && betweenResults.contains(target));
        }
        return results.contains(true);
    }

    /**
     * The method makes a copy of the matrix. Making a copy means that the method returns a matrix with the
     * same corresponding elements.
     * <p>
     * In general, if you have matrix A of N order and matrix B. Matrix B can be a copy of matrix A,
     * then and only then, if:
     * <p>
     * 0. Matrix B of N order.
     * <p>
     * 1. B[ i ][ j ] = A[ i ][ j ], where "i" and "j" are the numbers of a string and a column respectively.
     * @return an object of the class whose class implements "Table" interface.
     */
    public Table copy() {

        Matrix copy = new Matrix(STRINGS, COLUMNS);
        for (int i = 0; i < STRINGS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                copy.getAsArray()[i][j] = this.getAsArray()[i][j];
            }
        }
        return copy;

    }

    /**
     * UNDERDONE.
     * The method defines whether matrix is singular or not (that is, its determinant equals 0).
     * @return true if a matrix determinant equals 0, false - otherwise.
     * @exception IncompatibleSizeException is thrown is a matrix is not square.
     */
    public boolean isSingular() throws IncompatibleSizeException {
        if (this.STRINGS == this.COLUMNS) {
            return (this.containsColumnWithOnly(0d) || this.containsStringWithOnly(0d));
        } else {
            throw new IncompatibleSizeException();
        }
    }

    /**
     * The method replaces an original matrix's string or column to a specified matrix's string or column.
     * @param object is what is going to be replaced.
     * @param mIndex is an index of an object of a matrix is going to be edited.
     * @param origin is a matrix which is an origin of a "new" object for edition.
     * @param originIndex is an index of an object of a matrix whose contains a "new" object.
     * @return an object whose class implements "Table" interface.
     * @throws IncompatibleSizeException is throws when the size of editable objects of both matrices is not the same.
     */
    @Override
    public Table paste(String object, int mIndex, Table origin, int originIndex) throws IncompatibleSizeException {

        Table m = this.copy();

        if (object.equals("strings") && origin.getColumns() == m.getColumns()) {
            for (int i = 0; i < COLUMNS; i++) {
                m.getAsArray()[mIndex-1][i] = origin.getAsArray()[originIndex-1][i];
            }
        } else if (object.equals("columns") && origin.getStrings() == m.getStrings()) {
            for (int i = 0; i < STRINGS; i++) {
                m.getAsArray()[i][mIndex-1] = origin.getAsArray()[i][originIndex-1];
            }
        } else {
            throw new IncompatibleSizeException();
        }

        return m;

    }

}