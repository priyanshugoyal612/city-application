package com.sg.citylistapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author priyanshu.goyal 
 * This Entity represents the City table
 */
@Entity
@Table(name = "city")
public class City {

	public City() {
		super();
	}

	public City(long id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	@Id
	public long id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "url", length = 1000)
	private String url;

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
