package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class PrepareBeginCommand extends SimpleCommand {

	public PrepareBeginCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareBeginCommand");
	}

}
