import java.awt.EventQueue;

import javax.swing.UIManager;

import app.ApplicationFacade;

public class Application implements Runnable 
{
	private static final ApplicationFacade shell = new ApplicationFacade();

	public static void main(String[] args) 
	{
		try { 
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
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
	private void initialize() 
	{
		
	}
}

