package app.controller.commands.windows;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.DatabaseProxy;
import app.model.proxy.LocaleProxy;
import app.model.proxy.UserProxy;
import app.view.components.HistoryWindow;
import app.view.mediators.HistoryWindowMediator;
import consts.commands.LocalizationCommands;

public class CreateHistoryWindowCommand extends SimpleCommand 
{
	public void execute( INotification notification ) 
	{
		DatabaseProxy databaseProxy = (DatabaseProxy) facade.retrieveProxy( DatabaseProxy.NAME );
		
		HistoryWindow historyWindow = new HistoryWindow();
		facade.registerMediator( new HistoryWindowMediator( historyWindow ));
		
		this.sendNotification( 
			LocalizationCommands.LOCALIZE_WINDOW_COMPONENTS, 
			historyWindow.getContentPane().getComponents(), 
			historyWindow.getName()
		);
		
		this.sendNotification( 
			HistoryWindowNotifications.SET_HISTORY_DATA, 
			databaseProxy.getHistoryOfNames() 
		);

		/*	 
			for(Object item : databaseProxy.getHistoryOfNames()) {
				UserNameVO userNameVO = (UserNameVO) item;
				historyWindow.appendNameAndDate(userNameVO.value, userNameVO.date);
			}
		*/

		historyWindow.setVisible(true);
	}
}


