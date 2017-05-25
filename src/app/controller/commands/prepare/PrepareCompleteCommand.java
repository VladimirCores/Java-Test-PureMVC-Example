package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.DatabaseProxy;
import app.model.proxy.LocaleProxy;
import app.model.proxy.UserProxy;
import app.model.vo.UserVO;
import consts.commands.ApplicationCommands;
import consts.notifications.CommonNotification;
import consts.notifications.MainWindowNotifications;

public class PrepareCompleteCommand extends SimpleCommand {
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareCompleteCommand");
		
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		DatabaseProxy databaseProxy = (DatabaseProxy) facade.retrieveProxy(DatabaseProxy.NAME);
		UserProxy userProxy = (UserProxy) facade.retrieveProxy(UserProxy.NAME);
		
		UserVO user = databaseProxy.retrieveUser();
		
		if(user == null) {
			user = userProxy.createDefaultUser();
			databaseProxy.saveUser(user);
		}
		
		userProxy.setData(user);
		
		this.sendNotification( CommonNotification.LANGUAGE_CHANGED, localeProxy.getCurrentLanguage() );
		this.sendNotification( MainWindowNotifications.USER_NAME_UPDATED, user.name );
		
		this.sendNotification( ApplicationCommands.READY );
		
		facade.removeCommand( ApplicationCommands.STARTUP );
		facade.removeCommand( ApplicationCommands.READY );

	}

}
