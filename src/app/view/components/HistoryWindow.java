package app.view.components;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;

import app.model.vo.UserNameVO;

public class HistoryWindow extends JFrame {

	private static final long serialVersionUID = 2L;

	public JList listHistory;
	private DefaultListModel<String> _model;
	
	public HistoryWindow() {
		Initialize();
	}

	private void Initialize() {
		setName("history");

		this.setBounds(150, 150, 248, 267);
		getContentPane().setLayout(null);
		
		JLabel lblHistoryOfUser = new JLabel("History of user names");
		lblHistoryOfUser.setInheritsPopupMenu(false);
		lblHistoryOfUser.setName("lblHistoryOfUser");
		lblHistoryOfUser.setBounds(10, 11, 239, 14);
		getContentPane().add(lblHistoryOfUser);
		
		_model = new DefaultListModel<String>();
		listHistory.setModel(_model);

		listHistory = new JList();
		listHistory.setBounds(10, 50, 212, 173);
		getContentPane().add(listHistory);
	}
		
	public void appendNameAndDate(String name, long date) {
		_model.addElement(new Date(date).toLocaleString() + " - " + name);
	}

	public void appendName(String value) {
		_model.addElement(new Date().toLocaleString() + " - " + value);
	}
}
