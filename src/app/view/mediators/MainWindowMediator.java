package app.view.mediators;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import app.view.components.MainWindow;
import consts.commands.UserCommands;
import consts.notifications.MainWindowNotifications;

public class MainWindowMediator extends Mediator implements ActionListener {

	static public String NAME = "MainScreenMediator";
	
	private MainWindow main;
	
	public MainWindowMediator(Object viewComponent) {
		super(NAME, viewComponent);
		main = (MainWindow) this.viewComponent;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String[] listNotificationInterests() {
		return new String[]{
			MainWindowNotifications.SHOW_WINDOW
		};
	}
	
	@Override
	public void handleNotification(INotification notification) {
		switch (notification.getName()) {
			case MainWindowNotifications.SHOW_WINDOW:
				main.setVisible(true);
			break;
		}
	}
	
	@Override
	public void onRegister() {
		main.btnSetUserName.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource(); 
		if(source == main.btnSetUserName)
			this.facade.sendNotification( UserCommands.SET_NAME, main.tfUserName.getText() );
		
	}
}
