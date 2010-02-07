package jp.fukui.mapper.MapperData;


import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MapperData{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private Long type;
	@Persistent
	private String keyword;
	@Persistent
	private String propaty;
	@Persistent
	private String parameter;


	public void SetKeyword(String a_keyword){
		this.keyword = a_keyword;
	}

	public void SetType(Long a_type){
		this.type= a_type;
	}

	public void SetPropaty(String a_propaty){
		this.propaty=a_propaty;
	}

	public void SetParameter(String a_parameter){
		this.parameter= a_parameter;
	}

	public String GetParameter(){return this.parameter;}
	public String GetPropaty(){return this.propaty;}
	public Long GetType(){return this.type;}
	public String GetKeyword(){return this.keyword;}





}
