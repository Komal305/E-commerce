package com.cg.northwind.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SalesDTO {
	
	LocalDate getOrderDate();
    BigDecimal getAmount();
    BigDecimal getTotalRevenue();

}
