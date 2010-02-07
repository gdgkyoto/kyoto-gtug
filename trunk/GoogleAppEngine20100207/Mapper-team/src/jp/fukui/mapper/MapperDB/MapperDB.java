package jp.fukui.mapper.MapperDB;

import jp.fukui.mapper.MapperData.*;
import PMF.*;

import java.util.ArrayList;
import javax.jdo.PersistenceManager;

public class MapperDB {

	String keyword="";
	String propaty="";


	public boolean NewKeyword(String keyword)
	{

		MapperData data = new MapperData();
		data.SetKeyword(keyword);
		data.SetType(1l);
		data.SetPropaty("");
		data.SetParameter("");

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		try{
			pm.makePersistent(data);
		}finally{
			pm.close();
		}


		return true;

	}


	public ArrayList<String> GetKeywordList()
	{

		ArrayList<String> keywordlist = new ArrayList<String>();
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		String query = "select from MapperData where type==1";
		keywordlist = (ArrayList<String>)pm.newQuery(query).execute();





		return keywordlist;

	}

	public boolean SetKeyword(String keyword){

		this.keyword = keyword;
		return true;
	}

	public boolean NewPropaty(String propaty)
	{
		if(this.keyword==""){
			return false;
		}

		MapperData data = new MapperData();
		data.SetKeyword(this.keyword);
		data.SetType(2l);
		data.SetPropaty(propaty);
		data.SetParameter("");

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		try{
			pm.makePersistent(data);
		}finally{
			pm.close();
		}


		return true;

	}

	public boolean SetPropaty(String propaty)
	{
		this.propaty = propaty;
		return true;
	}

	public boolean NewParameter(String parameter)
	{
		if(this.keyword==""||this.propaty==""){
			return false;
		}

		MapperData data = new MapperData();
		data.SetKeyword(this.keyword);
		data.SetType(3l);
		data.SetPropaty(this.propaty);
		data.SetParameter(parameter);

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		try{
			pm.makePersistent(data);
		}finally{
			pm.close();
		}


		return true;

	}








}
