package Mathematics;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Determinant implements Serializable, Table {

    @Serial
    private static final long serialVersionUID = -2189551668450737029L;

    /**
     * The standard input stream.
     */
    private final Scanner STANDARD_INPUT = new Scanner(System.in);

    /**
     * What is the order of a determinant (strings = columns).
     */
    private final int ORDER;

    /**
     * Two-dimensional array -- the matrix itself.
     */
    private final Double[][] DETERMINANT;

    /**
     * The standard constructor used while making a determinant.
     */
    public Determinant(int size) {
        ORDER = size;
        DETERMINANT = new Double[ORDER][ORDER];
    }

    /**
     * The constructor used while making a determinant which intends to be stored in some collection.
     */
    public Determinant(int size, Collection<Determinant> collection) {
        ORDER = size;
        DETERMINANT = new Double[ORDER][ORDER];
        collection.add(this);
    }

    /**
     * The method created for realization of other methods of this class.
     * @return Two-dimensional array itself.
     */
    @Override
    public Double[][] getAsArray() {
        return DETERMINANT;
    }

    /**
     * Returns how many strings a determinant has.
     *
     * @return A string length of a determinant as integer.
     */
    @Override
    public int getStrings() {
        return ORDER;
    }

    /**
     * Returns how many columns a determinant has.
     *
     * @return A size of a determinant as integer.
     */
    @Override
    public int getColumns() {
        return ORDER;
    }

    /**
     * Returns how many columns a matrix has.
     *
     * @return A size of a matrix as integer.
     */
    @Override
    public int getSize() {
        return (ORDER * ORDER);
    }

    /**
     * Returns the determinant element specified. Note that the first array index in Java begins with '0',
     * though a determinant element begins with '1'. To make method work correct, '-1' has been added to return
     * statement.
     *
     * @param string is required to find the element definitely.
     * @param column is required to find the element definitely.
     * @return Determinant element specified.
     */
    @Override
    public Double getElement(int string, int column) {
        return DETERMINANT[string-1][column-1];
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
        for (int i = 0; i < ORDER; i++) {
            for (int j = 0; j < ORDER; j++) {
                index *= Math.pow(-1, (int) (Math.random() * increment));
                DETERMINANT[i][j] = Math.random() * increment * index;
            }
        }
    }

    /**
     * Shows a determinant as a table in the Console.
     */
    @Override
    public void show() {
        for (int i = 0; i < ORDER; i++) {
            for (int j = 0; j < ORDER; j++) {
                System.out.print("|" + DETERMINANT[i][j] + "| ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    /**
     * The method creates a new determinant where string and column numbers from original determinant are swapped.
     *
     * @return A new determinant as an object of Determinant which implements Table.
     */
    @Override
    public Table transposition() {
        Table determinant = new Matrix(this.ORDER, this.ORDER);
        for (int i = 0; i < this.ORDER; i++) {
            for (int j = 0; j < this.ORDER; j++) {
                determinant.getAsArray()[i][j] = this.getAsArray()[j][i];
            }
        }
        return determinant;
    }

    /**
     * The method allows to find a minor of any element in any determinant.
     * In ArrayList "elements", there are all the elements which are not in a string and a column that contain
     * the element whose minor we are looking for. When the ArrayList is completely full, in new Determinant (its order
     * is one less than in the original determinant) the rest elements start to appear.
     * <p>
     * NOTE! In mathematics a counting starts with '1', but in arrays - with '0'. In parameters, the counting starts
     * with '1', even though it's required to use '0' if it's necessary to access to the first element. So, in the body
     * of the method there are '-1' operation to avoid mistakes while it is working.
     *
     * @param string is a string of an elements whose minor we are looking for.
     * @param column is a column of an elements whose minor we are looking for.
     *
     * @return Determinant as an object of Determinant class.
     */
    public Determinant getMinor(int string, int column) {

        Determinant minor = new Determinant(this.ORDER -1);
        List<Double> elements = new ArrayList<>();

        for (int i = 0; i < this.ORDER; i++) {
            for (int j = 0; j < this.ORDER; j++) {
                if (i != string-1 && j != column-1) {
                    elements.add(this.getAsArray()[i][j]);
                }
            }
        }

        int index = 0;
        for (int i = 0; i < minor.getStrings(); i++) {
            for (int j = 0; j < minor.getColumns(); j++) {
                minor.getAsArray()[i][j] = elements.get(index);
                index++;
            }
        }

        return minor;

    }

    /**
     * The method allows to user to enter a value of each determinant element, using keyboard.
     * The user enters the values sequentially one by one until the determinant is full.
     * Also, the user can see which element (being more precise, string and column of the element) he
     * fills.
     */
    @Override
    public void fillIndependently() {
        System.out.println("Enter the determinant number: ");
        for (int i = 0; i < this.ORDER; i++) {
            for (int j = 0; j < this.ORDER; j++) {
                System.out.print("["+(i+1)+"]["+(j+1)+"] = ");
                this.getAsArray()[i][j] = STANDARD_INPUT.nextDouble();
            }
        }
        System.out.println("The determinant has been successfully filled.");
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
        for (int i = 0; i < this.ORDER; i++) {
            Set<Double> betweenResults = new HashSet<>();
            for (int j = 0; j < this.ORDER; j++) {
                betweenResults.add(this.DETERMINANT[i][j]);
            }
            results.add(betweenResults.size() == 1 && betweenResults.contains(target));
        }
        return results.contains(true);
    }
    /**
     * The method finds a column which consist of only the element specified.
     *
     * @param target is the only element a column should contain.
     * @return true if a table contains at least one column with only specified element,
     * false - otherwise.
     */
    @Override
    public boolean containsColumnWithOnly(Double target) {
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < this.ORDER; i++) {
            Set<Double> betweenResults = new HashSet<>();
            for (int j = 0; j < this.ORDER; j++) {
                betweenResults.add(this.DETERMINANT[j][i]);
            }
            results.add(betweenResults.size() == 1 && betweenResults.contains(target));
        }
        return results.contains(true);
    }

    /**
     * The method allows to multiply any determinants by number specified. Unlike matrices, where each element takes
     * place in multiplying, in determinants only one string (by the way, column, too) can be multiplied by a number.
     *
     * @param num is the number which is used for multiplying.
     * @param string is what string has been chosen for multiplying.
     * @return Determinant as an object of Determinant class.
     */
    public Determinant multiplyByNumber(double num, int string) {
        Determinant det = new Determinant(this.ORDER);
        for (int i = 0; i < this.ORDER; i++) {
            if (i == string-1) {
                for (int j = 0; j < this.ORDER; j++) {
                    det.getAsArray()[i][j] = num * this.DETERMINANT[i][j];
                }
            } else {
                for (int j = 0; j < this.ORDER; j++) {
                    det.getAsArray()[i][j] = this.DETERMINANT[i][j];
                }
            }
        }
        return det;
    }

    /**
     * The method allows to find an algebraic addition for some elements of a determinant. This method is a basic for
     * the next method that allows to calculate four and higher order determinants. Trying to find an algebraic
     * addition we use minors anyway. Any minor is a determinant, so if we intend to get an algebraic addition
     * we need to calculate determinants, perhaps that have four and higher order.
     *
     * @param column is which column specified element is located on.
     * @param string is which string specified element is located on.
     * @return Determinant as an object of Determinant class.
     */
    public Determinant getAlgAddition(int string, int column) {
        int i = (int) Math.pow(-1, string+column);
        Determinant minor = this.getMinor(string, column);
        return minor.multiplyByNumber(i, 1);
    }

    /**
     * The method is used if it's necessary to check the determinants from the same collection whether they are
     * appropriate to specified conditions. These are: More than A, Equals A, Less than A, where 'A' is the
     * number we compare with. ALL the determinants must be appropriate.
     *
     * @param determinants is a collection where determinants are stored.
     * @param type is what helps the method to select the necessary comparison operation.
     * @param value is the number the sizes of determinants compare with.
     * @return true if all the determinants are appropriate for specified conditions, false -- otherwise.
     */
    public static boolean sizeOfAll(Collection<Determinant> determinants, String type, int value) {
        boolean result = true;
        for (Determinant d : determinants) {
            if (type.equals("More") && d.getSize() <= value) {
                result = false;
            }
            if (type.equals("Less") && d.getSize() >= value) {
                result = false;
            }
            if (type.equals("Equals") && d.getSize() != value) {
                result = false;
            }
        }
        return result;
    }

    /**
     * The method is used if it's necessary to check the determinants from the same collection whether they are
     * appropriate to specified conditions. These are: More than A, Equals A, Less than A, where 'A' is the
     * number we compare with. AT LEAST ONE of the determinant must be appropriate!
     *
     * @param determinants is a collection where determinants are stored.
     * @param type is what helps the method to select the necessary comparison operation.
     * @param value is the number the sizes of determinants compare with.
     * @return true if at least one of the determinants is appropriate for specified conditions, false -- otherwise.
     */
    public static boolean sizeOfAny(Collection<Determinant> determinants, String type, int value) {
        boolean result = false;
        for (Determinant d : determinants) {
            if (type.equals("More") && d.getSize() >= value) {
                result = true;
            }
            if (type.equals("Less") && d.getSize() <= value) {
                result = true;
            }
            if (type.equals("Equals") && d.getSize() == value) {
                result = true;
            }
        }
        return result;
    }

    /**
     * The method makes a copy of the determinant. Making a copy means that the method returns a determinant with the
     * same corresponding elements.
     * <p>
     * In general, if you have determinant A of N order and determinant B. Determinant B can be a copy of determinant A,
     * then and only then, if:
     * <p>
     * 0. Determinant B of N order.
     * <p>
     * 1. B[ i ][ j ] = A[ i ][ j ], where "i" and "j" are the numbers of a string and a column respectively.
     * @return an object of the class whose class implements "Table" interface.
     */
    public Table copy() {

        int order = this.ORDER;
        Determinant copy = new Determinant(order);
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                copy.getAsArray()[i][j] = this.getAsArray()[i][j];
            }
        }
        return copy;

    }

    /**
     * The method calculates a determinant giving a return statement as a number.
     * @return A number of double type.
     * @exception ArithmeticException is thrown when a division by zero occurs while the method is working.
     */
    public double getDeterminantAsNumber() {

        double result = 1;
        Double[][] copyOf = this.copy().getAsArray();

        for (int i = 0; i < ORDER - 1; i++) {
            for (int j = i + 1; j < ORDER; j++) {
                if (copyOf[i][i] == 0) {
                    throw new ArithmeticException("Division by zero!");
                }
                double coefficient = copyOf[j][i] / copyOf[i][i];
                for (int k = 0; k < ORDER; k++) {
                    copyOf[j][k] = copyOf[j][k] - copyOf[i][k] * coefficient;
                }
            }
        }

        for (int x = 0; x < ORDER; x++) {
            for (int y = 0; y < ORDER; y++) {
                result *= (x == y) ? copyOf[x][y] : 1;
            }
        }
        return result;

    }

    /**
     * The method replaces an original determinant's string or column to a specified determinant's string or column.
     * @param object is what is going to be replaced.
     * @param mIndex is an index of an object of a determinant is going to be edited.
     * @param origin is a determinant which is an origin of a "new" object for edition.
     * @param originIndex is an index of an object of a determinant whose contains a "new" object.
     * @return an object whose class implements "Table" interface.
     * @throws IncompatibleSizeException is throws when the size of editable objects of both of determinants is not the same.
     */
    @Override
    public Table paste(String object, int mIndex, Table origin, int originIndex) throws IncompatibleSizeException {

        Table m = this.copy();

        if (object.equals("strings") && origin.getColumns() == m.getColumns()) {
            for (int i = 0; i < ORDER; i++) {
                m.getAsArray()[mIndex-1][i] = origin.getAsArray()[originIndex-1][i];
            }
        } else if (object.equals("columns") && origin.getStrings() == m.getStrings()) {
            for (int i = 0; i < ORDER; i++) {
                m.getAsArray()[i][mIndex-1] = origin.getAsArray()[i][originIndex-1];
            }
        } else {
            throw new IncompatibleSizeException();
        }

        return m;

    }

}