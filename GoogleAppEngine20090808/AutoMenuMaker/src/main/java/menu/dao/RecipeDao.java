package menu.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import menu.dto.Recipe;

public class RecipeDao {

	public void insert(Recipe recipe) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//pm.currentTransaction().begin();
			pm.makePersistent(recipe);
			//pm.currentTransaction().commit();
		} finally {
			pm.close();
		}
	}

	public void insertList(List<Recipe> recipeList) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistentAll(recipeList);
		} finally {
			pm.close();
		}
	}

	public List<Recipe> findListByTitle(String condition) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Recipe.class);
			if (condition != null) {
				query.setFilter("title == condition");
				query.declareParameters("String condition");
			}
			List<Recipe> result = (List<Recipe>) query.execute(condition);
			result.size();
			return result;
		} finally {
			pm.close();
		}
	}
	
	public List<Recipe> findListByCategory(String condition) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Recipe.class);
			if (condition != null) {
				query.setFilter("category == condition");
				query.declareParameters("String condition");
			}
			List<Recipe> result = (List<Recipe>) query.execute(condition);
			result.size();
			return result;
		} finally {
			pm.close();
		}
	}
	
	public int count(String condition) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Recipe.class);
			if (condition != null) {
				query.setFilter("title == condition");
				query.declareParameters("String condition");
			}
			int size = ((List<Recipe>) query.execute(condition)).size();
			return size;
		} finally {
			pm.close();
		}
	}

}
