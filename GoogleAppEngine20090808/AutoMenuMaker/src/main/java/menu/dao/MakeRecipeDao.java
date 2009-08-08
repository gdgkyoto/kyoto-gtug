package menu.dao;

import javax.jdo.PersistenceManager;

import menu.dto.MakeRecipe;

public class MakeRecipeDao{
	
	public void save(MakeRecipe recipe) {
	    PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
        	System.out.println("test2");
            pm.makePersistent(recipe);
        } finally {
            pm.close();
        }
	}
}