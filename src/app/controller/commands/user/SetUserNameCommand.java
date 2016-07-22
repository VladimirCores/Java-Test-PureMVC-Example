package app.controller.commands.user;

import java.util.Random;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.UserProxy;
import consts.messages.UserDialogMessages;
import consts.notifications.ApplicationNotifications;

public class SetUserNameCommand extends SimpleCommand {

	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > SetUserNameCommand > executes");
		
		String message = UserDialogMessages.NAME_CHANGED_FAILED;
		String userName = (String)notification.getBody();
		
		if(userName.isEmpty()) {
			message = UserDialogMessages.NAME_IS_EMPTY;
		} else {
			Boolean isNameSet = new Random().nextBoolean();
			UserProxy userProxy = (UserProxy)facade.retrieveProxy(UserProxy.NAME);
			if(isNameSet) {
				userProxy.setUserName(userName);
				message = UserDialogMessages.NAME_CHANGED_SUCCESS;
			}
		}
		
		this.sendNotification ( ApplicationNotifications.SHOW_DIALOG_MESSAGE, message );
	}
}
