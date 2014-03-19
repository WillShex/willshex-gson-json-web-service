//
//  HttpException.java
//  spchopr.gson.json.service
//
//  Created by William Shakour (billy1380) on 19 Mar 2014.
//  Copyright © 2014 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.willshex.gson.json.service.client;

/**
 * @author billy1380
 * 
 */
public class HttpException extends Exception {

	private static final long serialVersionUID = -4377085913478787937L;

	private int code;
	private String content;

	public HttpException(int code, String text, Throwable cause, String content) {
		super(text, cause);

		this.code = code;
		this.content = content;
	}

	public HttpException(int code, String text, Throwable cause) {
		this(code, text, cause, null);
	}

	public HttpException(int code, String text) {
		this(code, text, null);
	}

	public HttpException(int code) {
		this(code, null);
	}

	public String getContent() {
		return content;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		String description = "Http Error " + code;

		if (content != null) {
			description += " content: " + content;
		}

		description += super.toString();

		return description;
	}
}
