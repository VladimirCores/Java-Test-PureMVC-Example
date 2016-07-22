package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import consts.commands.ApplicationCommands;

public class PrepareCompleteCommand extends SimpleCommand {
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareCompleteCommand");
		
		this.sendNotification( ApplicationCommands.READY );
		
		facade.removeCommand( ApplicationCommands.STARTUP );
		facade.removeCommand( ApplicationCommands.READY );

	}

}
