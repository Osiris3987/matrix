package Mathematics;
import java.io.Serial;

/**
 * The exception is thrown when a table has not an appropriate size.
 */
public class IncompatibleSizeException extends Exception  {

    @Serial
    private static final long serialVersionUID = -8553903929051593702L;

    public IncompatibleSizeException() {
        super();
    }

}
