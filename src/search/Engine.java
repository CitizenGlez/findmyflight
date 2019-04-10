/**
 * 
 */
package search;

import java.io.IOException;
import java.util.ArrayList;

import data.WorkspaceFile;

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
    
    private void parseArguments()
    {
        // TODO: do
    }

    @Override
    public void run()
    {
        try
        {
            this.parseArguments();
            
            WorkspaceFile workspace = new WorkspaceFile(WorkspaceFile.DEFAULT_WORKSPACE_FILE);
            this.connectionsManager = new ConnectionsManager(workspace);
            
            this.results.addAll(this.connectionsManager.search());
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
