package app.controller.commands.locale;

import java.awt.Component;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import app.model.proxy.LocaleProxy;

public class LocalizeWindowComponentsCommand extends SimpleCommand {

	public void execute( INotification notification ) 
	{
		String windowName = notification.getType();
		Component[] mainWindowComponents = (Component[]) notification.getBody();
		LocaleProxy localeProxy = (LocaleProxy) facade.retrieveProxy(LocaleProxy.NAME);
		Map<String, String> localeForMainWindow = localeProxy.getItemsLocalizationForWindow(windowName);
		
		JButton tempBtn;
		JLabel tempLbl;
		String tempText;
		for (Component component : mainWindowComponents) {
			tempText = localeForMainWindow.get(component.getName());
			//System.out.println("Component:" + component.getName() + " | " + tempText);
			if(tempText == null) continue; 
			if(component instanceof JButton) {
				tempBtn = (JButton)component;
				tempBtn.setText(tempText);
			} else if(component instanceof JLabel) {
				tempLbl = (JLabel)component;
				tempLbl.setText(tempText);
			}
		}
	}
}
