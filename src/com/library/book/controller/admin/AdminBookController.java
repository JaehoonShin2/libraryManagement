package com.library.book.controller.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.library.book.service.BookService_Impl;
import com.library.common.template.FunctionTemplate;
import com.library.common.vo.Book;
import com.library.common.vo.ShareData;
import com.library.common.vo.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class AdminBookController implements Initializable{
	
	
	@FXML
    private Label _label_id;
    @FXML
    private Hyperlink _hy_logout, _hy_mypage;
    @FXML
    private TableView<Book> _tv_book;
    @FXML
    private TableColumn<Book, String> _tc_isbn, _tc_title, _tc_page, _tc_price;
    @FXML
    private Button _btn_search;
    @FXML
    private TextField _tf_keyword;
    @FXML
    private Button _btn_enroll;
    
    
    @FXML
    private DialogPane _dia_book;
    @FXML
    private Label _label_dia_title, _label_dia_page, _label_dia_author, _label_dia_price, _label_dia_translator, _label_dia_publisher, _label_dia_rentYN;
    @FXML
    private Button _btn_dia_ok, _btn_dia_delete;

    
    @FXML
    private DialogPane _dia_enroll;
    @FXML
    private TextField _tf_title, _tf_page, _tf_price, _tf_author, _tf_translator, _tf_publisher, _tf_date;
    @FXML
    private Button _btn_dia_enroll, _btn_dia_cancel;

    @FXML
    private DialogPane _dia_mypage;
    @FXML
    private PasswordField _pf_pwd;
    @FXML
    private Button _btn_dia_pwd_ok,  _btn_dia_pwd_cancel;
    
    
    private static Logger logger = Logger.getLogger(AdminBookController.class);
	private String bisbn;
	private ObservableList<Book> blist;
	private Alert alert;
    
	public void selectList(Book book) {
		ArrayList<Book> list = new BookService_Impl().selectList(book);
		blist = FXCollections.observableArrayList();
		for(Book b : list) {
			blist.add(b);
		}
		_tv_book.setItems(blist);
		tableRow_Book();
	}
    
	// 로그아웃
	private void __hy_logout() {
		Scene scene = ShareData.getScene("loginform", "user/loginform");
		ShareData.getStage().setScene(scene);
		ShareData.getStage().show();
		ShareData.setUser(null);
	}

	private void __btn_enroll() {
		_dia_enroll.setVisible(true);
		
		_btn_dia_enroll.setOnAction(e1->{
			Book b = new Book();
			b.setIsbn(FunctionTemplate.ranISBN());
			b.setTitle(_tf_title.getText());
			b.setDate(_tf_date.getText());
			b.setPage(Integer.parseInt(_tf_page.getText()));
			b.setPrice(Integer.parseInt(_tf_price.getText()));
			b.setAuthor(_tf_author.getText());
			b.setTranslator(_tf_translator.getText());
			b.setPublisher(_tf_publisher.getText());
			
			Book book = new BookService_Impl().insert(b);
			if(book != null) {
				selectList(null);
				_dia_enroll.setVisible(false);
				setTextFiedText(_tf_keyword, null);
				setTextFiedText(_tf_title, null);
				setTextFiedText(_tf_page, null);
				setTextFiedText(_tf_price, null);
				setTextFiedText(_tf_author, null);
				setTextFiedText(_tf_translator, null);
				setTextFiedText(_tf_date, null);
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("안내창");
				alert.setContentText("도서 등록에 오류가 발생했습니다.");
				alert.setTitle("확인");
				alert.showAndWait();
			}
		});
		
		_btn_dia_cancel.setOnAction(e1->{
			_dia_enroll.setVisible(false);
		});
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
							if(book != null) {
								_dia_book.setVisible(true);
								setLabelText(_label_dia_title, book.getTitle());
								setLabelText(_label_dia_author, book.getAuthor());
								setLabelText(_label_dia_page, String.valueOf(book.getPage()));;
								setLabelText(_label_dia_price, String.valueOf(book.getPrice()));;
								setLabelText(_label_dia_publisher, book.getPublisher());
								setLabelText(_label_dia_translator, book.getTranslator());
								setLabelText(_label_dia_rentYN, book.getRentYN());
								
								_btn_dia_ok.setOnAction( e2 -> {
									_dia_book.setVisible(false);
								});
								
								_btn_dia_delete.setOnAction(e2->{
									
									// 현재 도서의 대출가능여부가 'Y' 때
									if(book.getRentYN().equals("Y")){
										Book b = new BookService_Impl().delete(book);
											if(b != null) {
											_dia_book.setVisible(false);
											selectList(null);
										}
									} else {
										alert = new Alert(AlertType.CONFIRMATION);
										alert.setHeaderText("안내창");
										alert.setContentText("도서가 반납된 뒤 다시 시도해주세요.");
										alert.setTitle("확인");
										alert.showAndWait();
									}
								});
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

	private void setLabelText(Label label, String s) {
		label.setText(s);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		_label_id.setText(ShareData.getUser().getUserId() + "님");
		
		// dialog는 시작 시 Visible = false
		_dia_book.setVisible(false);
		_dia_enroll.setVisible(false);
		_dia_mypage.setVisible(false);
		
		setTextFiedText(_tf_keyword, null);
		setTextFiedText(_tf_title, null);
		setTextFiedText(_tf_page, null);
		setTextFiedText(_tf_price, null);
		setTextFiedText(_tf_author, null);
		setTextFiedText(_tf_translator, null);
		setTextFiedText(_tf_date, null);
		
		setBookCellvalueFactory(_tc_isbn, "isbn");
		setBookCellvalueFactory(_tc_title, "title");
		setBookCellvalueFactory(_tc_page, "page");
		setBookCellvalueFactory(_tc_price, "price");
		
		selectList(null);
		Book book = new Book();
		_btn_search.setOnAction(e->{
			book.setTitle(_tf_keyword.getText());
			selectList(book);
		});
		
		_tf_keyword.setOnAction(e->{
			book.setTitle(_tf_keyword.getText());
			selectList(book);
		});
		
		_hy_logout.setOnAction(e->{
			__hy_logout();
		});
		_btn_enroll.setOnAction(e->{
			__btn_enroll();	
		});
	}


	private void setTextFiedText(TextField tf, String str) {
		tf.setText(str);
	}
	
}
