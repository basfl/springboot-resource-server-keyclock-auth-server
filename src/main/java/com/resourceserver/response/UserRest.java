package com.resourceserver.response;

import lombok.Data;

@Data
public class UserRest {
	private String userFirstName;
	private String userLastName;
	private String userId;
	public UserRest(String userFirstName, String userLastName, String userId) {
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userId = userId;
	}

}
