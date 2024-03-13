package com.cg.northwind.controller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.cg.northwind.entity.Shippers;
import com.cg.northwind.exception.ShipperIdNotFoundException;
import com.cg.northwind.repository.ShippersRepository;
import com.cg.northwind.service.ShippersServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = com.cg.northwind.NorthWindApplication.class)
public class ShippersControllerTest {

	@InjectMocks
    private ShippersServiceImpl shipperService;
	
	@Mock
	private ShippersRepository shippersRepository;
		
	@Test
	public void getAllShippersTest() {
		when(shippersRepository.findAll()).thenReturn(Stream
				.of(new Shippers(1,"Speedy","(503) 555-9831")).collect(Collectors.toList()));
		assertEquals(1,shipperService.getAllShippers().size());
	}
	
	@Test
	public void addShippersTest() throws ShipperIdNotFoundException {
		Shippers s = new Shippers(1,"Deepak","54234252");
		when(shippersRepository.save(s)).thenReturn(s);
		assertEquals(s, shipperService.addShippers(s));
	}
	
	@Test
	public void updateShippersTest() throws ShipperIdNotFoundException {
		Shippers s = new Shippers(1,"Speedy","(503) 555-9831");
		Shippers s1 = new Shippers(2,"Shashank","36272534");
		when(shippersRepository.findById(s.getShipperId())).thenReturn(Optional.of(s));
	    when(shippersRepository.save(s)).thenReturn(s1);
	    Shippers result = shipperService.updateByShippersId(s, s.getShipperId());
	    assertEquals(s1, result);
	}
	
	@Test
	public void updateParticularShippersTest() {
		Shippers existingShipper = new Shippers();
        existingShipper.setShipperId(1);
        existingShipper.setCompanyName("Speedy");
        existingShipper.setPhone("(503) 555-9831");
        Shippers updatedShipper = new Shippers();
        updatedShipper.setCompanyName("Capgemini");
        when(shippersRepository.findById(1)).thenReturn(Optional.of(existingShipper));
        boolean result = shipperService.updateShipper(1, updatedShipper);
        verify(shippersRepository, times(1)).save(existingShipper);
        assertTrue(result);
        assertEquals("Capgemini", existingShipper.getCompanyName());
	}
	 
}
