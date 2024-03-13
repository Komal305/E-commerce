package com.cg.northwind.service;
/*
 * @author M V Shashank Yadav
 */
import java.util.List;

import com.cg.northwind.DTO.ProductDetailsDTO;
import com.cg.northwind.DTO.ProductOutOfStockDTO;
import com.cg.northwind.entity.Products;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.ProductNotExistsException;

public interface ProductsService {

	List<Products> getAllProducts();

	List<Products> searchProductbyQuantityPerUnit(String quantity) throws NoContentException;

	List<Products> getDiscontinuedProducts();

	boolean isProductIdPresent(int productID);

	Products addProduct(Products products, int supplierId, int categoryId) throws IdAlreadyExistsException, IdDoesNotExistsException;

	Products getByProductByMaxUnitPrice();

	List<ProductDetailsDTO> getProductDetails();

	List<Products> getByCategoryName(String categoryName) throws NoContentException;

	List<Products> getUnitPriceLessThanAvgSupplierUnitPrice(int supplierID);

	List<String> getCompanyNameWithMoreProducts(int productId) throws NoContentException;

	List<Products> getProductBySupplier(int supplierId) throws NoContentException;
	
	Products updateUnitPrice(double unitPrice, int productId) throws ProductNotExistsException;

	Products maxUnitPrice();

	List<ProductOutOfStockDTO> getProductsOutOfStock(int i);

	List<Products> getUnitsOnOrder();

	List<Products> getUnitsInStock();

	List<Products> getlessThanUnitsPrice(double unitPrice) throws NoContentException;

	Products patchUnitInStock(int productId, int unitInStock);

	Products updateProduct(Products product, int productId, int supplierId, int categoryId) throws ProductNotExistsException, IdDoesNotExistsException;

}
