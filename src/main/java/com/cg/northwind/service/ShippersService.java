package com.cg.northwind.service;

import java.util.List;

import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.ShipperIdNotFoundException;

public interface ShippersService {

	List<Shippers> getAllShippers();
	List<Shippers> findByCompanyName(String companyName);
	Shippers updateByShippersId(Shippers shippers,int shipperId) throws ShipperIdNotFoundException;
	Shippers addShippers(Shippers shippers) throws ShipperIdNotFoundException;
	boolean updateShipper(int shipperId, Shippers updatedShipper);
}
