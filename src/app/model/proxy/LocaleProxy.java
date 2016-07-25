package app.model.proxy;


import java.util.Map;

import org.puremvc.java.patterns.proxy.Proxy;

import app.model.vo.LocaleVO;
import consts.Languages;
import services.LocalizationService;

public class LocaleProxy extends Proxy {

	static public String NAME = "LocaleProxy";
	
	private String[] path = new String[] {"assets", "xml", "locale", "lang", "locale.json"};
	
	private LocalizationService _localeServis = LocalizationService.getInstance();
	
	public LocaleProxy() {
		super(NAME);
		changeLanguage(Languages.DEFAULT);
	}

	public void changeLanguage(String name) {
		path[3] = name;
		try {
			this.data = _localeServis.getLocaleForClassFromPath(String.join("\\", path), LocaleVO.class);
			System.out.println("Load Locale Success!");
		} catch (Exception e) {
			this.data = null;
			System.out.println("Load Locale Failed!" + e.getMessage());
		}
	}

	public Map<String, String> getItemsLocalizationForWindow(String name) {
		LocaleVO locale = (LocaleVO) this.data; 
		return locale.windows.get(name);
	}
	
	public String getCurrentLanguage() {
		return path[3];
	}
	
}
