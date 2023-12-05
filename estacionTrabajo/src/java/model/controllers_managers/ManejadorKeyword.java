package model.controllers_managers;

import java.util.HashMap;
import java.util.Map;
import model.utils.Strings;
import model.clases.Keyword;

public class ManejadorKeyword {
	private static Strings utils = new Strings();
	private static ManejadorKeyword instance;
	private static Map<String, Keyword> keys;
	
	private ManejadorKeyword() {
		keys = new HashMap<String, Keyword>();
	}
	
	public static ManejadorKeyword getInstance(){
		if (instance == null) {
			instance = new ManejadorKeyword();
		}
		return instance;
	}
	
	public Map<String, Keyword> getKeywords(){
		return keys;
	}
	
	public void addKeyword(Keyword key) {
		keys.put(utils.unico(key.getClave()), key);
	}
	
	public Keyword getKeyword(String key) {
		return keys.get(utils.unico(key));
	}

}
