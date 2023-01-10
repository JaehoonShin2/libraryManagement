package com.library.rental.controller.admin;

import java.net.URL;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.library.common.vo.Rental;
import com.library.common.vo.ShareData;
import com.library.rental.service.RentalService_impl;

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

public class AdminRentalController implements Initializable{

	@FXML
    private Button _btn_rent_search;
    @FXML
    private TextField _tf_rent_keyword;
    @FXML
    private Label _label_id;
    @FXML
    private Hyperlink _hy_logout, _hy_mypage;
    @FXML
    private TableView<Rental> _tv_rent;
    @FXML
    private TableColumn<Rental, String> _tc_rent_brlNo, _tc_rent_title, _tc_rent_userId, _tc_rent_rentdate, _tc_rent_duedate;
    @FXML
    private DialogPane _dia_book;
    @FXML
    private Label _label_dia_title, _label_dia_page, _label_dia_author, _label_dia_price, _label_dia_translator, _label_dia_supplement,  _label_dia_rentYN;
    @FXML
    private Button _btn_dia_rent, _btn_dia_cancel;
    @FXML
    private DialogPane _dia_mypage;
    @FXML
    private PasswordField _pf_pwd;
    @FXML
    private Button _btn_dia_ok;
    @FXML
    private Button _btn_dia_pwd_cancel;
    @FXML
    private TableView<Rental> _tv_return;
    @FXML
    private TableColumn<Rental, String> _tc_return_brlNo, _tc_return_title, _tc_return_userId, _tc_return_rentdate, _tc_return_returnDate;
    @FXML
    private Button _btn_return_search;
    @FXML
    private TextField _tf_return_keyword;
    @FXML
    private ComboBox<String> _cb_rent, _cb_return;
    

    private static Logger logger = Logger.getLogger(AdminRentalController.class);
	private String isbn;
	private int brlNo;
	private ObservableList<Rental> rentallist;
	private ObservableList<Rental> returnlist;
	private Alert alert;
	private HashMap<String, String> map;
	private String key_type;
	

	
	private void selectRental(Rental rental) {
		rental.setStatus("N");
		logger.info(rental.toString());
		ArrayList<Rental> list = new RentalService_impl().selectList(rental);
		rentallist = FXCollections.observableArrayList();
		for(Rental r : list) {
			System.out.println(r.toString());
			rentallist.add(r);
		}
		_tv_rent.setItems(rentallist);
		tableRow_Rental();
	}
	
	private void selectReturn(Rental rental) {
		rental.setStatus("Y");
		logger.info(rental.toString());
		ArrayList<Rental> list = new RentalService_impl().selectList(rental);
		returnlist = FXCollections.observableArrayList();
		for(Rental r : list) {
			System.out.println(r.toString());
			returnlist.add(r);
		}
		_tv_return.setItems(returnlist);
		tableRow_Return();
	}

	private void tableRow_Rental() {
			
			_tv_rent.setRowFactory( e -> {
				
				TableRow<Rental> row = new TableRow<>();
				row.setOnMouseClicked(e1 -> {
					Rental rental = row.getItem();
					if(rental == null) {
						return;
					}
					if(e1.getButton().equals(MouseButton.PRIMARY)) {
						if(e1.getClickCount() == 2) {
							
						}
					}
				});
				return row;
			});
		}
	
	private void tableRow_Return() {
		_tv_return.setRowFactory( e -> {
			TableRow<Rental> row = new TableRow<>();
			row.setOnMouseClicked(e1 -> {
				Rental rental = row.getItem();
				if(rental == null) {
					return;
				}
				if(e1.getButton().equals(MouseButton.PRIMARY)) {
					if(e1.getClickCount() == 2) {
						
					}
				}
			});
			return row;
		});
	}
	
	private void __rent_search() {
		key_type = map.get(_cb_rent.getSelectionModel().getSelectedItem().toString());
		String keyword = _tf_rent_keyword.getText();
		Rental rental = new Rental();
		if(key_type.equals("userId")) {
			rental.setUserId(keyword);
		} else if (key_type.equals("title")) {
			rental.setTitle(keyword);
		}
		selectRental(rental);
		
		
	}
	
	private void __return_search() {
		key_type = map.get(_cb_return.getSelectionModel().getSelectedItem().toString());
		String keyword = _tf_return_keyword.getText();
		Rental rental = new Rental();
		if(key_type.equals("userId")) {
			rental.setUserId(keyword);
		} else if (key_type.equals("title")) {
			rental.setTitle(keyword);
		}
		selectReturn(rental);
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_label_id.setText(ShareData.getUser().getUserId() + "님");
		map = new HashMap<>();
		ObservableList<String> list = FXCollections.observableArrayList();
		map.put("유저 아이디로 검색", "userId");
		map.put("책 이름으로 검색", "title");
		for (String s : map.keySet()) {
			list.add(s);
		}
		_cb_rent.setItems(list);
		_cb_return.setItems(list);
		
		_hy_logout.setOnAction(e->{
			__hy_logout();
		});
		
		setRentalCellvalueFactory(_tc_rent_brlNo, "rentalNo");
		setRentalCellvalueFactory(_tc_rent_title, "title");
		setRentalCellvalueFactory(_tc_rent_userId, "userId");
		setRentalCellvalueFactory(_tc_rent_rentdate, "rentalDate");
		setRentalCellvalueFactory(_tc_rent_duedate, "dueDate");
		
		setRentalCellvalueFactory(_tc_return_brlNo, "rentalNo");
		setRentalCellvalueFactory(_tc_return_title, "title");
		setRentalCellvalueFactory(_tc_return_userId, "userId");
		setRentalCellvalueFactory(_tc_return_rentdate, "rentalDate");
		setRentalCellvalueFactory(_tc_return_returnDate, "returnDate");
		
		selectRental(new Rental());
		selectReturn(new Rental());
		
		_btn_rent_search.setOnAction(e->{
			__rent_search();
		});
		
		_tf_rent_keyword.setOnAction(e->{
			__rent_search();
		});
		
		_btn_return_search.setOnAction(e->{
			__return_search();
		});
		
		_tf_return_keyword.setOnAction(e->{
			__return_search();
		});
	}

	// 로그아웃
	private void __hy_logout() {
		Scene scene = ShareData.getScene("loginform", "user/loginform");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
		ShareData.setUser(null);
	}
	
	private void setRentalCellvalueFactory(TableColumn<Rental, String> tc, String col) {
		tc.setCellValueFactory(
				new PropertyValueFactory<Rental, String>(col));
	}
}
