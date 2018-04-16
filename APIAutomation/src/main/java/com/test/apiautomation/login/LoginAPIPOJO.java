package com.test.apiautomation.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginAPIPOJO {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("key")
	@Expose
	private Integer key;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

}