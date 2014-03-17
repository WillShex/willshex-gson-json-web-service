package com.willshex.gson.json.service.client;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.spacehopperstudios.utility.JsonUtils;
import com.willshex.gson.json.service.client.JsonServiceCallEventHandler.JsonServiceCallFailure;
import com.willshex.gson.json.service.client.JsonServiceCallEventHandler.JsonServiceCallStart;
import com.willshex.gson.json.service.client.JsonServiceCallEventHandler.JsonServiceCallSuccess;
import com.willshex.gson.json.service.shared.Error;
import com.willshex.gson.json.service.shared.Request;
import com.willshex.gson.json.service.shared.Response;
import com.willshex.gson.json.service.shared.StatusType;

public abstract class JsonService {

	protected String url;
	protected HasHandlers bus;

	public String getUrl() {
		return url;
	}

	public void setUrl(String value) {
		url = value;
	}

	public void setBus(HasHandlers value) {
		bus = value;
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

	protected com.google.gwt.http.client.Request sendRequest(String action, Request input, RequestCallback callback) throws RequestException {
		String requestData = "action=" + action + "&request=";

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));

		requestData += URL.encodeQueryString(JsonUtils.cleanJson(input.toJson().toString()));

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		return builder.sendRequest(requestData, callback);
	}

	protected void onCallStart(JsonService origin, String callName, Request input, com.google.gwt.http.client.Request requestHandle) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallStart(origin, callName, input, requestHandle));
		}
	}

	protected void onCallSuccess(JsonService origin, String callName, Request input, Response output) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallSuccess(origin, callName, input, output));
		}
	}

	protected void onCallFailure(JsonService origin, String callName, Request input, Throwable caught) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallFailure(origin, callName, input, caught));
		}
	}

}
