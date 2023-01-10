package com.library.user.controller.user;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MypageSidebarController implements Initializable {

 	@FXML
    private AnchorPane _ap_sidebar;
    @FXML
    private BorderPane _bp_sidebar;
    @FXML
    private Hyperlink _hy_mypage, _hy_pwdChange, _hy_delete;
    private Logger logger = Logger.getLogger(MypagePwdChangeController.class);

	private void __hy_mypage() {
		loadPage("mypage_userUpdate");
	}
	private void __hy_pwdChange() {
		loadPage("mypage_pwdChange");
	}
		

	private void loadPage(String page) {
		try {
			String s = "resources/view/user/"+ page + ".fxml";
			logger.info(s);
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(new File(s).toURI().toURL());
	        _bp_sidebar.setCenter(loader.load());
	        logger.info(loader);
	    } catch (IOException ex) {
	    	Logger.getLogger(MypageSidebarController.class.getName()).log(Level.ALL, null, ex);
	    }
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		__hy_mypage();
		
		_hy_mypage.setOnAction(e->{
			__hy_mypage();
		});
		_hy_pwdChange.setOnAction(e->{
			__hy_pwdChange();
		});

	}
	
}
