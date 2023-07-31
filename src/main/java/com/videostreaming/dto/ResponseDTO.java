package com.videostreaming.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 5060273595692339283L;

	private Boolean isError;
	private int code;
	private String message;
	private T payload;

	public ResponseDTO(Boolean isError, int code, String message, T payload) {
		this.isError = isError;
		this.code = code;
		this.message = message;
		this.payload = payload;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

}
