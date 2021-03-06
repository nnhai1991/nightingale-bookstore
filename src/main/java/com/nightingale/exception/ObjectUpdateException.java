package com.nightingale.exception;

public class ObjectUpdateException extends RuntimeException {

	private static final long serialVersionUID = -5715488934839712846L;
	
	private StackTraceElement[] stackTrace;
	private Object[] params;
	private String methodName;
	private String displayMessage;
	private String remarks;

	public ObjectUpdateException(StackTraceElement[] stackTrace, String methodName, String displayMessage,
			String remarks, Object... params) {

		this.stackTrace = stackTrace;
		this.params = params;
		this.methodName = methodName;
		this.displayMessage = displayMessage;
		this.remarks = remarks;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public Object[] getParams() {
		return params;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getRemarks() {
		return remarks;
	}

}
