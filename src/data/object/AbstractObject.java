/**
 * 
 */
package data.object;

import java.security.InvalidParameterException;

/**
 * @author alvaro
 *
 */
public abstract class AbstractObject
{
    public static final String CSV_DELIMITER = ",";
    public static final String HASH = "#";
    
    public AbstractObject(String line) throws InvalidParameterException
    {
        // Check validity
        String trimmedline = line.trim();
        if (trimmedline.isEmpty())
        {
            throw new InvalidParameterException("Empty line found");
        }
        else if (trimmedline.startsWith(AbstractObject.HASH))
        {
            throw new InvalidParameterException("Comment found");
        }
    }
}
