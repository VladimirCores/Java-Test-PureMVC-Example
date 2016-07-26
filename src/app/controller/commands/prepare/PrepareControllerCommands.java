package app.controller.commands.prepare;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.controller.commands.locale.LocalizeWindowComponentsCommand;
import app.controller.commands.locale.ChangeCurrentLanguageCommand;
import app.controller.commands.user.SetUserNameCommand;
import app.controller.commands.windows.CreateHistoryWindowCommand;
import consts.commands.LocalizationCommands;
import consts.commands.UserCommands;
import consts.commands.WindowCommands;

public class PrepareControllerCommands extends SimpleCommand {

	public PrepareControllerCommands() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes > PrepareControllerCommands");
		
		facade.registerCommand( UserCommands.SET_NAME, new SetUserNameCommand() );

		facade.registerCommand( WindowCommands.CREATE_HISTORY_WINDOW, new CreateHistoryWindowCommand() );
		
		facade.registerCommand( LocalizationCommands.LOCALIZE_WINDOW_COMPONENTS, new LocalizeWindowComponentsCommand() );
		facade.registerCommand( LocalizationCommands.CHANGE_CURRENT_LANGUAGE, new ChangeCurrentLanguageCommand() );

	}

}
