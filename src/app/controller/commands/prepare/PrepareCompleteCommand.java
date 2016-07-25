package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.LocaleProxy;
import consts.commands.ApplicationCommands;
import consts.notifications.CommonNotification;

public class PrepareCompleteCommand extends SimpleCommand {
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareCompleteCommand");
		
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		
		this.sendNotification( CommonNotification.LANGUAGE_CHANGED, localeProxy.getCurrentLanguage() );
		
		this.sendNotification( ApplicationCommands.READY );
		
		facade.removeCommand( ApplicationCommands.STARTUP );
		facade.removeCommand( ApplicationCommands.READY );

	}

}
