package com.library.common.template;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionTemplate {

	public static boolean dateDiff(String dueDate, String returnDate) {
		 
		//yyyy-MM-dd 포맷 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
		//비교할 date와 today를데이터 포맷으로 변경
		Date dueDateFm = null;
		Date returnDateFm = null;
		try {
			dueDateFm = new Date(dateFormat.parse(dueDate).getTime()); 
			returnDateFm = new Date(dateFormat.parse(returnDate).getTime());
		} catch (ParseException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		 
		//compareTo메서드를 통한 날짜비교
		int compare = returnDateFm.compareTo(returnDateFm); 
		if(compare < 0) {
		// 반납 예정일보다 실제 반납일이 더 늦은 경우
			return false;
		}else {
			return true;
		}
	}
	
	public static String ranISBN() {
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int ranNum = (int) (Math.random()*90000 + 10000);
		return currentTime+ranNum;
	}
	
	
}
