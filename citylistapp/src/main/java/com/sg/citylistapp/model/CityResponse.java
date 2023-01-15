package com.sg.citylistapp.model;

/**
 * Response DTO for Rest API
 * @author priyanshu.goyal
 *
 */
public class CityResponse {

	private long id;
	private String name;
	private String url;

	public CityResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityResponse(long id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
