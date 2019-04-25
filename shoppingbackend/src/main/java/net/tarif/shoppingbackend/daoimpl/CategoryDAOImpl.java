package net.tarif.shoppingbackend.daoimpl;

import java.util.List;

import net.tarif.shoppingbackend.dao.CategoryDAO;
import net.tarif.shoppingbackend.dto.Category;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * private static List<Category> categories = new ArrayList<Category>();
	 * 
	 * static { Category category = new Category(); category.setId(1);
	 * category.setName("television");
	 * category.setDescription("this is television"); categories.add(category);
	 * 
	 * category = new Category(); category.setId(2); category.setName("mobile");
	 * category.setDescription("this is mobile"); categories.add(category);
	 * 
	 * category = new Category(); category.setId(3); category.setName("laptop");
	 * category.setDescription("this is laptop"); categories.add(category); }
	 */
	@Override
	
	public List<Category> list() {

		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
				
		query.setParameter("active", true);
						
		return query.getResultList();
	}

	/*
	 * Getting single category based on id
	 */
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class,
				Integer.valueOf(id));

	}

	@Override
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * Updating a single category
	 */
	@Override
	public boolean update(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {

		category.setActive(false);

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
