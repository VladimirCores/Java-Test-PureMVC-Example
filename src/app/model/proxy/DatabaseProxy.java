package app.model.proxy;

import java.util.ArrayList;
import java.util.Date;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.DatabaseVO;
import app.model.vo.UserNameVO;
import app.model.vo.UserVO;
import services.DatabaseService;

public class DatabaseProxy extends Proxy 
{
	static public String NAME = "DatabaseProxy";
	/*
	static public String 
		NOTE_USER_SAVE_SUCCESS = "notification_database_user_save_success"
	,	NOTE_USER_SAVE_FAILURE = "notification_database_user_save_failure"	
	; */
	
	private DatabaseService service = DatabaseService.getInstance();
	
	public DatabaseProxy() {
		super(NAME, new DatabaseVO());
		try {
			service.initialize(((DatabaseVO)data).name);
			service.createTableFromClass(UserVO.class, "name");
			service.createTableFromClass(UserNameVO.class, "id");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Object> getHistoryOfNames() { ... }
	
	public void saveUser(UserVO value) {
		try {
			service.storeObjectToTable(value);
			this.sendNotification( DatabaseNotifications.USER_SAVE_SUCCESS );
		} catch(Exception e) {
			e.printStackTrace();
			this.sendNotification( DatabaseNotifications.USER_SAVE_FAILURE );
		}
	}
	
	public void userNameChanged( String name, int id ) { ... }
	public void updateUserParam( String param, Object value ) { ... }
	public UserVO retrieveUser( String name ) { ... }	
}


