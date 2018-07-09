package com.simple.resume.common;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int state;
	private String message = "";
	private T data;

	public JsonResult() {
		state = StatusEnum.SUCCESS.getCode();
	}

	public JsonResult(int state, String message, T data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public JsonResult(String error) {
		this(StatusEnum.ERROR.getCode(), error, null);
	}

	public JsonResult(T data) {
		this(StatusEnum.SUCCESS.getCode(), "", data);
	}

	public JsonResult(int state) {
		this(state, "", null);
	}

	public JsonResult(Throwable e) {
		this(StatusEnum.ERROR.getCode(), e.getMessage(), null);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
}
