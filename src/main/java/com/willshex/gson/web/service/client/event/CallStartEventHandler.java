//
//  CallStartEventHandler.java
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
public interface CallStartEventHandler extends EventHandler {

	public static final GwtEvent.Type<CallStartEventHandler> TYPE = new GwtEvent.Type<CallStartEventHandler>();

	void jsonServiceCallStart (JsonService origin, String callName,
			Request input, com.google.gwt.http.client.Request handle);

	public class JsonServiceCallStart extends GwtEvent<CallStartEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private com.google.gwt.http.client.Request handle;

		public JsonServiceCallStart (final JsonService origin,
				final String callName, final Request input,
				final com.google.gwt.http.client.Request handle) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.handle = handle;
		}

		@Override
		public GwtEvent.Type<CallStartEventHandler> getAssociatedType () {
			return TYPE;
		}

		@Override
		protected void dispatch (CallStartEventHandler handler) {
			handler.jsonServiceCallStart(origin, callName, input, handle);
		}
	}

}