package org.javaleo.cointrade.braziliex.stubs;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BrzCurrencyStub implements Serializable {

	private static final long serialVersionUID = 2425568227622696686L;

	@SerializedName("name")
	private String name;

	@SerializedName("decimal")
	private Integer decimal;

	@SerializedName("active")
	private Integer active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDecimal() {
		return decimal;
	}

	public void setDecimal(Integer decimal) {
		this.decimal = decimal;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

}
