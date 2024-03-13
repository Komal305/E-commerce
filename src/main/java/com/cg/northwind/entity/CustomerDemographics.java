package com.cg.northwind.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_demographics")
public class CustomerDemographics {
    @Id
    @Column(name = "customer_typeid")
    private String customerTypeID;

    @Column(name = "customer_desc")
    private String customerDesc;
    
    @ManyToMany(mappedBy = "customerDemographics")
    private List<Customers> customers;
    }

