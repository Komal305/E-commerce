package com.cg.northwind.controller.tests;
/*
 * Author --> M V Shashank Yadav
 * 
 * Unit testing for Category 
 * 
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.cg.northwind.entity.Categories;
import com.cg.northwind.exception.CategoryDoesNotExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.repository.CategoryRepository;
import com.cg.northwind.service.CategoryServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = com.cg.northwind.NorthWindApplication.class)
public class CategoryControllerTest {
	@InjectMocks
	CategoryServiceImpl categoryService;

	@Mock
	CategoryRepository categoryRepository;

	@Test
	public void addCategoryTest() {
		Categories categories = new Categories(1, "Beverages", "Soft drinks, coffees, teas, beers, and ales", "photo");
		when(categoryRepository.save(categories)).thenReturn(categories);
		assertEquals(categories, categoryService.addCategory(categories));
	}
	
	@Test
	public void getAllCategoryTest() throws NoContentException {
		when(categoryRepository.findAll())
		.thenReturn(Stream.of(new Categories(1,"drinks","soft drinks, hard drinks","photo.jpg"),
				(new Categories(2,"abc category","sub cat 1, sub cat 2","photo"))).collect(Collectors.toList()));
		assertEquals(2, categoryService.getAllCategory().size());
	}
	
	@Test
	public void updateByCategoryIdTest() throws CategoryDoesNotExistsException {
		Categories oldCategories = new Categories(1,"abc category","sub cat 1, sub cat 2","photo");
		Categories newCategories = new Categories(0, "CG", "chennai", "campus pic");
		when(categoryRepository.findById(1)).thenReturn(Optional.of(oldCategories));
		oldCategories.setCategoryName(newCategories.getCategoryName());
		oldCategories.setDescription(newCategories.getDescription());
		oldCategories.setPicture(newCategories.getPicture());
		
		categoryService.updateByCategoryId(newCategories, 1);
		verify(categoryRepository,times(1)).save(oldCategories);
		assertEquals(newCategories.getCategoryName(), oldCategories.getCategoryName());
		assertEquals(newCategories.getDescription(), oldCategories.getDescription());
		assertEquals(newCategories.getPicture(), oldCategories.getPicture());

	}
	
	@Test
	public void findByIdTest() throws IdDoesNotExistsException {
	    Categories oldCategory = new Categories();
	    oldCategory.setCategoryID(1);
	    oldCategory.setCategoryName("drinks");
	    oldCategory.setDescription("hot and cold drinks");
	    oldCategory.setPicture("tea juice pic");
	    
	    when(categoryRepository.findById(1)).thenReturn(Optional.of(oldCategory));
	    
	    Categories result = categoryService.findById(1);
	    
	    verify(categoryRepository, times(1)).findById(1);
	    assertNotNull(result);
	    assertEquals(oldCategory.getCategoryID(), result.getCategoryID());
	    assertEquals(oldCategory.getCategoryName(), result.getCategoryName());
	    assertEquals(oldCategory.getDescription(), result.getDescription());
	    assertEquals(oldCategory.getPicture(), result.getPicture());
	}
}
