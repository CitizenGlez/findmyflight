/**
 * 
 */
package search.exception;

import java.io.FileNotFoundException;

/**
 * @author AGZ3
 *
 */
public class OriginNotFoundException extends FileNotFoundException
{

    /**
     * 
     */
    private static final long serialVersionUID = 7139054755956462874L;

    /**
     * @param s
     */
    public OriginNotFoundException(String origin)
    {
        super(origin + " is not an available airport");
    }

}
