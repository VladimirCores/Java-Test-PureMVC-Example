package app.view.components;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow extends JFrame 
{
	public JLabel lblUserName;
	public JButton btnSetUserName;
	public JTextField tfUserName;

	private String _name = "main";
	
	public MainWindow() {
		initialize();
	}
	
	public String getName(){ return _name; }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 248, 213);
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
		
		lblUserName = new JLabel("Current User Name : ");
		lblUserName.setName("lblUserName");
		lblUserName.setBounds(10, 11, 212, 14);
		this.getContentPane().add(lblUserName);
	}
}
