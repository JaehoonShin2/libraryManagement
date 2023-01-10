package com.library.common.vo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShareData {

	private static Stage stage;
	private static HashMap<String, Scene> map;
	private static Logger logger = Logger.getLogger(ShareData.class);
	private static User user;
	
	static {
		map = new HashMap<>();
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User u) {
		user = u;
	}

	public ShareData() {
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}

	public static HashMap<String, Scene> getMap() {
		return map;
	}

	public static void setMap(HashMap<String, Scene> SceneMap) {
		map = SceneMap;
	}
	
	public static Scene getScene(String key, String resource) {
	
		Scene scene = null;
		FXMLLoader loader = new FXMLLoader();
		String url = "resources/view/"+resource+".fxml";
		try {
			loader.setLocation(new File(url).toURI().toURL());
			if(!map.containsKey(key)) {
			scene = new Scene(loader.load());
			map.put(key, scene);
			} else {
				logger.info(key + " : 해당 Scene 은 이미 맵에 존재합니다.");
			}
		} catch (IOException e) {
			logger.info(scene+" Scene2 생성 오류");
		}
		return map.get(key);
	
	}
}
