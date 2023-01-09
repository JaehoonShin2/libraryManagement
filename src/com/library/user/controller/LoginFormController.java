package com.library.user.controller;

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController implements Initializable{

	@FXML private TextField _tf_id;
	@FXML private PasswordField _pf_pwd;
	@FXML private Button _btn_login;
	@FXML private Label _label_msg_login;
	@FXML private Hyperlink _hy_enroll, _hy_login;
	
	private static Logger logger = Logger.getLogger(LoginFormController.class);
	private Scene scene;
	
	private User login(User user) {
		return new UserService_Impl().select(user);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// __btn_login();
		// initialize : 페이지 로드 시 바로 실행되어야 하는 이벤트 
		// 다시 로그인 페이지로 돌아왔을 때 textfield 의 텍스트 값을 초기화
		_tf_id.setText(null);
		_pf_pwd.setText(null);
		_tf_id.setPromptText("아이디를 입력해주세요");
		_pf_pwd.setPromptText("비밀번호를 입력해주세요");
		
		_hy_enroll.setOnAction(e->{
			try {
				__hy_enroll();
				_tf_id.setText(null);
				_pf_pwd.setText(null);
			} catch (IOException e1) {
			}
		});
		
		_btn_login.setOnAction(e->{
			try {
				__btn_login();
				_tf_id.setText(null);
				_pf_pwd.setText(null);
			} catch (IOException e1) {
			}
		});
	}
	
	private void __btn_login() throws IOException {
		String id = _tf_id.getText();
		String pwd = _pf_pwd.getText();
		
		User loginUer = new User();
		loginUer.setUserId(id);
		loginUer.setUserPwd(pwd);
		
		ShareData.setUser(login(loginUer));
		if(ShareData.getUser() != null) {
			_label_msg_login.setText("");
			logger.info("로그인 성공");
			StringBuffer key = new StringBuffer();
			StringBuffer resource = new StringBuffer();
			if ( ShareData.getUser().getAdminYN().equals("Y")) {
				key.append("adminSidebar");
				resource.append("admin/adminSidebar");
				scene = ShareData.getScene(key.toString(), resource.toString());
			} 
			else if (ShareData.getUser().getAdminYN().equals("N")) {
				key.append("libraryView");
				resource.append("user/userSidebar");
			}
			ShareData.getStage().setScene(scene);
			ShareData.getStage().show();
			
		} else {
			_label_msg_login.setText("잘못된 아이디나 비밀번호를 입력하셨습니다.");
		}
	}

	private void __hy_enroll() throws IOException {
		scene = ShareData.getScene("enrollUser", "user/enrollUser");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
	}
}
