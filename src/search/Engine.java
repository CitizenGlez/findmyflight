/**
 * 
 */
package search;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import data.WorkspaceFile;
import search.arg.Arguments;

/**
 * @author agz3
 *
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
