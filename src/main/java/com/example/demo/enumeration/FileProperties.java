package com.example.demo.enumeration;

public enum FileProperties {
	
	CONTENT_TYPE("application/octet-stream"),
	HEADER_VALUE("attachment; filename=\"");
	
	private String value;
	
	private FileProperties(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
