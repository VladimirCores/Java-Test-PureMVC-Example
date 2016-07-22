package services;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class LocalizationService {

	static private LocalizationService _instance = new LocalizationService();
	static public LocalizationService getInstance() { return _instance; }
	private LocalizationService() { }
	
	public <T extends Object> T getLocaleForClassFromPath(String path, Class<T> className) throws Exception {
		return new Gson().fromJson(loadFile(path), className);
	}
	
	private JsonReader loadFile(String path) throws Exception
	{
		System.out.println("Loading Locale from: " + path);
		return new JsonReader(new FileReader(path));
	}

}
