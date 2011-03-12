package com.google.code.japarser.model;

import java.util.List;

public class ClassInfo {
	private int line;
	
	private boolean isInterface = false;
	
	private String qualifiedTypeName;
	
	private String name;
	
	private List<FieldInfo> fields;
	
	private List<MethodInfo> methods;
	
	private List<ClassInfo> extendsClasses;
	
	private List<ClassInfo> implementsInterfaces;
	
	public ClassInfo() {
	}
	
	public ClassInfo(String name) {
		this(name, null, null);
	}
	
	public ClassInfo(String name, List<FieldInfo> fields, List<MethodInfo> methods) {
		this.name = name;
		this.fields = fields;
		this.methods = methods;
	}
	
	public void setLine(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}
	
	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setQualifiedTypeName(String qualifiedTypeName) {
		this.qualifiedTypeName = qualifiedTypeName;
	}
	
	public String getQualifiedTypeName() {
		return this.qualifiedTypeName;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}

	public List<MethodInfo> getMethods() {
		return methods;
	}

	public void setExtendsClasses(List<ClassInfo> extendsClasses) {
		this.extendsClasses = extendsClasses;
	}

	public List<ClassInfo> getExtendsClasses() {
		return extendsClasses;
	}

	public void setImplementsInterfaces(List<ClassInfo> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
	}

	public List<ClassInfo> getImplementsInterfaces() {
		return implementsInterfaces;
	}
}
