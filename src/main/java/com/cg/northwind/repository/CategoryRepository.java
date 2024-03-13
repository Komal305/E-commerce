package com.cg.northwind.repository;
/*
 * @author 
 * M V Shashank Yadav
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.northwind.entity.Categories;

//Category Repository 
public interface CategoryRepository extends JpaRepository<Categories, Integer>{
	
	List<Categories> findByCategoryName(String categoryName);
}
