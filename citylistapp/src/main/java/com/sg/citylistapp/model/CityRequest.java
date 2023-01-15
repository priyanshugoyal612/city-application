package com.sg.citylistapp.model;

/**
 * Request DTO for updating city details
 * @author priyanshu.goyal
 *
 */
public class CityRequest {

	private String name;
	private String url;

	public CityRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityRequest(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
