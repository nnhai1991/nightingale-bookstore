package com.nightingale.app.exception;

import java.util.Map;

/**
 * @author hai
 *
 */
public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = -5853685929057541987L;

	private StackTraceElement[] stackTrace;
	private Map<String, Object> params;
	private String methodName;
	private String displayMessage;


	public ObjectNotFoundException(StackTraceElement[] stackTrace, String methodName, String displayMessage, Map<String, Object> params) {

		this.stackTrace = stackTrace;
		this.methodName = methodName;
		this.displayMessage = displayMessage;
		this.params = params;
	}

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}


}
