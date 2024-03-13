package com.cg.northwind.repository;
/*
 * @author M V Shashank Yadav
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.northwind.DTO.ProductDetailsDTO;
import com.cg.northwind.DTO.ProductOutOfStockDTO;
import com.cg.northwind.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

	List<Products> findByQuantityPerUnitIgnoreCase(String quantity);

	List<Products> findByDiscontinued(boolean discontinued);

	@Query("select p from Products p where p.unitPrice = (select max(p2.unitPrice) from Products p2)")
	Products findMaxUnitPrice();

	@Query("select p.productName as productName,p.categories.categoryName as categoryName,p.suppliers.companyName as companyName from Products p")
	List<ProductDetailsDTO> findProductDetails();

	@Query("select p from Products p where p.categories.categoryName = :categoryName")
	List<Products> findByCategoryNameIgnoreCase(String categoryName);

	@Query("select p from Products p where p.unitPrice<(select avg(p1.unitPrice) from Products p1 where p1.suppliers.supplierID=:supplierId)")
	List<Products> findByUnitPriceLessThanAvgSupplierUnitPrice(int supplierId);

	@Query("select p.suppliers.companyName from Products p group by p.suppliers.companyName having count(*)> :noOfProducts")
	List<String> findByCompanyNameWithMoreProducts(int noOfProducts);

	@Query("select p from Products p  where p.suppliers.supplierID=:supplierId")
	List<Products> findByProductBySupplierId(int supplierId);

	Products findTopByOrderByUnitPriceDesc();

	List<ProductOutOfStockDTO> findByUnitsInStockEquals(int stock);

	List<Products> findByUnitsInStockNot(int stock);

	List<Products> findByUnitsOnOrderNot(int i);

	List<Products> findByUnitPriceLessThan(double unitPrice);
}
