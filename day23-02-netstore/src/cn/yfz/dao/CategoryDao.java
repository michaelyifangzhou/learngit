package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Category;

public interface CategoryDao {

	void save(Category c);

	List<Category> findAll();

	Category findById(String categoryId);

}
