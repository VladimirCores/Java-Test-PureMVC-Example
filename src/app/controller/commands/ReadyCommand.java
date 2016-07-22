package app.controller.commands;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import consts.notifications.MainWindowNotifications;

public class ReadyCommand extends SimpleCommand {

	public ReadyCommand() {
		// TODO Auto-generated constructor stub
	}

	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > ReadyCommand > executes");
		
		this.sendNotification(MainWindowNotifications.SHOW_WINDOW);
	}

	
}
