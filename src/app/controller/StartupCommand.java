package app.controller;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.MacroCommand;

import app.controller.commands.prepare.PrepareBeginCommand;
import app.controller.commands.prepare.PrepareCompleteCommand;
import app.controller.commands.prepare.PrepareControllerCommands;
import app.controller.commands.prepare.PrepareModelCommand;
import app.controller.commands.prepare.PrepareViewCommand;

public class StartupCommand extends MacroCommand {

	public StartupCommand() {
		// TODO Auto-generated constructor stub
		
		this.addSubCommand( new 	PrepareBeginCommand			());
		this.addSubCommand( new 	PrepareModelCommand			());
		this.addSubCommand( new 	PrepareControllerCommands	());
		this.addSubCommand( new 	PrepareViewCommand			());
		this.addSubCommand( new 	PrepareCompleteCommand		());
	}
	
	public void execute( INotification notification ) 
	{
		System.out.println("PureMVC > StartupCommand > executes");
		super.execute( notification );
	}

}
