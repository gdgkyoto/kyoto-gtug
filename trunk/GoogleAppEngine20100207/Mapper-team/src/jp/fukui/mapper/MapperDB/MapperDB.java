package jp.fukui.mapper.MapperDB;

import jp.fukui.mapper.MapperData.*;
import PMF.*;

import java.util.ArrayList;
import javax.jdo.PersistenceManager;
import java.util.List;



public class MapperDB {

	String keyword="";
	String propaty="";

	//PersistenceManager pm = PMF.getInstance().getPersistenceManager();

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
		//
		//String query = "select from " + MapperData.class.getName();
		String[] query= new String[2];
		query[0]= "select from "+ MapperData.class.getName()+ " where type==1";
		//query[1]="where type = 1";
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		@SuppressWarnings("unchecked")
		List<MapperData> mapperdata = (List<MapperData>)pm.newQuery(query[0]).execute();



		if(mapperdata.isEmpty()){
			return null;
		}

		try{
			for(MapperData md: mapperdata){
				keywordlist.add(md.GetKeyword());
				pm.deletePersistent(md);
			}

		}finally{
			pm.close();
		}
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


	public ArrayList<MapperData> Get(String keyword){



		//
		//String query = "select from " + MapperData.class.getName();
		String[] query= new String[2];
		query[0]= "select from "+ MapperData.class.getName()+ " where keyword=="+keyword;
		//query[1]="where type = 1";
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		@SuppressWarnings("unchecked")
		List<MapperData> googledata = (List<MapperData>)pm.newQuery(query[0]).execute();

		ArrayList<MapperData> mapperdata = new ArrayList<MapperData>();

		if(googledata.isEmpty()){
			return null;
		}

		try{

			for(MapperData md: googledata){
				mapperdata.add(md);
				pm.deletePersistent(md);
			}

		}finally{
			pm.close();
		}





		return mapperdata;

	}


	public ArrayList<String> GetPropatyList()
	{

		ArrayList<String> propatylist = new ArrayList<String>();
		//
		//String query = "select from " + MapperData.class.getName();

		String query= "select from "+ MapperData.class.getName()+ " where type==2 && keyword =="+this.keyword;

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		@SuppressWarnings("unchecked")
		List<MapperData> mapperdata = (List<MapperData>)pm.newQuery(query).execute();



		if(mapperdata.isEmpty()){
			return null;
		}

		try{
			for(MapperData md: mapperdata){
				propatylist.add(md.GetPropaty());
				pm.deletePersistent(md);
			}

		}finally{
			pm.close();
		}
		return propatylist;

	}

	public ArrayList<String> GetParameterList()
	{

		ArrayList<String> parameterlist = new ArrayList<String>();

		String query= "select from "+ MapperData.class.getName()+ " where type==3 && keyword =="+this.keyword +" && propaty=="+this.propaty;

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();

		@SuppressWarnings("unchecked")
		List<MapperData> mapperdata = (List<MapperData>)pm.newQuery(query).execute();



		if(mapperdata.isEmpty()){
			return null;
		}

		try{
			for(MapperData md: mapperdata){
				parameterlist.add(md.GetParameter());
				pm.deletePersistent(md);
			}

		}finally{
			pm.close();
		}
		return parameterlist;

	}



}
