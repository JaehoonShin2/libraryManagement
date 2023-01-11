package com.library.user.controller.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.library.common.vo.ShareData;
import com.library.common.vo.User;
import com.library.user.service.UserService_Impl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EnrollUserController implements Initializable {

	
	private static Logger logger = Logger.getLogger(EnrollUserController.class);
	@FXML private TextField _tf_id, _tf_userName, _tf_phone;
	@FXML private PasswordField _pf_pwd, _pf_check_pwd;
	@FXML private Label _label_idCheck, _label_pwd;
	@FXML private Button _btn_check_id, _btn_enroll_user, _btn_cancel;
    @FXML private RadioButton _radio_m, _radio_f;
	private String userId, userPwd, userName, phone, gender;
	private Boolean check_id_;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		check_id_ = false;
		_radio_f.setOnAction(e->{
			if(_radio_f.isSelected()) {
				_radio_m.setSelected(false);
			} else {
				_radio_m.setSelected(true);
			}
		});
		_radio_m.setOnAction(e->{
			if(_radio_m.isSelected()) {
				_radio_f.setSelected(false);
			} else {
				_radio_f.setSelected(true);
			}	
		});
		
	}
	
	@FXML public void __btn_check_id() {
		userId = _tf_id.getText();
		// 기존에 아이디가 사용되어있지 않았다면
			User user = new User();
			user.setUserId(userId);
			User u = new UserService_Impl().select(user);
			if( u == null ) {
				logger.info("사용가능");
				_label_idCheck.setText("사용 가능한 아이디 입니다.");
				check_id_ = true;
			} else {
				logger.info("사용불가");
				_label_idCheck.setText("이미 사용중인 아이디 입니다.");
				check_id_ = false;
			}
		}
	
	@FXML public void __btn_enroll_user() throws Exception {

		userPwd = _pf_pwd.getText();
		String userCheckpwd = _pf_check_pwd.getText();
		userName = _tf_userName.getText();
		phone = _tf_phone.getText();
		if(_radio_m.isSelected()) {
			gender = "M";
		} else if (_radio_f.isSelected()) {
			gender = "F";
		}
		
		if(userPwd.equals(userCheckpwd)
				&& check_id_ 
				&& !userName.equals("")
				&& userName != null
				&& gender != null ) // && 정규표현식 결과가 맞다면 
			{	
			User user = new UserService_Impl().insert(new User(userId, userPwd, userName, phone, gender));
			logger.info("아이디 생성");
			Scene scene = ShareData.getScene("loginform", "common/loginform");
			ShareData.getStage().setScene(scene);
			ShareData.getStage().show();
			}
		else {
			_label_pwd.setText("비밀번호가 동일하지 않습니다.");
		}
	}
	
	@FXML private void __btn_cancel() throws IOException {
		Scene scene = ShareData.getScene("loginform", "common/loginform");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
	}
	
}
