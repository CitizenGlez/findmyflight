/**
 * 
 */
package search;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.ArrayList;

import data.WorkspaceFile;
import search.arg.Arguments;

/**
 * This is just a sample application for using the ConnectionsManager class.
 * @author alvaro
 */
public class Engine implements Runnable
{
    private String[] args;
    ConnectionsManager connectionsManager;
    ArrayList<String> results;
    
    public Engine(String[] args)
    {
        this.args = args;
        this.results = new ArrayList<String>();
    }

    @Override
    public void run()
    {
        try
        {
            if (this.args.length >= 1)
            {
                // Turn the first argument into proper Arguments, ignore the rest
                Arguments arguments = new Arguments(args[1]);
                
                this.connectionsManager = new ConnectionsManager(new WorkspaceFile());
                
                this.results.addAll(this.connectionsManager.search(arguments));
            }
            else
            {
                throw new InvalidParameterException("Insufficient number of arguments provided");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getResults()
    {
        return results;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Thread t = new Thread(new Engine(args));
        t.start();
    }

}
