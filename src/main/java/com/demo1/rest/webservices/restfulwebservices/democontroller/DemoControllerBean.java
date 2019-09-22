package com.demo1.rest.webservices.restfulwebservices.democontroller;

public class DemoControllerBean {

	private String message;

	public DemoControllerBean(String message) {
		this.setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DemoControllerBean [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
}
