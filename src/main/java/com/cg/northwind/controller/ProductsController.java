package com.cg.northwind.controller;
/*
 * @author M V Shashank Yadav
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.DTO.ProductDetailsDTO;
import com.cg.northwind.DTO.ProductOutOfStockDTO;
import com.cg.northwind.entity.Products;
import com.cg.northwind.exception.BodyNotFoundException;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.exception.ProductNotExistsException;
import com.cg.northwind.service.CategoryService;
import com.cg.northwind.service.ProductsService;
import com.cg.northwind.service.SuppliersService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SuppliersService suppliersService;

	@GetMapping("")
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> products = productsService.getAllProducts();
		if (products != null)
			return ResponseEntity.ok(products);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{quantityperunit}")
	public ResponseEntity<List<Products>> searchProductbyQuantityPerUnit(
			@PathVariable("quantityperunit") String quantity) throws NoContentException {
			return ResponseEntity.ok(productsService.searchProductbyQuantityPerUnit(quantity));
		
	}

	@GetMapping("/discontinuedproduct")
	public ResponseEntity<List<Products>> getDiscontinuedProducts() {
		List<Products> discontinuedProds = productsService.getDiscontinuedProducts();
		if (discontinuedProds != null)
			return ResponseEntity.ok(discontinuedProds);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("")
	public EntityModel<Products> addProduct(@RequestBody Products products, @RequestParam("supplierid") int supplierId,
			@RequestParam("categoryid") int categoryId)
			throws IdAlreadyExistsException, IdDoesNotExistsException {
		if (productsService.isProductIdPresent(products.getProductID())) {
			throw new IdAlreadyExistsException("Validation Failed... ID Already Exist's");
		}
			suppliersService.findById(supplierId);
			categoryService.findById(categoryId);
		
		productsService.addProduct(products, supplierId, categoryId);
		WebMvcLinkBuilder link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
		
		return EntityModel.of(products).add(link.withRel("Record Added Successfully!! View All Products -->"));
	}

	@GetMapping("/productbymaxprice")
	public ResponseEntity<Products> getByProductByMaxUnitPrice() {
		Products maxProd = productsService.getByProductByMaxUnitPrice();
		if (maxProd != null)
			return ResponseEntity.ok(maxProd);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/productdetails")
	public ResponseEntity<List<ProductDetailsDTO>> getProductDetails() {
		List<ProductDetailsDTO> prods = productsService.getProductDetails();
		if (prods != null)
			return ResponseEntity.ok(prods);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/bycategoryname/{bycategoryname}")
	public ResponseEntity<List<Products>> getByCategoryName(@PathVariable("bycategoryname") String categoryName)
			throws NoContentException {
			return ResponseEntity.ok(productsService.getByCategoryName(categoryName));
	}

	@GetMapping("/productdetails/supplierid/{supplierID}")
	public ResponseEntity<List<Products>> getUnitPriceLessThanAvgSupplierUnitPrice(@PathVariable int supplierID)
			throws NoContentException {
		List<Products> prods = productsService.getUnitPriceLessThanAvgSupplierUnitPrice(supplierID);
		if (prods != null)
			return ResponseEntity.ok(prods);
		throw new NoContentException("No Products Found");
	}

	@GetMapping("/companyname")
	public ResponseEntity<List<String>> getCompanyNameWithMoreProducts(@RequestParam("productid") int noOfProducts) throws NoContentException {
			return ResponseEntity.ok(productsService.getCompanyNameWithMoreProducts(noOfProducts));
	}

	@GetMapping("/productbysupplier/{supplierid}")
	public ResponseEntity<List<Products>> getProductBySupplier(@PathVariable("supplierid") int supplierId)
			throws NoContentException {
			return ResponseEntity.ok(productsService.getProductBySupplier(supplierId));
	}

	@PutMapping("/edit/productid/{productid}")
	public EntityModel<Products> updateProduct(@RequestBody double unitPrice,
			@PathVariable("productid") int productId) throws ProductNotExistsException {
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
		return EntityModel.of(productsService
				.updateUnitPrice(unitPrice, productId))
				.add(link.withRel("View All Products -->"));
		
	}

	@PutMapping("/edit/{productid}")
	public EntityModel<Products> updateUnitPrice(@RequestBody Products product,
			@PathVariable("productid") int productId,@RequestParam("categoryid") int categoryId,@RequestParam("supplierid") int supplierId) throws BodyNotFoundException, ProductNotExistsException, IdDoesNotExistsException {
		if (product == null)
			throw new BodyNotFoundException("Provide Body");
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
		return EntityModel.of(productsService.updateProduct(product, productId,supplierId,categoryId))
				.add(link.withRel("View All Products -->"));
	}

	@GetMapping("/productbyunitprice")
	public ResponseEntity<Products> maxUnitPrice() {
		return ResponseEntity.ok(productsService.maxUnitPrice());
	}

	@GetMapping("/outofstock")
	public ResponseEntity<List<ProductOutOfStockDTO>> getProductsOutOfStock() {
		return ResponseEntity.ok(productsService.getProductsOutOfStock(0));
	}

	@GetMapping("/unitsonorder")
	public ResponseEntity<List<Products>> getUnitsOnOrder() {
		return ResponseEntity.ok(productsService.getUnitsOnOrder());
	}

	@GetMapping("/unitsinstock")
	public ResponseEntity<List<Products>> getUnitsInStock() {
		return ResponseEntity.ok(productsService.getUnitsInStock());
	}

	@GetMapping("/unitprice/{unitprice}")
	public ResponseEntity<List<Products>> getlessThanUnitsPrice(@PathVariable("unitprice") double unitPrice) throws NoContentException {
		return ResponseEntity.ok(productsService.getlessThanUnitsPrice(unitPrice));
	}

	@PatchMapping("/edit/{productid}")
	public EntityModel<Products> patchUnitInStock(@PathVariable("productid") int productId,
			@RequestBody int unitInStock) {
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
		return EntityModel.of(productsService.patchUnitInStock(productId, unitInStock))
				.add(link.withRel("View All Products -->"));
	}

}
