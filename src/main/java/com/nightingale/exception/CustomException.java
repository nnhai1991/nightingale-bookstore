package com.nightingale.exception;

/**
 * @author hai
 *
 */

@SuppressWarnings("serial")
public class CustomException extends RuntimeException{
	

	private String url;
	private String functionName;
	private String controller;
	private StackTraceElement[] stackTrace;
	private String viewName;
	private String remark;
	
	public CustomException(String url,String functionName,String controller,StackTraceElement[] stackTrace,String viewName,String remark){
		this.url = url;
		this.functionName = functionName;
		this.controller = controller;
		this.stackTrace = stackTrace;
		this.viewName = viewName;
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
