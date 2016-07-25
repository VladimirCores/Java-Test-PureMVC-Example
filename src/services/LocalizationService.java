package services;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
		return new JsonReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
	}

}
