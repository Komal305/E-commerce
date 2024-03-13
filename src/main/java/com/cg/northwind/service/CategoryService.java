package com.cg.northwind.service;
/*
 * @author
 * M V Shashank Yadav
 */
import java.util.List;

import com.cg.northwind.entity.Categories;
import com.cg.northwind.exception.CategoryDoesNotExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;

//Category Service Interface
public interface CategoryService {
	List<Categories> getAllCategory() throws NoContentException;

	List<Categories> getByCategoryName(String categoryName) throws NoContentException;

	Categories findById(int id) throws IdDoesNotExistsException;

	Categories updateByCategoryId(Categories categories, int id) throws CategoryDoesNotExistsException;

	Categories addCategory(Categories categories);
	
	public boolean isCategoryIdPresent(int categoryId);
}
