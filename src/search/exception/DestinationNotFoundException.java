/**
 * 
 */
package search.exception;

import java.io.FileNotFoundException;

/**
 * @author alvaro
 *
 */
public class DestinationNotFoundException extends FileNotFoundException
{

    /**
     * 
     */
    private static final long serialVersionUID = -2031332564486162913L;

    /**
     * @param s
     */
    public DestinationNotFoundException(String origin, String destination)
    {
        super(destination + " is not an available destination from " + origin);
    }

}
