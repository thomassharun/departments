package com.tus.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorDto {
	private String apiPath;
	private String errorMessage;
	private HttpStatus errorCode;
	private LocalDateTime errorTime;

	public ErrorDto(String apiPath, String errorMessage, HttpStatus errorCode, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorTime = errorTime;
	}

	public ErrorDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	@Override
	public String toString() {
		return "ErrorDto [apiPath=" + apiPath + ", errorMessage=" + errorMessage + ", errorCode=" + errorCode
				+ ", errorTime=" + errorTime + "]";
	}

}
