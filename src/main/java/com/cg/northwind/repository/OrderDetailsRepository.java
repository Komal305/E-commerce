package com.cg.northwind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.northwind.DTO.OrderDetailsDTO;
import com.cg.northwind.DTO.SalesDTO;
import com.cg.northwind.entity.OrderDetails;
import com.cg.northwind.entity.OrderDetailsId;
import com.cg.northwind.entity.Orders;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,OrderDetailsId >{

   
    
    List<OrderDetails> findByOrds(Orders odi);

    @Query("SELECT o.ords.orderDate AS orderDate, " +
            "SUM(o.quantity * o.unitPrice) AS amount, " +
            "(SELECT SUM(o2.quantity * o2.unitPrice) FROM OrderDetails o2 WHERE o2.ords.orderDate <= o.ords.orderDate) AS totalRevenue " +
            "FROM OrderDetails o " +
            "GROUP BY o.ords.orderDate " +
            "ORDER BY o.ords.orderDate")
    List<SalesDTO> findSalesAmountAndTotalRevenue();

    @Query("select o.product.productName as productName,o.product.categories.categoryName as categoryName,(o.unitPrice*o.quantity)-o.discount as price from OrderDetails o where o.ords.orderID=:orderid")
	List<OrderDetailsDTO> findByOrderdetails(int orderid);
    
   
}
	


