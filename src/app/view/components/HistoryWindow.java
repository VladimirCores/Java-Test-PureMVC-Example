package app.view.components;

import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;

public class HistoryWindow extends JFrame {

	private static final long serialVersionUID = 2L;

	public JList<String> listHistory;
	private DefaultListModel<String> _model;
	
	public HistoryWindow() {
		Initialize();
	}

	private void Initialize() {
		setName("history");

		int margin = 8;
		int windowWidth = 360;
		int windowHeight = 272;
		int fieldWidth = windowWidth - margin * 2;
				
		this.setBounds(256, 240, windowWidth, windowHeight);
		getContentPane().setLayout(null);
		
		JLabel lblHistoryOfUser = new JLabel("History of user names");
		lblHistoryOfUser.setInheritsPopupMenu(false);
		lblHistoryOfUser.setName("lblHistoryOfUser");
		lblHistoryOfUser.setBounds(margin, margin, fieldWidth, 16);
		getContentPane().add(lblHistoryOfUser);
		
		_model = new DefaultListModel<String>();
		
		int listPositionY = lblHistoryOfUser.getBounds().y + lblHistoryOfUser.getHeight() + margin;
		int listHeight = 198;
		listHistory = new JList<String>();
		listHistory.setBounds(margin, listPositionY, fieldWidth, listHeight);
		listHistory.setModel(_model);
		getContentPane().add(listHistory);
	}
		
	public void appendNameAndDate(String name, long date) {
		_model.addElement(new Date(date).toLocaleString() + " - " + name);
	}

	public void appendName(String value) {
		_model.addElement(new Date().toLocaleString() + " - " + value);
	}
}
