package com.library.common.view;

import java.util.ArrayList;

import com.library.book.controller.BookController;
import com.library.book.service.BookService_Impl;
import com.library.common.vo.Book;
import com.library.common.vo.ShareData;
import com.library.common.vo.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ShareData.setStage(primaryStage);
		ShareData.getStage().setTitle("도서관리 프로그램");
		String key = "loginform";
		String resource = "common/loginform";
		Scene scene = ShareData.getScene(key, resource);
		
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
		
	}
	
	public static void main(String[] args) { 
		
		launch(args);
		
	}
	
}
