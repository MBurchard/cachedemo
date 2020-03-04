package de.mbur.cachedemo.domain;

public class User {
	private String customerID;
	private String id;
	private String username;

	public String getCustomerID() {
		return customerID;
	}

	public User setCustomerID(final String customerID) {
		this.customerID = customerID;
		return this;
	}

	public String getId() {
		return id;
	}

	public User setId(final String id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(final String username) {
		this.username = username;
		return this;
	}
}
