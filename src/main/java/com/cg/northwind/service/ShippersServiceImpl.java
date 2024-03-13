package com.cg.northwind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.ShipperIdNotFoundException;
import com.cg.northwind.repository.ShippersRepository;

@Service
public class ShippersServiceImpl implements ShippersService{
	
	@Autowired
	private ShippersRepository repo;

	@Override
	public List<Shippers> getAllShippers() {
		return repo.findAll();
	}

	@Override
	public List<Shippers> findByCompanyName(String companyName) {
		return repo.getByCompanyName(companyName);
	}

	@Override
	public Shippers updateByShippersId(Shippers shippers, int shipperId) throws ShipperIdNotFoundException {
		Optional<Shippers> s = repo.findById(shipperId);
		if(s.isPresent()) {
		Shippers shipper = s.get();
		shipper.setShipperId(shippers.getShipperId());
		shipper.setCompanyName(shippers.getCompanyName());
		shipper.setPhone(shippers.getPhone());
		
			
		return repo.save(shipper);}
		else {
			throw new ShipperIdNotFoundException("Shipper Id you are trying to update is not present");
		}
	}

	@Override
	public Shippers addShippers(Shippers shippers) throws ShipperIdNotFoundException {
		if(repo.existsById(shippers.getShipperId())){
			throw new ShipperIdNotFoundException("Shipper Id you are trying to update is not present");
		}
		return repo.save(shippers);
	}

	@Override
	public boolean updateShipper(int shipperId, Shippers updatedShipper) {
		Optional<Shippers> existingShipperOptional = repo.findById(shipperId);
        if (existingShipperOptional.isPresent()) {
            Shippers existingShipper = existingShipperOptional.get();
            existingShipper.setCompanyName(updatedShipper.getCompanyName());
            repo.save(existingShipper);
            return true;
        } else {
            return false;
        }
	}
	
	
	
	
	
}
