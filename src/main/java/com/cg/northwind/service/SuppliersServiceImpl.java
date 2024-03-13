package com.cg.northwind.service;
/*
 * @author
 * M V Shashank Yadav
 * 
 * Supplier Service Implementation
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.repository.SuppliersRepository;

@Service
public class SuppliersServiceImpl implements SuppliersService {

	@Autowired
	private SuppliersRepository suppliersRepository;

	@Override
	public Suppliers addSupplier(Suppliers suppliers) {
		try {
			return suppliersRepository.save(suppliers);
		} catch (Exception e) {
			throw new InternalError("Failed To Add :( \n Might Be Caused By Incorrect Parameters");
		}
	}

	@Override
	public boolean isSupplierIdPresent(int supplierId) {
		return suppliersRepository.existsById(supplierId);
	}

	@Override
	public List<Suppliers> getAllSuppliers() {
		return suppliersRepository.findAll();
	}

	@Override
	public Suppliers findById(int id) throws IdDoesNotExistsException {
		return suppliersRepository.findById(id)
				.orElseThrow(() -> new IdDoesNotExistsException("Supplier ID Does Not Present"));
	}

	@Override
	public Suppliers updateBySupplierId(Suppliers newSupplier, int id) {
		Optional<Suppliers> oldSupplierOptional = suppliersRepository.findById(id);
		Suppliers oldSupplier = oldSupplierOptional.get();
		oldSupplier.setAddress(newSupplier.getAddress());
		oldSupplier.setCity(newSupplier.getCity());
		oldSupplier.setPostalCode(newSupplier.getPostalCode());
		oldSupplier.setCountry(newSupplier.getCountry());

		return suppliersRepository.save(oldSupplier);
	}

	@Override
	public List<Suppliers> searchSuppliersByCountry(String country) {
		return suppliersRepository.findByCountryIgnoreCase(country);
	}

	@Override
	public List<Suppliers> getSuppliersRegionNotNull() {
		return suppliersRepository.findByRegionNotNull();
	}

	@Override
	public List<Suppliers> searchContactTitleManager(String Manager) {
		return suppliersRepository.findByContactTitleContainingIgnoreCase(Manager);
	}

	@Override
	public List<Object[]> getNumberOfSupplierFromEachCountry() {
		return suppliersRepository.countSupplierByCountry();
	}

}
