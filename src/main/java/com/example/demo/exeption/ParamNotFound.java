package com.example.demo.exeption;

public class ParamNotFound extends RuntimeException {
	public ParamNotFound(String error) {
		super(error);
	}

}
