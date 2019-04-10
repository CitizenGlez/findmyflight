/**
 * 
 */
package data.parser;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author alvaro
 *
 */
public class AbstractParser
{
    protected String path;
    
    /**
     * @param path
     * @throws FileNotFoundException 
     */
    public AbstractParser(String path) throws FileNotFoundException
    {
        File file = new File(path);
        if (!file.exists())
        {
            throw new FileNotFoundException();
        }
        
        this.path = path;
    }

}
