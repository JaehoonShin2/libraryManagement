package com.library.book.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.library.book.service.BookService_Impl;
import com.library.common.vo.Book;
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
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class BookController implements Initializable {

	@FXML private TextField _tf_keyword;
	@FXML private Button _btn_search, _btn_dia_rent, _btn_dia_cancel, _btn_dia_ok, _btn_dia_pwd_cancel;
	@FXML private Hyperlink _hy_mypage, _hy_logout;
	@FXML private DialogPane _dia_book, _dia_mypage;
    @FXML private Label _label_dia_title, _label_dia_page, _label_dia_author, _label_dia_price, _label_dia_translator, _label_dia_supplement, _label_dia_rentYN;
	@FXML private Label _label_id, _label_point;
	@FXML private TableColumn<Book, String> _tc_isbn,_tc_title,_tc_page, _tc_price, _tc_author, _tc_publisher, _tc_rentYN;
	@FXML private TableView<Book> _tv_book;
	@FXML private TableView<Rental> _tv_rent;
    @FXML private TableColumn<Rental, String> _tc_rent_title, _tc_rent_rentdate, _tc_rent_duedate;
    @FXML private PasswordField _pf_pwd;
    @FXML private ComboBox<String> _cb;
    
    private static Logger logger = Logger.getLogger(BookController.class);
	private String isbn;
	private int rentalNo;
	private ObservableList<Book> blist;
	private ObservableList<Rental> rlist;
	private Alert alert;
	
	public void setComboBox() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("title");
		list.add("author");
		_cb.setItems(list);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		_dia_book.setVisible(false);
//		_dia_mypage.setVisible(false);
		setComboBox();
		_label_id.setText(ShareData.getUser().getUserId() + "님");
		setLabelPoint(_label_point, ShareData.getUser().getPoint());
		
		setBookCellvalueFactory(_tc_isbn, "isbn");
		setBookCellvalueFactory(_tc_title, "title");
		setBookCellvalueFactory(_tc_page, "page");
		setBookCellvalueFactory(_tc_price, "price");
		setBookCellvalueFactory(_tc_author, "author");
		setBookCellvalueFactory(_tc_publisher, "publisher");
		setBookCellvalueFactory(_tc_rentYN, "rentYN");

		setRentalCellvalueFactory(_tc_rent_title, "title");
		setRentalCellvalueFactory(_tc_rent_rentdate, "rentalDate");
		setRentalCellvalueFactory(_tc_rent_duedate, "dueDate");
		
		selectRentalList(new Rental(ShareData.getUser().getUserNo()));
		
		
		search();
		
		_hy_mypage.setOnAction(e->{ __hy_mypage(); });
		_hy_logout.setOnAction(e->{ __hy_logout(); });
		
	}
	
	public void search() {
		_btn_search.setOnAction(e->{
			selectBookList(new Book(_tf_keyword.getText()));
		});
		
		_tf_keyword.setOnAction(e->{
			selectBookList(new Book(_tf_keyword.getText()));
		});
	}

	// 마이페이지
	private void __hy_mypage() {
		_dia_mypage.setVisible(true);
		
		// 비밀번호가 일치한다면 마이페이지로 이동
		_btn_dia_ok.setOnAction(e->{
			
			String userPwd = _pf_pwd.getText();
			// 만약 비밀번호가 일치한다면
			if(ShareData.getUser().getUserPwd().equals(userPwd)) {
				String key = "myPage_sidebar";
				String resource = "user/mypage_sidebar";
				Scene scene = ShareData.getScene(key, resource);
				ShareData.getStage().setScene(scene);
				ShareData.getStage().show();
				_dia_mypage.setVisible(false);
				_pf_pwd.setText(null);
			} else {
				alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				alert.setTitle("안내");
				alert.showAndWait();
			}
		});
		
		_btn_dia_pwd_cancel.setOnAction(e->{
			_dia_mypage.setVisible(false);
		});
		
	}
	// 로그아웃
	private void __hy_logout() {
		String key = "loginform";
		String resource = "common/loginform";
		Scene scene = ShareData.getScene(key, resource);
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
		ShareData.setUser(null);
	}
	
	private void tableRow_Book() {
		
		_tv_book.setRowFactory( e -> {
			
				TableRow<Book> row = new TableRow<>();
				row.setOnMouseClicked(e1 -> {
					Book book = row.getItem();
					if(book == null) {
						return;
					}
					if(e1.getButton().equals(MouseButton.PRIMARY)) {
						
						if(e1.getClickCount() == 2) {
							Book b = new BookService_Impl().select(book);
							if(b != null) {
								_dia_book.setVisible(true);
								setLabelText(_label_dia_title, b.getTitle());
								setLabelText(_label_dia_author, b.getAuthor());
								setLabelText(_label_dia_page, String.valueOf(b.getPage()));;
								setLabelText(_label_dia_price, String.valueOf(b.getPrice()));;
								setLabelText(_label_dia_supplement, b.getPublisher());;
								setLabelText(_label_dia_translator, b.getTranslator());
								setLabelText(_label_dia_rentYN, b.getRentYN());
								
								if(_label_dia_rentYN.getText().equals("Y")) {
									_btn_dia_rent.setDisable(false);
								} else {
									_btn_dia_rent.setDisable(true);
								}
								
								_btn_dia_rent.setOnAction( e2 -> {
									int cnt = new RentalService_impl().selectRentalCount(
												new Rental(ShareData.getUser().getUserNo())
											);
									
									// rent여부가 Y이고, 현재 유저의 대출 권수가 유저에게 주어진 맥수 권수보다 작을 때
									if(b.getRentYN().equals("Y") && 
										ShareData.getUser().getMaxRentalCount() > cnt){
										Rental r = new RentalService_impl().insert(
												new Rental(ShareData.getUser().getUserNo(), b.getIsbn()));
										if(r != null) {
											// dialog 창을 닫는다.
											_dia_book.setVisible(false);
											// rental 객체에 유저번호를 주입한 뒤 다시 한 번 rentalList를 조회한다.
											selectRentalList(new Rental(ShareData.getUser().getUserNo()));
											selectBookList(new Book(_tf_keyword.getText()));
										}
									} else {
										alert = new Alert(AlertType.CONFIRMATION);
										alert.setHeaderText("안내창");
										alert.setContentText("대출 한계 권수를 초과하셨습니다.");
										alert.setTitle("확인");
										alert.showAndWait();
									}
								});
								_btn_dia_cancel.setOnAction( e3 -> {
									_dia_book.setVisible(false);
								});
							}
						}
					}
				});
				return row;
			});
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
						alert = new Alert(AlertType.CONFIRMATION);
						alert.setHeaderText("이 책을 반납하시겠습니까?");
						alert.setTitle("안내");
						Optional<ButtonType> btType = alert.showAndWait();
						if(btType.get() == ButtonType.OK) {
							new RentalService_impl().update(rental);
							
							selectRentalList(new Rental(ShareData.getUser().getUserNo()));
							// 대출이 이루어지고 나면, 테이블 뷰에 담긴 book 리스트의 rent 여부 역시 업데이트가 이루어져야 한다.
							selectBookList(new Book(_tf_keyword.getText()));
							ShareData.setUser(new UserService_Impl().select(ShareData.getUser()));
							setLabelPoint(_label_point, ShareData.getUser().getPoint());
							alert.close();
						} else {
							alert.close();
						}
						
					}
				}
			});
			return row;
		});
	}
	
	private void setBookCellvalueFactory(TableColumn<Book, String> tc, String col) {
		tc.setCellValueFactory(
				new PropertyValueFactory<Book, String>(col));
	}
	
	private void setRentalCellvalueFactory(TableColumn<Rental, String> tc, String col) {
		tc.setCellValueFactory(
				new PropertyValueFactory<Rental, String>(col));
	}
	
	private void setLabelText(Label label, String s) {
		label.setText(s);
	}
	
	private void setLabelPoint(Label label, int point) {
		label.setText(ShareData.getUser().getPoint() + "점");
	}

	public void selectBookList(Book book) {
		ArrayList<Book> list = new BookService_Impl().selectList(book);
		blist = FXCollections.observableArrayList();
		for(Book b : list) {
			blist.add(b);
		}
		_tv_book.setItems(blist);
		tableRow_Book();
		
	}

	private void selectRentalList(Rental rental) {
		ArrayList<Rental> list = new RentalService_impl().selectList(rental);
		rlist = FXCollections.observableArrayList();
		for(Rental b : list) {
			rlist.add(b);
		}
		_tv_rent.setItems(rlist);
		tableRow_Rental();
	}
	
	
	
}
