import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import org.puremvc.java.patterns.observer.Notification;

import app.ApplicationFacade;
import app.controller.StartupCommand;
import app.controller.commands.ReadyCommand;
import consts.commands.ApplicationCommands;

import javax.swing.JLabel;

public class Application {
	
	Application app;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ApplicationFacade().startup(new Application());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	}
}
