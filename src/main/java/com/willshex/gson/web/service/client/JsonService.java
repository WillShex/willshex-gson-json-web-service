//  
//  JsonService.java
//  willshex-gson-web-service
//
//  Created by William Shakour on 21 June 2013.
//  Copyrights © 2013 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.client;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.willshex.gson.web.service.client.JsonServiceCallEventHandler.JsonServiceCallFailure;
import com.willshex.gson.web.service.client.JsonServiceCallEventHandler.JsonServiceCallStart;
import com.willshex.gson.web.service.client.JsonServiceCallEventHandler.JsonServiceCallSuccess;
import com.willshex.gson.web.service.shared.Request;
import com.willshex.gson.web.service.shared.Response;
import com.willshex.utility.JsonUtils;

public abstract class JsonService {

	protected String url;
	protected HasHandlers bus;

	public String getUrl () {
		return url;
	}

	public void setUrl (String value) {
		url = value;
	}

	public void setBus (HasHandlers value) {
		bus = value;
	}

	protected void parseResponse (com.google.gwt.http.client.Response response,
			Response outputParameter) throws HttpException {
		String responseText = null;
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300
				&& (responseText = response.getText()) != null
				&& !"".equals(responseText)) {
			outputParameter.fromJson(responseText);
		} else if (response.getStatusCode() >= 400)
			throw new HttpException(response.getStatusCode(),
					response.getStatusText());
	}

	protected com.google.gwt.http.client.Request sendRequest (String action,
			Request input, RequestCallback callback) throws RequestException {
		String requestData = "action=" + action + "&request=";

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
				URL.encode(url));

		requestData += URL.encodeQueryString(
				JsonUtils.cleanJson(input.toJson().toString()));

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		return builder.sendRequest(requestData, callback);
	}

	protected void onCallStart (JsonService origin, String callName,
			Request input, com.google.gwt.http.client.Request requestHandle) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallStart(origin, callName, input,
					requestHandle));
		}
	}

	protected void onCallSuccess (JsonService origin, String callName,
			Request input, Response output) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallSuccess(origin, callName, input,
					output));
		}
	}

	protected void onCallFailure (JsonService origin, String callName,
			Request input, Throwable caught) {
		if (bus != null) {
			bus.fireEvent(new JsonServiceCallFailure(origin, callName, input,
					caught));
		}
	}

}
