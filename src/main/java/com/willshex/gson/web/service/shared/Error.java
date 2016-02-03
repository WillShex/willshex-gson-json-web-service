//  
//  Error.java
//  willshex-gson-web-service
//
//  Created by William Shakour on 21 June 2013.
//  Copyrights © 2013 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.willshex.gson.web.service.shared;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.willshex.gson.shared.Jsonable;

public class Error extends Jsonable {
	public Integer code;
	public String message;

	@Override
	public JsonObject toJson() {
		JsonObject object = super.toJson();
		JsonElement jsonCode = code == null ? JsonNull.INSTANCE : new JsonPrimitive(code);
		object.add("code", jsonCode);
		JsonElement jsonMessage = message == null ? JsonNull.INSTANCE : new JsonPrimitive(message);
		object.add("message", jsonMessage);
		return object;
	}

	@Override
	public void fromJson(JsonObject jsonObject) {
		super.fromJson(jsonObject);
		if (jsonObject.has("code")) {
			JsonElement jsonCode = jsonObject.get("code");
			if (jsonCode != null) {
				code = Integer.valueOf(jsonCode.getAsInt());
			}
		}
		if (jsonObject.has("message")) {
			JsonElement jsonMessage = jsonObject.get("message");
			if (jsonMessage != null) {
				message = jsonMessage.getAsString();
			}
		}
	}
}