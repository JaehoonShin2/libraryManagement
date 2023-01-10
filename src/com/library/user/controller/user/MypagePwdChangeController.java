package com.library.user.controller.user;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.library.common.vo.ShareData;
import com.library.common.vo.User;
import com.library.user.service.UserService_Impl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class MypagePwdChangeController implements Initializable {


    @FXML
    private Button _btn_update, _btn_cancel;
    @FXML
    private PasswordField _pf_pwd,  _pf_check_pwd;
    private Alert alert;
    
    private void __btn_update() {
    	String userPwd = _pf_pwd.getText();
    	String checkPwd = _pf_check_pwd.getText();
    	
    	if(userPwd.equals(checkPwd)) {
    		User temp = new User();
    		temp.setUserNo(ShareData.getUser().getUserNo());
    		temp.setUserPwd(userPwd);
    		User user = new UserService_Impl().update(temp);
    		ShareData.setUser(user);
    		Scene scene = ShareData.getScene("loginform", "common/loginform");
			ShareData.getStage().setScene(scene);
			ShareData.getStage().show();
    	} else {
    		alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("비밀번호가 일치하지 않습니다.");
    		alert.setTitle("경고창");
    		alert.showAndWait();
    	}
    }
    
    private void __btn_cancel() {
    	Scene scene = ShareData.getScene("libraryForm", "user/libraryForm");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_pf_pwd.setText(null);
		_pf_check_pwd.setText(null);
		_btn_cancel.setOnAction(e1->{
			__btn_cancel();
		});
		
		_btn_update.setOnAction(e1->{
			__btn_update();
		});
		
	}
}
