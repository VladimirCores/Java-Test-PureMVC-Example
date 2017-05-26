package app.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.UserVO;

public class UserProxy extends Proxy 
{
	static public String NAME = "UserProxy";
	
	public UserProxy() {
		super(NAME);
	}
	
	public void setUserName(String value) {
		user().name = value;
		user().id++;
	}
	
	public String getUserName() { return user().name; }
	public int getUserID() { return user().id; }
	
	public UserVO createDefaultUser() {
		UserVO result = new UserVO();
		result.name = "Default";
		result.age = 21;
		result.id = 0;
		return result;
	}

	private UserVO user() { return (UserVO)data; }
}
