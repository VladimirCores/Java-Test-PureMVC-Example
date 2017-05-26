import java.awt.EventQueue;

import app.ApplicationFacade;
import consts.notifications.ApplicationNotifications;

public class Application implements Runnable 
{
	private static final ApplicationFacade shell = new ApplicationFacade();

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Application());
	}
	
	public void run() 
	{
		shell.startup(this);
	}

	/**
	 * Create the application.
	 */
	public Application() {	initialize(); }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { }
}

