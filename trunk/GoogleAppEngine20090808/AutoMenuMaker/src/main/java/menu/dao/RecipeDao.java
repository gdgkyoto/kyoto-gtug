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

	public List<Recipe> findList(String condition) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Recipe.class);
			//String query = "select from " + Recipe.class.getName();
			return (List<Recipe>) query.execute();
		} finally {
			pm.close();
		}
	}
	
	public int count(String condition) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Recipe.class);
			//String query = "select from " + Recipe.class.getName();
			int size = ((List<Recipe>) query.execute()).size();
			System.out.println("size=" + size);
			return size;
		} finally {
			pm.close();
		}
	}

}
