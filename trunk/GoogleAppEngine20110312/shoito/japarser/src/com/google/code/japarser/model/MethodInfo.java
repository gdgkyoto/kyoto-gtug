package com.google.code.japarser.model;

public class MethodInfo {
	private int line;
	private String name;
	private String modifiersName;
	private String returnName;
	private String paramName;
	private boolean isConstructor = false;

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

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public String getReturnName() {
		return returnName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return paramName;
	}

    public void setConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
    }

    public boolean isConstructor() {
        return isConstructor;
    }
}