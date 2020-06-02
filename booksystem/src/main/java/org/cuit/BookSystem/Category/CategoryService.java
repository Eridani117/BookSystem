package org.cuit.BookSystem.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;

	public boolean addCategory(Category category) {
		return categoryDao.addCategory(category);
	}
	
	public Category findCategory(String id) {
        return categoryDao.findCategoryById(id);
    }

	public List<Category> getAllCategory() {
        return categoryDao.getAllCategorys();
    }

	public boolean deleteCategoryById(String id) {
		return categoryDao.deleteCategoryById(id);
	}

}
