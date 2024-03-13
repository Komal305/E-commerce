package com.cg.northwind.service;
/*
 * @author
 * M V Shashank Yadav
 * 
 * Supplier Service Interface
 */
import java.util.List;

import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.IdDoesNotExistsException;

public interface SuppliersService {

	Suppliers addSupplier(Suppliers suppliers);

	boolean isSupplierIdPresent(int supplierId);

	List<Suppliers> getAllSuppliers();

	Suppliers updateBySupplierId(Suppliers suppliers, int id);

	Suppliers findById(int id) throws IdDoesNotExistsException;

	List<Suppliers> searchSuppliersByCountry(String country);

	List<Suppliers> getSuppliersRegionNotNull();

	List<Suppliers> searchContactTitleManager(String Manager);

	List<Object[]> getNumberOfSupplierFromEachCountry();

}
