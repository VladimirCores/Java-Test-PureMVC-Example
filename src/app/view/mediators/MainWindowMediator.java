package app.view.mediators;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import app.view.components.MainWindow;
import consts.commands.LocalizationCommands;
import consts.commands.UserCommands;
import consts.commands.WindowCommands;
import consts.notifications.CommonNotification;
import consts.notifications.MainWindowNotifications;

public class MainWindowMediator extends Mediator implements ActionListener 
{
	static public String NAME = "MainScreenMediator";

	private MainWindow main;
	
	public MainWindowMediator(Object viewComponent) {
		super(NAME, viewComponent);
		main = (MainWindow) this.viewComponent;
	}
	
	@Override
	public String[] listNotificationInterests() {
		return new String[] {
			MainWindowNotifications.SHOW_WINDOW
		,	MainWindowNotifications.USER_NAME_UPDATED
		
		,	DatabaseNotifications.USER_SAVE_SUCCESS	
		,	DatabaseNotifications.USER_SAVE_FAILURE	
		
		,	CommonNotification.LANGUAGE_CHANGED	
		};
	}
	
	@Override
	public void handleNotification(INotification notification) {
		switch (notification.getName()) {
			case MainWindowNotifications.USER_NAME_UPDATED:
				main.btnSetUserName.setEnabled(true);
				main.updateUserName((String) notification.getBody());
			break;
			case DatabaseNotifications.USER_SAVE_SUCCESS: main.highlightSaveSuccess(); break;
			case DatabaseNotifications.USER_SAVE_FAILURE: main.highlightSaveFailure(); break;
			case MainWindowNotifications.SHOW_WINDOW: main.setVisible(true); break;
			case CommonNotification.LANGUAGE_CHANGED: UpdateLanguage((String)notification.getBody()); break;
		}
	}
	
	private void UpdateLanguage(String lng) {
		main.setLanguageButton(lng);
		this.sendNotification(
			LocalizationCommands.LOCALIZE_WINDOW_COMPONENTS, 
			main.getContentPane().getComponents(), 
			main.getName()
		);
	}
	
	@Override
	public void onRegister() {
		main.btnSetUserName.addActionListener(this);
		main.btnOpenHistory.addActionListener(this);
		main.btnLngCz.addActionListener(this);
		main.btnLngEn.addActionListener(this);
		main.btnLngRu.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource(); 
		if( source == main.btnSetUserName ) 
		{
			main.btnSetUserName.setEnabled(false);
			this.sendNotification( UserCommands.SET_NAME, main.tfUserName.getText());
		}
		else if( source == main.btnOpenHistory ) 
		{
			this.sendNotification( WindowCommands.CREATE_HISTORY_WINDOW );
		} 
		else if(
				source == main.btnLngCz 
			|| 	source == main.btnLngEn
			|| 	source == main.btnLngRu
			) {
			this.sendNotification( 
				LocalizationCommands.CHANGE_CURRENT_LANGUAGE 
			,	main.getLanguageNameFromButton(source) 
			);
		}
	}
}
