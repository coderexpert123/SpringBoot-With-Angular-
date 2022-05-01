package com.exam.models;

public class jwtResponse {
String token;

public jwtResponse(String token) {
	super();
	this.token = token;
}

public jwtResponse() {
 
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}
 
}
