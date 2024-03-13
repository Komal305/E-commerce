package com.cg.northwind.entity;
/*
 * @author 
 * M V Shashank Yadav
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "categories")

/*
 * Entity class categories table
 */
public class Categories {
	@Id
	@Column(name = "category_id")
	private int categoryID;
	@Column(name = "category_name", nullable = false)
	private String categoryName;
	@Column(name = "Description")
	private String description;
	@Column(name = "Picture")
	private String picture;

}
