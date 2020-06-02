package org.cuit.BookSystem.Category;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	private String id;
	private String name;
	private String description;

}
