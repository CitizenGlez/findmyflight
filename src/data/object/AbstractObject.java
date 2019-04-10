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
    
    protected String line;
    
    public AbstractObject(String line) throws InvalidParameterException
    {
        // Check validity
        this.line = line.trim();
        if (this.line.isEmpty())
        {
            throw new InvalidParameterException("Empty line found");
        }
        else if (this.line.startsWith(AbstractObject.HASH))
        {
            throw new InvalidParameterException("Comment found");
        }
    }
}
