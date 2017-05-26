package app.view.mediators;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import app.model.vo.UserNameVO;
import app.view.components.HistoryWindow;
import consts.commands.LocalizationCommands;
import consts.notifications.CommonNotification;
import consts.notifications.HistoryWindowNotifications;
import consts.notifications.MainWindowNotifications;

public class HistoryWindowMediator extends Mediator {

	static public String NAME = "HistoryScreenMediator";
	
	private HistoryWindow history;
	
	public HistoryWindowMediator(Object viewComponent) {
		super(NAME, viewComponent);
		history = (HistoryWindow) viewComponent;
	}
	
	@Override
	public String[] listNotificationInterests() {
		return new String[]{
			MainWindowNotifications.USER_NAME_UPDATED	
		,	HistoryWindowNotifications.SET_HISTORY_DATA
		,	CommonNotification.LANGUAGE_CHANGED
		};
	}
	
	@Override
	public void handleNotification(INotification notification) {
		switch (notification.getName()) {
			case HistoryWindowNotifications.SET_HISTORY_DATA:
				for( Object item : (ArrayList<Object>)notification.getBody() ) {
					UserNameVO userNameVO = (UserNameVO) item;
					history.appendNameAndDate(userNameVO.value, userNameVO.date);
				}
			break;
			case MainWindowNotifications.USER_NAME_UPDATED:
				history.appendName((String)notification.getBody());
			break;
			case CommonNotification.LANGUAGE_CHANGED:
				this.sendNotification(
					LocalizationCommands.LOCALIZE_WINDOW_COMPONENTS, 
					history.getContentPane().getComponents(), 
					history.getName()
				);
			break;
		}
	}
	
	public void onRegister() {
		history.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	HandleCloseWindow();
		    }
		});
	}
	
	private void HandleCloseWindow() {
		viewComponent = null;
		this.sendNotification( MainWindowNotifications.UNLOCK_HISTORY_BUTTON );
		this.facade.removeMediator(NAME);
		
	}

}
