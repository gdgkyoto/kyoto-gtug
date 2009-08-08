package menu.page;

import java.util.ArrayList;
import java.util.Iterator;

import menu.dao.CookPadRecipeDao;
import menu.dao.DataAccessException;
import menu.dto.Recipe;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CookPadRecipeDataProvider implements IDataProvider<Recipe> {
	
	private static final long serialVersionUID = 1L;
	transient CookPadRecipeDao dao;
	private String condition;

	
	public String getCondition() {
		return condition;
	}

	
	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public Iterator<Recipe> iterator(int first, int count) {
		if (getCondition() == null) {
			return new ArrayList<Recipe>().iterator();
		}
		
		resolveDao();
		
		int page = first / count;
		try {
			return dao.findList(getCondition(), page+1).iterator();
		} catch (DataAccessException e) {
			throw new WicketRuntimeException(e);
		}
	}

	@Override
	public IModel<Recipe> model(Recipe object) {
		return new Model<Recipe>(object);
	}

	@Override
	public int size() {
		if (getCondition() == null) {
			return 0;
		}
		
		resolveDao();
		try {
			return dao.count(getCondition());
		} catch (DataAccessException e) {
			throw new WicketRuntimeException(e);
		}
	}

	@Override
	public void detach() {
	}
	
	void resolveDao() {
		if (dao == null) {
			dao = new CookPadRecipeDao();
		}
	}

}
