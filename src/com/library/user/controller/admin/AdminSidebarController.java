package com.library.user.controller.admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AdminSidebarController implements Initializable {
    @FXML
    private AnchorPane _ap_sidebar;
    @FXML
    private BorderPane _bp_sidebar;
    @FXML
    private Hyperlink _hy_mng_book, _hy_mng_user, _hy_mng_brl;
	
    private void __hy_mng_book() {
    	pageLoad("admin_book");
    }
    
	private void __hy_mng_user() {
		pageLoad("admin_user");
    }
	
	private void __hy_mng_brl() {
		pageLoad("admin_rental");
	}
    
	private void pageLoad(String page) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(new File("resources/view/admin/"+page+".fxml").toURI().toURL());
			Parent root = loader.load();
			_bp_sidebar.setCenter(root);
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		__hy_mng_book();
		
		_hy_mng_book.setOnAction(e->{
			__hy_mng_book();
		});
		
		_hy_mng_user.setOnAction(e->{
			__hy_mng_user();
		});

		_hy_mng_brl.setOnAction(e->{
			__hy_mng_brl();
		});
	}

}
