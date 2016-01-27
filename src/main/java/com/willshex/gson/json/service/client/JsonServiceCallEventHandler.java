//  
//  JsonServiceCallEventHandler.java
//  willshex-gson-web-service
//
//  Created by William Shakour on March 17, 2014.
//  Copyrights © 2014 WillShex Limited. All rights reserved.
//
package com.willshex.gson.json.service.client;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.willshex.gson.json.service.shared.Request;
import com.willshex.gson.json.service.shared.Response;

public interface JsonServiceCallEventHandler extends EventHandler {
	public static final GwtEvent.Type<JsonServiceCallEventHandler> TYPE = new GwtEvent.Type<JsonServiceCallEventHandler>();

	public void jsonServiceCallStart(final JsonService origin, final String callName, final Request input, final com.google.gwt.http.client.Request handle);

	public void jsonServiceCallSuccess(final JsonService origin, final String callName, final Request input, final Response output);

	public void jsonServiceCallFailure(final JsonService origin, final String callName, final Request input, final Throwable caught);

	public class JsonServiceCallStart extends GwtEvent<JsonServiceCallEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private com.google.gwt.http.client.Request handle;

		public JsonServiceCallStart(final JsonService origin, final String callName, final Request input, final com.google.gwt.http.client.Request handle) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.handle = handle;
		}

		@Override
		public GwtEvent.Type<JsonServiceCallEventHandler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(JsonServiceCallEventHandler handler) {
			handler.jsonServiceCallStart(origin, callName, input, handle);
		}
	}
	
	public class JsonServiceCallSuccess extends GwtEvent<JsonServiceCallEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private Response output;

		public JsonServiceCallSuccess(final JsonService origin, final String callName, final Request input, final Response output) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.output = output;
		}

		@Override
		public GwtEvent.Type<JsonServiceCallEventHandler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(JsonServiceCallEventHandler handler) {
			handler.jsonServiceCallSuccess(origin, callName, input, output);
		}
	}

	public class JsonServiceCallFailure extends GwtEvent<JsonServiceCallEventHandler> {
		private JsonService origin;
		private String callName;
		private Request input;
		private Throwable caught;

		public JsonServiceCallFailure(final JsonService origin, final String callName, final Request input, final Throwable caught) {
			this.origin = origin;
			this.callName = callName;
			this.input = input;
			this.caught = caught;
		}

		@Override
		public GwtEvent.Type<JsonServiceCallEventHandler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(JsonServiceCallEventHandler handler) {
			handler.jsonServiceCallFailure(origin, callName, input, caught);
		}
	}

}