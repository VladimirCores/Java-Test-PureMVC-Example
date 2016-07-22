package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.LocaleProxy;
import app.model.proxy.UserProxy;

public class PrepareModelCommand extends SimpleCommand {

	public PrepareModelCommand() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareModelCommand");
		
		facade.registerProxy(new UserProxy());
		facade.registerProxy(new LocaleProxy());

		
		
	}

}
