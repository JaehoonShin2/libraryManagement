package com.library.common.view;

import java.util.ArrayList;

import com.library.common.vo.ShareData;
import com.library.common.vo.User;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ShareData.setStage(primaryStage);
		ShareData.getStage().setTitle("도서관리 프로그램");
		String key = "loginForm";
		String resource = "common/loginForm";
		ShareData.getScene(key, resource);
		
	}
	
	public static void main(String[] args) { launch(args); }
	
}
