package com.qainfotech.tap.training.resourceio.model;

import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Individual {

	private final String name;
	private final Integer id;
	private final Boolean active;

	/**
	 * Initialized global variables
	 * 
	 * @param individualMap
	 */
	public Individual(Map<String, Object> individualMap) {
		Object[] values = individualMap.values().toArray();
		@SuppressWarnings("unused")
		Object jsonObject = new Object();
		jsonObject = (Object) values[0];
		this.name = (String) individualMap.get("name");
		this.id = (Integer) (individualMap).get("id");
		this.active = (Boolean) individualMap.get("active");
		if (values[0] == null)
			throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * return values of class Individual
	 */
	@Override
	public String toString() {
		return "Individual [name=" + name + ", id=" + id + ", active=" + active + "]";
	}

	/**
	 * get the name of individual
	 * 
	 * @return individual name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the employee of of individual
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get the active status of individual
	 * 
	 * @return
	 */
	public Boolean isActive() {
		return active;
	}
}
