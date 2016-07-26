package app.controller.commands.user;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;
import org.puremvc.java.patterns.observer.Notifier;

import app.model.proxy.DatabaseProxy;
import app.model.proxy.LocaleProxy;
import app.model.proxy.UserProxy;
import consts.messages.UserDialogMessages;
import consts.notifications.ApplicationNotifications;
import consts.notifications.MainWindowNotifications;

public class SetUserNameCommand extends SimpleCommand {

	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > SetUserNameCommand > executes");
		
		String message = UserDialogMessages.NAME_CHANGED_FAILED;
		String userName = (String)notification.getBody();
		
		UserProxy userProxy = (UserProxy)facade.retrieveProxy(UserProxy.NAME);
		DatabaseProxy databaseProxy = (DatabaseProxy) facade.retrieveProxy(DatabaseProxy.NAME);
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		
		Boolean isNameEmpty = userName.isEmpty();
		
		if(isNameEmpty) {
			message = localeProxy.getString(UserDialogMessages.NAME_IS_EMPTY);
			userName = userProxy.getUserName();
		} else {
			Boolean isNameSet = new Random().nextBoolean();
			if(isNameSet) {
				userProxy.setUserName(userName);
				databaseProxy.userNameChanged(userName, userProxy.getUserID());
				message = localeProxy.getString(UserDialogMessages.NAME_CHANGED_SUCCESS);
			} else {
				userName = userProxy.getUserName();
				message = localeProxy.getString(UserDialogMessages.NAME_CHANGED_FAILED);
			}
		}
		
		final Notifier notifier = this;
		final String msg = message;
		final String name = userName == null ? "" : userName;
		final int taskTime = isNameEmpty ? 0 : 1*1000;
		
		try {
			new Timer().schedule(new TimerTask() {
				@Override 
				public void run() {
					notifier.sendNotification ( ApplicationNotifications.SHOW_DIALOG_MESSAGE, msg );
					notifier.sendNotification( MainWindowNotifications.USER_NAME_UPDATED, name );
				}
			}, taskTime);
		} catch(Exception e) {
			
		}
	}
}
