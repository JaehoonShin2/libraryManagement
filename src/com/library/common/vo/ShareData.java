package com.library.common.vo;

import java.io.IOException;
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
		loader.setLocation(ShareData.class.getResource("/view/"+resource+".fxml"));
			if(!getMap().containsKey(key)) {
				try {
					scene = new Scene(loader.load());
				} catch (IOException e) {
					logger.info(scene+" Scene 생성 오류");
				}
				getMap().put(key, scene);
				logger.info(key+" 생성");
			} else {
				logger.info(key + " : 해당 Scene 은 이미 맵에 존재합니다.");
			}
		return scene;
	}
	
}
