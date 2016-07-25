package app.controller.commands.locale;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.LocaleProxy;
import consts.notifications.CommonNotification;

public final class ChangeCurrentLanguageCommand extends SimpleCommand {

	public void execute( INotification notification ) 
	{
		String newLanguage = (String) notification.getBody();
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		
		System.out.println("ChangeCurrentLanguageCommand: " + newLanguage);
		
		if(!newLanguage.equals(localeProxy.getCurrentLanguage())) 
		{
			localeProxy.changeLanguage(newLanguage);
			this.sendNotification( CommonNotification.LANGUAGE_CHANGED, newLanguage );
		}
	}
}
