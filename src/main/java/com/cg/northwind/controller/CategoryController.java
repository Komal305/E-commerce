package com.cg.northwind.controller;

/*
 * @author
 * M V Shashank Yadav
 * 
 * Category controller class
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.northwind.entity.Categories;
import com.cg.northwind.exception.IdAlreadyExistsException;
import com.cg.northwind.exception.CategoryDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/*
	 * Display's all categories in a List
	 */
	@GetMapping("")
	public ResponseEntity<List<Categories>> getAllCategory() throws NoContentException {
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

	/*
	 * Search Category by Category Name in a List
	 */
	@GetMapping("/categoryname/{categoryname}")
	public ResponseEntity<List<Categories>> getByCategoryName(@PathVariable String categoryname)
			throws NoContentException {
		return ResponseEntity.ok(categoryService.getByCategoryName(categoryname));
	}

	/*
	 * updating all details of category by their Category ID
	 */
	@PutMapping("/edit/{id}")
	public EntityModel<Categories> updateById(@RequestBody Categories categories, @PathVariable int id)
			throws CategoryDoesNotExistsException, NoContentException {
		ResponseEntity.ok(categoryService.updateByCategoryId(categories, id));
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCategory());
		return EntityModel.of(categories).add(link.withRel("Retrieve All Categories -->"));

	}
	
	/*
	 * Inserting new category
	 */
	@PostMapping("")
	public EntityModel<Categories> addCategory(@RequestBody Categories categories)
			throws IdAlreadyExistsException, NoContentException {
		if (categoryService.isCategoryIdPresent(categories.getCategoryID())) {
			throw new IdAlreadyExistsException("Validation failed. ID Already present");
		}
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCategory());
		categoryService.addCategory(categories);
		return EntityModel.of(categories)
				.add(link.withRel("Record Added Successfully!!    Retrieve All Categories -->"));

	}

}
