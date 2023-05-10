package Mathematics;

/**
 * The Table was created to be implemented by Matrix, Determinant and other classes which are intending
 * to have the same methods for transformations. Any object which classes implements Table has ONLY
 * integers and no other types of data.
 */
public interface Table {
    Double[][] getAsArray();

    int getStrings();

    int getColumns();

    int getSize();

    Double getElement(int string, int column);

    void fillRandomly(int increment);

    void show();

    Table transposition();

    void fillIndependently();

    boolean containsStringWithOnly(Double target);

    boolean containsColumnWithOnly(Double target);

    Table copy();

    Table paste(String object, int thisIndex, Table origin, int originIndex) throws IncompatibleSizeException;

}