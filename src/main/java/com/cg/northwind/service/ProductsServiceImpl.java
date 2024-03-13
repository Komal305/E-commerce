package com.cg.northwind.service;
/*
 * @author M V Shashank Yadav
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.DTO.ProductDetailsDTO;
import com.cg.northwind.DTO.ProductOutOfStockDTO;
import com.cg.northwind.entity.Categories;
import com.cg.northwind.entity.Products;
import com.cg.northwind.entity.Suppliers;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.ProductNotExistsException;
import com.cg.northwind.repository.ProductsRepository;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SuppliersService suppliersService;

	@Override
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

	@Override
	public List<Products> searchProductbyQuantityPerUnit(String quantity) throws NoContentException {
		List<Products> products=productsRepository.findByQuantityPerUnitIgnoreCase(quantity);
		if(products.isEmpty())
			throw new NoContentException("Nothing Found");
		return productsRepository.findByQuantityPerUnitIgnoreCase(quantity);
	}

	@Override
	public List<Products> getDiscontinuedProducts() {
		return productsRepository.findByDiscontinued(true);
	}

	@Override
	public boolean isProductIdPresent(int productID) {
		return productsRepository.existsById(productID);
	}

	@Override
	public Products getByProductByMaxUnitPrice() {
		return productsRepository.findMaxUnitPrice();
	}

	@Override
	public List<ProductDetailsDTO> getProductDetails() {
		return productsRepository.findProductDetails();
	}
	
	@Override
	public List<Products> getByCategoryName(String categoryName) throws NoContentException {
		List<Products> products= productsRepository.findByCategoryNameIgnoreCase(categoryName);
		if(products.isEmpty())
			throw new NoContentException("No Products Found Under Given Category Name");
		return products;
	}

	@Override
	public List<Products> getUnitPriceLessThanAvgSupplierUnitPrice(int supplierID) {
		return productsRepository.findByUnitPriceLessThanAvgSupplierUnitPrice(supplierID);
	}

	@Override
	public List<String> getCompanyNameWithMoreProducts(int productId) throws NoContentException {
		List<String> list = productsRepository.findByCompanyNameWithMoreProducts(productId);
		if(list.isEmpty())
			throw new NoContentException("No Company sells these many Product...");
		return list;
	}

	@Override
	public List<Products> getProductBySupplier(int supplierId) throws NoContentException {
		List<Products> products= productsRepository.findByProductBySupplierId(supplierId);
		if(products.isEmpty())
			throw new NoContentException("No Products Found For this Supplier");
		return products;
	}

	@Override
	public Products updateProduct(Products product, int productId,int supplierId,int categoryId) throws ProductNotExistsException, IdDoesNotExistsException {
		if (!productsRepository.existsById(productId))
			throw new ProductNotExistsException("Product With Given Id Doesn't Exist");
		Suppliers suppliers=suppliersService.findById(supplierId);
		Categories categories= categoryService.findById(categoryId);
		Products oldProduct = productsRepository.findById(productId).orElseThrow(()->new IdDoesNotExistsException("Product Id Not Found Exception"));
		oldProduct.setProductName(product.getProductName());
		oldProduct.setCategories(categories);
		oldProduct.setQuantityPerUnit(product.getQuantityPerUnit());
		oldProduct.setReorderLevel(product.getReorderLevel());
		oldProduct.setSuppliers(suppliers);
		oldProduct.setUnitPrice(product.getUnitPrice());
		oldProduct.setUnitsInStock(product.getUnitsInStock());
		oldProduct.setUnitsOnOrder(product.getUnitsOnOrder());
		return productsRepository.save(oldProduct);
	}

	@Override
	public Products updateUnitPrice(double unitPrice, int productId) throws ProductNotExistsException {
		if (!productsRepository.existsById(productId))
			throw new ProductNotExistsException("Product With Given Id Doesn't Exist");
		Products prod = productsRepository.findById(productId).get();
		prod.setUnitPrice(unitPrice);
		return productsRepository.save(prod);
	}

	@Override
	public Products maxUnitPrice() {
		return productsRepository.findTopByOrderByUnitPriceDesc();
	}

	@Override
	public List<ProductOutOfStockDTO> getProductsOutOfStock(int i) {
		return productsRepository.findByUnitsInStockEquals(0);
	}

	@Override
	public List<Products> getUnitsOnOrder() {
		return productsRepository.findByUnitsOnOrderNot(0);
	}

	@Override
	public List<Products> getUnitsInStock() {
		return productsRepository.findByUnitsInStockNot(0);
	}

	@Override
	public List<Products> getlessThanUnitsPrice(double unitPrice) throws NoContentException {
		List<Products> products=productsRepository.findByUnitPriceLessThan(unitPrice);
		if(products.isEmpty())
			throw new NoContentException("Nothing Found");
		return products;
	}

	@Override
	public Products addProduct(Products products, int supplierId, int categoryId) throws IdAlreadyExistsException, IdDoesNotExistsException {
		Suppliers suppliers = suppliersService.findById(supplierId);
		Categories categories = categoryService.findById(categoryId);

		products.setCategories(categories);
		products.setSuppliers(suppliers);

		return productsRepository.save(products);
	}

	@Override
	public Products patchUnitInStock(int productId, int unitInStock) {
		Products prod = productsRepository.findById(productId).get();
		prod.setUnitsInStock(unitInStock);
		return productsRepository.save(prod);
	}
}
