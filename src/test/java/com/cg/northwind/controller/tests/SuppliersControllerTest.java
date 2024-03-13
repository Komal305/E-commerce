package com.cg.northwind.controller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.repository.SuppliersRepository;
import com.cg.northwind.service.SuppliersServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = com.cg.northwind.NorthWindApplication.class)
public class SuppliersControllerTest {
	@InjectMocks
	SuppliersServiceImpl suppliersService;

	@Mock
	SuppliersRepository suppliersRepository;

	@Test
	public void addSupplierTest() {
		Suppliers supplier = new Suppliers(1, "ABC Supplier", "John Doe", "Manager", "123 Street, City", "New York",
				"Region", "12345", "USA", "123-456-7890", "123-456-7899", "www.example.com");
		when(suppliersRepository.save(supplier)).thenReturn(supplier);
		Suppliers addedSupplier = suppliersService.addSupplier(supplier);
		assertEquals(supplier, addedSupplier);
	}

	@Test
	public void isSupplierIdPresentTest() {
		int supplierId = 1;
		when(suppliersRepository.existsById(supplierId)).thenReturn(true);

		boolean result = suppliersService.isSupplierIdPresent(supplierId);

		assertTrue(result);
	}

	@Test
	public void isSupplierIdNotPresentTest() {
		int supplierId = 1;
		when(suppliersRepository.existsById(supplierId)).thenReturn(false);

		boolean result = suppliersService.isSupplierIdPresent(supplierId);

		assertFalse(result);
	}

	@Test
	public void getAllSuppliersTest() {
		when(suppliersRepository.findAll()).thenReturn(Stream.of(
				new Suppliers(1, "Supplier 1", "John Doe", "Manager", "Address 1", "City 1", "Region 1", "12345",
						"Country 1", "123-456-7890", "123-456-7899", "www.example.com"),
				new Suppliers(2, "Supplier 2", "Jane Smith", "Supervisor", "Address 2", "City 2", "Region 2", "67890",
						"Country 2", "987-654-3210", "987-654-3219", "www.example.com"))
				.collect(Collectors.toList()));

		List<Suppliers> supplierList = suppliersService.getAllSuppliers();

		assertEquals(2, supplierList.size());
	}

	@Test
	public void findByIdTest() throws IdDoesNotExistsException {
		Suppliers existingSupplier = new Suppliers(1, "Supplier 1", "John Doe", "Manager", "Address 1", "City 1",
				"Region 1", "12345", "Country 1", "123-456-7890", "123-456-7899", "www.example.com");

		when(suppliersRepository.findById(1)).thenReturn(Optional.of(existingSupplier));

		Suppliers result = suppliersService.findById(1);

		verify(suppliersRepository, times(1)).findById(1);
		assertNotNull(result);
		assertEquals(existingSupplier.getSupplierID(), result.getSupplierID());
		assertEquals(existingSupplier.getCompanyName(), result.getCompanyName());
		assertEquals(existingSupplier.getContactName(), result.getContactName());
		assertEquals(existingSupplier.getContactTitle(), result.getContactTitle());
		assertEquals(existingSupplier.getAddress(), result.getAddress());
		assertEquals(existingSupplier.getCity(), result.getCity());
		assertEquals(existingSupplier.getRegion(), result.getRegion());
		assertEquals(existingSupplier.getPostalCode(), result.getPostalCode());
		assertEquals(existingSupplier.getCountry(), result.getCountry());
		assertEquals(existingSupplier.getPhone(), result.getPhone());
		assertEquals(existingSupplier.getFax(), result.getFax());
		assertEquals(existingSupplier.getHomePage(), result.getHomePage());
	}

	@Test
	public void updateBySupplierIdTest() throws IdDoesNotExistsException {
		Suppliers oldSupplier = new Suppliers(1, "Old Supplier", "John Doe", "Manager", "Old Address", "Old City",
				"Old Region", "Old PostalCode", "Old Country", "Old Phone", "Old Fax", "www.oldexample.com");

		Suppliers newSupplier = new Suppliers(1, "New Supplier", "Jane Smith", "Supervisor", "New Address", "New City",
				"New Region", "New PostalCode", "New Country", "New Phone", "New Fax", "www.newexample.com");

		when(suppliersRepository.findById(1)).thenReturn(Optional.of(oldSupplier));
		when(suppliersRepository.save(oldSupplier)).thenReturn(newSupplier);
		Suppliers updatedSupplier = suppliersService.updateBySupplierId(newSupplier, 1);

		verify(suppliersRepository, times(1)).save(oldSupplier);
		assertEquals(newSupplier.getCompanyName(), updatedSupplier.getCompanyName());
		assertEquals(newSupplier.getContactName(), updatedSupplier.getContactName());
		assertEquals(newSupplier.getContactTitle(), updatedSupplier.getContactTitle());
		assertEquals(newSupplier.getAddress(), updatedSupplier.getAddress());
		assertEquals(newSupplier.getCity(), updatedSupplier.getCity());
		assertEquals(newSupplier.getRegion(), updatedSupplier.getRegion());
		assertEquals(newSupplier.getPostalCode(), updatedSupplier.getPostalCode());
		assertEquals(newSupplier.getCountry(), updatedSupplier.getCountry());
		assertEquals(newSupplier.getPhone(), updatedSupplier.getPhone());
		assertEquals(newSupplier.getFax(), updatedSupplier.getFax());
		assertEquals(newSupplier.getHomePage(), updatedSupplier.getHomePage());
	}

	@Test
	public void searchSuppliersByCountryTest() {
		String country = "USA";
		when(suppliersRepository.findByCountryIgnoreCase(country)).thenReturn(Stream
				.of(new Suppliers(1, "Supplier 1", "John Doe", "Manager", "Address 1", "City 1", "Region 1", "12345",
						"USA", "123-456-7890", "123-456-7899", "www.example.com"),
						new Suppliers(2, "Supplier 2", "Jane Smith", "Supervisor", "Address 2", "City 2", "Region 2",
								"67890", "USA", "987-654-3210", "987-654-3219", "www.example.com"))
				.collect(Collectors.toList()));

		List<Suppliers> supplierList = suppliersService.searchSuppliersByCountry(country);

		assertEquals(2, supplierList.size());
		assertEquals(country, supplierList.get(0).getCountry());
		assertEquals(country, supplierList.get(1).getCountry());
	}

	@Test
	public void searchContactTitleManagerTest() {
		String manager = "Manager";
		when(suppliersRepository.findByContactTitleContainingIgnoreCase(manager)).thenReturn(Stream
				.of(new Suppliers(1, "Supplier 1", "John Doe", "Manager", "Address 1", "City 1", "Region 1", "12345",
						"Country 1", "123-456-7890", "123-456-7899", "www.example.com"),
						new Suppliers(2, "Supplier 2", "Jane Smith", "Managerial Assistant", "Address 2", "City 2",
								"Region 2", "67890", "Country 2", "987-654-3210", "987-654-3219", "www.example.com"))
				.collect(Collectors.toList()));

		List<Suppliers> supplierList = suppliersService.searchContactTitleManager(manager);

		assertEquals(2, supplierList.size());
		assertTrue(supplierList.get(0).getContactTitle().contains(manager));
		assertTrue(supplierList.get(1).getContactTitle().contains(manager));
	}

	@Test
	public void getNumberOfSupplierFromEachCountryTest() {
	    when(suppliersRepository.countSupplierByCountry()).thenReturn(
	        Stream.of(
	            new Object[] { "USA", 5 },
	            new Object[] { "UK", 3 },
	            new Object[] { "Canada", 2 }
	        ).collect(Collectors.toList())
	    );

	    List<Object[]> supplierCountList = suppliersService.getNumberOfSupplierFromEachCountry();

	    assertEquals(3, supplierCountList.size());

	    assertEquals("USA", supplierCountList.get(0)[0]);
	    assertEquals(5, supplierCountList.get(0)[1]);
	    assertEquals("UK", supplierCountList.get(1)[0]);
	    assertEquals(3, supplierCountList.get(1)[1]);
	    assertEquals("Canada", supplierCountList.get(2)[0]);
	    assertEquals(2, supplierCountList.get(2)[1]);
	}


	@Test
	public void getSuppliersRegionNotNullTest() {
		when(suppliersRepository.findByRegionNotNull()).thenReturn(Stream.of(
				new Suppliers(1, "Supplier 1", "John Doe", "Manager", "Address 1", "City 1", "Region 1", "12345",
						"Country 1", "123-456-7890", "123-456-7899", "www.example.com"),
				new Suppliers(2, "Supplier 2", "Jane Smith", "Supervisor", "Address 2", "City 2", "Region 2", "67890",
						"Country 2", "987-654-3210", "987-654-3219", "www.example.com"))
				.collect(Collectors.toList()));

		List<Suppliers> supplierList = suppliersService.getSuppliersRegionNotNull();

		assertEquals(2, supplierList.size());
		assertNotNull(supplierList.get(0).getRegion());
		assertNotNull(supplierList.get(1).getRegion());
	}
}
