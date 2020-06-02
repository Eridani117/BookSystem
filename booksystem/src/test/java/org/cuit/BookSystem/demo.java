package org.cuit.BookSystem;

import java.util.List;

import org.cuit.BookSystem.Category.Category;
import org.cuit.BookSystem.Category.CategoryDao;
import org.junit.jupiter.api.Test;

public class demo {

   
    
    @Test
    public void add() {
    	CategoryDao category1 = new CategoryDao();
        Category category = new Category();
        category.setId("2");
        category.setName("数据库系列");
        category.setDescription("这是数据库系列");

        category1.addCategory(category);

    }

    @Test
    public void find() {
    	CategoryDao category1 = new CategoryDao();
        String id = "2";
        Category category = category1.findCategoryById(id);

        System.out.println(category.getName());
    }
    
    @Test
    public void getAll() {
    	CategoryDao category1 = new CategoryDao();
        List<Category> categories = category1.getAllCategorys();

        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

}