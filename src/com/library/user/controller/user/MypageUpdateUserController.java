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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MypageUpdateUserController implements Initializable {
	
	@FXML
    private Button _btn_update;
    @FXML
    private Button _btn_cancel;
    @FXML
    private TextField _tf_id, _tf_userName, _tf_phone, _tf_point;
    @FXML
    private Button _btn_delete;
    private String phone;
	private Alert alert;
    
    
    private void __btn_cancel() {
    	Scene scene = ShareData.getScene("libraryForm", "user/libraryForm");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
    }

    private void __btn_update() {
    	
		phone = _tf_phone.getText();
		User temp = new User();
		temp.setUserNo(ShareData.getUser().getUserNo());
		temp.setPhone(phone);
	 	User user = new UserService_Impl().update(temp);
		ShareData.setUser(user);
		Scene scene = ShareData.getScene("libraryForm", "user/libraryForm");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
    }

    private void __btn_delete() {
    	
    	alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("정말로 탈퇴를 진행하시겠습니까?");
		alert.setContentText("회원 탈퇴 후에는 해당 서비스를 이용하실 수 없습니다");
		alert.setTitle("경고창");
		Optional<ButtonType> btType = alert.showAndWait();
		
		if(btType.get() == ButtonType.OK) {
			new UserService_Impl().delete(ShareData.getUser());
			alert.close();
			Scene scene = ShareData.getScene("loginform", "common/loginform");
			ShareData.getStage().setScene(scene);
			ShareData.getStage().show();
		} else {
			alert.close();
		}
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		_tf_id.setText(ShareData.getUser().getUserId());
		_tf_userName.setText(ShareData.getUser().getUserName());
		_tf_phone.setText(ShareData.getUser().getPhone());
	    _tf_point.setText(String.valueOf(ShareData.getUser().getPoint()));
		
		
		_btn_cancel.setOnAction(e1->{
			__btn_cancel();
		});
		
		_btn_delete.setOnAction(e1->{
			__btn_delete();
		});

		_btn_update.setOnAction(e1->{
			__btn_update();
		});
	}
	
}
