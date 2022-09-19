package com.example.demo.response.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {
	
	@JsonProperty("data")
	private List<ResponseObject> data;

	public List<ResponseObject> getData() {
		return data;
	}

	public void setData(List<ResponseObject> data) {
		this.data = data;
	}

	
	
	

}
