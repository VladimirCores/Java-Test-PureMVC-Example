package app.model.proxy;

import java.util.ArrayList;
import java.util.Date;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.DatabaseVO;
import app.model.vo.UserNameVO;
import app.model.vo.UserVO;
import consts.notifications.DatabaseNotifications;
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
	
	public ArrayList<Object> getHistoryOfNames() {
 		try {
 			return service.retrieveObjectWithCriteria(UserNameVO.class, "");
 		} catch (Exception e) {
			e.printStackTrace();
 			return null;
 		}
 	}
	
	public void saveUser(UserVO value) 
	{
		try {
			service.storeObjectToTable(value);
			this.sendNotification( DatabaseNotifications.USER_SAVE_SUCCESS );
		} catch(Exception e) {
			e.printStackTrace();
			this.sendNotification( DatabaseNotifications.USER_SAVE_FAILURE );
		}
	}
	
	public void userNameChanged(String name, int id) 
	{
 		updateUserParam("name", name);
 		updateUserParam("id", id);
 		UserNameVO userNameVO = new UserNameVO();
 		userNameVO.value = name;
 		userNameVO.date = new Date().getTime();
 		userNameVO.id = id;
 		try { 
 			service.storeObjectToTable(userNameVO);
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
	public void updateUserParam(String param, Object value) 
	{
 		try {
 			String criteria = DatabaseService.QUERY_LIMIT + "1";
 			service.updateObjectParams(
 				UserVO.class 
 			,	new String[]{param} 
 			,	new Object[]{value} 
 			,	criteria
 			);
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public UserVO retrieveUser() 
 	{
 		String criteria = DatabaseService.QUERY_LIMIT + "1";
 		UserVO result = null;
 		try {
 			ArrayList<Object> received = service.retrieveObjectWithCriteria(UserVO.class, criteria);
 			if(received.size() > 0) {
 				result = (UserVO)service.retrieveObjectWithCriteria(UserVO.class, criteria).get(0);
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return result;
 	}
}


