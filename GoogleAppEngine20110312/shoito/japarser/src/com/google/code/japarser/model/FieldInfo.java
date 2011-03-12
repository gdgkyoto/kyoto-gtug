package com.google.code.japarser.model;

public class FieldInfo {
	private int line;
	private String name;
	private String modifiersName;
	private String simpleTypeName;
	private String qualifiedTypeName;

	public void setLine(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setModifiersName(String modifiersName) {
		this.modifiersName = modifiersName;
	}

	public String getModifiersName() {
		return modifiersName;
	}

	public void setSimpleTypeName(String simpleTypeName) {
		this.simpleTypeName = simpleTypeName;
	}

	public String getSimpleTypeName() {
		return simpleTypeName;
	}

	public void setQualifiedTypeName(String qualifiedTypeName) {
		this.qualifiedTypeName = qualifiedTypeName;
	}

	public String getQualifiedTypeName() {
		return qualifiedTypeName;
	}
}