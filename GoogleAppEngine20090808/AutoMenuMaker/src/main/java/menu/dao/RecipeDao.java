package menu.dao;

import java.util.List;

import javax.jdo.PersistenceManager;

import menu.dto.Recipe;

public class RecipeDao {

	public void insert(Recipe recipe) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(recipe);
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

	public List<Recipe> findList() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + Recipe.class.getName();
			return (List<Recipe>) pm.newQuery(query).execute();
		} finally {
			pm.close();
		}
	}

}
