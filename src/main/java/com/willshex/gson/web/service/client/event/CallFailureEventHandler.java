//
//  CallFailureEventHandler.java
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

/**
 * @author William Shakour (billy1380)
 *
 */
@FunctionalInterface
public interface CallFailureEventHandler extends EventHandler {

	public static final GwtEvent.Type<CallFailureEventHandler> TYPE = new GwtEvent.Type<CallFailureEventHandler>();

	void jsonServiceCallFailure (JsonService origin, String callName,
			Request input, Throwable caught);

	public class JsonServiceCallFailure
			extends GwtEvent<CallFailureEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private Throwable caught;

		public JsonServiceCallFailure (final JsonService origin,
				final String callName, final Request input,
				final Throwable caught) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.caught = caught;
		}

		@Override
		public GwtEvent.Type<CallFailureEventHandler> getAssociatedType () {
			return TYPE;
		}

		@Override
		protected void dispatch (CallFailureEventHandler handler) {
			handler.jsonServiceCallFailure(origin, callName, input, caught);
		}
	}

}