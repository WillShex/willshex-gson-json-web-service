//  
//  StatusType.java
//  willshex-gson-web-service
//
//  Created by William Shakour on 21 June 2013.
//  Copyrights © 2013 WillShex Limited. All rights reserved.
//
package com.willshex.gson.json.service.shared;

import java.util.HashMap;
import java.util.Map;

public enum StatusType {
	StatusTypeSuccess("success"), StatusTypeFailure("failure"), ;
	private String value;
	private static Map<String, StatusType> valueLookup = null;

	public String toString() {
		return value;
	}

	private StatusType(String value) {
		this.value = value;
	}

	public static StatusType fromString(String value) {
		if (valueLookup == null) {
			valueLookup = new HashMap<String, StatusType>();
			for (StatusType currentStatusType : StatusType.values()) {
				valueLookup.put(currentStatusType.value, currentStatusType);
			}
		}
		return valueLookup.get(value);
	}
}