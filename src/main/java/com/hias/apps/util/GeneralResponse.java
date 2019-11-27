package com.hias.apps.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralResponse {

	@JsonProperty("data")
	private Object data;
	
	@JsonProperty("status")
	private boolean status;
	
	@JsonProperty("error")
	private Object errorResponse;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(Object errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public GeneralResponse(Object data, boolean status, Object errorResponse) {
		this.data = data;
		this.status = status;
		this.errorResponse = errorResponse;
	}
}
