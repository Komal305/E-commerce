package com.cg.northwind.controller.tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.northwind.entity.Categories;
import com.cg.northwind.entity.Products;
import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.repository.ProductsRepository;
import com.cg.northwind.service.CategoryService;
import com.cg.northwind.service.ProductsServiceImpl;
import com.cg.northwind.service.SuppliersService;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = com.cg.northwind.NorthWindApplication.class)
public class ProductsControllerTest {
	@InjectMocks
	ProductsServiceImpl productsService;

	@Mock
	ProductsRepository productsRepository;

	@Mock
	CategoryService categoryService;

	@Mock
	SuppliersService suppliersService;

	@Test
	public void getProductBySupplierNoContentTest() {
	    int supplierId = 1;
	    when(productsRepository.findByProductBySupplierId(supplierId)).thenReturn(Collections.emptyList());

	    try {
	        productsService.getProductBySupplier(supplierId);
	        fail("NoContentException should be thrown.");
	    } catch (NoContentException ex) {
	        assertEquals("No Products Found For this Supplier", ex.getMessage());
	    }
	}

}
