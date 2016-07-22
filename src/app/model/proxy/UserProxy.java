package app.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.UserVO;

public class UserProxy extends Proxy {

	static public String NAME = "UserProxy";
	
	public UserProxy() {
		super(NAME, new UserVO());
	}
	
	public void setUserName(String value) {
		((UserVO)data).name = value;
	}
}
