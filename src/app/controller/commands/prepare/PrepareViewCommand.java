package app.controller.commands.prepare;

import javax.swing.JFrame;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.view.ApplicationMediator;
import app.view.components.MainWindow;
import app.view.mediators.MainWindowMediator;

public class PrepareViewCommand extends SimpleCommand {

	public PrepareViewCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareViewCommand");
		
		MainWindow mainWindow = new MainWindow();
		Object application = notification.getBody();
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		facade.registerMediator(new ApplicationMediator(application));
		facade.registerMediator(new MainWindowMediator(mainWindow));
	}
}
