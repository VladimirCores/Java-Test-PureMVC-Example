package app.view.components;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.OSUtils;

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
		
		int langButtons = 3;
		int margin = 8;
		int width = 296;
		int fieldWidth = width - margin * 2;
		int langButtonWidth = (fieldWidth - margin * (langButtons - 1)) / langButtons;
		int langButtonHeight = 36;
		int langBtnPositionX = margin;
		int langBtnPositionY = 136;
		
		this.setBounds(100, 100, width, 264);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		lblUserName = new JLabel("Current User Name: ");
		lblUserName.setName("lblUserName");
		lblUserName.setBounds(margin, 8, fieldWidth, 16);
		this.getContentPane().add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setName("tfUserName");
//		tfUserName.setToolTipText("User Name");
		tfUserName.setBounds(margin, 32, fieldWidth, 36);
		this.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		btnSetUserName = new JButton("Set User Name");
		btnSetUserName.setName("btnSetUserName");
		btnSetUserName.setBounds(margin, 78, fieldWidth, 36);
		this.getContentPane().add(btnSetUserName);
		
		btnLngEn = new JButton("En");
		btnLngEn.setName("btnLngEN");
		btnLngEn.setBounds(langBtnPositionX, langBtnPositionY, langButtonWidth, langButtonHeight);
		getContentPane().add(btnLngEn);
				
		langBtnPositionX += langButtonWidth + margin;
		btnLngCz = new JButton("Cz");
		btnLngCz.setName("btnLngCZ");
		btnLngCz.setBounds(langBtnPositionX, langBtnPositionY, langButtonWidth, langButtonHeight);
		getContentPane().add(btnLngCz);
		
		langBtnPositionX += langButtonWidth + margin;
		btnLngRu = new JButton("Ru");
		btnLngRu.setName("btnLngRU");
		btnLngRu.setBounds(langBtnPositionX, langBtnPositionY, langButtonWidth, langButtonHeight);
		getContentPane().add(btnLngRu);
		
		lngButtons.put(btnLngCz.getName(), btnLngCz);
		lngButtons.put(btnLngEn.getName(), btnLngEn);
		lngButtons.put(btnLngRu.getName(), btnLngRu);
		
		btnOpenHistory = new JButton("Open History Window");
		btnOpenHistory.setName("btnOpenHistory");
		btnOpenHistory.setBounds(margin, 184, fieldWidth, 36);
		getContentPane().add(btnOpenHistory);
	}
	
	public void highlightSaveFailure() {
		
	}
	
	public void highlightSaveSuccess() {
		
	}
	
	public void updateUserName(String value) {
		String currentLabel = lblUserName.getText();
		String[] splitted = currentLabel.split(":");
		splitted[1] = value; 
		lblUserName.setText(String.join(": ", splitted));
	}
	
	public void clearUserNameInput() {
		tfUserName.setText("");
	}
	
	public void setLanguageButton(String lng) {
		String lngBtnName = "btnLng" + lng.toUpperCase();
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
