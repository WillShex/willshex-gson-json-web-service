package com.willshex.gson.json.service.client;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.spacehopperstudios.utility.JsonUtils;
import com.willshex.gson.json.service.shared.Error;
import com.willshex.gson.json.service.shared.Request;
import com.willshex.gson.json.service.shared.Response;
import com.willshex.gson.json.service.shared.StatusType;

public abstract class JsonService {

	protected String Url;

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	protected void parseResponse(String responseText, Response outputParameter) {
		if (responseText != null && !"".equals(responseText)) {
			outputParameter.fromJson(responseText);
		} else {
			outputParameter.status = StatusType.StatusTypeFailure;
			outputParameter.error = new Error();
			outputParameter.error.code = -1;
			outputParameter.error.message = "Response was empty";
		}
	}

	protected void sendRequest(String action, Request input, RequestCallback callback) throws RequestException {
		String requestData = "action=" + action + "&request=";

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(Url));

		requestData += URL.encodeQueryString(JsonUtils.cleanJson(input.toJson().toString()));

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		builder.sendRequest(requestData, callback);
	}

}
