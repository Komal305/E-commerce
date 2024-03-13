package com.cg.northwind.service;

/*
 * @author
 * M V Shashank Yadav
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.northwind.entity.Categories;
import com.cg.northwind.exception.CategoryDoesNotExistsException;
import com.cg.northwind.exception.IdDoesNotExistsException;
import com.cg.northwind.exception.NoContentException;
import com.cg.northwind.repository.CategoryRepository;

//Implementation class for Category Service
//overrides superclass method in service
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Categories> getAllCategory() throws NoContentException {
		List<Categories> categories = categoryRepository.findAll();
		if (categories.isEmpty())
			throw new NoContentException("Empty ResultSet, No Records Found");
		return categories;
	}

	@Override
	public List<Categories> getByCategoryName(String categoryName) throws NoContentException {
		List<Categories> categories = categoryRepository.findByCategoryName(categoryName);
		if (categories.isEmpty())
			throw new NoContentException("Empty ResultSet, No Records Found");
		return categories;
	}

	@Override
	public Categories findById(int id) throws IdDoesNotExistsException {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IdDoesNotExistsException("Category ID Does Not Exists Exception"));
	}

	@Override
	public Categories updateByCategoryId(Categories newCategories, int id) throws CategoryDoesNotExistsException {

		Categories oldCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryDoesNotExistsException("ID Doesn't Exist To Update"));
		oldCategory.setCategoryName(newCategories.getCategoryName());
		oldCategory.setDescription(newCategories.getDescription());
		oldCategory.setPicture(newCategories.getPicture());

		return categoryRepository.save(oldCategory);

	}

	@Override
	public Categories addCategory(Categories categories) {
		return categoryRepository.save(categories);

	}

	public boolean isCategoryIdPresent(int categoryId) {
		return categoryRepository.existsById(categoryId);
	}

}
