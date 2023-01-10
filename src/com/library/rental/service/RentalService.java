package com.library.rental.service;

import java.util.ArrayList;

import com.library.common.vo.Rental;
import com.library.common.vo.User;

public interface RentalService {

	
	Rental select(Rental rental);

	ArrayList<Rental> selectList(Rental rental);

	int selectRentalCount(User user);

	Rental insert(Rental rental);

	Rental update(Rental rental);
	
}
