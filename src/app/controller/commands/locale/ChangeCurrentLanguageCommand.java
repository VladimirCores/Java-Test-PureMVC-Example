package app.controller.commands.locale;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.LocaleProxy;
import app.model.proxy.UserProxy;
import consts.notifications.CommonNotification;
import consts.notifications.MainWindowNotifications;

public final class ChangeCurrentLanguageCommand extends SimpleCommand {

	public void execute( INotification notification ) 
	{
		String newLanguage = (String) notification.getBody();
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		UserProxy userProxy = (UserProxy) facade.retrieveProxy(UserProxy.NAME);
		
		System.out.println("ChangeCurrentLanguageCommand: " + newLanguage);
		
		if(!newLanguage.equals(localeProxy.getCurrentLanguage())) 
		{
			localeProxy.changeLanguage(newLanguage);
			this.sendNotification( CommonNotification.LANGUAGE_CHANGED, newLanguage );
			this.sendNotification( MainWindowNotifications.RESTORE_USER_NAME, userProxy.getUserName() );
			
		}
	}
}
