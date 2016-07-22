package app;

import org.puremvc.java.patterns.facade.Facade;

import app.controller.StartupCommand;
import app.controller.commands.ReadyCommand;

import consts.commands.ApplicationCommands;

public class ApplicationFacade extends Facade {
	
	@Override
	protected void initializeController()
	{
		super.initializeController();
		
		this.registerCommand( ApplicationCommands.STARTUP, 	new StartupCommand() 	);
		this.registerCommand( ApplicationCommands.READY, 	new ReadyCommand() 		);
	}

	public void startup(Object app) {
		// TODO Auto-generated method stub
		this.sendNotification( ApplicationCommands.STARTUP, app );
	}
}
