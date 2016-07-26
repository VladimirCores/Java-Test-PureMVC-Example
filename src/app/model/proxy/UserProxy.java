package app.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.UserVO;

public class UserProxy extends Proxy {

	static public String NAME = "UserProxy";
	
	public UserProxy() {
		super(NAME);
	}
	
	public void setUserName(String value) {
		((UserVO)data).name = value;
		((UserVO)data).id++;
	}
	
	public String getUserName() {
		return ((UserVO)data).name;
	}
	
	public int getUserID() {
		return ((UserVO)data).id;
	}
	
	public UserVO defaultUser() {
		UserVO result = new UserVO();
		result.name = "Default";
		result.age = 21;
		result.id = 0;
		return result;
	}
}
