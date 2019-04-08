/**
 * 
 */
package search;

import java.io.IOException;

import data.WorkspaceFile;

/**
 * @author agz3
 *
 */
public class Engine implements Runnable
{
    private String[] args;
    
    public Engine(String[] args)
    {
        this.args = args;
    }

    @Override
    public void run()
    {
        try
        {
            WorkspaceFile workspace = new WorkspaceFile(WorkspaceFile.DEFAULT_WORKSPACE_FILE);
            
            System.out.println(workspace.getAirlinesPath());
            System.out.println(workspace.getAirportsPath());
            System.out.println(workspace.getDaysToDeparturePath());
            System.out.println(workspace.getFlightsPath());
            System.out.println(workspace.getPassengersPath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
