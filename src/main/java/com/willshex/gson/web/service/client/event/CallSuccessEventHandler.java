//
//  CallSuccessEventHandler.java
//  willshex-gson-web-service
//
//  Created by William Shakour (billy1380) on 21 Jul 2017.
//  Copyright © 2017 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.willshex.gson.web.service.client.JsonService;
import com.willshex.gson.web.service.shared.Request;
import com.willshex.gson.web.service.shared.Response;

/**
 * @author William Shakour (billy1380)
 *
 */
@FunctionalInterface
public interface CallSuccessEventHandler extends EventHandler {

	public static final GwtEvent.Type<CallSuccessEventHandler> TYPE = new GwtEvent.Type<CallSuccessEventHandler>();

	void jsonServiceCallSuccess (JsonService origin, String callName,
			Request input, Response output);

	public class JsonServiceCallSuccess
			extends GwtEvent<CallSuccessEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private Response output;

		public JsonServiceCallSuccess (final JsonService origin,
				final String callName, final Request input,
				final Response output) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.output = output;
		}

		@Override
		public GwtEvent.Type<CallSuccessEventHandler> getAssociatedType () {
			return TYPE;
		}

		@Override
		protected void dispatch (CallSuccessEventHandler handler) {
			handler.jsonServiceCallSuccess(origin, callName, input, output);
		}
	}

}