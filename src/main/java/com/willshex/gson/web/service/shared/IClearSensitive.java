//
//  IClearSensitive.java
//  willshex-gson-web-service
//
//  Created by William Shakour (billy1380) on 20 Jul 2016.
//  Copyright © 2016 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.shared;

/**
 * @author William Shakour (billy1380)
 *
 */
public interface IClearSensitive<T extends Response> {

	void clearSensitiveFields (T output);

}
