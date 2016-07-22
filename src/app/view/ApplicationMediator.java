package app.view;

import javax.swing.JOptionPane;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import consts.notifications.ApplicationNotifications;


public class ApplicationMediator extends Mediator {

	static public String NAME = "ApplicationMediator";
	
	public ApplicationMediator(Object viewComponent) {
		super(NAME, viewComponent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String[] listNotificationInterests() {
		return new String[]{
			ApplicationNotifications.SHOW_DIALOG_MESSAGE
		};
	}
	
	@Override
	public void handleNotification(INotification notification) {
		switch (notification.getName()) {
			case ApplicationNotifications.SHOW_DIALOG_MESSAGE:
				System.out.println(notification.getBody());
				JOptionPane.showMessageDialog(null, notification.getBody());
			break;
		}
	}
}
