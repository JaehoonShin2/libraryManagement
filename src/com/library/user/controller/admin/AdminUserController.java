package com.library.user.controller.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.library.common.vo.Rental;
import com.library.common.vo.ShareData;
import com.library.common.vo.User;
import com.library.rental.service.RentalService_impl;
import com.library.user.service.UserService_Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class AdminUserController implements Initializable{

	@FXML private Button _btn_user_search;
	@FXML private TextField _tf_user_keyword;
	@FXML private Label _label_id;
	@FXML private Hyperlink _hy_logout, _hy_mypage;
	@FXML private TableView<User> _tv_user;
	@FXML private TableColumn<User, String> _tc_userNo, _tc_userId, _tc_userName, _tc_phone, _tc_point, _tc_rentCount, _tc_maxRentalCount;
	@FXML private ComboBox<String> _cb;
	@FXML private DialogPane _dia_mypage;
	@FXML private PasswordField _pf_pwd;
	@FXML private Button _btn_dia_pwd_ok, _btn_dia_pwd_cancel;
	@FXML private DialogPane _dia_user;
	@FXML private Button _btn_update, _btn_cancel;
	@FXML private TextField _tf_id, _tf_userName, _tf_phone, _tf_point, _tf_maxRentalCount;
	@FXML private PasswordField _pf_my_pwd;
	@FXML private Button _btn_delete;
	
	private Logger logger = Logger.getLogger(getClass());
	private HashMap<String, String> map;
	private String key_type;
	private ObservableList<User> ulist;
	private Alert alert;
	
	private void selectList(User user) {
		ArrayList<User> list = new UserService_Impl().selectList(user);
		if(list != null) {
			ulist = FXCollections.observableArrayList();
			for(User u : list) {
				ulist.add(u);
			}
			_tv_user.setItems(ulist);
			tableRow_User();
		}
	}
	
	private void __user_search() {
		key_type = map.get(_cb.getSelectionModel().getSelectedItem().toString());
		String keyword = _tf_user_keyword.getText();
		User user = new User();
		if(key_type.equals("userId")) {
			user.setUserId(keyword);
		} else {
			user.setUserName(keyword);
		}
		logger.info(user.toString());
		selectList(user);
	}
	
	private void tableRow_User() {
		_tv_user.setRowFactory( e -> {
			TableRow<User> row = new TableRow<>();
			row.setOnMouseClicked(e1 -> {
				User user = row.getItem();
				if(user == null) {
					return;
				}
				if(e1.getButton().equals(MouseButton.PRIMARY)) {
					if(e1.getClickCount() == 2) {
						_dia_user.setVisible(true);
						setTextFieldText(_tf_id, user.getUserId());
						_pf_my_pwd.setText(user.getUserPwd());
						setTextFieldText(_tf_userName, user.getUserName());
						setTextFieldText(_tf_phone, user.getPhone());
						setTextFieldText(_tf_point, String.valueOf(user.getPoint()));
						setTextFieldText(_tf_maxRentalCount, String.valueOf(user.getMaxRentalCount()));
						
						_btn_update.setOnAction( e2 -> {
							
							User nu = new User();
							nu.setUserNo(user.getUserNo());
							nu.setUserPwd(_pf_my_pwd.getText());
							nu.setUserName(_tf_userName.getText());
							nu.setPhone(_tf_phone.getText());
							nu.setPoint(Integer.parseInt(_tf_point.getText()));
							nu.setMaxRentalCount(Integer.parseInt(_tf_maxRentalCount.getText()));
							
							logger.info(nu.toString());
							
							User nUser = new UserService_Impl().update(nu);
							
							logger.info(nUser.toString());
							_dia_user.setVisible(false);
							selectList(new User());
							
						});
						
						_btn_cancel.setOnAction( e2 -> {
							_dia_user.setVisible(false);
						});
						
						_btn_delete.setOnAction(e2->{
							
							alert = new Alert(AlertType.CONFIRMATION);
							alert.setHeaderText("정말로 탈퇴를 진행하시겠습니까?");
							alert.setContentText("회원 탈퇴 후에는 해당 서비스를 이용하실 수 없습니다");
							alert.setTitle("경고창");
							Optional<ButtonType> btType = alert.showAndWait();
								
							if(btType.get() == ButtonType.OK) {
								// 현재 도서의 대출권수가 0 일때
								int cnt = new RentalService_impl().selectRentalCount(new Rental(user.getUserNo()));
								if( cnt == 0){
									User u = new UserService_Impl().delete(user);
									if(u == null) {
										alert.close();
										_dia_user.setVisible(false);
										selectList(new User());
										}
								} else {
									alert = new Alert(AlertType.CONFIRMATION);
									alert.setHeaderText("안내창");
									alert.setContentText("대출중인 도서를 모두 반납한 후에 처리해주세요");
									alert.setTitle("확인");
									alert.showAndWait();
								}
							} else {
								alert.close();
							}
						});
							
						}
					}
				});
				return row;
			});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_label_id.setText(ShareData.getUser().getUserId() + "님");
		map = new HashMap<>();
		ObservableList<String> list = FXCollections.observableArrayList();
		map.put("아이디로 검색", "userId");
		map.put("이름으로 검색", "userName");
		for (String s : map.keySet()) {
			list.add(s);
		}
		_cb.setItems(list);                                                                                                                                                                                                                                                                                                                                                                                     
		_dia_user.setVisible(false);
		
		setUserCellvalueFactory(_tc_userNo, "userNo");
		setUserCellvalueFactory(_tc_userId, "userId");
		setUserCellvalueFactory(_tc_userName, "userName");
		setUserCellvalueFactory(_tc_phone, "phone");
		setUserCellvalueFactory(_tc_point, "point");
		setUserCellvalueFactory(_tc_rentCount, "rentalCount");
		setUserCellvalueFactory(_tc_maxRentalCount, "maxRentalCount");
		
		selectList(new User());
		
		_btn_user_search.setOnAction(e->{
			__user_search();
		});
		
		_tf_user_keyword.setOnAction(e->{
			__user_search();
		});
		
		
		_hy_logout.setOnAction(e->{
			__hy_logout();
		});
	}

	private void setUserCellvalueFactory(TableColumn<User, String> tc, String col) {
		tc.setCellValueFactory(
				new PropertyValueFactory<User, String>(col));
	}
	
	private void setTextFieldText(TextField tf, String s) {
		tf.setText(s);
	}
	
	
	// 로그아웃
	private void __hy_logout() {
		Scene scene = ShareData.getScene("loginform", "user/loginform");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
		ShareData.setUser(null);
	}
	
}
