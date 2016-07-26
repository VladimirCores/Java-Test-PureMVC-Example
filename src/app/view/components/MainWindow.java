package app.view.components;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public JLabel lblUserName;
	
	public JButton 
		btnSetUserName
	,	btnOpenHistory

	,	btnLngEn
	,	btnLngCz
	,	btnLngRu
	;
	
	public JTextField tfUserName;

	public HashMap<String, JButton> lngButtons = new HashMap<>();
	
	private JButton _activeLanguageButton;
	
	public MainWindow() {
		Initialize();
	}
	
	private void Initialize() {
		
		setName("main");
		
		this.setBounds(100, 100, 248, 272);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		btnSetUserName = new JButton("Set User Name");
		btnSetUserName.setName("btnSetUserName");
		btnSetUserName.setBounds(10, 78, 212, 36);
		this.getContentPane().add(btnSetUserName);
		
		tfUserName = new JTextField();
		tfUserName.setName("tfUserName");
		tfUserName.setToolTipText("User Name");
		tfUserName.setBounds(10, 32, 212, 32);
		this.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		lblUserName = new JLabel("Current User Name: ");
		lblUserName.setName("lblUserName");
		lblUserName.setBounds(10, 11, 212, 14);
		this.getContentPane().add(lblUserName);
		
		btnLngEn = new JButton("En");
		btnLngEn.setName("btnLngEn");
		btnLngEn.setBounds(10, 141, 62, 23);
		getContentPane().add(btnLngEn);
		
		
		btnLngCz = new JButton("Cz");
		btnLngCz.setName("btnLngCz");
		btnLngCz.setBounds(82, 141, 62, 23);
		getContentPane().add(btnLngCz);
		
		btnLngRu = new JButton("Ru");
		btnLngRu.setName("btnLngRu");
		btnLngRu.setBounds(154, 141, 62, 23);
		getContentPane().add(btnLngRu);
		
		lngButtons.put(btnLngCz.getName(), btnLngCz);
		lngButtons.put(btnLngEn.getName(), btnLngEn);
		lngButtons.put(btnLngRu.getName(), btnLngRu);
		
		btnOpenHistory = new JButton("Open History Window");
		btnOpenHistory.setName("btnOpenHistory");
		btnOpenHistory.setBounds(10, 187, 206, 36);
		getContentPane().add(btnOpenHistory);
	}
	
	public void updateUserName(String value) {
		String currentLabel = lblUserName.getText();
		String[] splitted = currentLabel.split(":");
		splitted[1] = value; 
		lblUserName.setText(String.join(": ", splitted));
	}
	
	public void setLanguageButton(String lng) {
		String lngBtnName = "btnLng" + lng;
		if(_activeLanguageButton != null) {
			_activeLanguageButton.setEnabled(true);
		}
		_activeLanguageButton = lngButtons.get(lngBtnName);
		_activeLanguageButton.setEnabled(false);
	}
	
	public String getLanguageNameFromButton(Object input) {
		JButton btn = (JButton) input;
		return btn != null ? btn.getText() : _activeLanguageButton.getText();
	}
}
