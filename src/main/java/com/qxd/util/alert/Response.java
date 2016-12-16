package com.qxd.util.alert;

public class Response {
	private ResponseStatus status;
	private Object object;
	private AlertSet alertSet;
	
	public Response() {
		this(ResponseStatus.DONE, null);
	}
	
	public Response(ResponseStatus status, Object object) {
		this.status = status;
		this.object = object;
		this.alertSet = new AlertSet();
	}
	
	public Response withDoneStatus() {
		this.status = ResponseStatus.DONE;
		return this;
	}
	
	public Response withFailStatus() {
		this.status = ResponseStatus.FAIL;
		return this;
	}
	
	public Response withInfoMessage(String message) {
		this.alertSet.withShortInfo(message).get(0);
		return this;
	}
	
	public Response withSuccessMessage(String message) {
		this.alertSet.withShortSuccess(message).get(0);
		return this;
	}
	
	public Response withWarningMessage(String message) {
		this.alertSet.withShortWarning(message).get(0);
		return this;
	}
	
	public Response withErrorMessage(String message) {
		this.alertSet.withShortError(message).get(0);
		return this;
	}
	
	public Response withObject(Object object) {
		this.object = object;
		return this;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public AlertSet getAlertSet() {
		return alertSet;
	}
	
	public void setAlertSet(AlertSet alertSet) {
		this.alertSet = alertSet;
	}
	
}